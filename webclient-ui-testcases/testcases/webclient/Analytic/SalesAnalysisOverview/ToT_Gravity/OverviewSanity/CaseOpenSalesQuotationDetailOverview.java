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

public class CaseOpenSalesQuotationDetailOverview {
    
    @Test
    public void openSalesQuotationDetailOverview() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesQuotationDetailOverview);
        
        //part-1: test data preparation
        //title
        String overviewPageTitle = OverviewTitle.SalesQuotationDetailOverview;
        String mchartPageTitle = MultiFunctionalChartTitle.SalesQuotationDetailChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextDocumentDate,
            Analysis.TextBusinessPartnerName,
            Analysis.TextBusinessPartnerGroupName,
            Analysis.TextItemCode,
            Analysis.TextItemGroup
        );
        //filter values
        //empty
        
        //cards' titles
        List<String> cards = Arrays.asList(
                AnalyticCardString.TitleAnnualQuotedQuantities,
                AnalyticCardString.TitleQuoteValueLC,
                AnalyticCardString.TitleQuoteValueVsGrossProfitLC
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
        
        //check cards
        overview.compareCardsTitle(cards);
        
        //check: navigate from card to related chart correctly
        overview.navigateToChart(0);
        mchart.comparePageTitle(mchartPageTitle);
        
        overview.navigateToHome();
    }
}
