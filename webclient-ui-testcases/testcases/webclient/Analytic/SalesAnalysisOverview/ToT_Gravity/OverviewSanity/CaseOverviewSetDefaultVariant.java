package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.OverviewTitle;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.home.Home;

public class CaseOverviewSetDefaultVariant {
    @Test
    public void overviewSetDefaultVariant() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        List<String> filterWhenFirstChange = Arrays.asList(Analysis.PostingDate,"01/01/2016...03/31/2016","1.3","M");
        List<String> filterWhenSecondChange = Arrays.asList(Analysis.DocumentTypeDisplayName, "A/R Invoice,Sales Order", "5.7", "M");
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        String variantCustom = "TA def variant 1";
        String variantStandard = "Standard";
        String variantCustom2 = "TA def variant 2";
        
        //check: can save as default
        overview.changeDateFilterValue(filterWhenFirstChange.get(0), filterWhenFirstChange.get(1));
        overview.saveAsVariant(variantCustom, true);
        overview.compareVariant(variantCustom);
        
        //check: can apply this new default variant
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.compareVariant(variantCustom);
        overview.compareCardKpi(0, filterWhenFirstChange.get(2), filterWhenFirstChange.get(3));
        
        //check: can change other custom variant to default
        overview.switchVariant(variantStandard);
        overview.selectEnumFilterValue(filterWhenSecondChange.get(0), filterWhenSecondChange.get(1));
        overview.saveAsVariant(variantCustom2, false);
        overview.setDefaultVariant(variantCustom2);
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.compareVariant(variantCustom2);
        
        //check: can change back to system default
        overview.setDefaultVariant(variantStandard);
        overview.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        overview.compareVariant(variantStandard);
        
        //clean data: remove all custom variants
        overview.removeVariant(variantCustom);
        overview.removeVariant(variantCustom2);
        
        overview.navigateToHome();
    }
}
