package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import java.util.Arrays;
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

public class CaseMChartVariantGeneric {
    
    @Test
    public void mchartVariantGeneric() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        
        //part-1: test data preparation
        List<String> filterWhenFirstChange = Arrays.asList(Analysis.PostingDate,"01/01/2016...03/31/2016","1.3","M");
        String chartTypeHeatmap = ChartTypes.heatMap;
        String chartTypePie = ChartTypes.pie;
        String dimension = "Business Partner Name";
        
        //part-2: start to test
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        String variantCustom = "TA variant 1";
        String variantStandard = "Standard";
        String variantCustom2 = "TA variant 2";
        String variantCustom3 = "TA variant 3";
        
        //check: can save as 
        mchart.changeDateFilterValue(filterWhenFirstChange.get(0), filterWhenFirstChange.get(1));
        mchart.switchChartType(chartTypeHeatmap);
        mchart.changeSecondDimension(dimension);
        mchart.showHideLegend();
        mchart.sortBy(ChartSortBy.xAxis, ChartSortOrder.descending);
        mchart.saveAsVariant(variantCustom, false);
        mchart.compareVariant(variantCustom);
        
        //check: can open this new saved variant
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.hasVariant(variantCustom);
        mchart.switchVariant(variantCustom);
        mchart.compareFilterValue(filterWhenFirstChange.get(0), filterWhenFirstChange.get(1));
        mchart.compareChartType(chartTypeHeatmap);
        mchart.compareSecondDimension(dimension);
        mchart.checkLegendShowHide(false);
        mchart.checkSortBySettings(ChartSortBy.xAxis, ChartSortOrder.descending);
        
        //check: can save
        mchart.switchChartType(chartTypePie);
        mchart.showHideLegend();
        mchart.saveCurrentVariant();
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.switchVariant(variantCustom);
        mchart.compareChartType(chartTypePie);
        mchart.checkLegendShowHide(true);
        
        //check: can switch to standard variant
        mchart.switchVariant(variantStandard);
        mchart.compareVariant(variantStandard);
        
        //check: remove custom variant
        mchart.switchVariant(variantCustom);
        mchart.saveAsVariant(variantCustom2, false);
        mchart.removeVariant(variantCustom);
        mchart.notHasVariant(variantCustom);
        
        //check: previous removed variant was removed when reopen page 
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.notHasVariant(variantCustom);
        
        //check: rename variant
        mchart.switchVariant(variantCustom2);
        mchart.renameVariant(variantCustom3);
        mchart.reopenFromHomeTile(AnalyticPageString.AnalyticCategory + "->" + MultiFunctionalChartTitle.SalesAnalysisByRowsChart);
        mchart.notHasVariant(variantCustom2);
        mchart.hasVariant(variantCustom3);
        
        //check: remove current variant, page will auto redirect to standard
        mchart.switchVariant(variantCustom3);
        mchart.removeVariant(variantCustom3);
        mchart.compareVariant(variantStandard);
        
        mchart.removeAllVariants();
        mchart.navigateToHome();
    }
}
