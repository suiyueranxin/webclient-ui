package webclient.dialogs;

import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class FormSettingDlg extends BaseDlg{

	public FormSettingDlg(){
		super();
	}
	
	public void setFormSetting(String[] selectCols, String[] unSelectCols){
		
		if((selectCols.length <= 0) && (unSelectCols.length <= 0)) {
			log.add(LogStatus.FAIL, "Please check the parameters.");
			Assert.assertEquals(true, false,"Please check the parameters.");
		}
		
		String xpath = "";
		try{
			//select columns
			if(selectCols.length > 0){
				log.add(LogStatus.INFO, "Form Settings - Select Columns: \"" + String.join(",", selectCols) + "\".");
				for(int i = 0; i < selectCols.length; i++){
					xpath = "//span[text()='" + selectCols[i] + "']/../..//input";
					WebElement e = conn.findElement("xpath", xpath);
					String state = e.getAttribute("checked");
					xpath = "//span[text()='" + selectCols[i] + "']/../..//input/..";
					e = conn.findElement("xpath", xpath);
					if(null == state){
						e.click();
						continue;
					}
					if(state.equals("true")){
						continue;
					}
				}
			}
			
			
			//unselect columns
			if(unSelectCols.length > 0){
				log.add(LogStatus.INFO, "Form Settings - UnSelect Columns: \"" + String.join(",", unSelectCols) + "\".");
				for(int i = 0; i < unSelectCols.length; i++){
					xpath = "//span[text()='" + unSelectCols[i] + "']/../..//input";
					WebElement e = conn.findElement("xpath", xpath);
					String state = e.getAttribute("checked");
					xpath = "//span[text()='" + unSelectCols[i] + "']/../..//input/..";
					e = conn.findElement("xpath", xpath);
					if(null == state){
						continue;
					}
					if(state.equals("true")){
						e.click();
						
					}
				}
			}
			
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to set FormSettings with: \"" + ex.toString() +"\"");
			Assert.assertEquals(true, false,"Failed to set FormSettings with: \"" + ex.toString() +"\"");
		}
	}
}
