package webclient.modules.analytic;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.util.HashMap;
import widgets.ui5.ChartTypes;
import webclient.filters.Filters;
import webclient.general.General;
import widgets.ui5.Button;
import widgets.ui5.CardTypes;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class BaseAnalysisOverview extends BaseAnalysisPage {
    public BaseAnalysisOverview(String locatorXMLPath) {
        super(locatorXMLPath);
        // TODO Auto-generated constructor stub
    }
    
    public BaseAnalysisOverview() {
        super("Analytic" + File.separator + "AnalyticPage_mapping.xml");
    }
    
    //business action
    public void enterEditMode() {};
    public void selectDataPointOfCard(int cardIndex, int dataPointIndex) {
        Item cardPath = this.locators.getItem("Card");
        Item dpPath = this.locators.getItem("DataPoint");
        String thisXDpPath = "("+cardPath.getByValue()+")["+ (cardIndex+1) +"]"+dpPath.getByValue()+"[@data-id='" + dataPointIndex + "']";
        //conn.clickInSVG("xpath", thisXDpPath, "Click one data point in card");
    };
    public void showDetails(int cardIndex) {
        Item cardPath = this.locators.getItem("Card");
        Item detailsPath = this.locators.getItem("Details");
        String thisDetailsPath = "("+cardPath.getByValue()+")["+ (cardIndex+1) +"]"+detailsPath.getByValue();
        Button btn = new Button("Details", thisDetailsPath);
        btn.click();
    };
    public void hideDetails(int cardIndex) {
        Item cardPath = this.locators.getItem("Card");
        conn.clickElement(cardPath.getByType(), cardPath.getByValue(), "Hide details");
    };
    public void navigateToChart(int cardIndex) {
        try{
            String cardHeaderXpath = "//div[contains(@class,'card-header')]";
            List<WebElement> els = conn.findElements("xpath", cardHeaderXpath);
            
            if(null != els && els.size() > cardIndex){
                els.get(cardIndex).click();
                log.add(LogStatus.INFO, "Open card \"" + Integer.toString(cardIndex) + "\".");
                General.waitRefreshComplete();
            }else{
                log.add(LogStatus.FAIL, "Card \"" + Integer.toString(cardIndex) + "\" doesn't show.");
                Assert.assertEquals(true, false,"Card \"" + Integer.toString(cardIndex) + "\" doesn't show.");
            }
        }catch(Exception ex){
            log.add(LogStatus.FAIL, "Failed to open card \"" + Integer.toString(cardIndex) + "\" with: " + ex.toString());
            Assert.assertEquals(true, false, "Failed to open card \"" + Integer.toString(cardIndex) + "\" with: " + ex.toString());
        }
    };
    
    //check
    public void compareCardTotal(int cardTotal) {
        Item domPath = this.locators.getItem("Card");
        List<WebElement> cards = conn.findElements(domPath.getByType(), domPath.getByValue());
        String actual = String.valueOf(cards.size());
        String expected = String.valueOf(cardTotal);
        General.compare(actual, expected, true, "Compare Total of Cards");
    };
    public void compareCardsTitle(List<String> cardTitles) {
        Item domPath = this.locators.getItem("CardTitle");
        List<WebElement> cards = conn.findElements(domPath.getByType(), domPath.getByValue());
        String actualTitles = "";
        if (cards.size() == cardTitles.size()) {
            for (int index=0 ; index<cards.size(); index++) {
                actualTitles += cards.get(index).getText();
                if (index < cards.size()-1) {
                    actualTitles += ",";
                }
            }
        }
        String expectedTitles = String.join(",", cardTitles);
        General.compare(actualTitles, expectedTitles, true, "Compare Cards Title");
    };
    public void compareCardTitle(int cardIndex, String cardTitle) {};
    public void compareCardSubtitle(int cardIndex, String cardSubTitle) {};
    public void compareCardKpi(int cardIndex, String kpiNumber, String kpiScale) {
        String cardChartXpath = "//div[@class='card fixFlexVertical sapUiFixFlex']";
        String kpiNumberXpath = "//div[contains(@id,'kpiNumberContent-value-scr')]";
        String kpiScaleXpath = "//div[contains(@id,'kpiNumberContent-scale')]";
        try{
            if(conn.isExistElement("xpath", cardChartXpath)){
                List<WebElement> charts = conn.findElements("xpath", cardChartXpath);
                WebElement eTemp = charts.get(cardIndex);
                
                
                if(null != kpiNumber){
                    WebElement eNumber = eTemp.findElement(By.xpath(kpiNumberXpath));

                    String number = eNumber.getText();
                    if(kpiNumber.equals(number)){
                        log.add(LogStatus.PASS,"Compare Kpi Number passed, actual: \"" + number +
                                "\", expected: \"" + kpiNumber + "\".");
                    }else{
                        log.add(LogStatus.FAIL,"Compare Kpi Number failed, actual: \"" + number +
                                "\", expected: \"" + kpiNumber + "\".");
                        Assert.assertEquals(true, false,"Compare Kpi Number failed, actual: \"" + number +
                                "\", expected: \"" + kpiNumber + "\".");
                    }
                }
                
                if(null != kpiScale){
                    WebElement eScale = eTemp.findElement(By.xpath(kpiScaleXpath));
                    
                    String scale = eScale.getText();
                    if(kpiScale.equals(scale)){
                        log.add(LogStatus.PASS,"Compare Kpi Scale passed, actual: \"" + scale +
                                "\", expected: \"" + kpiScale + "\".");
                        
                    }else{
                        log.add(LogStatus.FAIL,"Compare Kpi Scale failed, actual: \"" + scale +
                                "\", expected: \"" + kpiScale + "\".");
                        Assert.assertEquals(true, false,"Compare Kpi Scale failed, actual: \"" + scale +
                                "\", expected: \"" + kpiScale + "\".");
                    }
                        
                }
                
                
            }
        }catch(Exception ex){
            log.add(LogStatus.FAIL,"Compare Kpi Card Header failed with: \"" + ex.toString() + "\".");
            Assert.assertEquals(true, false,"Compare Kpi Card Header failed with: \"" + ex.toString() + "\".");
        }
    };
    public void compareContentType(int cardIndex, String contentType) {
        
    };
    public void compareSelectedTotalInCard(int cardIndex, String total) {
        Item cardPath = this.locators.getItem("Card");
        Item totalPath = this.locators.getItem("SelectedTotal");
        String thisTotalPath = "("+cardPath.getByValue()+")["+ (cardIndex+1) +"]"+totalPath.getByValue();
        String actualTotal = "";
        if (conn.isExistElement(totalPath.getByType(), thisTotalPath)) {
            actualTotal = conn.findElement(totalPath.getByType(), thisTotalPath).getText().split(": ")[1];
        }
        General.compare(actualTotal, total, true, "Compare Selected Total In Card");
    };
    public void checkCardHasContent(int cardIndex, CardTypes cardType, boolean hasContent) {
        String cardXpath = "//div[contains(@id,'67717e71.2db2.4375.b8cf.d479b1775a2e-scroll')]//div[contains(@id,'container')]/div";
        
        try{
            if(conn.isExistElement("xpath", cardXpath)){
                List<WebElement> cards = conn.findElements("xpath", cardXpath);
                WebElement e = cards.get(cardIndex);
                switch(cardType){
                    case Chart:
                        boolean isExistSvg = conn.isExistElement(e, "xpath", "//*[name()='svg']");
                        if((!hasContent && !isExistSvg) || (hasContent && isExistSvg)){
                            log.add(LogStatus.PASS, "Check Card contents whether is not empty passed. actual: \"" +
                                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(hasContent) + "\".");
                        }else{
                            log.add(LogStatus.FAIL, "Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(hasContent) + "\".");
                            Assert.assertEquals(true, false,"Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(hasContent) + "\".");
                        }
                        break;
                    case Table:
                        String value = conn.getElementValue("xpath", "(//tbody//tr//td)[1]//bdi", "");
                        boolean isTableEmpty = false;
                        if(null == value || value.equals("")){
                            isTableEmpty = true;
                        }
                        
                        if((!hasContent && isTableEmpty) || (hasContent && !isTableEmpty)){
                            log.add(LogStatus.PASS, "Check Card contents whether is not empty passed. actual: \"" +
                                    Boolean.toString(!isTableEmpty) + "\", expected: \"" + Boolean.toString(hasContent) + "\".");
                        }else{
                            log.add(LogStatus.FAIL, "Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(!isTableEmpty) + "\", expected: \"" + Boolean.toString(hasContent) + "\".");
                            Assert.assertEquals(true, false,"Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(!isTableEmpty) + "\", expected: \"" + Boolean.toString(hasContent) + "\".");
                        }
                        break;
                        
                }
        
            }else{
                log.add(LogStatus.FAIL, "Card contents are not shown correctly.");
                Assert.assertEquals(true, false,"Card contents are not shown correctly..");
            }
    
        }catch(Exception ex){
            log.add(LogStatus.FAIL, "Check Card contents whether is empty failed with: \"" +
                    ex.toString() + "\".");
            Assert.assertEquals(true, false,"Check Card contents whether is empty failed with: \"" +
                    ex.toString() + "\".");
        }
    }
    public void checkDetailsShow(int cardIndex) {
        Item detailsDlg = this.locators.getItem("DetailsDlg");
        try {
        if (conn.isExistElement(detailsDlg.getByType(), detailsDlg.getByValue())) {
            log.add(LogStatus.PASS,"Details show");
        }}catch(Exception ex){
            log.add(LogStatus.FAIL,"Details should show: \"" + ex.toString() + "\".");
            Assert.assertEquals(true, false,"Details show failed with: \"" + ex.toString() + "\".");
        }
        
    };
    public void checkLegendShow(int cardIndex, boolean show) {
        
    };
    public void checkDataLabelShow(int cardIndex, boolean show) {
        
    }
    
    //original function
    //Amy:move to compareCardKpi
    public void compareKpiCardHeader(int indexCard, String kpiNumber, String kpiScale){
        String cardChartXpath = "//div[@class='card fixFlexVertical sapUiFixFlex']";
        String kpiNumberXpath = "//div[contains(@id,'kpiNumberContent-value-scr')]";
        String kpiScaleXpath = "//div[contains(@id,'kpiNumberContent-scale')]";
        try{
            if(conn.isExistElement("xpath", cardChartXpath)){
                List<WebElement> charts = conn.findElements("xpath", cardChartXpath);
                WebElement eTemp = charts.get(indexCard);
                
                
                if(null != kpiNumber){
                    WebElement eNumber = eTemp.findElement(By.xpath(kpiNumberXpath));

                    String number = eNumber.getText();
                    if(kpiNumber.equals(number)){
                        log.add(LogStatus.PASS,"Compare Kpi Number passed, actual: \"" + number +
                                "\", expected: \"" + kpiNumber + "\".");
                    }else{
                        log.add(LogStatus.FAIL,"Compare Kpi Number failed, actual: \"" + number +
                                "\", expected: \"" + kpiNumber + "\".");
                        Assert.assertEquals(true, false,"Compare Kpi Number failed, actual: \"" + number +
                                "\", expected: \"" + kpiNumber + "\".");
                    }
                }
                
                if(null != kpiScale){
                    WebElement eScale = eTemp.findElement(By.xpath(kpiScaleXpath));
                    
                    String scale = eScale.getText();
                    if(kpiScale.equals(scale)){
                        log.add(LogStatus.PASS,"Compare Kpi Scale passed, actual: \"" + scale +
                                "\", expected: \"" + kpiScale + "\".");
                        
                    }else{
                        log.add(LogStatus.FAIL,"Compare Kpi Scale failed, actual: \"" + scale +
                                "\", expected: \"" + kpiScale + "\".");
                        Assert.assertEquals(true, false,"Compare Kpi Scale failed, actual: \"" + scale +
                                "\", expected: \"" + kpiScale + "\".");
                    }
                        
                }
                
                
            }
        }catch(Exception ex){
            log.add(LogStatus.FAIL,"Compare Kpi Card Header failed with: \"" + ex.toString() + "\".");
            Assert.assertEquals(true, false,"Compare Kpi Card Header failed with: \"" + ex.toString() + "\".");
        }
    }
    
    //Amy: moved to checkCardHasContent
    public void checkCardContentsShow(int indexCard, CardTypes type, boolean isNotEmpty){
        
        String cardXpath = "//div[contains(@id,'67717e71.2db2.4375.b8cf.d479b1775a2e-scroll')]//div[contains(@id,'container')]/div";

        try{
            if(conn.isExistElement("xpath", cardXpath)){
                List<WebElement> cards = conn.findElements("xpath", cardXpath);
                WebElement e = cards.get(indexCard);
                switch(type){
                    case Chart:
                        boolean isExistSvg = conn.isExistElement(e, "xpath", "//*[name()='svg']");
                        if((!isNotEmpty && !isExistSvg) || (isNotEmpty && isExistSvg)){
                            log.add(LogStatus.PASS, "Check Card contents whether is not empty passed. actual: \"" +
                                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
                        }else{
                            log.add(LogStatus.FAIL, "Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
                            Assert.assertEquals(true, false,"Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(isExistSvg) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
                        }
                        break;
                    case Table:
                        String value = conn.getElementValue("xpath", "(//tbody//tr//td)[1]//bdi", "");
                        boolean isTableEmpty = false;
                        if(null == value || value.equals("")){
                            isTableEmpty = true;
                        }
                        
                        if((!isNotEmpty && isTableEmpty) || (isNotEmpty && !isTableEmpty)){
                            log.add(LogStatus.PASS, "Check Card contents whether is not empty passed. actual: \"" +
                                    Boolean.toString(!isTableEmpty) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
                        }else{
                            log.add(LogStatus.FAIL, "Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(!isTableEmpty) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
                            Assert.assertEquals(true, false,"Check Card contents whether is not empty failed. actual: \"" +
                                    Boolean.toString(!isTableEmpty) + "\", expected: \"" + Boolean.toString(isNotEmpty) + "\".");
                        }
                        break;
                        
                }
        
            }else{
                log.add(LogStatus.FAIL, "Card contents are not shown correctly.");
                Assert.assertEquals(true, false,"Card contents are not shown correctly..");
            }
    
        }catch(Exception ex){
            log.add(LogStatus.FAIL, "Check Card contents whether is empty failed with: \"" +
                    ex.toString() + "\".");
            Assert.assertEquals(true, false,"Check Card contents whether is empty failed with: \"" +
                    ex.toString() + "\".");
        }
    }
    
    //Amy:move to navigateToChart
    public void openCard(int indexCard){
        try{
            String cardHeaderXpath = "//div[contains(@class,'card-header')]";
            List<WebElement> els = conn.findElements("xpath", cardHeaderXpath);
            
            if(null != els && els.size() > indexCard){
                els.get(indexCard).click();
                log.add(LogStatus.INFO, "Open card \"" + Integer.toString(indexCard) + "\".");
                General.waitRefreshComplete();
            }else{
                log.add(LogStatus.FAIL, "Card \"" + Integer.toString(indexCard) + "\" doesn't show.");
                Assert.assertEquals(true, false,"Card \"" + Integer.toString(indexCard) + "\" doesn't show.");
            }
        }catch(Exception ex){
            log.add(LogStatus.FAIL, "Failed to open card \"" + Integer.toString(indexCard) + "\" with: " + ex.toString());
            Assert.assertEquals(true, false, "Failed to open card \"" + Integer.toString(indexCard) + "\" with: " + ex.toString());
        }
    }
    
    //Amy:should remove from overview
    /**
     * Compare chart container's dimensions. you can join more values with ','.
     * such as:"Gross Profit (LC), NetSales Amount (SC)"
     */
    public void compareDimensions(String dim_01, String dim_02, String dim_03){
        try{
            String expectedValue = "";
            String actualValue = "";
            
            Map<String, String> dims = new HashMap<>();
            dims.put("dim_01", dim_01);
            dims.put("dim_02", dim_02);
            dims.put("dim_03", dim_03);
            for(int i = 0; i < 3; i++){
                String xpathCont = "//div[contains(@id,'chartContainer-" +  Integer.toString(i) + "')]";
                String xpathSpan = xpathCont + "//span[@class='sapMTokenText']";
                String xpathLabel = xpathCont + "//label[@class='sapMSltLabel']";
                expectedValue = dims.get("dim_0" + Integer.toString(i+1));
                if(i == 0){
                    
                    List<WebElement> els = conn.findElements("xpath", xpathSpan);
                    if(null != els && els.size() >= 1){
                        for(int j = 0; j < els.size(); j++){
                            if(actualValue.equals("")){
                                actualValue = els.get(j).getText();
                            }else{
                                actualValue += "," + els.get(j).getText();
                            }
                        }
                        
                    }else{
                        actualValue = "";
                    }
                }else{
                    actualValue = conn.getElementValue("xpath", xpathLabel, "");
                }
                if(!expectedValue.equals(actualValue)){
                    log.add(LogStatus.FAIL, "Failed to compare dimension \"" + Integer.toString(i) + "\", actual: \""
                            + actualValue + "\", expected: \"" + expectedValue + "\".");
                    Assert.assertEquals(true, false,"Failed to compare dimension \"" + Integer.toString(i) + "\", actual: \""
                            + actualValue + "\", expected: \"" + expectedValue + "\".");
                }else{
                    log.add(LogStatus.PASS, "Compare dimension \"" + Integer.toString(i) + "\", actual: \""
                            + actualValue + "\", expected: \"" + expectedValue + "\" pass.");
                }
                
            }
            
        }catch(Exception ex){
            log.add(LogStatus.FAIL, "Failed to compare dimension with: " + ex.toString() + ".");
            Assert.assertEquals(true, false,"Failed to compare dimension with: " + ex.toString() + ".");
        }
        
    }
}
