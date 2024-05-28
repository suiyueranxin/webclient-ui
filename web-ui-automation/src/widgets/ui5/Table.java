package widgets.ui5;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class Table {
	private WebConnection conn;
	private Logger log; 
	private String byType;
	private String byValue;
	private String tableName;
	private String type;
	private Locators loc;
	
	public Table(){}
	public Table(String tableName, Locators locator){
		conn = Global.conn;
		log = Global.log;
		loc = locator;
		
		Item item = locator.getItem(tableName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.tableName = tableName;
		type = item.getType();
		
	}
	public void selectRow(int rowIndex){
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@role='presentation']", byValue,rowIndex);
		conn.clickElement("xpath", xpath, String.format("Select table \"%s\", row: \"%d\".", tableName, rowIndex));
	}
	
	public void clickCell(int rowIndex, String colName){
		String desc = String.format("Click table \"%s\", row: \"%d\", column: \"%s\".", tableName, rowIndex, colName);
		int colIndex = getColumnIndex(byValue, colName);
		int actRowIndex = getActualRowIndex(rowIndex);
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]//a",
				byValue, actRowIndex, colIndex);
		if(conn.isExistElement("xpath", xpath)){
			conn.clickElement("xpath", xpath, desc);
		}else{
			 xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]//div[@class='sapBUiText']",
						byValue, rowIndex, colIndex);
			 conn.clickElement("xpath", xpath, desc);
		}
		General.waitBusyIndicatorInvisible();
	}
	/***
	 * Only for webClient.
	 * @param rowIndex - count from 0
	 * @param colIndex - count from 0
	 */
	public void clickCell(int rowIndex, int colIndex){
		String xpath = String.format("((%s//tbody//tr[not (@style='display: none;')])[%d]//td)[%d]",
				byValue, rowIndex + 1, colIndex + 1);
		
		if(conn.isExistElement("xpath", xpath + "//div[@class='sapMRbB']")){
			conn.clickElement("xpath", xpath + "//div[@class='sapMRbB']", "");
		}else if(conn.isExistElement("xpath", xpath + "//button")){
			conn.clickElement("xpath", xpath + "//button", "");
		}
		General.waitBusyIndicatorInvisible();
	}

	public void clickButton(String btnName){
		String buttonXpath = String.format("//preceding::div[@class='sapBUiTlbrCont']//span[text()='%s']", btnName);
		log.add(LogStatus.INFO, "Click Table \"" + this.tableName +"\" " + "button \"" + btnName + "\"");
		Button btn = new Button(btnName, this.byValue+buttonXpath);
		btn.click();
		General.waitBusyIndicatorInvisible();
	}
	
	public int getRowCount(){
		int rowCount = 0;
		try{
			
			String strRowCount = conn.getElementAttribute("xpath", byValue, "aria-rowcount");
			if(null != strRowCount && strRowCount != ""){
				return Integer.parseInt(strRowCount);
			}
			
		}catch(Exception ex){
			
		}
		return rowCount;
	}
	
	public int getColumnCount(){
		int colCount = 0;
		try{
			
			String strColCount = conn.getElementAttribute("xpath", byValue, "aria-colcounts");
			if(null != strColCount && strColCount != ""){
				return Integer.parseInt(strColCount);
			}
			
		}catch(Exception ex){
			
		}
		return colCount;
	}
	
	public int getColumnIndex(String tableHeaderXpath, String colName){
		int colIndex = 0;
		String thXpath = "//tr[@class='sapUiTableColHdrTr']//bdi";
		String tempValue = "";
		
		List<WebElement> eles = new ArrayList<WebElement>();
		eles = Global.conn.findElements("xpath", thXpath); 
		if(eles.size() > 0){
			for(WebElement ele : eles){
				++colIndex;
				tempValue = ele.getText();
				if(colName.equals(tempValue)) {
					break;
				}
				
			}
		}else{
			thXpath = tableHeaderXpath + "//th";
			eles = Global.conn.findElements("xpath", thXpath); 
			if(eles.size() > 0){
				for(WebElement ele : eles){
					colIndex++;
					tempValue = ele.getAttribute("aria-label");
					if(colName.equals(tempValue)) {
						colIndex = Integer.parseInt(ele.getAttribute("aria-colindex")); 
						break;
					}
				}
			}
		}
		return colIndex;
	}
	
		
	public int getActualRowIndex(int rowIndex){
		int actualRowIndex = 0;
		String tempValue = "";
		boolean hasSelectRow = false;
		int rowFrom = 0, rowTo = 0, tmpRowTo = 0, rowCount = 0;
		String selTrXpath = byValue +  "//tbody//tr[contains(@class,'sapBUiListRow') and @aria-selected= 'true']";
		String trXpath = byValue + "//tbody//tr[contains(@class,'sapBUiListRow')]";
		try{
			hasSelectRow = Global.conn.isExistElement("xpath", selTrXpath);
			List<WebElement> eles = Global.conn.findElements("xpath", trXpath); 
			rowCount = eles.size();
			if(rowIndex < rowCount){
				actualRowIndex = rowIndex;
				
			}
			if(hasSelectRow){
				if(rowIndex >= rowCount){
					actualRowIndex = rowCount - 1;
				}
				
			}else{
				if(rowIndex == rowCount){
					actualRowIndex = rowIndex;
				}else{
					Actions builder = new Actions(Global.driver);
					String scrollBarPopoverXpath = "//div[@role='dialog' and contains(@style,'visible')]//span[contains(@class,'sapBUiScrollbarTxt')]";
					WebElement eScroll = conn.findElement("xpath", byValue + "/../..//div[@class='sapBUiScrollbar-ver']");
					Rectangle r = eScroll.getRect();
					while(true){
						General.waitBusyIndicatorInvisible();
						if(conn.isExistElement("xpath", scrollBarPopoverXpath)){
							tempValue = conn.getElementValue("xpath",scrollBarPopoverXpath, "");
							String rowRange = tempValue.split(" ")[1];
							rowFrom = Integer.parseInt(rowRange.split("-")[0]);
							rowTo = Integer.parseInt(rowRange.split("-")[1]);
							if(tmpRowTo == rowTo){break;}else{tmpRowTo = rowTo;}
							if( rowIndex <= rowTo && rowIndex >= rowFrom){
								actualRowIndex = rowIndex - rowFrom + 1;
								break;
							}else{
								builder.moveToElement(eScroll, r.getWidth()/2 - 1, r.getHeight()/2 -1 ).click().build().perform();
							}
						}else{
							builder.moveToElement(eScroll, r.getWidth()/2 - 1, r.getHeight()/2 -1 ).click().build().perform();
							continue;
						}
						
					}
				}
				}
				
		}catch(Exception ex){
			//output exception
		}
		
		return actualRowIndex;
	}
	
	public void setCellValueFromList( String colName,int rowIndex, String value){
		int colIndex = getColumnIndex(byValue, colName);
		String desc = String.format("Set table \"%s\", row: \"%d\", column: \"%s\" to \"%s\" from list.", tableName, rowIndex, colName, value);
		int actualRowIndex = getActualRowIndex(rowIndex);
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
				byValue, actualRowIndex, colIndex);
		ValueSelector cell = new ValueSelector("xpath", xpath, desc);
		cell.choose(value);
		General.waitBusyIndicatorInvisible();
	}
	
	//handle one column has two input value format: split with ",". like as: 10,box.
	public void setCellValue(String colName, int rowIndex, String value){
		int colIndex = getColumnIndex(byValue, colName);
		String desc = String.format("Set table \"%s\", row: \"%d\", column: \"%s\" to \"%s\"", tableName, rowIndex, colName, value);
		int actualRowIndex = getActualRowIndex(rowIndex);
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
				byValue, actualRowIndex, colIndex);
		java.util.List<WebElement> le = conn.findElements("xpath", xpath + "//input");
		String[] values = value.split(",");
		if(values.length > 1 && le.size() > 1){
			log.add(LogStatus.INFO, desc + value);
			WebElement e = conn.findElement("xpath", "(" + xpath + "//input)[1]");
			e.click();
			((JavascriptExecutor) Global.driver).executeScript("arguments[0].value = arguments[1];", e, values[0]);
			//conn.setElementValue("xpath", "(" + xpath + "//input)[1]", values[0], "");
			General.sleep(1);
			e = conn.findElement("xpath", "(" + xpath + "//input)[2]");
			((JavascriptExecutor) Global.driver).executeScript("arguments[0].value = arguments[1];", e, values[1]);
			General.pressKey(KeyEvent.VK_ENTER);
			General.waitBusyIndicatorInvisible();
		}else{
			Input cell = new Input("xpath", xpath, desc);
			cell.setValueWithPressEnter(value);
		}
		
		General.waitBusyIndicatorInvisible();
	}
	
	public void setCellValueFromList( String colName, String packageName, 
			String dlgName, String dlgColName, int rowIndex, String value){
		int colIndex = getColumnIndex(byValue, colName);
		String desc = String.format("Set table \"%s\", row: \"%d\", column: \"%s\" to \"%s\" from list.", tableName, rowIndex, colName, value);
		int actualRowIndex = getActualRowIndex(rowIndex);
		String cellXpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
				byValue, actualRowIndex, colIndex);
		ValueSelector cell = new ValueSelector(colName, cellXpath, desc);
		cell.chooseFromList(packageName, dlgName, dlgColName, value);
		General.waitBusyIndicatorInvisible();
		
	}
	/***
	 * Through clicking scroll bar.
	 * @param VerticalScrollBarXpath
	 */
	public void scrollDown(String VerticalScrollBarXpath){
		Actions builder = new Actions(Global.driver);
		WebElement eScroll = null;
		Rectangle r = null;
		if(eScroll == null){
			eScroll = conn.findElement("xpath", VerticalScrollBarXpath);
			r = eScroll.getRect();
		}
		builder.moveToElement(eScroll, r.getWidth()/2 - 1, r.getHeight()/2 -1 ).click().build().perform();
		General.waitBusyIndicatorInvisible();
	}
	
	public String getCellValue(String cellXpath){
		String value = "";
		if(conn.isExistElement("xpath", cellXpath + "//span")){
			return conn.getElementValue("xpath", cellXpath + "//span", "");
		}
		return value;
	}
	public boolean checkValuesFromTable(String colName, String value){
		General.waitRefreshComplete();
		boolean bShowAll = false;
		boolean bSucceed = false;
		
		String values[] = value.split(";");
		
		try{
		
			
			String trXpath = byValue + "//tbody//tr";
			String cellXpath = "";
			String tempValue = "";
			String tempValue_2 = "";
			String tmpColValue = "";
			int rowCount = 0, colIndex = 0;
			colIndex = getColumnIndex("//div[@role='dialog']", colName);
		
			List<WebElement> eles = Global.conn.findElements("xpath", trXpath); 
			rowCount = eles.size();
			String lastRowColXpath = String.format("((%s//tbody//tr)[%d]//td)[%d]",
					byValue, rowCount, colIndex + 1);
			int conCount = 0;
			while(true){
				tempValue = getCellValue(lastRowColXpath);
				for(int i = 1; i <= rowCount; i++){
					cellXpath = String.format("((%s//tbody//tr)[%d]//td)[%d]",
							byValue, i, colIndex + 1);
					tmpColValue = getCellValue(cellXpath);
					for(String v : values){
						if(v.equals(tmpColValue)){
							conn.clickElement("xpath", 
									String.format("(//section[@class='sapMDialogSection']//div[@class='sapUiTableRowHdrScr']//div[@role='rowheader'])[%s]", i), "");
							General.waitBusyIndicatorInvisible();
							conCount ++;
						}
					}
					
					
				}
			
				//if not be selected, scroll down.
				if(conCount < values.length){
					scrollDown("//section[@class='sapMDialogSection']//div[@class='sapUiTableVSb']");
					tempValue_2 = getCellValue(lastRowColXpath);
					if(tempValue.equals(tempValue_2)) bShowAll = true;
				
				}
				if(conCount == values.length){
					bSucceed = true;
					break;
				}
				if(bShowAll){
					break;
				}
				
			}
			
		}catch(Exception ex){
			
			Global.log.add(LogStatus.FAIL, "Failed to select value from table with: " + ex.toString(), Global.isStopRunAfterFail);
		}
		return bSucceed;
	}
	public boolean selectValueFromTable(String colName,String value){
		General.waitRefreshComplete();
		boolean bSelect = false;
		boolean bShowAll = false;
		boolean bSucceed = false;
		try{
		
			Actions builder = new Actions(Global.driver);
			String trXpath = byValue + "//tbody//tr[contains(@class,'sapBUiListRow')]";
			String cellXpath = "";
			System.out.println("test");
			String tempValue = "";
			String tempValue_2 = "";
			int rowCount = 0, colIndex = 0;
			colIndex = getColumnIndex(byValue, colName);
		
			List<WebElement> eles = Global.conn.findElements("xpath", trXpath); 
			rowCount = eles.size();
			WebElement eScroll = null;
			Rectangle r = null;
			while(true){
				tempValue = conn.getElementValue("xpath",String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
							byValue, rowCount, colIndex), "");
				for(int i = 1; i <= rowCount; i++){
					cellXpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
							byValue, i, colIndex);
					WebElement eCol = Global.conn.findElement("xpath", cellXpath);
					String tmpColValue = eCol.getText();
					if(null != eCol){
						if(value.equals(tmpColValue)){
							eCol.click();
							General.waitBusyIndicatorInvisible();
							bSelect = true;
							break;
						}
						
					}
					
				}
			
				//if not be selected, scroll down.
				if(!bSelect){
					if(eScroll == null){
						eScroll = conn.findElement("xpath", byValue + "/../..//div[@class='sapBUiScrollbar-ver']");
						r = eScroll.getRect();
					}
					builder.moveToElement(eScroll, r.getWidth()/2 - 1, r.getHeight()/2 -1 ).click().build().perform();
					General.waitBusyIndicatorInvisible();
					tempValue_2 = conn.getElementValue("xpath",String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
							byValue, rowCount, colIndex), "");
					if(tempValue.equals(tempValue_2)) bShowAll = true;
				
				}
			
				if(bShowAll && !bSelect){
					break;
				}
				if(bSelect){
					bSucceed = true;
					break;
				}
		
			}
			
		}catch(Exception ex){
			
			Global.log.add(LogStatus.FAIL, "Failed to select value from table with: " + ex.toString(), Global.isStopRunAfterFail);
		}
		return bSucceed;
	}

	public void setCellValueFromComboBox( String colName,int rowIndex, String value){
		int colIndex = getColumnIndex(byValue, colName);
		String desc = String.format("Set table \"%s\", row: \"%d\", column: \"%s\" to \"%s\" from list.", tableName, rowIndex, colName, value);
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
				byValue, rowIndex, colIndex);
		ComboBox cell = new ComboBox("xpath", xpath, desc);
		cell.select(value);
		General.waitBusyIndicatorInvisible();
	}
	
	public String getCellValue(String colName, int rowIndex){
		String value = "";
		int colIndex = getColumnIndex(byValue, colName);
		int actualRowIndex = getActualRowIndex(rowIndex);
		String cellXpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",
				byValue, actualRowIndex, colIndex);
		String xPath = String.format("%s//a",cellXpath);
		if(!conn.isExistElement("xpath", xPath)){
			xPath = String.format("%s//div[@class='sapBUiText']",cellXpath);
		}
		if(!conn.isExistElement("xpath", xPath)){
			xPath = String.format("%s//input",cellXpath);
			value = conn.getElementAttribute("xpath", xPath, "value");
			return value;
		}
		
		value = conn.getElementValue("xpath", cellXpath, "");
		return value;
	}
	public void compareCellValue(String colName, int rowIndex, String expectedValue){
		String actualValue = getCellValue(colName, rowIndex);
		String desc = String.format("Compare table \"%s\", row: \"%d\", column: \"%s\", Expected value: \"%s\", Actual value:\"%s\"", 
				tableName, rowIndex, colName, expectedValue, actualValue);
		if(expectedValue.equals(actualValue)){
			log.add(LogStatus.PASS, desc);
		}else{
			log.add(LogStatus.FAIL, desc, Global.isStopRunAfterFail);			
		}
	}
	
	public void compareCellValueFromInput(String colName, int rowIndex, String expectedValue){
		int colIndex = getColumnIndex(byValue, colName);
		String desc = String.format("Compare table \"%s\", row: \"%d\", column: \"%s\"", 
				tableName, rowIndex, colName);
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",byValue, rowIndex, colIndex);
		Input cell = new Input("xpath", xpath, desc);
		String actualValue = cell.getValue();
		desc = desc + ", Expected value: " + expectedValue + ", Actual value: " + actualValue;
		if(expectedValue.equals(actualValue)){
			log.add(LogStatus.PASS, desc);
		}else{
			log.add(LogStatus.FAIL, desc, Global.isStopRunAfterFail);			
		}
	}
		
	public void compareCellValueFromComboBox(String colName, int rowIndex, String expectedValue){
		int colIndex = getColumnIndex(byValue, colName);
		String desc = String.format("Compare table \"%s\", row: \"%d\", column: \"%s\"", 
				tableName, rowIndex, colName);
		String xpath = String.format("%s//tbody//tr[@aria-rowindex = %d]//td[@aria-colindex = %d]",byValue, rowIndex, colIndex);
		ComboBox cell = new ComboBox("xpath", xpath, desc);
		String actualValue = cell.getValue();
		desc = desc + ", Expected value: " + expectedValue + ", Actual value: " + actualValue;
		if(expectedValue.equals(actualValue)){
			log.add(LogStatus.PASS, desc);
		}else{
			log.add(LogStatus.FAIL, desc, Global.isStopRunAfterFail);			
		}
	}
	
}
