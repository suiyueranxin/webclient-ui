package webuita.locating;

import java.io.File;
import java.util.Map;

import org.testng.Assert;

import webuita.log.Logger;
import webuita.xml.ReadXml;

import com.relevantcodes.extentreports.LogStatus;

public class Locators {
	private Map<String, Item> LocatingMapping;
	private Logger log;
	public Locators(Map<String, Item> LocatingMapping, Logger log){
		this.LocatingMapping = LocatingMapping;
		this.log = log;
	}
	
	public Locators(String LocatingFile, Logger log){
		String locatingFilePath = "testcases" + File.separator + "webclient" + File.separator +  "Locating_mapping" + File.separator + LocatingFile;
	    this.LocatingMapping = ReadXml.getLocators(locatingFilePath);
	    this.log = log;
	 }
	
	public Item getItem(String name){
		Item item = null;
		try{
			item = LocatingMapping.get(name);
			if(null == item){
				log.add(LogStatus.FAIL,"Could not find \"" + name + "\" locating info.");
				Assert.assertTrue(false, "Could not find \"" + name + "\" the locating info.");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL,"Failed to find \"" + name + "\" locating info with: " + ex.toString());
			Assert.assertTrue(false,"Failed to find \"" + name + "\" locating info with: " + ex.toString());
		}
		return item;
	}
	
	public Column getColumnItem(String gridName, String colName){
		Column col = null;
		try{
			Map<String, Column> columns = LocatingMapping.get(gridName).getColumns();
			col = columns.get(colName);
			if(null == col){
				log.add(LogStatus.FAIL, "Could not find  column : \"" +colName + "\" grid : \"" + gridName + "\" locating info.");
				Assert.assertTrue(false, "Could not find  column : \"" +colName + "\" grid : \"" + gridName + "\" locating info.");
			}
		}catch(Exception ex){
			
			log.add(LogStatus.FAIL, 
					"Failed to find  column : \"" +colName + "\" grid : \"" + gridName + "\" locating info with: " + ex.toString());
			Assert.assertTrue(false, 
					"Failed to find  column : \"" +colName + "\" grid : \"" + gridName + "\" locating info with: " + ex.toString());
		}
		return col;
	}
	
}
