package webuita.chart;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebElement;
import webuita.general.Global;

import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;

public class Chart {
	public static boolean compare(String fileName, WebElement e){
		boolean isEqual = false;
		Path path = Paths.get(fileName);
		Global.chartResultFileName = fileName;
		OcularResult result = Ocular.snapshot()
								    .from(path)
								    .sample()
								    .using(Global.driver)
								    .element(e)
								    .compare();
		isEqual = result.isEqualsImages();
		if(!isEqual){
			Global.bAttachedCompResult = true;
		}
		
		return isEqual;
		
	}
}
