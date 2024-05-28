package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;


import org.testng.annotations.Test;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartSetDefaultVariant {
    
    @Test
    public void mchartSetDefaultVariant() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        String chartType1 = ChartTypes.heatMap;
        String chartType2 = ChartTypes.pie;
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        String variantCustom = "TA def variant 1";
        String variantStandard = "Standard";
        String variantCustom2 = "TA def variant 2";
        
        //check: can save as default
        mchart.switchChartType(chartType1);
        mchart.saveAsVariant(variantCustom, true);
        mchart.compareVariant(variantCustom);
        
        //check: can apply this new default variant
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.compareVariant(variantCustom);
        mchart.compareChartType(chartType1);
        
        //check: can change other custom variant to default
        mchart.switchVariant(variantStandard);
        mchart.switchChartType(chartType2);
        mchart.saveAsVariant(variantCustom2, false);
        mchart.setDefaultVariant(variantCustom2);
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.compareVariant(variantCustom2);
        mchart.compareChartType(chartType2);
        
        //check: can change back to system default
        mchart.setDefaultVariant(variantStandard);
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.compareVariant(variantStandard);
        
        //clean data: remove all custom variants
        mchart.removeAllVariants();
        mchart.navigateToHome();
    }
}
