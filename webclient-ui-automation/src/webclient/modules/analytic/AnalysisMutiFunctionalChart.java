package webclient.modules.analytic;

import webclient.modules.analytic.BaseAnalysisChart;
import widgets.ui5.ChartTypes;
import widgets.ui5.MultiComboBox;
import widgets.ui5.ChartSortBy;
import widgets.ui5.ChartSortOrder;

public class AnalysisMutiFunctionalChart extends BaseAnalysisChart {
    
    //business action
    public void changeMeasures(String ...measures) {
        for (String measure : measures) {
            this.changeMeasure(measure);
        }
    };
    public void sortBy(String sortBy, String order) {};
    public void unselectAllMeasures() {
        MultiComboBox cMeasures = new MultiComboBox("Measures", this.locators);
        cMeasures.clear();
    };
    
    //check
    public void compareMeasures(String ...measures) {};
    public void checkSortBySettings(String sortBy, String order) {};
    public void checkChartDataSortBy(String sortBy, String order) {};
}
