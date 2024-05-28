package widgets.ui5;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;

public class Chart {
	private WebConnection conn;
	private Logger log; 
	private Locators loc;
	public Chart(){
		conn = Global.conn;
		log = Global.log;
		this.loc = new Locators("General" + File.separator + "Chart_mapping.xml", log);
	}
	
	public void clickLegend(){
		Button legend = new Button("Legend", loc);
		legend.click();
		
	}
	
	public void clickZoomIn(){
		Button btn = new Button("Zoom In", loc);
		btn.click();
	}
	
	public void clickZoomOut(){
		Button btn = new Button("Zoom Out", loc);
		btn.click();
	}
	
	public void openFullScreen(){
		Button btn = new Button("Open Full screen", loc);
		btn.click();
	}
	
	public void closeFullScreen(){
		Button btn = new Button("Close Full screen", loc);
		btn.click();
	}
	
	public void sort(String sortBy, String sortOrder){
		log.add(LogStatus.INFO, String.format("Sort Chart by \'{0}\' and \'{1}\'.", sortBy, sortOrder));
		Button btnSort = new Button("SortButton", loc);
		btnSort.click();
		Select selSortBy = new Select("Sort By List", loc);
		selSortBy.select(sortBy);
		Select selSortOrder = new Select("Sort Option List", loc);
		selSortOrder.select(sortOrder);
		
		
	}
	public void selectType(String chartType){
		
		try{
			log.add(LogStatus.INFO, "Select chart type to \"" + chartType + "\".");
			Button btnMore = new Button("More", loc);
			if(btnMore.isExist())
			{
				btnMore.click();
				General.sleep(1);
				Select sel = new Select(chartType, loc);
				sel.select(chartType);
			}else{
				Button btnChartType = new Button(chartType, "//li[@title='" + chartType + "']");
				btnChartType.click();
			}
		
			General.waitPageLoadComplete();
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Fail to select chart type with: " + ex.toString());
			Assert.assertEquals(true, false,"Fail to select chart type with: " + ex.toString());
		}
		
	}
	public void compare(String chartType, String expectedFileName){
		
		selectType(chartType);
		WebElement chart = conn.findElement("xpath", "//div[@class='sapVizFrame']");
		boolean isEqual = webuita.chart.Chart.compare(expectedFileName, chart);
		
		if(!isEqual){
			Global.log.add(LogStatus.FAIL, "Check Chart compare failed", Global.isStopRunAfterFail); 
		}else{
			log.add(LogStatus.PASS, "Pass to compare \"" + chartType.toString() + " Chart\" with expecte file: \"" + expectedFileName + "\".");
		}
	}
	public void checkChartShow(String chartType, boolean isNotEmpty){
		String xpath = "//*[name()='svg']";
		
		boolean isExistSvg = conn.isExistElement("xpath", xpath);
		if((!isNotEmpty && !isExistSvg) || (isNotEmpty && isExistSvg)){
			log.add(LogStatus.PASS, "Check Chart contents whether is not empty passed. actual: \"" +
					Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
		}else{
			Global.log.add(LogStatus.FAIL, "Check Chart contents whether is not empty failed", Global.isStopRunAfterFail); 
		}
	}
	
	public void checkLegendShow(boolean show){
		boolean flag ;
		String legendContentXpath = "//*[@class='v-content']"; 
		flag = conn.isExistElement("xpath", legendContentXpath);
		if((!flag && !show)||(flag && show)){
			log.add(LogStatus.PASS, "Check Chart legend show/hide passed. actual: \"" +
					Boolean.toString(flag) + "\", expected: \"" + Boolean.toString(show) + "\".");
		}else{
			Global.log.add(LogStatus.FAIL, "Check Chart legend show/hide failed.", Global.isStopRunAfterFail); 
		}
	}
	

	public void checkChartFullScreen(boolean fullScreen){
		boolean flag;
		String xpath = "//div[contains(@class,'analyticChartContainer') and contains(@class,'sapSuiteUiCommonsChartContainer')]" ;
		WebElement chartContainer = conn.findElement("xpath", xpath);
		WebElement parentNode = chartContainer.findElement(By.xpath("./.."));
		String idAttribute = parentNode.getAttribute("id");
		System.out.println("Id Attribute: " + idAttribute);
		
		flag = idAttribute.startsWith("id-");
		
		if((!flag && !fullScreen)||(flag && fullScreen)){
			log.add(LogStatus.PASS, "Check Chart full screen/not full screen passed. actual: \"" +
					Boolean.toString(flag) + "\", expected: \"" + Boolean.toString(fullScreen) + "\".");
		}else{
			Global.log.add(LogStatus.FAIL, "Check Chart full screen/not full screen failed.", Global.isStopRunAfterFail); 
		}
	}
	
	public void checkChartFilterResult(String dateRange,boolean success){
		String axisLabelXpath = "//*[@class='v-axis-label-wrapper']";
		String noDataXpath = "//div[@class='ui5-viz-controls-viz-description-title']";
		String noData = "No data";
		boolean flag = false;
		if(conn.isExistElement("xpath", axisLabelXpath)){
			List<WebElement> axisLabelTextNodes=conn.findElements("xpath", axisLabelXpath);
			for(WebElement e: axisLabelTextNodes) {
				String axisLabelText = e.getText();
				String startDateStr = dateRange.substring(0,dateRange.indexOf("..."));
				String endDateStr = dateRange.substring(dateRange.indexOf("...")+3);
				
				DateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");  
				Date date = null;  
				Date startDate = null;
				Date endDate = null;
				try {
					date = format1.parse(axisLabelText);
					startDate = format1.parse(startDateStr);
					endDate = format1.parse(endDateStr);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				System.out.println("flag1: " + date + "#### " + startDate + date.after(startDate));
				System.out.println("flag2: " + date + "#### " + endDate+ date.before(endDate));
				flag = date.after(startDate) && date.before(endDate);
				System.out.println("flag1 && flag2: " + flag);
			}
		}else{
			WebElement noDataDiv =  conn.findElement("xpath", noDataXpath);
			String noDataText = noDataDiv.getText();
			flag = noDataText.equals(noData);
		}
		
		if((!flag && !success)||(flag && success)){
			log.add(LogStatus.PASS, "Check chart filters passed. actual: \"" +
					Boolean.toString(flag) + "\", expected: \"" + Boolean.toString(success) + "\".");
		}else{
			Global.log.add(LogStatus.FAIL, "Check chart filters failed.", Global.isStopRunAfterFail);
		}
	}
	
	public void checkHeatmapShowWarningMessage(String type,boolean showMessage){
		String missDimText = "Select at least one value";
		String bigDatasetText = "The number of records being charted is too large. Please change the chart parameters and filters and try again.";
		boolean flag=false;
		String xpath = "//div[@class='ui5-viz-controls-viz-description-title']";
		List<WebElement> titleNodes = conn.findElements("xpath", xpath);
		WebElement messageNode = titleNodes.get(1);
		String message = messageNode.getText();
		System.out.println("Heatmap "+ type + " hint message: "  + message);
		if(type.equals("MissDimension")){
			flag = message.equals(missDimText);
		}else if(type.equals("BigDataSet")){
			flag = message.equals(bigDatasetText);
		}else{
			System.out.println("Heatmap is rendering as expected!");
		}
		System.out.println("The flag value is: " + flag);
		
		if((!flag && !showMessage)||(flag && showMessage)){
			log.add(LogStatus.PASS, "Check heatmap " + type +  " show message passed. actual: \"" +
					Boolean.toString(flag) + "\", expected: \"" + Boolean.toString(showMessage) + "\".");
		}else{
			Global.log.add(LogStatus.FAIL, "Check heatmap " + type +  " show message failed", Global.isStopRunAfterFail);
		}
	}
	
}
