package webuita.datafile;


import java.text.*;
import java.util.*;


public class Datas {

	private Map<String, Parameter> parameters;
	
	public Datas(Map<String, Parameter> parameters){
		this.parameters = parameters;
		
	}
	
	public Datas(String datafile){
		
	}
	public static boolean isInteger(String text) {
		try{
			Integer.parseInt(text);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	public String get(String name){
		String value = "";
		try{
			value = parameters.get(name).getValue();
			if(name.contains("Date")){
				if(isInteger(value)){
					int offset = Integer.parseInt(value);
					Calendar c = new GregorianCalendar();
					if(offset <= 31 && offset >=0){
						
						c.add(Calendar.DATE, offset);
						Date d = c.getTime();
						DateFormat df = new SimpleDateFormat("yyyyMMdd");
						value = df.format(d);
					}else if(offset < 0 && offset >= -31){
						c.add(Calendar.DATE, offset);
						Date d = c.getTime();
						DateFormat df = new SimpleDateFormat("yyyyMMdd");
						value = df.format(d);
					}
					
				}
			}
			
		}catch(Exception ex){
			
		}
		
		return value;
	}
	
	public String get(String matrixName, String colName, int row){
		String value = "";
		try{
			Parameter oMatrix = parameters.get(matrixName);
			Map<String,DataColumn> cols = oMatrix.getColumns();
			
			DataColumn col = cols.get(colName);
			Map<String,DataCell> cells = col.getCells();
			value = cells.get(Integer.toString(row+1)).getValue();
			
		}catch(Exception ex){
			
		}
		
		
		return value;
		
	}
	
	public int getRowNumber(String matrixName){
		int row = 0;
		try{
			Parameter oMatrix = parameters.get(matrixName);
			Map<String,DataColumn> cols = oMatrix.getColumns();
			
			DataColumn col = null;
			Iterator it = cols.entrySet().iterator();
			if(it.hasNext()){
				Map.Entry<String,DataColumn> colTemp = (Map.Entry<String,DataColumn>)it.next();
				col = colTemp.getValue();
			}
			
			Map<String,DataCell> cells = col.getCells();
			row = cells.size();
			
		}catch(Exception ex){
			
		}
		
		
		return row;
	}
}
