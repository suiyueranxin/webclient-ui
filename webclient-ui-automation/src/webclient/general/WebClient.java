package webclient.general;

import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.LogStatus;

import webclient.config.CompanyInfo;
import webclient.config.HanaServer;
import webclient.config.SldServer;
import webclient.config.ThinClientServer;
import webclient.schema.Schema;
import webclient.service.B1A;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.log.Logger;

public class WebClient {
	
	private WebConnection conn;
	private Logger log;
	private String url = null;
	private String writeDBNameFile = null;
	private boolean isLinuxDbbackupSrcPath = false;
	
	//thin client variables
	private ThinClientServer tcServer= new ThinClientServer();
	private SldServer sldServer = new SldServer();
	public static HanaServer hanaServer = new HanaServer();
	
	public WebClient(){}
	public WebClient(ITestContext testContext) {
		XmlTest xml = testContext.getCurrentXmlTest();
		Map<String, String> parameters = xml.getAllParameters();
		
		
		hanaServer.setHostIp(parameters.get("hanaServerHost"));
		hanaServer.setUsername(parameters.get("hanaServerHostUsername"));
		hanaServer.setPassword(parameters.get("hanaServerHostPassword"));
		hanaServer.setPort(parameters.get("hanaServerPort"));
		hanaServer.setDbUsername(parameters.get("hanaDBUsername"));
		hanaServer.setDbPassword(parameters.get("hanaDBPassword"));
		hanaServer.setDbbackupSrcPath(parameters.get("dbbackupSrcPath"));
		
		
		//thin client variables
		
		tcServer.setHostIp(parameters.get("tcServerHost"));
		tcServer.setUsername(parameters.get("tcServerUsername"));
		tcServer.setPassword(parameters.get("tcServerPassword"));
		tcServer.setTcBuildPath(parameters.get("tcbuildPath"));
		tcServer.setTcCodeLine(parameters.get("tcCodeLine"));
		tcServer.setSapJvmPath(parameters.get("sapJvmPath"));
		
		//refresh sld variables
		
		sldServer.setRefApiUrl(parameters.get("sldRefApiUrl"));
		sldServer.setPort(parameters.get("sldPort"));
		sldServer.setAccount( parameters.get("sldAccount"));
		sldServer.setPassword(parameters.get("sldPassword"));
				
		//b1a
		GlobalWebClient.b1aContentPath = parameters.get("b1aContentPath");
										
		
		isLinuxDbbackupSrcPath = hanaServer.getDbbackupSrcPath().contains("/");
		GlobalWebClient.restoreCompanyDB = parameters.get("restoreCompanyDB");
		GlobalWebClient.dropCompanyDbAfterRunning = parameters.get("dropCompanyDbAfterRunning");
		writeDBNameFile = parameters.get("writeDBNameFile");

		url = parameters.get("tcHomePage");
		log = Global.log;
		
	}

	public void open(){
		conn = new WebConnection();
		Global.conn = conn;
		conn.gotoUrl(url);
	}
	public String restoreInitialCompany(String companyDbName, String companyDbusername, String companyDbpassword, 
			String newCompanyDbName, String companyDbbackupName){
		
		try{
			CompanyInfo company = new CompanyInfo();
			company.setDbName(companyDbName);
			company.setUserName(companyDbusername);
			company.setPassword(companyDbpassword);
			company.setNewDbName(newCompanyDbName);
			company.setDbBackupName(companyDbbackupName);
			
			if("no".equalsIgnoreCase(GlobalWebClient.restoreCompanyDB)) {
				GlobalWebClient.companyDbName = "";
				log.add(LogStatus.INFO, "Ignore the shcema restore and initialization.");
			}else{
				GlobalWebClient.companyDbName = Schema.restoreInitCompany(hanaServer, tcServer, company, 
						isLinuxDbbackupSrcPath, writeDBNameFile);
				log.add(LogStatus.PASS, "Success to restore the company db: \"" + GlobalWebClient.companyDbName + "\"");
				
				 //refresh SLD Schema
				int retCode = Schema.refreshSLDSchema(sldServer, hanaServer);
			    if(200 == retCode){
			    	Global.log.add(LogStatus.PASS,"Success to refresh SLD Schema.");
			    }else{
			    	Global.log.add(LogStatus.FAIL,"Failed to refresh SLD Schema.");
			    	Assert.assertTrue(false, "Failed to refresh SLD Schema.");
			    }
			}
					
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Faile to restore the company db with: \"" + ex.toString() + "\".");
			Assert.assertTrue(false, "Faile to restore the company db with: \"" + ex.toString() + "\".");
		}
		
	
		return GlobalWebClient.companyDbName;
	}
	
	public void initialB1A(String schemaName){
		if(null == schemaName || schemaName.equals("")){
			return;
		}
		if(schemaName.length() > 23){
			log.add(LogStatus.FAIL, "The schemaName \"" + schemaName + "\" length is too long( <= 23).");
			Assert.assertTrue(false, "The schemaName \"" + schemaName + "\" length is too long( <= 23).");
		}
		GlobalWebClient.isB1ACase = true;
		B1A.initializeCompany(tcServer.getHostIp(), schemaName, log);
	}
	
	public void initialB1A(){
		if(!GlobalWebClient.companyDbName.equals("")){
			if(GlobalWebClient.companyDbName.length() > 23){
				log.add(LogStatus.FAIL, "The schemaName \"" + GlobalWebClient.companyDbName + "\" length is too long( <= 23).");
				Assert.assertTrue(false, "The schemaName \"" + GlobalWebClient.companyDbName + "\" length is too long( <= 23).");
			}
			GlobalWebClient.isB1ACase = true;
			B1A.initializeCompany(tcServer.getHostIp(), GlobalWebClient.companyDbName, log);
		}
		
	}
}
