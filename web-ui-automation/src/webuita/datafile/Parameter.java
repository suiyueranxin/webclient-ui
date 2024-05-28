package webuita.datafile;


import java.util.Map;

public class Parameter {

	
	private String name;
	private String value;
	private Map<String,DataColumn> columns;
	
	public Parameter(){}
	
	public Parameter(String name, String value, Map<String,DataColumn> columns){
		
		this.name = name;
		this.value = value;
		this.columns = columns;
	}
	
	
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void SetColumns(Map<String,DataColumn> columns){
		this.columns = columns;
	}
	
	public Map<String,DataColumn> getColumns(){
		return this.columns;
	}
	
	
}
