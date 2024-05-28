package webclient.service;

import io.restassured.RestAssured;
import io.restassured.config.ParamConfig;
import io.restassured.config.ParamConfig.UpdateStrategy;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.relevantcodes.extentreports.LogStatus;

import webuita.log.Logger;

public class B1A {

	public static void initializeCompany(String b1ahServer,String schemaName, Logger log){
		try{
			RestAssured.baseURI = "https://" + b1ahServer + ":40000";
			RestAssured.useRelaxedHTTPSValidation();
			CookieFilter cookieFilter = new CookieFilter();
			
			//request SP
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.given().
							log().all().
							filter(cookieFilter).
							header("Content-Type", "application/x-www-form-urlencoded").get("/Enablement/");
			
			
			String responseBody = response.getBody().asString();
			System.out.println("responseBody -> " + responseBody);
			Matcher matcher = Pattern.compile("name=\"SAMLRequest\" value=\"(.*?)\"").matcher(response.getBody().asString());
			matcher.find();                  
		    //String samlRequest = new String(Base64.getDecoder().decode(matcher.group(1)));
			String samlRequest = matcher.group(1);
			Matcher matcherRelay = Pattern.compile("name=\"RelayState\" value=\"(.*?)\"").matcher(responseBody);
			matcherRelay.find();
			String relayState =  matcherRelay.group(1);
			
		
			//login IDP 
			ObjectMapper mapper = new ObjectMapper();
			JsonNode requestBody = mapper.createObjectNode();
			((ObjectNode) requestBody).put("Account","B1SiteUser");
			((ObjectNode) requestBody).put("Password","1234");
			
			Response resLoginIDP = httpRequest.given().
								header("Content-Type", "application/json;odata=verbose").
								header("Accept","application/json").
								filter(cookieFilter).
								body(requestBody).
								when().post("/sld/sld.svc/LogonByNamedUser");
			System.out.println("resLoginIDP -> " + resLoginIDP.getBody().asString());
			//IDP SSO
			Response resLoginSSO = httpRequest.given().
					header("Content-Type", "application/x-www-form-urlencoded").
					log().all().
					filter(cookieFilter).
					queryParam("SAMLRequest", samlRequest).
					queryParam("RelayState", relayState).
					when().post("/sld/saml2/idp/sso");

			System.out.println("resLoginSSO -> " + resLoginSSO.getBody().asString());
			Matcher matcher_req = Pattern.compile("name=\"SAMLResponse\" value=\"(.*?)\"").matcher(resLoginSSO.getBody().asString());
			matcher_req.find(); 
			String samlResponse = matcher_req.group(1);

			//SP SSO Callback
			RequestSpecification httpRequest_02 = RestAssured.given();
			Response loginSPsso = httpRequest_02.given().
					header("Content-Type", "application/x-www-form-urlencoded").
					log().all().filter(cookieFilter).
					queryParam("SAMLResponse", samlResponse).
					queryParam("RelayState",relayState).
					when().post("/Enablement/saml2/sp/acs");
			int statusCode = loginSPsso.getStatusCode();
			System.out.println("StatusCode -> " + statusCode);
			
			//Get company Info
			RequestSpecification httpRequest_03 = RestAssured.given();
			Response getCompanyInfo = httpRequest_03.given().
					log().all().filter(cookieFilter).
					when().get("/Enablement/companyInfo");
			System.out.println("getCompanyInfo -> " + getCompanyInfo.getBody().asString());
			log.add(LogStatus.INFO, "Start to initialize \"" + schemaName + "\" for B1A.");
			//initialize schema
			RequestSpecification httpRequest_04 = RestAssured.given();
			Response initialCompany = httpRequest_04.given().
					header("Content-Type", "application/json").
					log().all().filter(cookieFilter).
					when().post("/Enablement/companyInfo?action=init&companyName=" + schemaName + "&compatibleMode=Off");

			System.out.println("initialCompany -> " + initialCompany.getBody().asString());
			//wait initialization complete
			RequestSpecification httpRequest_05 = RestAssured.given();
			int loopCount = 20;
			boolean bSuccess = false;
			do{
				
				Response getInitialState = httpRequest_05.given().
						config(RestAssured.config().paramConfig(ParamConfig.paramConfig().queryParamsUpdateStrategy(UpdateStrategy.REPLACE))).
						header("Content-Type", "application/json").
						log().all().filter(cookieFilter).
						when().get("/Enablement/companyInfo?action=status&companyName=" + schemaName);
				System.out.println("getInitialState -> " + getInitialState.getBody().asString());
				JsonPath jsonPath = getInitialState.jsonPath();
				String status = jsonPath.get("status");
				if("INITIALIZED".equals(status)){
					bSuccess = true;
					log.add(LogStatus.PASS, "B1A initialize schema \"" + schemaName + "\" successfully.");
					break;
				}
				
				if("INITIALIZE_FAILED".equals(status)){
					
					break;
				}
				loopCount --;
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(loopCount > 0);
			if(loopCount == 0){
				log.add(LogStatus.FAIL, "B1A initialize schema \"" + schemaName + "\" time-out.");
				Assert.assertTrue(false,"B1A initialize schema \"" + schemaName + "\" time-out.");
			}
			if(!bSuccess){
				log.add(LogStatus.FAIL, "B1A initialize schema \"" + schemaName + "\" failed.");
				Assert.assertTrue(false,"B1A initialize schema \"" + schemaName + "\" failed.");
			}
			
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "B1A initialize schema \"" + schemaName + "\" failed! with: " + ex.toString());
			Assert.assertTrue(false, 
					"B1A initialize schema \"" + schemaName + "\" failed! with: " + ex.toString());
		}
		
	}
	
	/**
	 * Initial B1a for uiapi framework
	 * @param b1ahServer
	 * @param schemaName
	 * @return bSuccess
	 * Owner: Deyu. li 2019-01-23
	 */
	public static boolean initializeCompany(String b1ahServer,String schemaName){
		boolean bSuccess = false;
		try{
			RestAssured.baseURI = "https://" + b1ahServer + ":40000";
			RestAssured.useRelaxedHTTPSValidation();
			CookieFilter cookieFilter = new CookieFilter();
			
			//request SP
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.given().
							log().all().
							filter(cookieFilter).
							header("Content-Type", "application/x-www-form-urlencoded").get("/Enablement/");
			
			
			String responseBody = response.getBody().asString();
			System.out.println("responseBody -> " + responseBody);
			Matcher matcher = Pattern.compile("name=\"SAMLRequest\" value=\"(.*?)\"").matcher(response.getBody().asString());
			matcher.find();                  
		    //String samlRequest = new String(Base64.getDecoder().decode(matcher.group(1)));
			String samlRequest = matcher.group(1);
			Matcher matcherRelay = Pattern.compile("name=\"RelayState\" value=\"(.*?)\"").matcher(responseBody);
			matcherRelay.find();
			String relayState =  matcherRelay.group(1);
			
		
			//login IDP 
			ObjectMapper mapper = new ObjectMapper();
			JsonNode requestBody = mapper.createObjectNode();
			((ObjectNode) requestBody).put("Account","B1SiteUser");
			((ObjectNode) requestBody).put("Password","1234");
			
			Response resLoginIDP = httpRequest.given().
								header("Content-Type", "application/json;odata=verbose").
								header("Accept","application/json").
								filter(cookieFilter).
								body(requestBody).
								when().post("/sld/sld.svc/LogonByNamedUser");
			System.out.println("resLoginIDP -> " + resLoginIDP.getBody().asString());
			//IDP SSO
			Response resLoginSSO = httpRequest.given().
					header("Content-Type", "application/x-www-form-urlencoded").
					log().all().
					filter(cookieFilter).
					queryParam("SAMLRequest", samlRequest).
					queryParam("RelayState", relayState).
					when().post("/sld/saml2/idp/sso");

			System.out.println("resLoginSSO -> " + resLoginSSO.getBody().asString());
			Matcher matcher_req = Pattern.compile("name=\"SAMLResponse\" value=\"(.*?)\"").matcher(resLoginSSO.getBody().asString());
			matcher_req.find(); 
			String samlResponse = matcher_req.group(1);

			//SP SSO Callback
			RequestSpecification httpRequest_02 = RestAssured.given();
			Response loginSPsso = httpRequest_02.given().
					header("Content-Type", "application/x-www-form-urlencoded").
					log().all().filter(cookieFilter).
					queryParam("SAMLResponse", samlResponse).
					queryParam("RelayState",relayState).
					when().post("/Enablement/saml2/sp/acs");
			int statusCode = loginSPsso.getStatusCode();
			System.out.println("StatusCode -> " + statusCode);
			
			//Get company Info
			RequestSpecification httpRequest_03 = RestAssured.given();
			Response getCompanyInfo = httpRequest_03.given().
					log().all().filter(cookieFilter).
					when().get("/Enablement/companyInfo");
			System.out.println("getCompanyInfo -> " + getCompanyInfo.getBody().asString());
			System.out.println("Start to initialize \"" + schemaName + "\" for B1A.");
			//initialize schema
			RequestSpecification httpRequest_04 = RestAssured.given();
			Response initialCompany = httpRequest_04.given().
					header("Content-Type", "application/json").
					log().all().filter(cookieFilter).
					when().post("/Enablement/companyInfo?action=init&companyName=" + schemaName + "&compatibleMode=Off");

			System.out.println("initialCompany -> " + initialCompany.getBody().asString());
			//wait initialization complete
			RequestSpecification httpRequest_05 = RestAssured.given();
			int loopCount = 20;
			
			do{
				
				Response getInitialState = httpRequest_05.given().
						config(RestAssured.config().paramConfig(ParamConfig.paramConfig().queryParamsUpdateStrategy(UpdateStrategy.REPLACE))).
						header("Content-Type", "application/json").
						log().all().filter(cookieFilter).
						when().get("/Enablement/companyInfo?action=status&companyName=" + schemaName);
				System.out.println("getInitialState -> " + getInitialState.getBody().asString());
				JsonPath jsonPath = getInitialState.jsonPath();
				String status = jsonPath.get("status");
				if("INITIALIZED".equals(status)){
					bSuccess = true;
					System.out.println("B1A initialize schema \"" + schemaName + "\" successfully.");
					break;
				}
				
				if("INITIALIZE_FAILED".equals(status)){
					
					break;
				}
				loopCount --;
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(loopCount > 0);
			if(loopCount == 0){
				System.err.println("B1A initialize schema \"" + schemaName + "\" time-out.");
				
			}
			if(!bSuccess){
				System.err.println("B1A initialize schema \"" + schemaName + "\" failed.");
				
			}
			
		}catch(Exception ex){
			System.err.println("B1A initialize schema \"" + schemaName + "\" failed! with: " + ex.toString());
			
		}
		return bSuccess;
	}

}
