package widgets.ui5;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class MultiComboBox  extends ValueSelector{
	
	public MultiComboBox(String itemName, Locators loc) {
		super(itemName, loc);
	}

	public void clear(){
		try{
			String tokenXpath = byValue + "//span[contains(@class,'sapMTokenText')]/..//span[contains(@id,'icon')]";
			java.util.List<WebElement> tokens = conn.findElements("xpath", tokenXpath);
			if(null != tokens){
				int countConditions = tokens.size();
				for(int i = 0; i < countConditions; i++){
					tokens = conn.findElements("xpath", tokenXpath);
					tokens.get(tokens.size() - 1).click();
				}
			}
			
		}catch(Exception ex){
			Global.log.add(LogStatus.FAIL, String.format("Fail to clear the \"%s\".", name));
		}
		
	}
	
	public void checkValuesFromList(String packageName, String dlgName, String colName, String values){
		try{
			boolean bSuccessed = false;
			openValueHelp();
			StringBuilder className = new StringBuilder(packageName);
			className.append(dlgName);
			Class<?> dlgClass = Class.forName(className.toString());
			Object obj = dlgClass.newInstance();
			Class<?>[] paramTypes = {String.class, String.class};
			Method cfl = dlgClass.getDeclaredMethod("checkValuesFromTable",paramTypes);
			bSuccessed = (boolean)cfl.invoke(obj, colName, values);
			if(bSuccessed){
				Global.log.add(LogStatus.INFO, "Check \"" + colName + "\"" + ", value: \"" + values + "\" on \"" + dlgName + "\"");
			}else{
				Global.log.add(LogStatus.FAIL, "Fail to check \"" + colName + "\"" + ", value: \"" + values + "\" on \"" + dlgName + "\"", Global.isStopRunAfterFail);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Global.log.add(LogStatus.FAIL, "Fail to set \"" + colName + "\"" + ", value: \"" + values + "\" on \"" + dlgName + "\"， with: " + e.toString(), Global.isStopRunAfterFail);
		}
		
	}
}
