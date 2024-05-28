package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.FilterFields.Analysis;
import webclient.filters.Operators;
import webclient.modules.analytic.AnalyticPageString;
import webclient.modules.analytic.OverviewTitle;
//import webclient.filters.TimePeriods;
import webclient.modules.analytic.BaseAnalysisOverview;
import webclient.modules.home.Home;

public class CaseOverviewChangeFilters {
    
    @Test
    public void overviewChangeFilters() {
        Home home = new Home();
        home.navigateToFromTiles(AnalyticPageString.AnalyticCategory + "->" + OverviewTitle.SalesAnalysisByRowsOverview);
        
        //part-1: test data preparation
        
        //filter values
        //dateFilter: filter label, filter value, expected KPI num, expected KPI value
        List<String> dateFilter = Arrays.asList(Analysis.PostingDate,"01/01/2016...03/31/2016","1.3","M");
        //enumFilter
        List<String> enumFilter = Arrays.asList(Analysis.DocumentTypeDisplayName, "A/R Invoice;Sales Order", "116", "K");
        //boFilter
        List<String> boFilter = Arrays.asList(Analysis.BusinessPartnerCode, FilterFields.BPCode, "C23900;C20000", "2.3", "K");
        
        //date operator
        //TODO: result depends on the data of related time filters
//        List<String> dateInRange = Arrays.asList(TimePeriods.DateRange, "X", "X", "0", "M");
//        
//        List<String> dateToday = Arrays.asList(TimePeriods.Today, "0", "M");
//        List<String> dateLastXDays = Arrays.asList(TimePeriods.LastXDays, "X", "0", "M");
//        List<String> dateNextXDays = Arrays.asList(TimePeriods.NextXDays, "X", "0", "M");
//        
//        List<String> dateThisWeek = Arrays.asList(TimePeriods.ThisWeek, "0", "M");
//        List<String> dateLastXWeeks = Arrays.asList(TimePeriods.LastXWeeks, "X", "0", "M");
//        List<String> dateNextXWeeks = Arrays.asList(TimePeriods.NextXWeeks, "X", "0", "M");
//        
//        List<String> dateThisMonth = Arrays.asList(TimePeriods.ThisMonth, "0", "M");
//        List<String> dateLastMonths = Arrays.asList(TimePeriods.LastXMonths, "X", "0", "M");
//        List<String> dateNextMonths = Arrays.asList(TimePeriods.NextXMonths, "X", "0", "M");
//        
//        List<String> dateThisQuarter = Arrays.asList(TimePeriods.ThisQuarter, "0", "M");
//        List<String> dateThisYear = Arrays.asList(TimePeriods.ThisYear, "0", "M");
//        List<String> dateLastYears = Arrays.asList(TimePeriods.LastXYears, "X", "0", "M");
//        List<String> dateNextYears = Arrays.asList(TimePeriods.NextXYears, "X", "0", "M");
        
        //string operator
        List<String> stringEqualTo = Arrays.asList(Analysis.BusinessPartnerCode, Operators.EqualTo, "C20000","1.2", "K");
        List<String> stringContains = Arrays.asList(Analysis.BusinessPartnerCode, Operators.Contains, "C20000", "1.2", "K");
        List<String> stringStartsWith = Arrays.asList(Analysis.BusinessPartnerCode, Operators.StartsWith, "C2", "2.6", "K");
        List<String> stringEndsWith = Arrays.asList(Analysis.BusinessPartnerCode, Operators.EndsWith, "900", "1.1", "K");
        
        //time string operator
        List<String> timeStringEqualTo = Arrays.asList(Analysis.PostingYear, Operators.EqualTo, "2006","1.6", "M");
        List<String> timeStringInRange = Arrays.asList(Analysis.PostingYear, Operators.InRange, "2006;2008", "3.4", "M");
        List<String> timeStringContains = Arrays.asList(Analysis.PostingYear, Operators.Contains, "2006", "1.6", "M");
        List<String> timeStringStartsWith = Arrays.asList(Analysis.PostingYear, Operators.StartsWith, "201", "11", "M");
        List<String> timeStringEndsWith = Arrays.asList(Analysis.PostingYear, Operators.EndsWith, "006", "1.6", "M");
        
        //part-2: start to test
        BaseAnalysisOverview overview = new BaseAnalysisOverview();
        String variantStandard = "Standard"; 
        String variantPostingYear = "variant 1";
        
        //check
        //check date filter equal to
        overview.switchVariant(variantStandard);
        overview.changeDateFilterValue(dateFilter.get(0), dateFilter.get(1));
        overview.compareCardKpi(0, dateFilter.get(2), dateFilter.get(3));
        
        overview.switchVariant(variantStandard);
        overview.selectEnumFilterValue(enumFilter.get(0), enumFilter.get(1));
        overview.compareCardKpi(0, enumFilter.get(2), enumFilter.get(3));
        
        overview.switchVariant(variantStandard);
        overview.selectBoFilterValueFromList(boFilter.get(0), boFilter.get(1), boFilter.get(2));
        overview.compareCardKpi(0, boFilter.get(3), boFilter.get(4));
        
        //check string operator
        overview.switchVariant(variantStandard);
        overview.selectStringFilterValue(stringEqualTo.get(0), stringEqualTo.get(1), stringEqualTo.get(2));
        overview.compareCardKpi(0, stringEqualTo.get(3), stringEqualTo.get(4));
        
        overview.switchVariant(variantStandard);
        overview.selectStringFilterValue(stringContains.get(0), stringContains.get(1), stringContains.get(2));
        overview.compareCardKpi(0, stringEqualTo.get(3), stringEqualTo.get(4));
        
        overview.switchVariant(variantStandard);
        overview.selectStringFilterValue(stringStartsWith.get(0), stringStartsWith.get(1), stringStartsWith.get(2));
        overview.compareCardKpi(0, stringEqualTo.get(3), stringEqualTo.get(4));
        
        overview.switchVariant(variantStandard);
        overview.selectStringFilterValue(stringEndsWith.get(0), stringEndsWith.get(1), stringEndsWith.get(2));
        overview.compareCardKpi(0, stringEndsWith.get(3), stringEndsWith.get(4));
        
//        //check time string operator
//        overview.clearFilter(Analysis.PostingDate);
//        overview.addFilter(Analysis.TextPostingYear);
//        overview.saveAsVariant(variantPostingYear, false);
//        overview.selectStringFilterValue(timeStringEqualTo.get(0), timeStringEqualTo.get(1), timeStringEqualTo.get(2));
//        overview.compareCardKpi(0, timeStringEqualTo.get(3), timeStringEqualTo.get(4));
//        
//        overview.switchVariant(variantPostingYear);
//        overview.selectStringFilterValue(timeStringInRange.get(0), timeStringInRange.get(1), timeStringInRange.get(2));
//        overview.compareCardKpi(0, timeStringInRange.get(3), timeStringInRange.get(4));
//        
//        overview.switchVariant(variantPostingYear);
//        overview.selectStringFilterValue(timeStringContains.get(0), timeStringContains.get(1), timeStringContains.get(2));
//        overview.compareCardKpi(0, timeStringContains.get(3), timeStringContains.get(4));
//        
//        overview.switchVariant(variantPostingYear);
//        overview.selectStringFilterValue(timeStringStartsWith.get(0), timeStringStartsWith.get(1), timeStringStartsWith.get(2));
//        overview.compareCardKpi(0, timeStringStartsWith.get(3), timeStringStartsWith.get(4));
//        
//        overview.switchVariant(variantPostingYear);
//        overview.selectStringFilterValue(timeStringEndsWith.get(0), timeStringEndsWith.get(1), timeStringEndsWith.get(2));
//        overview.compareCardKpi(0, timeStringEndsWith.get(3), timeStringEndsWith.get(4));
//        
//        overview.removeVariant(variantPostingYear);
        overview.navigateToHome();
    }

}
