package webclient.modules.sales;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webclient.general.BaseDocList;
import webclient.general.BaseListForm;
import webclient.general.General;
import webclient.modules.analytic.ChartTypes;
import webuita.chart.Chart;
import webuita.locating.Locators;
import widgets.ui5.Button;
import widgets.ui5.Select;


public class SalesOrderList extends BaseDocList {
	
	private Locators loc;
	public SalesOrderList(){
		super(); 
		loc = new Locators("Sales" + File.separator + "SalesOrderList_Mapping.xml", log);
		
	}


	public void setChartDimensions(String dim_01, String dim_02, String dim_03){
		if(dim_02.equals(dim_03) && !dim_02.equals("All")){
			log.add(LogStatus.FAIL, "The second dimenstion could not equal to the third dimension.");
			Assert.assertEquals(true, false, "The second dimenstion could not equal to the third dimension.");
			return;
		}
		
		Select dp_01 = new Select("Dimension 01", loc);
		dp_01.select(dim_01);
		Select dp_02 = new Select("Dimension 02", loc);
		dp_02.select(dim_02);
		Select dp_03 = new Select("Dimension 03", loc);
		dp_03.select(dim_03);
		General.waitRefreshComplete();
	}
}
