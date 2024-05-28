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

public class CaseOpenCustomerBalancesChart {
    
    @Test
    public void openCustomerBalancesChart() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.CustomerBalancesChart);
        
        //part-1: test data preparation
        //title
        String mchartPageTitle = MultiFunctionalChartTitle.CustomerBalancesChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextBusinessPartnerCode,
            Analysis.TextBusinessPartnerName,
            Analysis.TextBusinessPartnerGroupName,
            Analysis.TextCurrencyCode,
            Analysis.TextCurrencyName
        );
        
        //filter values
        //empty
        
        //chart type
        String chartType = ChartTypes.line;
        
        //chart bindings
        String measures = "Account Reveivable Balance (In BP Currency)";
        String dimension1 = "Currency Code";
        String dimension2 = "Business Partner Group Name";
        
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
