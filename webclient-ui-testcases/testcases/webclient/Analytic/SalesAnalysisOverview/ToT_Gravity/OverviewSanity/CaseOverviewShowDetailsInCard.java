package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.OverviewTitle;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.home.Home;

public class CaseOverviewShowDetailsInCard {
    @Test
    public void overviewShowDetailsInCard() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        int dualLineCardIndex = 1;
        
        int[] cards = {0, 1, 2, 4, 5, 7};
        List<String> expectedSelectedTotal = Arrays.asList(
            "42,018.478;0", //selected total;selected datapoint index
            "595.270;0",
            "1,200.000;4",
            "37,428.478;0",
            "1,200.000;0",
            "1,200.000;1"
        );
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        for (int index=0; index<cards.length; index++) {
            System.out.println("amy: " + index);
            int cardIndex = cards[index];
            String[] selectedDp = expectedSelectedTotal.get(index).split(";");
            String selectedTotal = selectedDp[0];
            int dpIndex = Integer.parseInt(selectedDp[1]);
            overview.selectDataPointOfCard(cardIndex, dpIndex);
            overview.compareSelectedTotalInCard(cardIndex, selectedTotal);
            
            overview.showDetails(cardIndex);
            overview.checkDetailsShow(cardIndex);
            
            //check: no selected total
//            if(cardIndex == dualLineCardIndex) {
//                overview.selectDataPointOfCard(cardIndex, 7);
//                //check: for dual line chart, select another datapoint and check no sum total
//                overview.compareSelectedTotalInCard(cardIndex, ""); // for dual
//                
//                //check: for dual line chart, open details and check details content
//                overview.showDetails(cardIndex);
//                overview.checkDetailsShow(cardIndex);
//            }
            
            overview.hideDetails(cardIndex);
        }
    }
}
