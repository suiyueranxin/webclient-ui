package webclient.modules.analytic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.general.General;
import webclient.modules.home.Home;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class BaseAnalysisPage {
    /**
     * base form as below
     */
    //properties
    protected WebConnection conn;
    protected Logger log;
    protected Locators locators;
    
    public BaseAnalysisPage(String locatorXMLPath) {
        conn = Global.conn;
        log = Global.log;
        locators = new Locators(locatorXMLPath, log);
    }
    
    //business action
    //action:common
    public void navigateToHome() {
        conn.clickElement("Id", "homeBtn", "");
    };
    public void back() {
        String backBtnXpath = "//a[@id='backBtn']";
        try {
            WebElement backBtn = conn.findElement("xpath", backBtnXpath);
            backBtn.click();
        } catch(Exception ex){
            log.add(LogStatus.FAIL, "Failed to back to overview with: " + ex.toString());
            Assert.assertEquals(true, false, "Failed to back to overview with: " + ex.toString());
        }
    };
    public void reopenFromHomeMenu(String modulePath) {
        this.navigateToHome();
        Home home = new Home();
        home.navigateTo(modulePath);
    };
    public void reopenFromHomeTile(String modulePath) {
        this.navigateToHome();
        Home home = new Home();
        home.navigateToFromTiles(modulePath);
    };
    //action:filter
    public void addFilter(String field) {
        Filters filters = new Filters();
        filters.addFilter(field);
    };
    public void removeFilter(String field) {};
    public void clearFilter(String field) {
        Filters filters = new Filters();
        filters.clearFilter(field);
    };
    public void changeFiltersValue(HashMap<String,String> filters) {};
    public void changeFilterValue(String field, String Value) {};
    public void changeDateFilterValue(String field, String value) {
        Filters filters = new Filters();
        filters.setFilterConditions(field, new String[][]{
            {Operators.EqualTo, value}
        });
        filters.go();
    };
    public void selectEnumFilterValue(String field, String values) {
        Filters filters = new Filters();
        filters.selectFilterConditions(field, values);
        filters.go();
    };
    public void selectBoFilterValueFromList(String field, String colName, String values) {
        Filters filters = new Filters();
        filters.selectFilterConditionsFromList(field, colName, values);
        filters.go();
    };
    public void selectStringFilterValue(String field, String opeartor, String values) {
        Filters filters = new Filters();
        filters.setFilterConditions(field, new String[][]{
            {opeartor, values}
        });
        filters.go();
    };

    //action:variant
    public void switchVariant(String variant) {
        Filters filters = new Filters();
        filters.selectView(variant);
    };
    public void pinVariant(String title, String category) {
        Filters filters = new Filters();
        filters.share(title, "", "", category, "share a variant as a tile");
    };
    public void setDefaultVariant(String variant) {
        Filters filters = new Filters();
        filters.setAsDefault(variant);
    };
    public void removeVariant(String variant) {
        Filters filters = new Filters();
        filters.deleteView(variant);
    };
    public void saveAsVariant(String variant, boolean isDefault) {
        Filters filters = new Filters();
        filters.saveAsView(variant, isDefault, false);
    };
    public void saveCurrentVariant() {
        Filters filters = new Filters();
        filters.saveView();
    };
    public void renameVariant(String original, String target) {
        Filters filters = new Filters();
        filters.renameView(original, target);
    };
    public void removeAllVariants() {
        Filters filters = new Filters();
        filters.deleteAllViews();
    };
    
    //check
    //check:common
    public void comparePageTitle(String title) {
        Item item = locators.getItem("Title");
        String actualTitle = conn.getElementValue(item.getByType(), item.getByValue(), "");
        General.compare(actualTitle, title, true, "Compare Page Title");
    };
    //check:filter
    public void compareFilters(List<String> filters) {
        Filters cfilters = new Filters();
        cfilters.compareFilters(filters);
    };
    public void compareFiltersValue(HashMap<String, String> filtersValue) {
        Filters cfilters = new Filters();
        for (Map.Entry<String, String> entry:filtersValue.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            cfilters.compareFilterCondition(fieldName, value);
        }
    };
    public void compareFilterValue(String field, String Value) {
        Filters cfilters = new Filters();
        cfilters.compareFilterCondition(field, Value);
    };
    
    //check:variant
    public void compareVariant(String variant) {
        Filters filter = new Filters();
        filter.compareDefaultViewName(variant);
    };
    public void hasVariant(String variant) {};
    public void hasVariants(List<String> variants) {};
    public void notHasVariant(String variant) {};
}
