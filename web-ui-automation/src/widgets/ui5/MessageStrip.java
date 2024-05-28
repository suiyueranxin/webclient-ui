package widgets.ui5;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;

public class MessageStrip {
	public static void compareMessageType(MessageType expectedMsgType ){
		String xpath = "//div[@class='sapBUiMessageArea']//span[@role='presentation']";
		String actualMsgType = Global.conn.getElementAttribute("xpath", xpath, "aria-label");
		String desc = String.format("Compare message strip type, expected value: \"%s\", actual vlaue: \"%s\"",
				expectedMsgType, actualMsgType);
		if(expectedMsgType.equals(actualMsgType)){
			Global.log.add(LogStatus.PASS, desc);
		}else{
			Global.log.add(LogStatus.FAIL, desc, Global.isStopRunAfterFail);
		}
	}
	
	public static void compareMessageText(String expectedMstText){
		String xpath = "//div[@class='sapBUiMessageArea']//span[contains(@class, 'sapMText ')]";
		String actualMsgText = Global.conn.getElementValue("xpath", xpath, "");
		String desc = String.format("Compare message strip text, expected value: \"%s\", actual vlaue: \"%s\"",
				expectedMstText, actualMsgText);
		if(expectedMstText.equals(actualMsgText)){
			Global.log.add(LogStatus.PASS, desc);
		}else{
			Global.log.add(LogStatus.FAIL, desc, Global.isStopRunAfterFail);
		}
	}
	
	public static void close(){
		String xpath = "//div[@class='sapBUiMessageArea']//button";
		Global.conn.clickElement("xpath", xpath, "Clolse message strip.");
	}
}
