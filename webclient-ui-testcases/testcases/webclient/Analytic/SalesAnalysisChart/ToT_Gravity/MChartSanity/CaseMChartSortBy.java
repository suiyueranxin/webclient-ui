package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import org.testng.annotations.Test;

import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartSortBy;
import widgets.ui5.ChartSortOrder;
import widgets.ui5.ChartTypes;

public class CaseMChartSortBy {
    
    @Test
    public void mchartSortByMappingRule() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
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
        
        //xy->pie
        //not sorted
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by x-axis
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.descending);
        //sort by y-axis
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.segment, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.descending);
        
        //xy->heatmap
        //not sorted
        mchart.switchVariant(variantLine);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by x-axis
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.xAxis, ChartSortOrder.descending);
        //sort by y-axis
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.yAxis, ChartSortOrder.descending);
        
        //xy->table
        //not sorted
        mchart.switchVariant(variantLine);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by x-axis
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.dimension, ChartSortOrder.descending);
        //sort by y-axis
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.measure, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.dimension2, ChartSortOrder.descending);
        
        //pie->xy
        //not sorted
        mchart.switchVariant(variantPie);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.descending);
        //sort by segment
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.segment, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.yAxis, ChartSortOrder.ascending);
        
        //pie->heatmap
        //not sorted
        mchart.switchVariant(variantPie);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.yAxis, ChartSortOrder.descending);
        //sort by segment
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.segment, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.ascending);
        
        //pie->table
        //not sorted
        mchart.switchVariant(variantPie);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.dimension2, ChartSortOrder.descending);
        //sort by segment
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.segment, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.measure, ChartSortOrder.ascending);
        
        //heatmap->xy
        //not sorted
        mchart.switchVariant(variantHeatmap);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by x-axis
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.xAxis, ChartSortOrder.descending);
        //sort by y-axis
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.yAxis, ChartSortOrder.descending);
        
        //heatmap->pie
        //not sorted
        mchart.switchVariant(variantHeatmap);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by x-axis
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by y-axis
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.segment, ChartSortOrder.descending);
        
        //heatmap->table
        //not sorted
        mchart.switchVariant(variantHeatmap);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by x-axis
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.dimension, ChartSortOrder.descending);
        //sort by y-axis
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.dimension2, ChartSortOrder.ascending);
        //sort by color
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.table);
        mchart.checkSortBySettings(ChartSortBy.measure, ChartSortOrder.descending);
        
        //table->xy
        //not sorted
        mchart.switchVariant(variantTable);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by dimension
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.xAxis, ChartSortOrder.descending);
        //sort by dimension 2
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension2, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.ascending);
        //sort by measure
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.measure, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.line);
        mchart.checkSortBySettings(ChartSortBy.yAxis, ChartSortOrder.descending);
        
        //table->pie
        //not sorted
        mchart.switchVariant(variantTable);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by dimension
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by dimension 2
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension2, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.ascending);
        //sort by measure
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.measure, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.pie);
        mchart.checkSortBySettings(ChartSortBy.segment, ChartSortOrder.descending);
        
        //table->heatmap
        //not sorted
        mchart.switchVariant(variantTable);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        //sort by dimension
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.xAxis, ChartSortOrder.descending);
        //sort by dimension 2
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension2, ChartSortOrder.ascending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.yAxis, ChartSortOrder.ascending);
        //sort by measure
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.measure, ChartSortOrder.descending);
        mchart.switchChartType(ChartTypes.heatMap);
        mchart.checkSortBySettings(ChartSortBy.color, ChartSortOrder.descending);
        
        mchart.removeAllVariants();
        mchart.navigateToHome();
    }
    
    @Test
    public void mchartSortBySwitchToNotSorted() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
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
        
        //xy
        mchart.switchVariant(variantLine);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.changeSecondDimension("All");
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.changeFirstDimension("All");
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.descending);
        mchart.unselectAllMeasures();
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        //pie
        mchart.switchVariant(variantPie);
        mchart.sortBy(ChartSortBy.color, ChartSortOrder.descending);
        mchart.changeFirstDimension("All");
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        mchart.sortBy(ChartSortBy.segment, ChartSortOrder.descending);
        mchart.unselectAllMeasures();
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        //heatmap
        mchart.switchVariant(variantHeatmap);
        mchart.sortBy(ChartSortBy.yAxis, ChartSortOrder.descending);
        mchart.changeSecondDimension("All");
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        //table
        mchart.switchVariant(variantTable);
        mchart.sortBy(ChartSortBy.dimension2, ChartSortOrder.descending);
        mchart.changeSecondDimension("All");
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        mchart.sortBy(ChartSortBy.dimension, ChartSortOrder.descending);
        mchart.changeFirstDimension("All");
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        mchart.sortBy(ChartSortBy.measure, ChartSortOrder.descending);
        mchart.unselectAllMeasures();
        mchart.checkSortBySettings(ChartSortBy.notSorted, ChartSortOrder.ascending);
        
        mchart.removeAllVariants();
        mchart.navigateToHome();
    }
}
