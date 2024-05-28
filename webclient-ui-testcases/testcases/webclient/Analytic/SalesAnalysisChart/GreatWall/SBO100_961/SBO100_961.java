package webclient.Analytic.SalesAnalysisChart.GreatWall.SBO100_961;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.filters.TimePeriodType;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.administration.Settings;
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.home.Home;
import webclient.modules.home.Notification;
import webclient.modules.home.NotificationBy;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartSortBy;
import widgets.ui5.ChartSortOrder;

public class SBO100_961 extends BaseWebClientTest{
	@Test
	public void add_then_CheckSalesOrder(ITestContext context) throws InterruptedException {
		WebClient tc = new WebClient(context);
		//tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
		//tc.initialB1A();
		tc.open();
		
		Logon logon = new Logon();
		logon.logon("manager", "manager", "SBODEMOUS_UI1904644");
		
//		Notification no = new Notification();
		
//		Settings setting = new Settings();
//		Settings.Notification notification = setting.new Notification();
//		notification.setNotificationLastDays(10);
//		notification.showActivityReminders(false);
//		notification.showPreviewInHomePage(false);
//		notification.save();
		
		Home home = new Home();
		
		home.navigateToFromTiles("Sales->Sales Orders");
		Filters filter = new Filters();
		filter.selectFilterConditionsFromList(FilterFields.UserSignature, "User ID", "37;27;1");
//		filter.defineFilterConditions(FilterFields.UserSignature, new String[][]{{Operators.EqualTo, "10"}, 
//																				{Operators.EqualTo, "20"}, 
//																				{Operators.EqualTo, "30"}});
//		
//		filter.removeCondition(FilterFields.UserSignature);
		filter.defineFilterByTimePeriod(FilterFields.DeliveryDate, new String[][]{{Operators.EqualTo, TimePeriodType.today}, 
																	 {Operators.EqualTo, TimePeriodType.lastXDays,"20"},
																	 {Operators.EqualTo, TimePeriodType.dateRange,"01/20/2020;01/25/2020"}});
		filter.saveAsView("View-Test", false, true);
		filter.setAsDefault("View-Test");
		filter.deleteView("View-Test");
//		home.navigateToFromTiles("Analytic->Sales Analysis Overview");
//		SalesAnalysisOverview saov = new SalesAnalysisOverview();
//		saov.openCard(1);
//		saov.compareDimensions("Net Sales Amount (LC),Gross Profit (LC)", "Posting Year and Month", "All");
//		Filters filter = new Filters();
//		filter.go();
//		filter.compareFilterCondition(FilterFields.Analysis.PostingDate, "20160101...20180630");
//		filter.compareFilterCondition(FilterFields.Analysis.DocumentTypeGroup, "Invoice");
//		home.goBack();
//		saov.openCard(3);
//		saov.compareDimensions("Net Sales Amount (LC)", "Item Description & Code", "All");
//		saov.checkCardContentsShow(3, CardTypes.Table, true);
//		home.navigetTo("Analytic->Sales Analysis Chart");
//		SalesAnalysisChart sac = new SalesAnalysisChart();
//		sac.checkChartShow(ChartTypes.Line, true);
//		
//		Filters filter = new Filters();
//		filter.saveAsView("test1234", true, true);
//		
//		home.backHome();
//		home.navigetTo("Analytic->Sales Analysis Chart");
//		filter.compareDefaultViewName("test1234");
//		SalesOrderList sol = new SalesOrderList();
//		sol.clickChartView();
//		sol.sortChart(ChartSortBy.xAxis, ChartSortOrder.descending);
//		sol.clickChartLegend();
//		sol.clickChartZoomIn();
//		sol.clickChartZoomOut();
//		sol.openChartFullScreen();
//		sol.setChartDimensions("Record Count", "BP Code", "Status");
//		sol.compareChart(widgets.ui5.ChartTypes.column, "columnChart.png");
		
		
//		sol.compareChart(ChartTypes.Line, "noLegend.png");
//		sol.checkChartShow(ChartTypes.Line, true);
//		sol.setFormSetting(new String[]{"Approved","Bill To"},
//						   new String[]{"BP Code", "Posting Date", "Delivery Date"});
		
//		Filters filters = new Filters();
//		
//		filters.selectView("Open Sales Orders");
//		filters.search("C20000");
//		filters.setFilterConditions("Document Number", new String[][]{{Operators.EqualTo, "10"}, 
//																	  {Operators.EqualTo, "20"}, 
//																	  {Operators.EqualTo, "30"}});
		
//		filters.setFilterCondition("BP Name", Operators.Equal, "value");
//		filters.selectFromList("BP Code", "C20000, C23900");
//		filters.clickGo();
		
//		sol.SalesOrderFilters.search("C20000");
//		sol.SalesOrderFilters.setFilterCondition([{"Item Description", Operators.Equal, "C20000"},
//												{"Item Description", Operators.Equal, "C20000"}]);
//		sol.SalesOrderFilters.selectFromList([{"BP Code", "C30000"}, {"BP Name", "Microchips"}]);
		//search then select  row 1,2,3,4
//		sol.SalesOrderFilters.selectFromList("SearchCondition","1,2,3,4");
//		sol.SalesOrderFilters.clickOK();
//		sol.SalesOrderFilters.clickGo();
//		SalesOrderFilters sof = new SalesOrderFilters();
//		sof.search("C20000");
//		sof.setFilterCondition("")
//		sof.setItemNo.
//		sof.clickGo();
		
//		sol.clickChartView();
//		sol.setChartType("Line Chart");
//		sol.checkLineChart();
	}
	
}
