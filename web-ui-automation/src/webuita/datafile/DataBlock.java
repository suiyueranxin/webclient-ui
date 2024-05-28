package webuita.datafile;

import java.util.*;

public class DataBlock {
	private String instance;
	private String operation;
	private String formName;
	private Map<String,Parameter> parameters;
	
	public String getInstance(){
		return this.instance;
	}
	
	public void setInstance(String instance){
		this.instance = instance;
	}
	
	public String getOperation(){
		return this.operation;
	}
	
	public void setOperation(String operation){
		this.operation = operation;
	}
	
	public String getFormName(){
		return this.formName;
	}
	
	public void setFormName(String formName){
		this.formName = formName;
	}
	
	public Map<String,Parameter> getParameters(){
		return this.parameters;
	}
	
	public void setParameters(Map<String,Parameter> parameters){
		this.parameters = parameters;
	}
}
