package webclient.general;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.LogStatus;

import webclient.schema.Schema;
import webuita.general.Global;
import webuita.testcase.BaseTest;

public class BaseWebClientTest extends BaseTest {
	@AfterTest
	public void killWebDriverProcess(){
		try {
			String hostIp = WebClient.hanaServer.getHostIp();
			String port = WebClient.hanaServer.getPort();
			String username = WebClient.hanaServer.getDbUsername();
			String pwd = WebClient.hanaServer.getDbPassword();
			
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
			if(GlobalWebClient.dropCompanyDbAfterRunning.equalsIgnoreCase("yes")){
				String sql = "drop schema \"" + GlobalWebClient.companyDbName + "\" CASCADE";
				if(Schema.execHanaSql(hostIp, port, username, pwd, sql)){
					Global.log.add(LogStatus.PASS,"Finished to drop the schema: \"" + GlobalWebClient.companyDbName + "\"!");
				}else{
					Global.log.add(LogStatus.FAIL,"Fail to drop the schema: \"" + GlobalWebClient.companyDbName + "\".");
				}
				//delete b1a produced file
				if(GlobalWebClient.isB1ACase){
					String cmd = "rm -r " + GlobalWebClient.b1aContentPath  + "content/" + GlobalWebClient.companyDbName;
					if(Schema.execCmdOnLinuxRetStatusCode(hostIp, username, pwd, cmd) == 0){
						Global.log.add(LogStatus.PASS,"Finished to execute \"" + cmd + "\".");
					}else{
						Global.log.add(LogStatus.FAIL,"Failed to execute \"" + cmd + "\".");
					}
					
					cmd = "rm -r " + GlobalWebClient.b1aContentPath  + "conf/iahome/" + GlobalWebClient.companyDbName;
					if(Schema.execCmdOnLinuxRetStatusCode(hostIp, username, pwd, cmd) == 0){
						Global.log.add(LogStatus.PASS,"Finished to execute \"" + cmd + "\".");
					}else{
						Global.log.add(LogStatus.FAIL,"Failed to execute \"" + cmd + "\".");
					}	
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
