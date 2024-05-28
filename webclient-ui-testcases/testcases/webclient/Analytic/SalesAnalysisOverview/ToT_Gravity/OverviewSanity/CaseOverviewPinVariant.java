package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.OverviewTitle;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.home.Home;

public class CaseOverviewPinVariant {
    @Test
    public void overviewPinVariant() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        List<String> filterWhenFirstChange = Arrays.asList(Analysis.PostingDate,"01/01/2016...03/31/2016","1.3","M");
//        List<String> filterWhenSecondChange = Arrays.asList(Analysis.DocumentTypeDisplayName, "A/R Invoice,Sales Order", "5.7", "M");
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        String tilePinStandard = "TA pinned standard";
        String variantCustom = "TA pin variant 1";
        String tilePinCustom = "TA pinned 1";
        String variantStandard = "Standard";
        String tilePinStandardStar = "TA pinned standard star";

        
        //check: can pin standard
        overview.pinVariant(tilePinStandard, AnalyticPageString.AnalyticCategory);
        overview.navigateToHome();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinStandard);
        overview.compareVariant(tilePinStandard);
        
        //check: can pinned custom variant and reload from tile
        overview.changeDateFilterValue(filterWhenFirstChange.get(0), filterWhenFirstChange.get(1));
        overview.saveAsVariant(variantCustom, false);
        overview.pinVariant(tilePinCustom, AnalyticPageString.AnalyticCategory);
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinCustom);
        overview.compareVariant(variantCustom);
        overview.compareCardKpi(0, filterWhenFirstChange.get(2), filterWhenFirstChange.get(3));
        
        //check: can pinned custom *
        overview.switchVariant(variantStandard);
        overview.changeDateFilterValue(filterWhenFirstChange.get(0), filterWhenFirstChange.get(1));
        overview.pinVariant(tilePinStandardStar, AnalyticPageString.AnalyticCategory);
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinStandardStar);
        overview.compareVariant(variantStandard + " *");
        
        //check: update related variant in page, tile will be update when reopen tile (
        //TODO: BUG: SBO100-7749
//        overview.switchVariant(variantCustom);
//        overview.selectEnumFilterValue(filterWhenSecondChange.get(0), filterWhenSecondChange.get(1));
//        overview.saveCurrentVariant();
//        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinCustom);
//        overview.compareVariant(variantCustom);
//        overview.compareCardKpi(0, filterWhenSecondChange.get(2), filterWhenSecondChange.get(3));
        
        //check: remove related variant in page, tile will be direct to standard when reopen tile
        //TODO: BUG: SBO100-7744
//        overview.removeFilter(variantCustom);
//        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + tilePinCustom);
//        overview.compareVariant(variantStandard);
        
        //clean data: remove all custom variants
        overview.removeVariant(variantCustom);
        
        overview.navigateToHome();
    }
}
