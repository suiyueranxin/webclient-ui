package webclient.general;

import webuita.general.Global;

public class MessageBox {
	public static void compareMessage(String expectedMsg){
		String msgXpath = "//div[contains(@class, 'sapMMessageToast') and @role='alert']";
		String actual = Global.conn.getElementValue("xpath", msgXpath, "");
		General.compare(actual, expectedMsg, true, "Compare Message contents.");
	}
	
	public static void click(String btnName){
		String xpath = "";
		switch(btnName){
		case "OK":
			xpath = "//div[contains(@class, 'sapMDialog')]//button//bdi[text()='OK']/..";
			Global.conn.clickElement("xpath", xpath, "click OK button");
			break;
		
		}
		
	}
}
