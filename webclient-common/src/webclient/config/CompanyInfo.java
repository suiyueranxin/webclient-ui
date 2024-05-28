package webclient.config;

public class CompanyInfo {
	private String dbName;
	private String userName;
	private String password;
	private String newDbName;
	private String dbBackupName;
	
	public void setDbName(String dbName){
		this.dbName = dbName;
	}
	public String getDbName(){
		return dbName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return userName;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setNewDbName(String newDbName){
		this.newDbName = newDbName;
	}
	public String getNewDbName(){
		return newDbName;
	}
	
	public void setDbBackupName(String dbBackupName){
		this.dbBackupName = dbBackupName;
	}
	public String getDbBackupName(){
		return dbBackupName;
	}
}
