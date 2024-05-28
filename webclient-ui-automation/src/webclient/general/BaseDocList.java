package webclient.general;

import java.io.File;

import webclient.filters.Filters;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;
import widgets.ui5.Chart;
import widgets.ui5.ChartTypes;

public class BaseDocList {
	protected WebConnection conn;
	protected Logger log;
	private Locators locators;
	
	private Chart chart;
	private Filters filters;
	
	public BaseDocList(){
		conn = Global.conn;
		log = Global.log;
		locators = new Locators("General" + File.separator + "BaseDocList_mapping.xml", log);
		chart = new Chart();
		filters = new Filters();
	}
	
	
	public void clickCreate(){
		Button btnCreate = new Button("Create", locators);
		btnCreate.click();
		General.waitBusyIndicatorInvisible();
	}
		
	public void clickChartView(){
		Button btn = new Button("Chart View", locators);
		btn.click();
		General.waitBusyIndicatorInvisible();
	}
	
	public void clickTableView(){
		Button btn = new Button("Table View", locators);
		btn.click();
		General.waitBusyIndicatorInvisible();
	}
	
	public void clickChartLegend(){
		chart.clickLegend();
	}
	
	public void clickChartZoomIn(){
		chart.clickZoomIn();
	}
	
	public void clickChartZoomOut(){
		chart.clickZoomOut();
	}

	public void openChartFullScreen(){
		chart.openFullScreen();
	}
	
	public void closeFullScreen(){
		chart.closeFullScreen();
	}
	
	public void sortChart(String sortBy, String sortOrder){
		chart.sort(sortBy, sortOrder);
	}
	public void selectChartType(String chartType){
		chart.selectType(chartType);
	}
	
	public void compareChart(String chartType, String expectedFileName){
		chart.compare(chartType, expectedFileName);
	}
	
	public void checkChartShow(String chartType, boolean isNotEmpty){
		chart.checkChartShow(chartType, isNotEmpty);
	}
	
	public void checkLegendShow(boolean show){
		chart.checkLegendShow(show);
	}
	
	public void checkChartFullScreen(boolean fullScreen){
		chart.checkChartFullScreen(fullScreen);
	}
	
	public void checkChartFilterResult(String dateRange,boolean success){
		chart.checkChartFilterResult(dateRange, success);
	}
	
	public void checkHeatmapShowWarningMessage(String type,boolean showMessage){
		chart.checkHeatmapShowWarningMessage(type, showMessage);
	}
}
