package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.home.Home;
import widgets.ui5.ChartSortBy;
import widgets.ui5.ChartSortOrder;
import widgets.ui5.ChartTypes;

public class CaseOpenInventoryStatusChart {
    
    @Test
    public void openInventoryStatusChart() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.InventoryStatusChart);
        
        //part-1: test data preparation
        //title
        String mchartPageTitle = MultiFunctionalChartTitle.InventoryStatusChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextItemCode,
            Analysis.TextItemDescription,
            Analysis.TextItemGroup,
            Analysis.TextItemType,
            Analysis.TextUoMGroup
        );
        
        //filter values
        //empty
        
        //chart type
        String chartType = ChartTypes.line;
        
        //chart bindings
        String measures = "Available Quantity";
        String dimension1 = "Item Group";
        String dimension2 = "Warehouse Code";
        
        //legend
        boolean showLegend = true;
        
        //sort by
        String sortBy = ChartSortBy.notSorted;
        String sortOrder = ChartSortOrder.ascending;
        
        //variant
        String variant = "Standard";
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        
        //check: title
        mchart.comparePageTitle(mchartPageTitle);
        
        //check: variant
        mchart.compareVariant(variant);
        
        //check: filter
        mchart.compareFilters(filters);
        
        //check: chart type
        mchart.compareChartType(chartType);
        
        //check: bindings
        mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);
        
        //check: legend
        mchart.checkLegendShowHide(showLegend);
        
        //check: sort by
        mchart.checkSortBySettings(sortBy, sortOrder);
        mchart.checkChartDataSortBy(sortBy, sortOrder);
    }
}
