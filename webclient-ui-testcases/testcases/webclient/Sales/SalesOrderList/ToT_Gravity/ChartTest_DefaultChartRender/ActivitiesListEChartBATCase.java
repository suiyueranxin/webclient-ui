package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultChartRender;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.bp.ActivityList;
import webclient.modules.home.Home;
import widgets.ui5.ChartTypes;

public class ActivitiesListEChartBATCase extends BaseWebClientTest{
	
	@Test
    public void testDefaultChartRender(){
    	Home home = new Home();
    	home.navigateToFromTiles("Business Partners->Activities");
        ActivityList al = new ActivityList();
        al.clickChartView();
        al.checkChartShow(ChartTypes.line, true);
        home.backHome();
    }

}