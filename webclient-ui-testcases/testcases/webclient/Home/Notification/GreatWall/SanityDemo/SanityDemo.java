package webclient.Home.Notification.GreatWall.SanityDemo;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.administration.Settings;
import webclient.modules.bp.Activity;
import webclient.modules.home.Home;
import webclient.modules.home.Notification;
import webclient.modules.home.NotificationBy;
import webclient.modules.home.NotificationPriority;

public class SanityDemo extends BaseWebClientTest{
	@Test
	public void check_Notifications(ITestContext context) throws InterruptedException {
		WebClient tc = new WebClient(context);
		tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
		//tc.initialB1A();
		tc.open();
		
		Logon logon = new Logon();
		logon.logon("manager", "manager", "SBODEMOUS_UI2639037");
		
//		Home home = new Home();
//		home.navigateToFromTiles("Business Partners->Create Activity");
//		Settings.Notification notification = setting.new Notification();
//		Settings s = new Settings();
//		Settings.Notification sn = s.new Notification();
//		sn.setNotificationLastDays(5);
//		sn.showActivityReminders(true);
//		sn.showPreviewInHomePage(true);
//		sn.save();


//		n.comparePreviewItemHeader(1, "Task　　17:37　　January 21, 2019");
//		n.comparePreviewItemPriority(1, NotificationPriority.Normal);
//		
//		n.compareItemHeader(NotificationBy.All, 3, "Meeting　　18:04　　January 21, 2019");
		//n.compareItemHeader(NotificationBy.All, 0, "");
		//n.compareItemHeader(NotificationBy.Date, 1, "10　Activities Scheduled for Today　　January 21, 2019");
		
//		n.compareSubItemHeader(NotificationBy.Date, 0, 3, "Meeting　　18:04");
//		
		Home home = new Home();
		home.navigateToFromTiles("Business Partners->Create Activity");
		Activity act = new Activity();
		act.setType("Task");
		act.setCustomer("C30000");
		act.setSubject("Test for Task");
		
		act.setContent("test for notification");
		
		DateFormat df = new SimpleDateFormat("MMM dd,yyyy");
		Date d = new Date(System.currentTimeMillis()+5*60*1000);
		String beginDate =  df.format(d);
		df = new SimpleDateFormat("hh:mm:ss a");
		String beginTime =  df.format(d);
		
		act.setStartTime(beginTime);
		act.setStartDate(beginDate);
		act.checkReminder(false);
		act.setRecurrence("Daily");

		act.setReminderQuantity("2");
		act.setReminderType("Minutes");
		act.clickAddAndView();
		Notification n = new Notification();
		
		n.checkFloatItemHeaderContains(3, 0, "Task　　Microchips");
		n.checkFloatItemBodyContains(3, 0, "Start");
		n.compareNewNotificationCount(1);
		
		n.checkItemBodyContains(NotificationBy.All, 0, "Task　　Microchips");
		n.checkSubItemHeaderContains(NotificationBy.Date, 0, 0, "Task　　Microchips");
		n.closeSubItem(NotificationBy.Date, 0, 0);
	}
}
