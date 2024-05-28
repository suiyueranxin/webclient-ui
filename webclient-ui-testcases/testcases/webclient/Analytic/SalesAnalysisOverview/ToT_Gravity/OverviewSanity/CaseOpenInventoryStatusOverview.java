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

public class CaseOpenInventoryStatusOverview {
    @Test
    public void openInventoryStatusOverview() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.InventoryStatusOverview);
        
        //part-1: test data preparation
        //title
        String overviewPageTitle = OverviewTitle.InventoryStatusOverview;
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

        //cards' titles
        List<String> cards = Arrays.asList(
                AnalyticCardString.TitleInStockQuantityByWarehouse,
                AnalyticCardString.TitleInStockValue,
                AnalyticCardString.TitleInStockVsAvailableQuantity,
                AnalyticCardString.TitleCommittedItemQuantity
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
