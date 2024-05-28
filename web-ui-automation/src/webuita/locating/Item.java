package webuita.locating;

import java.util.HashMap;

public class Item {
	
	private String byType;
	private String byValue;
	private String name;
	private String type;
	
	private HashMap<String, Column> columns;
	
	public String getByType(){
		return byType;
	}
	
	public void setByType(String byType){
		this.byType = byType;
	}
	
	
	public String getByValue(){
		return byValue;
	}
	
	public void setByValue(String byValue){
		this.byValue = byValue;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	
	public HashMap<String, Column> getColumns(){
		return columns;
	}
	
	public void setColumns(HashMap<String, Column> columns){
		this.columns = columns;
	}
}
