package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import org.testng.annotations.Test;

import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartInteraction {
    @Test
    public void mchartInteraction() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        
        //part-2: start to test
        //check: show/hide legend
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        mchart.showHideLegend();
        mchart.checkLegendShowHide(false);
        mchart.showHideLegend();
        mchart.checkLegendShowHide(true);
        
        //check: zoom in/out
        mchart.zoomIn();
        mchart.zoomOut();
        
        //check: enter/exit fullscreen
        mchart.enterExitFullScreen();
        mchart.checkChartInFullScreen(true);
        mchart.enterExitFullScreen();
        mchart.checkChartInFullScreen(false);
        
        //check: show/hide details
        mchart.selectFirstDataPointOfChart();
        mchart.compareSelectedTotal("1234");
        mchart.showDetails();
        mchart.checkDetailsShowHide(true);
        mchart.switchChartType(ChartTypes.bar);
        mchart.checkDetailsShowHide(false);
    }
}
