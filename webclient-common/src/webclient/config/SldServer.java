package webclient.config;

import webuita.general.Global;

public class SldServer {
	//refresh sld variables
//	Global.sldRefApiUrl =  parameters.get("sldRefApiUrl");
//	Global.sldPort =  parameters.get("sldPort");
//	Global.sldAccount =  parameters.get("sldAccount");
//	Global.sldPassword =  parameters.get("sldPassword");
	private String refApiUrl;
	private String port;
	private String account;
	private String password;
	
	public void setRefApiUrl(String refApiUrl){
		this.refApiUrl = refApiUrl;
	}
	public String getRefApiUrl(){
		return refApiUrl;
	}
	
	public void setPort(String port){
		this.port = port;
	}
	public String getPort(){
		return port;
	}
	
	public void setAccount(String account){
		this.account = account;
	}
	public String getAccount(){
		return account;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String gettPassword(){
		return password;
	}
}
