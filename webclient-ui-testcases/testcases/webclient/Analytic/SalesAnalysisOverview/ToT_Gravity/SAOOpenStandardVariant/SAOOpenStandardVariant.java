package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.SAOOpenStandardVariant;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.Filters;
import webclient.filters.FilterFields.Analysis;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.CardTypes;
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.home.Home;

public class SAOOpenStandardVariant extends BaseWebClientTest {
    @Test
    public void openStandardVariant(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis Overview");
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        
        // check-point-1: check variant name is "Standard"
        Filters filter = new Filters();
        filter.compareDefaultViewName("Standard");
        
        // check-point-2: check filter settings
        filter.compareFilterCondition(Analysis.PostingDate, "Year:Begin...Today");
        filter.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
        // check-point-3: current 6 cards are not empty
        int cardTotal = 6;
        for (int cardIndex=0; cardIndex<cardTotal; cardIndex++) {
            if (cardIndex == 3) {
                overview.checkCardContentsShow(cardIndex, CardTypes.Table, true);
            } else {
                overview.checkCardContentsShow(cardIndex, CardTypes.Chart, true);
            }
        }
        
        // check-point-4: calculated value of kpi value of the first card is correct.
//        overview.compareKpiCardHeader(0, "42", "K");
    }
}
