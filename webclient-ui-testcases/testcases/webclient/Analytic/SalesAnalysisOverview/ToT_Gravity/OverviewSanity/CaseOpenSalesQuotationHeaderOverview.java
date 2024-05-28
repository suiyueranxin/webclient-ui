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

public class CaseOpenSalesQuotationHeaderOverview {
    
    @Test
    public void openSalesQuotationHeaderOverview() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesQuotationHeaderOverview);
        
        //part-1: test data preparation
        //title
        String overviewPageTitle = OverviewTitle.SalesQuotationHeaderOverview;
        String mchartPageTitle = MultiFunctionalChartTitle.SalesQuotationHeaderChart;
        
        //visible filter
        List<String> filters = Arrays.asList(
            Analysis.TextDocumentDate,
            Analysis.TextBusinessPartnerCode,
            Analysis.TextBusinessPartnerName,
            Analysis.TextBusinessPartnerType,
            Analysis.TextBusinessPartnerGroupName
        );
        //filter values
        //empty
        
        //cards' titles
        List<String> cards = Arrays.asList(
                AnalyticCardString.TitleAnnualQuotationVolumesLC,
                AnalyticCardString.TitleOpenQuotationValueLC,
                AnalyticCardString.TitleQuotationValue
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
        
        //check KPI value
        overview.compareCardKpi(2, "23", "M");
        
        //check cards
        overview.compareCardsTitle(cards);
        
        //check: navigate from card to related chart correctly
        overview.navigateToChart(0);
        mchart.comparePageTitle(mchartPageTitle);
        
        overview.navigateToHome();
    }
}
