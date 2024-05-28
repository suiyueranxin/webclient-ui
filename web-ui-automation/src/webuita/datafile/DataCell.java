package webuita.datafile;

public class DataCell {
	private String value;
	private String row;
	
	public void setValue(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
	
	public void setRow(String row){
		this.row = row;
	}
	public String getRow(){
		return this.row;
	}
}
