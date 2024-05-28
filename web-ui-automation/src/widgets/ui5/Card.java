package widgets.ui5;

import java.io.File;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;

public class Card {
	private WebConnection conn;
	private Logger log; 
	private Locators loc;
	public Card(){
		conn = Global.conn;
		log = Global.log;
		this.loc = new Locators("General" + File.separator + "Card_mapping.xml", log);
	}
	
	
	public void checkNoData(){
		
	}
	
	public void open(){
		
	}
	
	public void checkChartType(int cardIndex, String chartType){
		String cardHeaderXpath = String.format("(//div[contains(@class,'card-header')])[%d]/../..", cardIndex);
		String xpath = cardHeaderXpath + "//*[name()='g' and @class='v-m-desc-title']//*[name()='title']";
		String title = ChartTypes.table;
		if(conn.isExistElement("xpath", xpath)){
			title = conn.getText("xpath", xpath, "");
			if(title.contains("Line")){
				title = ChartTypes.line;
			}
			if(title.contains("Donut")){
				title = ChartTypes.donut;
			}
			if(title.contains("Pie")){
				title = ChartTypes.pie;
			}
			if(title.contains("Heat Map")){
				title = ChartTypes.heatMap;
			}
			if(title.contains("Stacked Column")){
				title = ChartTypes.stackedColumn;
			}
			if(title.contains("Stacked Bar")){
				title = ChartTypes.stackedBar;
			}
		}
		General.compare(title, chartType, true, String.format("Compare card \"%d\" chart type.", cardIndex));
	}
	
	public void compareKpiNumber(int cardIndex, String expectedKpiNumber){
		String xpath = String.format("(//div[contains(@class,'card-header')])[%d]//span[contains(@class,'card-title')]//span", cardIndex);
		String actual = conn.getElementValue("xpath", xpath, "");
		General.compare(actual,expectedKpiNumber,true, String.format("Compare the \"%d\" card Kpi number", cardIndex));
	}
	/***
	 * Check card header text.
	 * @param cardIndex  Start from 1....
	 * @param expected	 Expected header text.
	 */
	public void checkHeader(int cardIndex, String expected){
		String kpiXpath = String.format("(//div[contains(@class,'card-header')])[%d]//div[contains(@id,'kpiNumberContent-value-scr')]", cardIndex);
		String kpiNumber = conn.getElementValue("xpath", kpiXpath, "");
		String indicatorXpath = String.format("(//div[contains(@class,'card-header')])[%d]//div[contains(@id,'kpiNumberContent-scale')]", cardIndex);
		String kpiIndicator = conn.getElementValue("xpath", indicatorXpath, "");
		String actual = kpiNumber + kpiIndicator;
		General.compare(actual,expected,true, String.format("Compare the \"%d\" card header", cardIndex));
	}
	
	public void selectDataPoint(int cardIndex, int dataPointIndex){
		String cardHeaderXpath = String.format("(//div[contains(@class,'card-header')])[%d]/../..", cardIndex);
		String xpath = cardHeaderXpath + String.format("//*[name()='g' and @data-id='%d']", dataPointIndex);
//		xpath = "(//div[contains(@class,'card-header')])[2]/../..//*[name()='g' and @data-id='2']";
		conn.clickElement("xpath", xpath, "");
	}
	
	public void compareDetails(int cardIndex, int dataPointIndex, String expectedDetails){
		String detailBtnXpath = String.format("(//div[contains(@class,'card-header')])[%d]/../..//button", cardIndex);
		selectDataPoint(cardIndex, dataPointIndex);
		conn.clickElement("xpath", detailBtnXpath, "");
		String cardHeaderXpath = String.format("(//div[contains(@class,'card-header')])[%d]/../..", cardIndex);
		String dataSumXpath = cardHeaderXpath + "//span[contains(@data-sap-ui,'dataset_sum')]";
		String actual = conn.getElementValue("xpath", dataSumXpath, "");
		General.compare(actual, expectedDetails, true, String.format("Compare card \"%d\", data point \"%d\".", cardIndex, dataPointIndex));
	}
}
