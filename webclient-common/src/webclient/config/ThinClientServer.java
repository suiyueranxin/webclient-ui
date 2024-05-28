package webclient.config;

public class ThinClientServer {
	private String hostIp;
	private String username;
	private String password;
	private String tcCodeLine;
	private String tcBuildPath;
	private String sapJvmPath;
	
	public void setHostIp(String hostIp){
		this.hostIp = hostIp;
	}
	public String getHostIp(){
		return hostIp;
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
	
	public void setTcCodeLine(String tcCodeLine){
		this.tcCodeLine = tcCodeLine;
	}
	public String getTcCodeLine(){
		return tcCodeLine;
	}
	
	public void setTcBuildPath(String tcBuildPath){
		this.tcBuildPath = tcBuildPath;
	}
	public String getTcBuildPath(){
		return tcBuildPath;
	}
	
	public void setSapJvmPath(String sapJvmPath){
		this.sapJvmPath = sapJvmPath;
	}
	public String getSapJvmPath(){
		return sapJvmPath;
	}
	
}
