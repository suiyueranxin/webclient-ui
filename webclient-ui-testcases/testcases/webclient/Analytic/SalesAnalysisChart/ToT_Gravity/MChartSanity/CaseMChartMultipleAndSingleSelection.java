package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartMultipleAndSingleSelection {
    
    @Test
    public void mchartMultipleAndSingleSelection() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        List<String> bindings = Arrays.asList("Net Sales Amount (LC),Gross Profit (LC)", "Posting Year and Month", "All");
        List<String> bindings2 = Arrays.asList("Net Sales Amount (LC)","Posting Year and Month","Business Partner Name");
        List<String> bindings3 = Arrays.asList("Net Sales Amount (LC)", "Posting Year and Month", "All");
        String variantStandard = "Standard";
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        //check: xy
        //check: measures will remove to one if select one item in dimension2
        mchart.changeSecondDimension(bindings2.get(2));
        mchart.compareMeasureAndDimensionSelectors(bindings2.get(0), bindings2.get(1), bindings2.get(2));
        //check: dimension will be changed to all if select one more item in measures
        mchart.changeMeasures(bindings.get(0));
        mchart.compareMeasureAndDimensionSelectors(bindings.get(0), bindings.get(1), bindings.get(2));
        
        //check: table
        mchart.switchChartType(ChartTypes.table);
        //check: measures will remove to one if select one item in dimension2
        mchart.changeSecondDimension(bindings2.get(2));
        mchart.compareMeasureAndDimensionSelectors(bindings2.get(0), bindings2.get(1), bindings2.get(2));
        //check: dimension will be changed to all if select one more item in measures
        mchart.changeMeasures(bindings.get(0));
        mchart.compareMeasureAndDimensionSelectors(bindings.get(0), bindings.get(1), bindings.get(2));
        
        //check: pie
        mchart.switchChartType(ChartTypes.donut);
        //check: measures will remove to one if select one item in dimension2
        mchart.changeSecondDimension(bindings2.get(2));
        mchart.compareMeasureAndDimensionSelectors(bindings2.get(0), bindings2.get(2), "");
        //check: dimension will be changed to all if select one more item in measures
        mchart.changeMeasures(bindings.get(0));
        mchart.compareMeasureAndDimensionSelectors(bindings.get(0), bindings.get(2), "");
        
        //check: xy->heatmap
        //check: measure will remove to one
        mchart.switchVariant(variantStandard);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.compareMeasureAndDimensionSelectors(bindings3.get(0), bindings3.get(1), bindings3.get(2));
        
        mchart.navigateToHome();
    }
}
