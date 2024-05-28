package webclient.modules.analytic;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import widgets.html.Select;
import widgets.ui5.ChartTypes;
import webclient.modules.analytic.ChartWarningTypes;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;

public class BaseAnalysisChart extends BaseAnalysisPage {
    
    public BaseAnalysisChart(String locatorXMLPath) {
        super(locatorXMLPath);
        // TODO Auto-generated constructor stub
    }
    
    public BaseAnalysisChart() {
        super("Analytic" + File.separator + "AnalyticPage_mapping.xml");
    }
    //business action
    //action:measure/dimension selector
    public void changeMeasure(String measure) {
        Select cMeasure = new Select("Measure", this.locators);
        cMeasure.select(measure);
    };
    public void changeFirstDimension(String firstDimension){
        Select cDimension = new Select("FirstDimension", this.locators);
        cDimension.select(firstDimension);
    };
    public void changeSecondDimension(String secondDimension){
        Select cDimension = new Select("SecondDimenison", this.locators);
        cDimension.select(secondDimension);
    };
    
    //action:toolbar
    public void showDetails(){};
    public void showHideLegend(){};
    public void zoomIn(){};
    public void zoomOut(){};
    public void enterExitFullScreen(){};
    public void switchChartType(String chartType){};
    public void changeSortBy(String sortBy, String sortOrder) {};
    //action:chart
    public void selectFirstDataPointOfChart(){};
    public void selectLegend(){};
    
    //check
    public void compareMeasure(String measure){};
    public void compareFirstDimension(String firstDimension){};
    public void compareSecondDimension(String secondDimension){};
    public void compareChartType(String chartType){}; //check chart icon and chart
    public void compareSelectedTotal(String total){};
    public void checkDetailsShowHide(Boolean show){};
    public void checkLegendShowHide(Boolean show){};
    public void checkDataLabelShowHide(Boolean show){};
    public void checkWarning(ChartWarningTypes type){};//No feed, Too much data
    public void checkNoData(){};
    public void checkChartInFullScreen(boolean in) {};
    
    //original functions
    public void checkChartShow(ChartTypes chartType, boolean isNotEmpty){
        String xpath = "//div[contains(@id,'RDR80002-67717e71.2db2.4375.b8cf.d479b1775a2e')]//*[name()='svg']";
        
        boolean isExistSvg = conn.isExistElement("xpath", xpath);
        if((!isNotEmpty && !isExistSvg) || (isNotEmpty && isExistSvg)){
            log.add(LogStatus.PASS, "Check Chart contents whether is not empty passed. actual: \"" +
                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
        }else{
            log.add(LogStatus.FAIL, "Check Chart contents whether is not empty failed. actual: \"" +
                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
            Assert.assertEquals(true, false,"Check Chart contents whether is not empty failed. actual: \"" +
                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
        }
    }
    
    public void compareViewType(ViewType viewType) {
        String viewXpath = "//div[contains(@id,'RDR80002-67717e71.2db2.4375.b8cf.d479b1775a2e')]";
        String chartXpath = viewXpath + "//*[name()='svg']//*[name()='title' and contains(@id,'UIComp')]";
        String tableXpath = viewXpath + "//div[@role='grid']";
        
        ViewType actualViewType = null;
        try {
            if (viewType.equals(ViewType.Table)) {
                actualViewType = conn.isExistElement("xpath", tableXpath) ? ViewType.Table : null;
            } else {
                WebElement chart = conn.findElement("xpath", chartXpath);
                String chartTitle = chart.getAttribute("innerHTML").trim();
                actualViewType = ViewType.getTypeFromTitle(chartTitle);
            }
            
            if (viewType.equals(actualViewType)) {
                log.add(LogStatus.PASS, "Check view type passed. actual: \"" +
                        actualViewType.toString() + "\", expected: \"" + viewType.toString() + "\".");
            } else {
                log.add(LogStatus.FAIL, "Check view type passed. actual: \"" +
                        actualViewType.toString() + "\", expected: \"" + viewType.toString() + "\".");
                Assert.assertEquals(true, false, "Check view type passed. actual: \"" +
                        actualViewType.toString() + "\", expected: \"" + viewType.toString() + "\".");
            }
        } catch(Exception ex){
            log.add(LogStatus.FAIL,"Check view type failed with: \"" + ex.toString() + "\".");
            Assert.assertEquals(true, false,"Check view type failed with: \"" + ex.toString() + "\".");
        }
    }
    
    public void checkViewShow(ViewType viewType, boolean isNotEmpty) {
        String viewXpath = "//div[contains(@id,'RDR80002-67717e71.2db2.4375.b8cf.d479b1775a2e')]";
        String nodataXpath = viewXpath + "//div[@class='ui5-viz-controls-viz-description-title'][text()='No data']";
        
        compareViewType(viewType);
        boolean actualIsNotEmpty = !conn.isExistElement("xpath", nodataXpath) || !conn.findElement("xpath", nodataXpath).isDisplayed();
        if (actualIsNotEmpty) {
            log.add(LogStatus.PASS, "Check view contents whether is not empty passed. actual: \"" +
                    Boolean.toString(actualIsNotEmpty) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
        } else {
            log.add(LogStatus.FAIL, "Check view contents whether is not empty failed. actual: \"" +
                    Boolean.toString(actualIsNotEmpty) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
            Assert.assertEquals(true, false,"Check Chart contents whether is not empty failed. actual: \"" +
                    Boolean.toString(actualIsNotEmpty) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
        }
    }
    
    /**
     * Compare measure and dimension selectors. For measures with multiple items, you can join items with "," (no whitespace),
     * such as:"Net Sales Amount (LC),Gross Profit (LC)"
     */
    public void compareMeasureAndDimensionSelectors(String measures, String dimension1, String dimension2) {
        String selectorXpath = "//div[@class='sapSuiteUiCommonsChartContainerToolBarArea']//div[@role='combobox']";
        String multiMeasureXpath = "(" + selectorXpath + ") [1]//div[@role='listitem'][contains(@class,'sapMToken')]";
        String measureXpath = "(" + selectorXpath + ") [1]//label[@class='sapMSltLabel']";
        String dimension1Xpath = "(" + selectorXpath + ") [2]//label[@class='sapMSltLabel']";
        String dimension2Xpath = "(" + selectorXpath + ") [3]//label[@class='sapMSltLabel']";
        
        try{
            //get measure selected values
            String actualMeasures = "";
            boolean isMultiMeasure = conn.isExistElement("xpath", multiMeasureXpath);
            if (isMultiMeasure) {
                List<WebElement> measureElements = conn.findElements("xpath", multiMeasureXpath);
                for (int spanIndex = 0; spanIndex < measureElements.size(); spanIndex++) {
                    actualMeasures += measureElements.get(spanIndex).getAttribute("title");
                    if (spanIndex != measureElements.size() - 1) {
                        actualMeasures += ",";
                    }
                }
            } else {
                WebElement measureElement = conn.findElement("xpath", measureXpath);
                actualMeasures = measureElement.getText();
            }
            
            //get dimension1 selected values
            String actualDimension1 = "";
            actualDimension1 = conn.findElement("xpath", dimension1Xpath).getText();
            
            //get dimension2(optional) 
            String actualDimension2 = "";
            if (conn.isExistElement("xpath", dimension2Xpath)) {
                actualDimension2 = conn.findElement("xpath", dimension2Xpath).getText();
            }
            
            //assertion measures and dimensions
            if (measures.equals(actualMeasures) && dimension1.equals(actualDimension1) && dimension2.equals(actualDimension2)) {
                log.add(LogStatus.PASS, "Compare measure and dimension selectors pass. actual: \"(" +
                        actualMeasures + ";" + actualDimension1 + ";" + actualDimension2 + ")\", expected: \"(" +
                        measures + ";" + dimension1 + ";" + dimension2 + ")\".");
            } else {
                log.add(LogStatus.FAIL, "Compare measure and dimension selectors failed with (measure;dimension1;dimension2). actual: \"(" +
                        actualMeasures + ";" + actualDimension1 + ";" + actualDimension2 + ")\", expected: \"(" +
                        measures + ";" + dimension1 + ";" + dimension2 + ")\".");
                Assert.assertEquals(true, false,"Compare measure and dimension selectors failed with (measure;dimension1;dimension2). actual: \"(" +
                        actualMeasures + ";" + actualDimension1 + ";" + actualDimension2 + ")\", expected: \"(" +
                        measures + ";" + dimension1 + ";" + dimension2 + ")\".");
            }
            
        } catch(Exception ex){
            log.add(LogStatus.FAIL, "Compare measure and dimension selectors failed with: " + ex.toString() + ".");
            Assert.assertEquals(true, false,"Compare measure and dimension selectors failed with: " + ex.toString() + ".");
        }
    }
    
    public void backToOverview() {
        String backBtnXpath = "//a[@id='backBtn']";
        try {
            WebElement backBtn = conn.findElement("xpath", backBtnXpath);
            backBtn.click();
        } catch(Exception ex){
            log.add(LogStatus.FAIL, "Failed to back to overview with: " + ex.toString());
            Assert.assertEquals(true, false, "Failed to back to overview with: " + ex.toString());
        }
    }
}
