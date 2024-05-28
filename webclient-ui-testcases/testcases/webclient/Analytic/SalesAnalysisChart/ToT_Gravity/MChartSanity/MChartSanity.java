package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;

public class MChartSanity extends BaseWebClientTest{
    @Test
    public void MChartSanity(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
          
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        //open each mchart page
        CaseOpenSalesAnalysisByRowsChart caseOpenMchart1 = new CaseOpenSalesAnalysisByRowsChart();
        caseOpenMchart1.openSalesAnalysisByRowsChart();
        CaseOpenSalesAnalysisByDocumentChart caseOpenMchart2 = new CaseOpenSalesAnalysisByDocumentChart();
        caseOpenMchart2.openSalesAnalysisByDocumentChart();
        CaseOpenCustomerBalancesChart caseOpenMchart3 = new CaseOpenCustomerBalancesChart();
        caseOpenMchart3.openCustomerBalancesChart();
        CaseOpenPurchaseAnalysisByRowsChart caseOpenMchart4 = new CaseOpenPurchaseAnalysisByRowsChart();
        caseOpenMchart4.openPurchaseAnalysisByRowsChart();
        CaseOpenPurchaseAnalysisByDocumentChart caseOpenMchart5 = new CaseOpenPurchaseAnalysisByDocumentChart();
        caseOpenMchart5.openPurchaseAnalysisByDocumentChart();
        CaseOpenVendorBalancesChart caseOpenMchart6 = new CaseOpenVendorBalancesChart();
        caseOpenMchart6.openVendorBalancesChart();
        CaseOpenInventoryStatusChart caseOpenMchart7 = new CaseOpenInventoryStatusChart();
        caseOpenMchart7.openInventoryStatusChart();
        CaseOpenInventoryTransactionsChart caseOpenMchart8 = new CaseOpenInventoryTransactionsChart();
        caseOpenMchart8.openInventoryTransactionsChart();
        CaseOpenFinancialAnalysisChart caseOpenMchart9 = new CaseOpenFinancialAnalysisChart();
        caseOpenMchart9.openFinancialAnalysisChart();
        CaseOpenSalesQuotationHeaderChart caseOpenMchart10 = new CaseOpenSalesQuotationHeaderChart();
        caseOpenMchart10.openSalesQuotationHeaderChart();
        CaseOpenSalesQuotationDetailChart caseOpenMchart11 = new CaseOpenSalesQuotationDetailChart();
        caseOpenMchart11.openSalesQuotationDetailChart();
        
        //take the first mchart page for generic function testing as below
        //change filters
        CaseMChartChangeFilters caseChangeFilters = new CaseMChartChangeFilters();
        caseChangeFilters.mchartChangeFilters();
        
        //switch chart type rules
        CaseMChartSwitchChartType caseSwitchChartType = new CaseMChartSwitchChartType();
        caseSwitchChartType.mchartSwitchChartTypeMappingRule();
        caseSwitchChartType.mchartSwitchChartTypeKeepHistory();
        
        //multiple and single selection
        CaseMChartMultipleAndSingleSelection caseMultipleAndSingleSelection = new CaseMChartMultipleAndSingleSelection();
        caseMultipleAndSingleSelection.mchartMultipleAndSingleSelection();
        
        //Warning
        CaseMChartWarning caseWarning = new CaseMChartWarning();
        caseWarning.mchartWarning();
        
        //chart interaction such as show/hide legend, zoom in/out, enter/exit fullscreen, show/hide details
        CaseMChartInteraction caseInteraction = new CaseMChartInteraction();
        caseInteraction.mchartInteraction();
        
        //sort by
        CaseMChartSortBy caseSortBy = new CaseMChartSortBy();
        caseSortBy.mchartSortByMappingRule();
        caseSortBy.mchartSortBySwitchToNotSorted();
        
        //variant generic such as save, save as, remove, switch
        CaseMChartVariantGeneric caseVariantGeneric = new CaseMChartVariantGeneric();
        caseVariantGeneric.mchartVariantGeneric();
        
        //set default variant
        CaseMChartSetDefaultVariant caseSetDefaultVariant = new CaseMChartSetDefaultVariant();
        caseSetDefaultVariant.mchartSetDefaultVariant();
        
        //pin variant
        CaseMChartPinVariant casePinVariant = new CaseMChartPinVariant();
        casePinVariant.mchartPinVariant();
    }
}
