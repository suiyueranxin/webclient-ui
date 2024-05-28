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

public class CaseOpenFinancialAnalysisOverview {
    @Test
    public void openFinancialAnalysisOverview() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.FinancialAnalysisOverview);
        
        //part-1: test data preparation
        //title
        String overviewPageTitle = OverviewTitle.FinancialAnalysisOverview;
        String mchartPageTitle = MultiFunctionalChartTitle.FinancialAnalysisChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextAccountCode,
            Analysis.TextPostingDate,
            Analysis.TextProjectCode,
            Analysis.TextDocumentTypeDisplayName
        );
        //filter values
        HashMap<String,String> filterValues = new HashMap<> ();
        filterValues.put(Analysis.PostingDate, "- 11 Month:Begin...Month:End");
        filterValues.put(Analysis.DocumentTypeDisplayName, "A/R Invoice");

        //cards' titles
        List<String> cards = Arrays.asList(
                AnalyticCardString.TitleAccountBalanceByBranch,
                AnalyticCardString.TitleDebitVsCreditVsTotalAmount
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
        
        //check: navigate from card to related chart correctly
        overview.navigateToChart(0);
        mchart.comparePageTitle(mchartPageTitle);
        
        overview.navigateToHome();
    }
}
