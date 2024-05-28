package webclient.service;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class WebClientService {
	public  static void logOut(String schemaName, Map<String, String> dictCookies){
		
		try{
			RequestSpecification request = RestAssured.given();
			RestAssured.baseURI = "http://10.58.114.82:8080";
			Header headerAccept = new Header("Accept", "*/*");
			Header headerContentType = new Header("Content-Type", "application/json");
			Header headerAL = new Header("Accept-Language", "en-US,en;q=0.9");
			Header headerAE = new Header("Accept-Encoding", "gzip, deflate");
			Header headerUA = new Header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
			List<Header> listHeader = new ArrayList<Header>();
			listHeader.add(headerAccept);
			listHeader.add(headerAL);
			listHeader.add(headerAE);
			listHeader.add(headerUA);
			listHeader.add(headerContentType);
			Headers headers = new Headers(listHeader);
			List<Cookie> cookies = new ArrayList<Cookie>();
			for(Entry<String, String> entry : dictCookies.entrySet()){
				cookies.add(new Cookie.Builder(entry.getKey(), entry.getValue()).build());
			}
			request
			.headers(headers)
            .cookies(dictCookies)
            .get("/auth/saml2/sp/logout?url=http%3A%2F%2F10.58.114.82%3A8080 ")
            .then().statusCode(200);

			
		}catch(Exception ex){
			
			Assert.assertTrue(false, 
					"B1A initialize schema \"" + schemaName + "\" failed! with: " + ex.toString());
		}
		
		
	}
}
