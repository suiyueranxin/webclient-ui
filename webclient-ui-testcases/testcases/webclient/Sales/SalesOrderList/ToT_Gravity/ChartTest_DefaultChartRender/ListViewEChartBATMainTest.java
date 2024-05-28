package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultChartRender;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.bp.ActivityList;
import webclient.modules.bp.BusinessPartnerList;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class ListViewEChartBATMainTest extends BaseWebClientTest {
	
    @Test
    public void listViewEChartDefaultRenderTest(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        //logon.logon("manager", "manager", "US_I073369_3868_12_19");
        
    	
        SalesQuotationListEChartBATCase sqlt = new SalesQuotationListEChartBATCase();
        sqlt.testDefaultChartRender();
        
        SalesDraftListEChartBATCase sdlt = new SalesDraftListEChartBATCase();
        sdlt.testDefaultChartRender();
        
        ItemListEChartBATCase ilt = new ItemListEChartBATCase();
        ilt.testDefaultChartRender();
        
        BusinessPartnerListEChartBATCase bplt = new BusinessPartnerListEChartBATCase();
        bplt.testDefaultChartRender();
        
        ActivitiesListEChartBATCase alt = new ActivitiesListEChartBATCase();
        alt.testDefaultChartRender();
    }
}