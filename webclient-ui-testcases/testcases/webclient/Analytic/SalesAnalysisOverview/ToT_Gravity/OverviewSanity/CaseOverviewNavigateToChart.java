package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;
import webclient.filters.FilterFields.Analysis;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.OverviewTitle;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.analytic.AnalysisMutiFunctionalChart;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;
import widgets.ui5.ChartSortBy;
import widgets.ui5.ChartSortOrder;

public class CaseOverviewNavigateToChart {
    @Test
    public void overviewNavigateToChart() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        int cardTotal = 8;
        List<ViewType> expectedViewTypes = Arrays.asList(
            ViewType.Line,
            ViewType.Line,
            ViewType.StackedColumn,
            ViewType.Table,
            ViewType.Donut,
            ViewType.HeatMap,
            ViewType.Table,
            ViewType.StackedBar
        );
        List<String> expectedMeasureAndDimensionSelector = Arrays.asList(
            "Net Sales Amount (LC);Posting Year;All",
            "Net Sales Amount (LC),Gross Profit (LC);Posting Year and Month;All",
            "Net Sales Amount (LC);Posting Year and Quarter;Business Partner Group Name",
            "Net Sales Amount (LC);Item Description & Code;All",
            "Net Sales Amount (LC);Sales Employee or Buyer Name",
            "Net Sales Amount (LC);Posting Year and Month;Bill-To Country",
            "Net Sales Amount (LC);Business Partner Name;All",
            "Quantity (In Inventory UoM);Item Group;Warehouse Name"
        ); 
       //filter values
        HashMap<String,String> filterValues = new HashMap<> ();
        filterValues.put(Analysis.PostingDate, "01/01/2016...03/31/2016");
        filterValues.put(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        //legend show/hide
        List<Boolean> expectedLegendShowHide = Arrays.asList(
            false,
            true,
            true,
            false,
            true,
            true,
            false,
            true
        );
        //sort by
        List<String> expectedSortBy = Arrays.asList(
            ChartSortBy.xAxis +","+ ChartSortOrder.ascending,
            ChartSortBy.xAxis +","+ ChartSortOrder.ascending,
            ChartSortBy.xAxis +","+ ChartSortOrder.ascending,
            ChartSortBy.measure +","+ ChartSortOrder.descending,
            ChartSortBy.notSorted +","+ ChartSortOrder.ascending,
            ChartSortBy.xAxis +","+ ChartSortOrder.ascending,
            ChartSortBy.measure +","+ ChartSortOrder.descending,
            ChartSortBy.xAxis +","+ ChartSortOrder.ascending
        );
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        overview.changeDateFilterValue(Analysis.PostingDate, "01/01/2016...03/31/2016");
        AnalysisMutiFunctionalChart mchart = new AnalysisMutiFunctionalChart();
        
        for (int cardIndex = 0; cardIndex < cardTotal; cardIndex++) {
            //naviage from card to chart
            overview.navigateToChart(cardIndex);
            
            //check: global filters
            mchart.compareFiltersValue(filterValues);
            
            //check: view type 
            mchart.checkViewShow(expectedViewTypes.get(cardIndex), true);
            
            //check: bindings, it means selected values of selector
            String[] expectedSelectedValues = expectedMeasureAndDimensionSelector.get(cardIndex).split(";");
            String measures = expectedSelectedValues[0];
            String dimension1 = expectedSelectedValues[1];
            String dimension2 = expectedSelectedValues.length > 2? expectedSelectedValues[2] : "";
            mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);
            
            //check: legend
            mchart.checkLegendShowHide(expectedLegendShowHide.get(cardIndex));
            
            //check: sort by
            String[] sortByInfo = expectedSortBy.get(cardIndex).split(",");
            mchart.checkSortBySettings(sortByInfo[0], sortByInfo[1]);
            mchart.checkChartDataSortBy(sortByInfo[0], sortByInfo[1]);
            
            // back to overview from chart
            mchart.backToOverview();
        }
        
        overview.navigateToHome();
    }
}
