package webclient.modules.administration;

import webclient.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;

public class Settings {
//	private WebConnection conn;
//	private Logger log;
//	private Locators locators;
//	
//	public Settings(){
//		conn = Global.conn;
//		log = Global.log;
//		locators = new Locators("Settings_mapping.xml", log);
//		
//		Button btn = new Button("MeArea", locators);
//		btn.click();
//		General.waitPageLoadComplete();
//		Button btnSettings = new Button("Settings", locators);
//		btnSettings.click();
//		General.waitPageLoadComplete();
//	}
//	public class Notification{
//		
//		public Notification(){
//			Button btn = new Button("Notifications", locators);
//			btn.click();
//			General.waitPageLoadComplete();
//		}
//		
//		public void showActivityReminders(boolean isShow){
//			SwitchWidget btn = new SwitchWidget("Show Activity Reminders", locators);	
//			if(isShow){
//				btn.on();
//			}else{
//				btn.off();
//			}
//			
//		}
//		
//		public void showPreviewInHomePage(boolean isShow){
//			SwitchWidget btn = new SwitchWidget("Show Preview in Home Page", locators);
//			if(isShow){
//				btn.on();
//			}else{
//				btn.off();
//			}
//		}
//		
//		public void setNotificationLastDays(int days){
//			StringWidget input = new StringWidget("Show Notifications for the Last Days", locators);
//			input.set(Integer.toString(days));
//		}
//		
//		public void save(){
//			Button btn = new Button("Save", locators);
//			btn.click();
//			
//		}
//	}
//    public class UserActivities{
//    	//recent activity and frequently
//    	public void showTrackActivities(boolean isShow)
//    	{
//    		
//    		SwitchWidget btn= new SwitchWidget("Track my recent activity and frequently used apps",locators);
//    		if(isShow)
//    		{
//    			btn.on();
//    		}
//    		else{
//				btn.off();
//			}
//    	   
//    	}
//    	public void Clear()
//    	{
//    		Button btn= new Button("Clear my history",locators);
//    		btn.click();
//    				
//    	}
//    }
}
