package webuita.datafile;


import java.util.List;
import java.util.Map;

public class  DataColumn{
	private String name;
	private String uuid;
	private String type;
	private Map<String,DataCell> cells;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setUuid(String uuid){
		this.uuid = uuid;
	}
	public String getUuid(){
		return this.uuid;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setCells(Map<String,DataCell> cells){
		this.cells = cells;
	}
	public Map<String,DataCell> getCells(){
		return this.cells;
	}
}
