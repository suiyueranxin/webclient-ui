package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.AnalyticCardString;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.analytic.MultiFunctionalChartTitle;
import webclient.modules.analytic.OverviewTitle;
import webclient.modules.home.Home;

public class CaseOpenSalesAnalysisByRowsOverview {
    
    @Test
    public void openSalesAnalysisByRowsOverview() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        //title
        String overviewPageTitle = OverviewTitle.SalesAnalysisByRowsOverview;
        String mchartPageTitle = MultiFunctionalChartTitle.SalesAnalysisByRowsChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextPostingDate,
            Analysis.TextDocumentTypeDisplayName,
            Analysis.TextBusinessPartnerCode,
            Analysis.TextBusinessPartnerGroupName,
            Analysis.TextItemCode,
            Analysis.TextItemGroup
        );
        //filter values
        HashMap<String,String> filterValues = new HashMap<> ();
        filterValues.put(Analysis.PostingDate, "- 3 Year:Begin...Month:End");
        filterValues.put(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        //cards' titles
        List<String> cards = Arrays.asList(
                AnalyticCardString.TitleSalesAmountByYear,
                AnalyticCardString.TitleSalesAndProfitByMonth,
                AnalyticCardString.TitleSalesAmountByQuarter,
                AnalyticCardString.TitleTop20ItemSales,
                AnalyticCardString.TitleSalesAmountBySalesEmployee,
                AnalyticCardString.TitleCountrySalesByMonth,
                AnalyticCardString.TitleTop10CustomerSales,
                AnalyticCardString.TitleItemGroupQuantitiesByWarehouse
        );
        //variant
        String variant = "Standard";
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        
        //check title
        overview.comparePageTitle(overviewPageTitle);
        
        //check variant
        overview.compareVariant(variant);
        
        //check filter
        overview.compareFilters(filters);
        //check filter values
        overview.compareFiltersValue(filterValues);
        
        //check cards
        overview.compareCardsTitle(cards);
        
        //check query result by KPI of some card
        overview.compareCardKpi(0, "2.4", "M");
        
        //check: navigate from card to related chart correctly
        overview.navigateToChart(0);
        mchart.comparePageTitle(mchartPageTitle);
        
        overview.navigateToHome();
    }
}
