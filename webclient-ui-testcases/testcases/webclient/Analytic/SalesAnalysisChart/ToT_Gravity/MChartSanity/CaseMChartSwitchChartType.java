package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import org.testng.annotations.Test;

import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class CaseMChartSwitchChartType {
    
    @Test
    public void mchartSwitchChartTypeMappingRule() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        String measures = "Net Sales Amount (LC)";
        String dimension1 = "Posting Year and Month";
        String dimension2 = "Business Partner Name";
        String variantLine = "variant line";
        String variantPie = "variant pie";
        String variantHeatmap = "variant heatmap";
        String variantTable = "variant table";
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        mchart.changeSecondDimension(dimension2);
        mchart.switchChartType(ChartTypes.line);
        mchart.saveAsVariant(variantLine, false);
        mchart.switchChartType(ChartTypes.pie);
        mchart.saveAsVariant(variantPie, false);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.saveAsVariant(variantHeatmap, false);
        mchart.switchChartType(ChartTypes.table);
        mchart.saveAsVariant(variantTable, false);
        
        //xy -> pie
        mchart.switchVariant(variantLine);
        mchart.switchChartType(ChartTypes.pie);
        mchart.compareMeasureAndDimensionSelectors(measures, dimension2, "");

        //xy -> heatmap
        mchart.switchVariant(variantTable);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);

        //pie -> xy
        mchart.switchVariant(variantPie);
        mchart.switchChartType(ChartTypes.stackedColumn);
        mchart.compareMeasureAndDimensionSelectors(measures, "All", dimension2);
        
        //pie -> heatmap
        mchart.switchVariant(variantPie);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.compareMeasureAndDimensionSelectors(measures, "", dimension2);
        
        //heatmap -> xy
        mchart.switchVariant(variantHeatmap);
        mchart.switchChartType(ChartTypes.table);
        mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);
        
        //heatmap -> pie
        mchart.switchVariant(variantHeatmap);
        mchart.switchChartType(ChartTypes.pie);
        mchart.compareMeasureAndDimensionSelectors(measures, dimension2, "");
        
        mchart.navigateToHome();
    }
    
    @Test
    public void mchartSwitchChartTypeKeepHistory() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        String measures = "Net Sales Amount (LC),Gross Profit (LC)";
        String dimension1 = "Posting Year and Month";
        String dimension2 = "All";
        String variantStandard = "Standard";
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        //keep two measures during switch chart type
        //xy->heatmap->xy
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.switchChartType(ChartTypes.table);
        mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);
        
        //keep first dimension during switch chart type
        //xy->pie->xy
        mchart.switchVariant(variantStandard);
        mchart.switchChartType(ChartTypes.pie);
        mchart.switchChartType(ChartTypes.bar);
        mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);
        
        mchart.navigateToHome();
    }
}
