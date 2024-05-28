package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import java.util.Arrays;
import java.util.HashMap;
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

public class CaseOpenInventoryTransactionsChart {
    
    @Test
    public void openInventoryTransactionsChart() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.InventoryTransactionsChart);
        
        //part-1: test data preparation
        //title
        String mchartPageTitle = MultiFunctionalChartTitle.InventoryTransactionsChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextPostingDate,
            Analysis.TextDocumentTypeDisplayName,
            Analysis.TextItemCode,
            Analysis.TextItemGroup,
            Analysis.TextBusinessPartnerCode,
            Analysis.TextBusinessPartnerGroupCode
        );
        
        //filter values
        HashMap<String,String> filterValues = new HashMap<> ();
        filterValues.put(Analysis.PostingDate, "- 11 Month:Begin...Month:End");
        filterValues.put(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
        
        //chart type
        String chartType = ChartTypes.line;
        
        //chart bindings
        String measures = "Inbound Inventory Value(LC),Outbound Inventory Value(LC)";
        String dimension1 = "Posting Year and Month";
        String dimension2 = "All";
        
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
        //check: filter values
        mchart.compareFiltersValue(filterValues);
        
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
