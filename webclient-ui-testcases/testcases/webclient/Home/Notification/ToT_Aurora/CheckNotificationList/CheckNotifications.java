package webclient.Home.Notification.ToT_Aurora.CheckNotificationList;


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

public class CheckNotifications extends BaseWebClientTest{
	@Test
	public void check_Notifications(ITestContext context) throws InterruptedException {
		WebClient tc = new WebClient(context);
		String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
		tc.initialB1A(schemaName);
		tc.open();
		Logon logon = new Logon();
		logon.logon("manager", "manager", schemaName);
		//logon.logon("manager", "manager", "US_I070880_3292_09_03");
		Thread.sleep(5000);
		//change settings
		Settings s = new Settings();
		Settings.Notification sn = s.new Notification();
		sn.setNotificationLastDays(5);
		sn.showActivityReminders(true);
		sn.showPreviewInHomePage(true);
		sn.save();
		
		//Add activity Doc1;add inactive doc1
		Home home = new Home();
		home.navigateTo("Business Partners->Create Activity");
		Activity act = new Activity();
		act.setType("Task");
		act.setSubject("Doc1");	
		act.setCustomer("C30000");
		act.setContent("");
		act.setStartTime("10:30");
		act.setRecurrence("Daily");
		act.setPriority("Low");
		act.setShowinCalendar("Yes");
		act.clickAddAndNew();
	
		//Add activity Doc2
		act.setType("Meeting");
		act.setSubject("Doc2");
		act.setCustomer("C20000");
		act.setContent("");	
		act.setStartTime("9:00");
	    act.setRoom("");
		act.setRecurrence("Daily");
		act.setPriority("Normal");
		act.setShowinCalendar("No");
		act.clickAddAndNew();
	
		
		//Add activity Doc3
//		act.setType("Phone Call");
		act.setSubject("Doc3");
		act.setCustomer("C30000");
		act.setContent("");	
		act.setStartTime("11:00");
		act.setDuration("15");
		act.setRecurrence("Daily");
		act.setPriority("High");
		act.setShowinCalendar("No");
		act.clickAddAndBack();


		
		//Check Notification list
		Notification n = new Notification();
		Thread.sleep(5000);
		n.compareNewNotificationCount(2);
	
		n.checkItemHeaderCotains(NotificationBy.All,0,"Meeting  Maxi-Teq  09:00");
		n.checkItemBodyContains(NotificationBy.All, 0, "Doc2");
		n.checkItemHeaderCotains(NotificationBy.All,1,"Phone Call  Microchips  11:00");
		n.checkItemBodyContains(NotificationBy.All, 1, "Doc3");
		n.checkItemHeaderCotains(NotificationBy.Date,0,"2 Activities");
		n.checkItemHeaderCotains(NotificationBy.Priority,0,"Phone Call  Microchips  11:00");
		n.checkItemBodyContains(NotificationBy.Priority, 0, "Doc3");
		n.checkItemHeaderCotains(NotificationBy.Priority,1,"Meeting  Maxi-Teq  09:00");
		n.checkItemBodyContains(NotificationBy.Priority, 1, "Doc2");
		
/*
		n.comparePreviewItemHeader(1, "Task  17:37  January 21, 2019");
		n.comparePreviewItemPriority(1, NotificationPriority.Normal);
		
		n.compareItemHeader(NotificationBy.All, 3, "Meeting  18:04  January 21, 2019");
		//n.compareItemHeader(NotificationBy.All, 0, "");
		//n.compareItemHeader(NotificationBy.Date, 1, "10 Activities Scheduled for Today  January 21, 2019");
		
		n.compareSubItemHeader(NotificationBy.Date, 0, 3, "Meeting  18:04");
*/		
		
		
/*		
		
		DateFormat df = new SimpleDateFormat("MMM dd,yyyy");
		Date d = new Date(System.currentTimeMillis()+5*60*1000);
		String beginDate =  df.format(d);
		df = new SimpleDateFormat("hh:mm:ss a");
		String beginTime =  df.format(d);
		
		act.setStartTime(beginTime);
		act.setStartDate(beginDate);
		act.checkReminder(false);
		act.setReminderQuantity("2");
		act.setReminderType("Minutes");
		act.clickAddAndView();
		
		
		n.checkFloatItemHeaderContains(3, 0, "Task  Microchips");
		n.checkFloatItemBodyContains(3, 0, "Start");
		n.compareNewNotificationCount(1);
		
		n.checkItemBodyContains(NotificationBy.All, 0, "Task  Microchips");
		n.checkSubItemHeaderContains(NotificationBy.Date, 0, 0, "Task  Microchips");
		n.closeSubItem(NotificationBy.Date, 0, 0);
*/
	}
}
