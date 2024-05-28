package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.OverviewTitle;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.home.Home;

public class CaseOverviewVariantGeneric {
    @Test
    public void overviewVariantGeneric() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        List<String> filterWhenFirstChange = Arrays.asList(Analysis.PostingDate,"01/01/2016...03/31/2016","1.3","M");
        List<String> filterWhenSecondChange = Arrays.asList(Analysis.DocumentTypeDisplayName, "A/R Invoice,Sales Order", "5.7", "M");
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        String variantCustom = "TA variant 1";
        String variantStandard = "Standard";
        String variantCustom2 = "TA variant 2";
        String variantCustom3 = "TA variant 3";
        
        //check: can save as 
        overview.changeDateFilterValue(filterWhenFirstChange.get(0), filterWhenFirstChange.get(1));
        overview.saveAsVariant(variantCustom, false);
        overview.compareVariant(variantCustom);
        //check: can open this new saved variant
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.hasVariant(variantCustom);
        overview.switchVariant(variantCustom);
        overview.compareCardKpi(0, filterWhenFirstChange.get(2), filterWhenFirstChange.get(3));
        
        //check: can save
        overview.selectEnumFilterValue(filterWhenSecondChange.get(0), filterWhenSecondChange.get(1));
        overview.saveCurrentVariant();
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.switchVariant(variantCustom);
        overview.compareCardKpi(0, filterWhenSecondChange.get(2), filterWhenSecondChange.get(3));
        
        //check: can switch to standard variant
        overview.switchVariant(variantStandard);
        overview.compareVariant(variantStandard);
        
        //check: remove custom variant
        overview.switchVariant(variantCustom);
        overview.saveAsVariant(variantCustom2, false);
        overview.removeVariant(variantCustom);
        overview.notHasVariant(variantCustom);
        
        //check: previous removed variant was removed when reopen page 
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.notHasVariant(variantCustom);
        
        //check: rename variant
        overview.switchVariant(variantCustom2);
        overview.renameVariant(variantCustom3);
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.notHasVariant(variantCustom2);
        overview.hasVariant(variantCustom3);
        
        //check: remove current variant, page will auto redirect to standard
        overview.switchVariant(variantCustom3);
        overview.removeVariant(variantCustom3);
        overview.compareVariant(variantStandard);
        
        overview.navigateToHome();
    }
}
