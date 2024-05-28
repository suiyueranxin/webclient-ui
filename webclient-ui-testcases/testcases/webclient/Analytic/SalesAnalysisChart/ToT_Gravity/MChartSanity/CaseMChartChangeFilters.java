package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;


import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartChangeFilters {
    
    @Test
    public void mchartChangeFilters() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        //It's hard to check data correct or not in chart, so just simple check. Otherwise, test filters work full in overview.
        List<String> dateFilter = Arrays.asList(Analysis.PostingDate,"01/01/2016...03/31/2016");
        String chartType = ChartTypes.line;
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        mchart.changeDateFilterValue(dateFilter.get(0), dateFilter.get(1));
        mchart.compareChartType(chartType);
        
        mchart.navigateToHome();
    }
}
