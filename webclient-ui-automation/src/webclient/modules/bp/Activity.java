package webclient.modules.bp;

import webclient.dialogs.DlgNames;
import webclient.general.BaseForm;
import widgets.ui5.Select;


public class Activity extends BaseForm{

//	public Activity(){
//		 super("Activity_mapping.xml");
//	}
//	
//	public void setType(String value){
//		Select widget = new Select("Type", locators);
//		widget.select(value);
//	}
//	public void compareType(String value){
//		Select widget = new Select("Type", locators);
//		widget.compare(value);
//	}
//	
//	public void setSubject(String value){
//		Select widget = new Select("Subject", locators);
//		widget.set(value);
//	}
//	public void compareSubject(String value){
//		Select widget = new StringWidget("Subject", locators);
//		widget.compare(value);
//	}
//	
//	public void setCategory(String value){
//		CFLWidget widget = new CFLWidget("Category", locators);
//		widget.chooseFromList(DlgNames.ActivityCategories, "Code", value);
//	}
//	public void compareCategory(String value){
//		CFLWidget widget = new CFLWidget("Category", locators);
//		widget.compare(value);
//	}
//	
//	public void setSubcategory(String value){
//		CFLWidget widget = new CFLWidget("Subcategory", locators);
//		widget.chooseFromList(DlgNames.ActivitySubcategories, "Code", value);
//	}
//	public void compareSubcategory(String value){
//		CFLWidget widget = new CFLWidget("Subcategory", locators);
//		widget.compare(value);
//	}
//	
//	public void setAssignedTo(String value){
//		SelectWidget widget = new SelectWidget("VAssignedTo", locators);
//		widget.select(value);
//	}
//	public void compareAssignedTo(String value){
//		SelectWidget widget = new SelectWidget("VAssignedTo", locators);
//		widget.compare(value);
//	}
//	
//	public void setAttendUser(String value){
//		CFLWidget widget = new CFLWidget("AttendUser", locators);
//		widget.chooseFromList(DlgNames.Users, "User ID", value);
//	}
//	public void compareAttendUser(String value){
//		CFLWidget widget = new CFLWidget("AttendUser", locators);
//		widget.compare(value);
//	}
//	
//	public void setAttendEmpl(String value){
//		CFLWidget widget = new CFLWidget("AttendEmpl", locators);
//		widget.chooseFromList(DlgNames.Employees, "Employee No.", "value");
//	}
//	public void compareAttendEmpl(String value){
//		CFLWidget widget = new CFLWidget("AttendEmpl", locators);
//		widget.compare(value);
//	}
//	
//	public void setAttendReci(String value){
//		CFLWidget widget = new CFLWidget("AttendReci", locators);
//		widget.chooseFromList(DlgNames.RecipientList, "Code", value);
//	}
//	public void compareAttendReci(String value){
//		CFLWidget widget = new CFLWidget("AttendReci", locators);
//		widget.compare(value);
//	}
//	
////	public void clickFlexibleAssignedTo(){
////		Button widget = new Button("Flexible Assigned To", locators);
////		widget.click();
////	}
////	public void setAssignedBy(String value){
////		NumberWidget widget = new NumberWidget("Assigned By", locators);
////		widget.set(value);
////	}
////	public void compareAssignedBy(String value){
////		NumberWidget widget = new NumberWidget("Assigned By", locators);
////		widget.compare(value);
////	}
//	
//	public void setTaskStatus(String value){
//		CFLWidget widget = new CFLWidget("Task Status", locators);
//		widget.chooseFromList(DlgNames.TaskStatuses, "Name", value);
//	}
//	public void compareTaskStatus(String value){
//		CFLWidget widget = new CFLWidget("Task Status", locators);
//		widget.compare(value);
//	}
//	
//	public void setCustomer(String value){
//		CFLWidget widget = new CFLWidget("CardCode", locators);
//		widget.chooseFromList(DlgNames.BusinessPartner, "BP Code", value);
//	}
//	public void compareCustomer(String value){
//		CFLWidget widget = new CFLWidget("CardCode", locators);
//		widget.compare(value);
//	}
//	
//	public void setContactPerson(String value){
//		CFLWidget widget = new CFLWidget("Contact Person", locators);
//		widget.chooseFromList(DlgNames.BusinessPartner, "BP Code", value);
//	}
//	public void compareContactPerson(String value){
//		CFLWidget widget = new CFLWidget("Contact Person", locators);
//		widget.compare(value);
//	}
//	
//	public void setTelephoneNo(String value){
//		StringWidget widget = new StringWidget("Telephone No.", locators);
//		widget.set(value);
//	}
//	public void compareTelephoneNo(String value){
//		StringWidget widget = new StringWidget("Telephone No.", locators);
//		widget.compare(value);
//	}
//	
//	public void setContent(String value){
//		TextAreaWidget widget = new TextAreaWidget("Content", locators);
//		widget.set(value);
//	}
//	public void compareContent(String value){
//		TextAreaWidget widget = new TextAreaWidget("Content", locators);
//		widget.compare(value);
//	}
//	
//	public void setPriority(String value){
//		SelectWidget widget = new SelectWidget("Priority", locators);
//		widget.select(value);
//	}
//	public void comparePriority(String value){
//		SelectWidget widget = new SelectWidget("Priority", locators);
//		widget.compare(value);
//	}
//	
//	public void compareStatus(String value){
//		SelectWidget widget = new SelectWidget("Status", locators);
//		widget.compare(value);
//	}
//	
//	public void setShowinCalendar(String value){
//		SelectWidget widget = new SelectWidget("Show in Calendar", locators);
//		widget.select(value);
//	}
//	public void compareShowinCalendar(String value){
//		SelectWidget widget = new SelectWidget("Show in Calendar", locators);
//		widget.compare(value);
//	}
//	
//	public void setStartDate(String value){
//		DateWidget widget = new DateWidget("StartDate", locators);
//		widget.set(value);
//	}
//	public void compareStartDate(String value){
//		DateWidget widget = new DateWidget("StartDate", locators);
//		widget.compare(value);
//	}
//	
//	public void setStartTime(String value){
//		TimeWidget widget = new TimeWidget("BeginTime", locators);
//		widget.set(value);
//	}
//	public void compareStartTime(String value){
//		TimeWidget widget = new TimeWidget("BeginTime", locators);
//		widget.compare(value);
//	}
//	
//	public void setEndDate(String value){
//		DateWidget widget = new DateWidget("endDate", locators);
//		widget.set(value);
//	}
//	public void compareEndDate(String value){
//		DateWidget widget = new DateWidget("endDate", locators);
//		widget.compare(value);
//	}
//	
//	public void setEndTime(String value){
//		TimeWidget widget = new TimeWidget("ENDTime", locators);
//		widget.set(value);
//	}
//	public void compareEndTime(String value){
//		TimeWidget widget = new TimeWidget("ENDTime", locators);
//		widget.compare(value);
//	}
//	
//	public void setDuration(String value){
//		QuantityWidget widget = new QuantityWidget("Duration", locators);
//		widget.set(value);
//	}
//	public void compareDuration(String value){
//		QuantityWidget widget = new QuantityWidget("Duration", locators);
//		widget.compare(value);
//	}
//	
//	public void setDurationType(String value){
//		SelectWidget widget = new SelectWidget("DurType", locators);
//		widget.select(value);
//	}
//	public void compareDurationType(String value){
//		SelectWidget widget = new SelectWidget("DurType", locators);
//		widget.compare(value);
//	}
//	
//	public void checkReminder(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Reminder", locators);
//		widget.check(value);
//	}
//	public void compareReminder(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Reminder", locators);
//		widget.compare(value);
//	}
//	
//	public void setReminderQuantity(String value){
//		QuantityWidget widget = new QuantityWidget("RemQty", locators);
//		widget.set(value);
//	}
//	public void compareReminderQuantity(String value){
//		QuantityWidget widget = new QuantityWidget("RemQty", locators);
//		widget.compare(value);
//	}
//	
//	public void setReminderType(String value){
//		SelectWidget widget = new SelectWidget("RemType", locators);
//		widget.select(value);
//	}
//	public void compareReminderType(String value){
//		SelectWidget widget = new SelectWidget("RemType", locators);
//		widget.compare(value);
//	}
//	
//	public void setMeetingLocation(String value){
//		CFLWidget widget = new CFLWidget("Meeting Location", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareMeetingLocation(String value){
//		CFLWidget widget = new CFLWidget("Meeting Location", locators);
//		widget.compare(value);
//	}
//	
////	public void setAddressID(String value){
////		SelectWidget widget = new SelectWidget("Address ID", locators);
////		widget.select(value);
////	}
////	public void compareAddressID(String value){
////		SelectWidget widget = new SelectWidget("Address ID", locators);
////		widget.compare(value);
////	}
//	
//	public void setStreet(String value){
//		StringWidget widget = new StringWidget("Street", locators);
//		widget.set(value);
//	}
//	public void compareStreet(String value){
//		StringWidget widget = new StringWidget("Street", locators);
//		widget.compare(value);
//	}
//	
//	public void setCity(String value){
//		StringWidget widget = new StringWidget("City", locators);
//		widget.set(value);
//	}
//	public void compareCity(String value){
//		StringWidget widget = new StringWidget("City", locators);
//		widget.compare(value);
//	}
//	
//	
//	public void setState(String value){
//		SelectWidget widget = new SelectWidget("State", locators);
//		widget.select(value);
//	}
//	public void compareState(String value){
//		SelectWidget widget = new SelectWidget("State", locators);
//		widget.compare(value);
//	}
//	
//	public void setCountry(String value){
//		CFLWidget widget = new CFLWidget("Country", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareCountry(String value){
//		CFLWidget widget = new CFLWidget("Country", locators);
//		widget.compare(value);
//	}
//	
//	public void setRoom(String value){
//		StringWidget widget = new StringWidget("Room", locators);
//		widget.set(value);
//	}
//	public void compareRoom(String value){
//		StringWidget widget = new StringWidget("Room", locators);
//		widget.compare(value);
//	}
//	
//	public void setRecurrence(String value){
//		SelectWidget widget = new SelectWidget("Recurrence", locators);
//		widget.select(value);
//	}
//	public void compareRecurrence(String value){
//		SelectWidget widget = new SelectWidget("Recurrence", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatonDaily(String value){
//		SelectWidget widget = new SelectWidget("Repeat on Daily", locators);
//		widget.select(value);
//	}
//	public void compareRepeatonDaily(String value){
//		SelectWidget widget = new SelectWidget("Repeat on Daily", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatEveryDays(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Days)", locators);
//		widget.set(value);
//	}
//	public void compareRepeatEveryDays(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Days)", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatEveryWeeks(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Weeks)", locators);
//		widget.set(value);
//	}
//	public void compareRepeatEveryWeeks(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Weeks)", locators);
//		widget.compare(value);
//	}
//	
//	public void checkMonday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Monday", locators);
//		widget.check(value);
//	}
//	public void compareMonday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Monday", locators);
//		widget.compare(value);
//	}
//	
//	public void checkTuesday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Tuesday", locators);
//		widget.check(value);
//	}
//	public void compareTuesday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Tuesday", locators);
//		widget.compare(value);
//	}
//	
//	public void checkWednesday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Wednesday", locators);
//		widget.check(value);
//	}
//	public void compareWednesday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Wednesday", locators);
//		widget.compare(value);
//	}
//	
//	public void checkThursday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Thursday", locators);
//		widget.check(value);
//	}
//	public void compareThursday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Thursday", locators);
//		widget.compare(value);
//	}
//	
//	public void checkFriday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Friday", locators);
//		widget.check(value);
//	}
//	public void compareFriday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Friday", locators);
//		widget.compare(value);
//	}
//	
//	public void checkSaturday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Saturday", locators);
//		widget.check(value);
//	}
//	public void compareSaturday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Saturday", locators);
//		widget.compare(value);
//	}
//	
//	public void checkSunday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Sunday", locators);
//		widget.check(value);
//	}
//	public void compareSunday(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Sunday", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatEveryMonths(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Months)", locators);
//		widget.set(value);
//	}
//	public void compareRepeatEveryMonths(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Months)", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatonMonthly(String value){
//		SelectWidget widget = new SelectWidget("Repeat on", locators);
//		widget.select(value);
//	}
//	public void compareRepeatonMonthly(String value){
//		SelectWidget widget = new SelectWidget("Repeat on", locators);
//		widget.compare(value);
//	}
//	
//	public void setDaySameDayEveryMonth(String value){
//		NumberWidget widget = new NumberWidget("Day", locators);
//		widget.set(value);
//	}
//	public void compareDaySameDayEveryMonth(String value){
//		NumberWidget widget = new NumberWidget("Day", locators);
//		widget.compare(value);
//	}
//	
//	public void setWeekSameWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("Week", locators);
//		widget.select(value);
//	}
//	public void compareWeekSameWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("Week", locators);
//		widget.compare(value);
//	}
//	
//	public void setDayOfWeekSameWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("DayOfWeek", locators);
//		widget.select(value);
//	}
//	public void compareDayOfWeekSameWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("DayOfWeek", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatEveryYears(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Years)", locators);
//		widget.set(value);
//	}
//	public void compareRepeatEveryYears(String value){
//		NumberWidget widget = new NumberWidget("Repeat Every (Years)", locators);
//		widget.compare(value);
//	}
//	
//	public void setRepeatonAnnually(String value){
//		SelectWidget widget = new SelectWidget("Repeat on", locators);
//		widget.select(value);
//	}
//	public void compareRepeatonAnnually(String value){
//		SelectWidget widget = new SelectWidget("Repeat on", locators);
//		widget.compare(value);
//	}
//	
//	public void setMonthSameMonthandDayEveryYear(String value){
//		SelectWidget widget = new SelectWidget("Month", locators);
//		widget.select(value);
//	}
//	public void compareMonthSameMonthandDayEveryYear(String value){
//		SelectWidget widget = new SelectWidget("Month", locators);
//		widget.compare(value);
//	}
//	
//	public void setDayInMonthSameMonthandDayEveryYear(String value){
//		NumberWidget widget = new NumberWidget("DayInMonth", locators);
//		widget.set(value);
//	}
//	public void compareDayInMonthSameMonthandDayEveryYear(String value){
//		NumberWidget widget = new NumberWidget("DayInMonth", locators);
//		widget.compare(value);
//	}
	
//	public void setWeekSameMonthWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("Week", locators);
//		widget.select(value);
//	}
//	public void compareWeekSameMonthWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("Week", locators);
//		widget.compare(value);
//	}
//	
//	public void setDayOfWeekSameMonthWeekandDayy(String value){
//		SelectWidget widget = new SelectWidget("DayOfWeek", locators);
//		widget.select(value);
//	}
//	public void compareDayOfWeekSameMonthWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("DayOfWeek", locators);
//		widget.compare(value);
//	}
//	
//	public void setMonthSameMonthWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("Month", locators);
//		widget.select(value);
//	}
//	public void compareMonthSameMonthWeekandDay(String value){
//		SelectWidget widget = new SelectWidget("Month", locators);
//		widget.compare(value);
//	}
//	
//	public void setRangeStart(String value){
//		DateWidget widget = new DateWidget("Range Start", locators);
//		widget.set(value);
//	}
//	public void compareRangeStart(String value){
//		DateWidget widget = new DateWidget("Range Start", locators);
//		widget.compare(value);
//	}
//	
//	public void setRangeEnd(String value){
//		SelectWidget widget = new SelectWidget("Range End", locators);
//		widget.select(value);
//	}
//	public void compareRangeEnd(String value){
//		SelectWidget widget = new SelectWidget("Range End", locators);
//		widget.compare(value);
//	}
//	
//	public void setNoofOccurrences(String value){
//		NumberWidget widget = new NumberWidget("No. of Occurrences", locators);
//		widget.set(value);
//	}
//	public void compareNoofOccurrences(String value){
//		NumberWidget widget = new NumberWidget("No. of Occurrences", locators);
//		widget.compare(value);
//	}
//	
//	public void setSpecificEndDate(String value){
//		DateWidget widget = new DateWidget("Specific End Date", locators);
//		widget.set(value);
//	}
//	public void compareSpecificEndDate(String value){
//		DateWidget widget = new DateWidget("Specific End Date", locators);
//		widget.compare(value);
//	}
//	
//	public void checkShowDocumentsRelatedtotheBP(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Show Documents Related to the BP", locators);
//		widget.check(value);
//	}
//	public void compareShowDocumentsRelatedtotheBP(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Show Documents Related to the BP", locators);
//		widget.compare(value);
//	}
//	
//	public void checkLinkDraft(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Link Draft", locators);
//		widget.check(value);
//	}
//	public void compareLinkDraft(boolean value){
//		CheckboxWidget widget = new CheckboxWidget("Link Draft", locators);
//		widget.compare(value);
//	}
//	
//	public void setDocumentType(String value){
//		SelectWidget widget = new SelectWidget("Document Type", locators);
//		widget.select(value);
//	}
//	public void compareDocumentType(String value){
//		SelectWidget widget = new SelectWidget("Document Type", locators);
//		widget.compare(value);
//	}
//	
//	public void setDocumentNo(String value){
//		CFLWidget widget = new CFLWidget("Document No.", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareDocumentNo(String value){
//		CFLWidget widget = new CFLWidget("Document No.", locators);
//		widget.compare(value);
//	}
//	
//	public void setPreviousActivity(String value){
//		NumberWidget widget = new NumberWidget("Previous Activity", locators);
//		widget.set(value);
//	}
//	public void comparePreviousActivity(String value){
//		NumberWidget widget = new NumberWidget("Previous Activity", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToSO(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToSO", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToSO(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToSO", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToBP(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToBP", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToBP(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToBP", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDO_MyDoc2(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDO_MyDoc2", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDO_MyDoc2(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDO_MyDoc2", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDT_Doc2R2(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Doc2R2", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDT_Doc2R2(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Doc2R2", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDT_Item2R1(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Item2R1", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDT_Item2R1(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Item2R1", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_Num_1To100(String value){
//		NumberWidget widget = new NumberWidget("AC_Num_1To100", locators);
//		widget.set(value);
//	}
//	public void compareAC_Num_1To100(String value){
//		NumberWidget widget = new NumberWidget("AC_Num_1To100", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_Time(String value){
//		TimeWidget widget = new TimeWidget("AC_Time", locators);
//		widget.set(value);
//	}
//	public void compareAC_Time(String value){
//		TimeWidget widget = new TimeWidget("AC_Time", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_Date_Grt20181228(String value){
//		DateWidget widget = new DateWidget("AC_Date_Grt20181228", locators);
//		widget.set(value);
//	}
//	public void compareAC_Date_Grt20181228(String value){
//		DateWidget widget = new DateWidget("AC_Date_Grt20181228", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDT_AutoWeekday(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_AutoWeekday", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDT_AutoWeekday(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_AutoWeekday", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDT_NO_MyStatus(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_NO_MyStatus", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDT_NO_MyStatus(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_NO_MyStatus", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDT_Doc2_Header(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Doc2_Header", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDT_Doc2_Header(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Doc2_Header", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDT_Item2_Header(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Item2_Header", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDT_Item2_Header(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDT_Item2_Header", locators);
//		widget.compare(value);
//	}
//	
//	public void setAC_LinkToUDO_MyItem2(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDO_MyItem2", locators);
//		widget.chooseFromList("dlgName", "colName", "value");
//	}
//	public void compareAC_LinkToUDO_MyItem2(String value){
//		CFLWidget widget = new CFLWidget("AC_LinkToUDO_MyItem2", locators);
//		widget.compare(value);
//	}
//	

}