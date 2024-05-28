package webclient.modules.analytic;

public enum ViewType {
    Line("Line"),
    Column("Column"),
    Bar("Bar"),
    StackedColumn("Stacked Column"),
    StackedBar("Stacked Bar"),
    Pie("Pie"),
    Donut("Donut"),
    HeatMap("Heat Map"),
    Table("Table");
    
    private String value;
    private String title;
    
    ViewType(String value) {
        this.value = value;
        if (value == "Table") {
            this.title = "";
        } else if (value == "Heat Map") {
            this.title = "An Interactive " + value;
        } else {
            this.title = "An Interactive " + value + " Chart";
        }
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public static ViewType getTypeFromTitle(String title) {
        for (ViewType type: ViewType.values()) {
            if (type.getTitle().equals(title)) {
                return type;
            }
        }
        return null;
    }
}
