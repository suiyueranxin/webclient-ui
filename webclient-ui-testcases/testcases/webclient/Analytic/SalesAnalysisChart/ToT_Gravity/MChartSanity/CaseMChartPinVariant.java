package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;


import org.testng.annotations.Test;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartPinVariant {
    
    @Test
    public void mchartPinVariant() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        String chartType1 = ChartTypes.heatMap;
        String chartType2 = ChartTypes.pie;
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        String tilePinStandard = "TA pinned standard";
        String variantCustom = "TA pin variant 1";
        String tilePinCustom = "TA pinned 1";
        String variantStandard = "Standard";
        String tilePinStandardStar = "TA pinned standard star";

        
        //check: can pin standard
        mchart.pinVariant(tilePinStandard, AnalyticPageString.AnalyticCategory);
        mchart.navigateToHome();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinStandard);
        mchart.compareVariant(tilePinStandard);
        
        //check: can pinned custom variant and reload from tile
        mchart.switchChartType(chartType1);
        mchart.saveAsVariant(variantCustom, false);
        mchart.pinVariant(tilePinCustom, AnalyticPageString.AnalyticCategory);
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinCustom);
        mchart.compareVariant(variantCustom);
        mchart.compareChartType(chartType1);
        
        //check: can pinned custom *
        mchart.switchVariant(variantStandard);
        mchart.switchChartType(chartType2);
        mchart.pinVariant(tilePinStandardStar, AnalyticPageString.AnalyticCategory);
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinStandardStar);
        mchart.compareVariant(variantStandard + " *");
        mchart.compareChartType(chartType2);
        
        //clean data: remove all custom variants
        mchart.removeVariant(variantCustom);
        mchart.navigateToHome();
    }
}
