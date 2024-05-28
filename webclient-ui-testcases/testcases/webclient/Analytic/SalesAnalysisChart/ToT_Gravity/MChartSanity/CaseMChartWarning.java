package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;


import org.testng.annotations.Test;
import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.ChartWarningTypes;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartWarning {
    
    @Test
    public void mchartWarning() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        String measures = "Net Sales Amount (LC)";
        String dimension = "Posting Date";
        
        //part-2: start to test
        //check: remove all measures, show warning
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        mchart.unselectAllMeasures();
        mchart.checkWarning(ChartWarningTypes.NoFeed);
        
        //check: add one more item in measure, hide warning
        mchart.changeMeasures(measures);
        mchart.compareViewType(ViewType.Line);
        
        //check: select all in first dimension switch to heatmap, show warning
        mchart.changeFirstDimension("All");
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkWarning(ChartWarningTypes.NoFeed);
        
        //check: too much data
        mchart.clearFilter(Analysis.PostingDate);
        mchart.clearFilter(Analysis.DocumentTypeDisplayName);
        mchart.changeFirstDimension(dimension);
        mchart.checkWarning(ChartWarningTypes.TooMuchData);
        mchart.navigateToHome();
    }
}
