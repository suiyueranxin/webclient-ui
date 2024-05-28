package webclient.modules.analytic;

import widgets.ui5.ChartTypes;
import widgets.ui5.ChartSortBy;
import widgets.ui5.ChartSortOrder;

public class AnalysisOverviewDesigner extends BaseAnalysisOverview {
    
    public AnalysisOverviewDesigner(String locatorXMLPath) {
        super("locatorXMLPath");
    }
    //business action
    public void exitEditMode() {};
    
    //overview action
    public void addCard() {};
    public void editCard() {};
    public void removeCard() {};
    public void reorderCard() {};
    
    //card editor right panel action
    public void changeTitle() {};
    public void changeSubtitle() {};
    public void toggleKPI() {};
    public void setKPI(String value, String Description) {};
    public void toggleLegend() {};
    public void switchContentType(ChartTypes contentType) {};
    public void changeMeasures() {};
    public void changeMeasure() {};
    public void changeFirstDimension() {};
    public void changeSecondDimension() {};
    public void changeSortBy(ChartSortBy sortBy, ChartSortOrder order) {};
    public void changeDisplayNumber() {};
    //card editor preview action
    public void selectFirstDatapoint() {};
    public void showDetails() {};
    
    //check
    //check overview
    public void compareCardTotal(int cardTotal) {};
    public void compareCardTitle(int cardIndex, String cardTitle) {};
    public void compareCardSubTitle(int cardIndex, String cardSubTitle) {};
    public void compareCardKPI(int cardIndex, String kpiValue, String kpiScale) {};
    public void compareContentType(int cardIndex, ChartTypes contentType) {};
    public void checkDetailsShow(int cardIndex) {};
    public void checkLegendShow(int cardIndex) {};
    public void checkDataLabelShow(int cardIndex) {};
    
    //check card editor right panel
    public void compareTitle(String title) {};
    public void compareSubtitle(String subtitle) {};
    public void checkKPIOnOff(boolean on) {};
    public void checkLegendOnOff(boolean on) {};
    public void compareMeasures(String ...measures) {};
    public void compareMeasure(String measure){};
    public void compareFirstDimension(String firstDimension){};
    public void compareSecondDimension(String secondDimension){};
    public void checkSortBy(ChartSortBy sortBy, ChartSortOrder order) {};
    
    //check card preview
    public void compareContentType(ChartTypes contentType){}; //check chart icon and chart
    public void compareSelectedTotal(String total){};
    public void checkDetailsShowHide(Boolean show){};
    public void checkLegendShowHide(Boolean show){};
    public void checkDataLabelShowHide(Boolean show){};
    public void checkWarning(ChartWarningTypes type){};//Red box, big data
    public void checkNoData(){};
    public void checkDataSorted(ChartSortBy sortBy, ChartSortOrder order) {};
}
