package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;


public class OverviewSanity extends BaseWebClientTest{
    @Test
    public void OverviewSanity(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        //open each overview page
        CaseOpenSalesAnalysisByRowsOverview caseOverviewPage1 = new CaseOpenSalesAnalysisByRowsOverview();
        caseOverviewPage1.openSalesAnalysisByRowsOverview();
        
        CaseOpenSalesAnalysisByDocumentOverview caseOverviewPage2 = new CaseOpenSalesAnalysisByDocumentOverview();
        caseOverviewPage2.openSalesAnalysisByDocumentOverview();
        
        CaseOpenCustomerBalancesOverview caseOverviewPage3 = new CaseOpenCustomerBalancesOverview();
        caseOverviewPage3.openCustomerBalancesOverview();
        
        CaseOpenPurchaseAnalysisByRowsOverview caseOverviewPage4 = new CaseOpenPurchaseAnalysisByRowsOverview();
        caseOverviewPage4.openPurchaseAnalysisByRowsOverview();
        
        CaseOpenPurchaseAnalysisByDocumentOverview caseOverviewPage5 = new CaseOpenPurchaseAnalysisByDocumentOverview();
        caseOverviewPage5.openPurchaseAnalysisByDocumentOverview();
        
        CaseOpenVendorBalancesOverview caseOverviewPage6 = new CaseOpenVendorBalancesOverview();
        caseOverviewPage6.openVendorBalancesOverview();
        
        CaseOpenInventoryStatusOverview caseOverviewPage7 = new CaseOpenInventoryStatusOverview();
        caseOverviewPage7.openInventoryStatusOverview();
        
        CaseOpenInventoryTransactionsOverview caseOverviewPage8 = new CaseOpenInventoryTransactionsOverview();
        caseOverviewPage8.openInventoryTransactionsOverview();
        
        CaseOpenFinancialAnalysisOverview caseOverviewPage9 = new CaseOpenFinancialAnalysisOverview();
        caseOverviewPage9.openFinancialAnalysisOverview();
        
        CaseOpenSalesQuotationHeaderOverview caseOverviewPage10 = new CaseOpenSalesQuotationHeaderOverview();
        caseOverviewPage10.openSalesQuotationHeaderOverview();
        
        CaseOpenSalesQuotationDetailOverview caseOverviewPage11 = new CaseOpenSalesQuotationDetailOverview();
        caseOverviewPage11.openSalesQuotationDetailOverview();
        
        //take the first overview page for generic function testing as below
        //change filters
        CaseOverviewChangeFilters caseChangeFitlers = new CaseOverviewChangeFilters();
        caseChangeFitlers.overviewChangeFilters();
        
        //show details
        CaseOverviewShowDetailsInCard caseShowDetailsInCard = new CaseOverviewShowDetailsInCard();
        caseShowDetailsInCard.overviewShowDetailsInCard();
        
        //navigate to chart
        CaseOverviewNavigateToChart caseNavigateToChart = new CaseOverviewNavigateToChart();
        caseNavigateToChart.overviewNavigateToChart();
        
        //save as, update, remove and switch variant (variant crud)
        CaseOverviewVariantGeneric caseVariantGeneric = new CaseOverviewVariantGeneric();
        caseVariantGeneric.overviewVariantGeneric();
        
        //set default variant
        CaseOverviewSetDefaultVariant caseSetDefaultVariant = new CaseOverviewSetDefaultVariant();
        caseSetDefaultVariant.overviewSetDefaultVariant();
        
        //pin variant
        CaseOverviewPinVariant casePinVariant = new CaseOverviewPinVariant();
        casePinVariant.overviewPinVariant();
    }
}
