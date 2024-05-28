package webclient.modules.addons;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.log.Logger;

public class Addons {

	private WebConnection conn;
	private Logger log;

	public Addons() {
		this.conn = Global.conn;
		this.log =Global.log;
	}

	public void checkAddon(List<String> list, boolean flag) {
//		conn.clickElement("id", "menuButton", "Click Assign button");
//		conn.ClickLiElementFromUlByName("id", "__list0", "click li", "Addons");
//		if (flag) {
//			if (conn.CompareLiElementFromUlByName("id", "__list0", "check li", list)) {
//				log.add(LogStatus.PASS, "Pass to Check Addon OnThin Client WebSite!");
//			} else {
//				log.add(LogStatus.FAIL, "Fail to Check Addon OnThin Client WebSite!");
//				Assert.assertTrue(false, "Fail to Check Addon OnThin Client WebSite!");
//			}
//		} else {
//			if (!(conn.CompareLiElementFromUlByName("id", "__list0", "check li", list))) {
//				log.add(LogStatus.PASS, "Pass to Check Addon OnThin Client WebSite!");
//			} else {
//				log.add(LogStatus.FAIL, "Fail to Check Addon OnThin Client WebSite!");
//				Assert.assertTrue(false, "Fail to Check Addon OnThin Client WebSite!");
//			}
//		}
	}

}
