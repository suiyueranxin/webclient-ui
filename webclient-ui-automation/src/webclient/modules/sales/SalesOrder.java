package webclient.modules.sales;

import java.io.File;

import webclient.general.BaseForm;
import webclient.general.General;
import widgets.ui5.Button;
import widgets.ui5.Select;
import webclient.general.MarketDocument;
import webuita.general.Global;
import webuita.locating.Locators;


public class SalesOrder extends MarketDocument {
	private Locators locators;		
	public SalesOrder(){
		super();
		//super("Sales" + File.separator + "SalesOrder_Mapping.xml");
		this.locators = new Locators("Sales" + File.separator + "SalesOrder_Mapping.xml", super.log);
	}
	
	//Tab General
	/*public void setCustomer(String value)
	{
		StringWidget customer = new StringWidget("Customer", locators);
		customer.set(value);
		
	}

	public void compareCustomer(String expectedValue){
		StringWidget customer = new StringWidget("Customer", locators);
		customer.compare(expectedValue);
	
	}
	
	public void setContactPerson(String value){
		StringWidget cntP = new StringWidget("Contact Person", locators);
		cntP.set(value);
	}
	
	public void setCustomerRefNo(String value){
		StringWidget crn = new StringWidget("Customer Ref No.", locators);
		crn.set(value);
	}
	
	public void setRemarks(String value){
		TextAreaWidget remarks = new TextAreaWidget("Remarks", locators);
		remarks.set(value);
	}
	
	public void compareRemarks(String expectedValue){
		TextAreaWidget remarks = new TextAreaWidget("Remarks", locators);
		remarks.compare(expectedValue);
	}
	
	public void setSeriesNo(String value){
		SelectWidget seriesNo = new SelectWidget("Series No", locators);
		seriesNo.select(value);
	
	}
	
	public void setPostingDate(String value){
		DateWidget pd = new DateWidget("Posting Date", locators);
		pd.set(value);
	}
	
	public void setDeliveryDate(String value){
		DateWidget dd = new DateWidget("Delivery Date", locators);
		dd.set(value);
	}
	public void setDocumentDate(String value){
		DateWidget dd = new DateWidget("Document Date", locators);
		dd.set(value);
	}
	
	public void setSalesEmployee(String value){
		StringWidget se = new StringWidget("Sales Employee", locators);
		se.set(value);
	}
	
	public void setOwner(String value){
		StringWidget owner = new StringWidget("Owner", locators);
		owner.set(value);
	}
	
	// Tab Contents
	public void setCurrency(String value){
		SelectWidget cur = new SelectWidget("Currency", locators);
		cur.set(value);
	}
	public void setItemServiceType(String value){
		SelectWidget isType = new SelectWidget("Item Service Type", locators);
		isType.select(value);;
	}
	public void setDiscount(String value){
		StringWidget dis = new StringWidget("Discount", locators);
		dis.set(value);
	}
	
	public void clickProduct_Contents(){
		Button btn = new Button("Product Segment Button", locators);
		btn.click();
	}
	
	public void clickGrossProfit_Contents(){
		Button btn = new Button("Gross Profit Segment Button", locators);
		btn.click();
	}
	public void clickVolumeAndWeight_Contents(){
		Button btn = new Button("Volume and Weight Segment Button", locators);
		btn.click();
	}
	
	//Tab Logistics
	
	public void editShipToAddress(){
		Button btn = new Button("Edit Ship To Address", locators);
		btn.click();
	}
	
	public void editBillToAddress(){
		Button btn = new Button("Edit Bill To Address", locators);
		btn.click();
	}
	
	public void defineNewShiptToAddress(){
		Button btn = new Button("Define New Ship To Address", locators);
		btn.click();
	}
	
	public void defineNewBillToAddress(){
		Button btn = new Button("Define New Bill To Address", locators);
		btn.click();
	}
	
	public void setShipToAddress(String value){
		Select sta = new Select("Ship To Address", locators);
		sta.set(value);
	}
	public void setBillToAddress(String value){
		Select bta = new Select("Bill To Address", locators);
		bta.set(value);
	}
	public void setShippingType(String value){
		StringWidget st = new StringWidget("Shipping Type", locators);
		st.set(value);
	}
	public void setApproved(String value){
		Select app = new Select("Approved", locators);
		app.set(value);
	}
	public void setAllowPartialDelivery(String value){
		Select apd = new Select("Allow Partial Delivery", locators);
		apd.set(value);
	}
	
	//Tab Accounting
	public void setPaymentTerms(String value){
		StringWidget pt = new StringWidget("Payment Terms", locators);
		pt.set(value);
	}
	public void setCancellationDate(String value){
		DateWidget cd = new DateWidget("Cancellation Date", locators);
		cd.set(value);
	}
	public void setRequiredDate(String value){
		DateWidget rd = new DateWidget("Required Date", locators);
		rd.set(value);
	}
	
	//Tab UDF
	public void setSOHeaderlinktoUDT(String value){
		StringWidget input = new StringWidget("SO Header link to UDT", locators);
		input.set(value);
	}
	public void setSOHeaderlinktoInvoice(String value){
		StringWidget input = new StringWidget("SO Header link to Invoice", locators);
		input.set(value);
	}
	public void setSOHeaderlinktoUDOMaster(String value){
		StringWidget input = new StringWidget("SO Header link to UDO Master", locators);
		input.set(value);
	}
	public void setSOHeaderlinktoUDODocs(String value){
		StringWidget input = new StringWidget("SO Header link to UDO Doc", locators);
		input.set(value);
	}
	public void setSOHeaderPrice(String value){
		StringWidget input = new StringWidget("SO Header Price", locators);
		input.set(value);
	}
	
	public void setSOHeaderlinktoBP(String value){
		StringWidget input = new StringWidget("SO Header link to BP", locators);
		input.set(value);
	}
	public void setSOHeaderNoKey(String value){
		StringWidget input = new StringWidget("SO Header No Key", locators);
		input.set(value);
	}
	public void setSOHeaderTime(String value){
		StringWidget input = new StringWidget("SO Header Time", locators);
		input.set(value);
	}
	public void setSOHeaderGreater(String value){
		StringWidget input = new StringWidget("SO Header Greater", locators);
		input.set(value);
	}*/
}
