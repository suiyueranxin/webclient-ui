package webclient.config;

public class HanaServer {
	private String hostIp;
	private String port;
	private String username;
	private String password;
	private String dbUsername;
	private String dbPassword;
	
	private String dbbackupSrcPath;
	
	public void setHostIp(String hostIp){
		this.hostIp = hostIp;
	}
	public String getHostIp(){
		return hostIp;
	}
	
	public void setPort(String port){
		this.port = port;
	}
	public String getPort(){
		return port;
	}
	
		
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setDbUsername(String dbUsername){
		this.dbUsername = dbUsername;
	}
	public String getDbUsername(){
		return dbUsername;
	}
	
	public void setDbPassword(String dbPassword){
		this.dbPassword = dbPassword;
	}
	public String getDbPassword(){
		return dbPassword;
	}
	
	public void setDbbackupSrcPath(String dbbackupSrcPath){
		this.dbbackupSrcPath = dbbackupSrcPath;
	}
	public String getDbbackupSrcPath(){
		return dbbackupSrcPath;
	}
}
