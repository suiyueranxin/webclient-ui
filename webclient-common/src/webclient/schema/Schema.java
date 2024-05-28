package webclient.schema;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.testng.Assert;

import webclient.config.CompanyInfo;
import webclient.config.HanaServer;
import webclient.config.SldServer;
import webclient.config.ThinClientServer;
import webuita.general.Global;

import com.google.gson.JsonObject;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.relevantcodes.extentreports.LogStatus;

public class Schema {
	private final static SftpProgressMonitor monitor = new SftpProgressMonitor() {
		long startTime, endTime;
		
        public void init(final int op, final String source, final String target, final long max) {
           System.out.println("sftp start uploading file from:" + source + " to:" + target);
           startTime = System.currentTimeMillis();
        }

        public boolean count(final long count) {
//        	System.out.println("sftp sending bytes: " + count);
            return true;
        }

        public void end() {
        	System.out.println("sftp uploading is done.");
        	endTime = System.currentTimeMillis();
        	System.out.println("Took:" + (endTime - startTime)/1000 + " s.");
        }
    };
    
    public static void getFileFromLinux(String hostname, String username, String password, String copyfrom, String copyto)
			throws JSchException, SftpException{
		System.out.println("Initiate getting file from Linux Server...");
        JSch jsch = new JSch();
        Session session = null;
        System.out.println("Trying to connect.....");
        session = jsch.getSession(username, hostname, 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();
        System.out.println("is server connected? " + session.isConnected());

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        try {
        	System.out.println(sftpChannel.getHome());
        } catch (SftpException e1) {
            e1.printStackTrace();
        }
        try {
            sftpChannel.get(copyfrom, copyto, monitor, ChannelSftp.OVERWRITE);
        } catch (SftpException e) {
        	System.out.println("file was not found: " + copyfrom);
        }

        sftpChannel.exit();
        session.disconnect();
        System.out.println("Finished getting file from Linux Server...");
	}
    
	public static void copyDbBackup(String hostname, String username, String password, String copyWinfrom, String copyLinuxto){
		try{
			 JSch jsch=new JSch();  
			 Session session = null;
			 System.out.println("Trying to connect.....");
			 session = jsch.getSession(username, hostname, 22);
		     session.setConfig("StrictHostKeyChecking", "no");
		     session.setPassword(password);
		     session.connect();
		     System.out.println("is server connected? " + session.isConnected());
		     Channel channel = session.openChannel("sftp");
		     channel.connect();
		     ChannelSftp sftpChannel = (ChannelSftp) channel;
		     sftpChannel.put(copyWinfrom, copyLinuxto, monitor, ChannelSftp.OVERWRITE);
		     sftpChannel.exit();
		     channel.disconnect();
		     session.disconnect();
		}catch(Exception ex){
			
		}
		
	}
	
	public static int execCmdOnLinuxRetStatusCode(Session session, String cmdString){
		int statusCode = -1;
		try{
			 ChannelExec execChannel=(ChannelExec) session.openChannel("exec");
		     execChannel.setCommand(cmdString);
		     
		     StringBuilder errorBuffer = new StringBuilder();
		     InputStream err = execChannel.getExtInputStream();
		     StringBuilder outputBuffer = new StringBuilder();
		     InputStream in = execChannel.getInputStream();
		     
		     execChannel.connect();
		     	     
		     byte[] tmp = new byte[1024];
		     while(true){
		     	while(in.available() > 0){
		     		int i = in.read(tmp, 0, 1024);
		     		if(i < 0){
		     			break;
		      		}
		     		outputBuffer.append(new String(tmp, 0, i));
		     	}
		     	while (err.available() > 0) {
		            int i = err.read(tmp, 0, 1024);
		            if (i < 0) break;
		            errorBuffer.append(new String(tmp, 0, i));
		        }
		      	if(execChannel.isClosed()){
		      		statusCode = execChannel.getExitStatus();
		      		break;
		  		}
		  		try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		     if(statusCode !=  0){
			   Assert.fail("Execute cmd \"" + cmdString + "\" show an error: " + errorBuffer.toString());
		     }
		     execChannel.disconnect();
		     in.close();

		}catch(Exception ex){
			statusCode = -1;
		}
		
	    return statusCode;
	}
	
	public static int execCmdOnLinuxRetStatusCode(String hostname, String username, String password, String cmdString){
		int statusCode = -1;
		try{
			 JSch jsch=new JSch();  
			 Session session = null;
			 System.out.println("Trying to connect.....");
			 session = jsch.getSession(username, hostname, 22);
		     session.setConfig("StrictHostKeyChecking", "no");
		     session.setPassword(password);
		     session.connect();
		     System.out.println("is server connected? " + session.isConnected());
		    
		     ChannelExec execChannel=(ChannelExec) session.openChannel("exec");
		     execChannel.setCommand(cmdString);
		    
		     StringBuilder errorBuffer = new StringBuilder();
		     InputStream err = execChannel.getExtInputStream();
		     StringBuilder outputBuffer = new StringBuilder();
		     InputStream in = execChannel.getInputStream();
		     execChannel.connect();
		     
		     byte[] tmp = new byte[1024];
		     while(true){
		     	while(in.available() > 0){
		     		int i = in.read(tmp, 0, 1024);
		     		if(i < 0){
		     			break;
		      		}
		     		
		     		outputBuffer.append(new String(tmp, 0, i));
		     		System.out.println(outputBuffer.toString());
		      	}
		     	while (err.available() > 0) {
		            int i = err.read(tmp, 0, 1024);
		            if (i < 0) break;
		            errorBuffer.append(new String(tmp, 0, i));
		        }
		      	if(execChannel.isClosed()){
		      		statusCode = execChannel.getExitStatus();
		      		break;
		  		}
		  		try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		    if(statusCode !=  0){
		    	Assert.fail("Execute cmd \"" + cmdString + "\" show an error: " + errorBuffer.toString());
	      	}
		     execChannel.disconnect();
		     in.close();
		     session.disconnect();

		}catch(Exception ex){
			
		}
		
	    return statusCode;
	}
	
	public static String execCmdOnLinuxRetValue(Session session, String cmdString){
		StringBuffer ret = new StringBuffer();
		try{
			 ChannelExec execChannel=(ChannelExec) session.openChannel("exec");
		     execChannel.setCommand(cmdString);
		     execChannel.connect();
		      
		     InputStream in = execChannel.getInputStream();
		     execChannel.setErrStream(System.err);

		     byte[] tmp = new byte[1024];
		     while(true){
		     	while(in.available() > 0){
		     		int i = in.read(tmp, 0, 1024);
		     		if(i < 0){
		     			break;
		      		}
		     		ret.append(new String(tmp, 0, i));
		      	}
		      	if(execChannel.isClosed()){
		      		System.out.println("exit-status: " + execChannel.getExitStatus());
		      		break;
		  		}
		  		try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		     
		     
		     execChannel.disconnect();
		     in.close();
		    
		}catch(Exception ex){
			
		}
		
	    return ret.toString();
	}
	
	public static String execCmdOnLinuxRetValue(String hostname, String username, String password, String cmdString){
		String ret = "";
		try{
			 JSch jsch=new JSch();  
			 Session session = null;
			 System.out.println("Trying to connect.....");
			 session = jsch.getSession(username, hostname, 22);
		     session.setConfig("StrictHostKeyChecking", "no");
		     session.setPassword(password);
		     session.connect();
		     System.out.println("is server connected? " + session.isConnected());
		    
		     ChannelExec execChannel=(ChannelExec) session.openChannel("exec");
		     execChannel.setCommand(cmdString);
		     execChannel.connect();
		      
		     InputStream in = execChannel.getInputStream();
		     execChannel.setErrStream(System.err);

		     byte[] tmp = new byte[1024];
		     while(true){
		     	while(in.available() > 0){
		     		int i = in.read(tmp, 0, 1024);
		     		if(i < 0){
		     			break;
		      		}
		      		System.out.print(new String(tmp, 0, i));
		      	}
		      	if(execChannel.isClosed()){
		      		System.out.println("exit-status: " + execChannel.getExitStatus());
		      		break;
		  		}
		  		try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		     ret = tmp.toString().replaceAll("\\n", "");
		     execChannel.disconnect();
		     in.close();
		     session.disconnect();
		}catch(Exception ex){
			Assert.fail("Execute cmd \"" + cmdString + "\" show an error: " + ex.toString());
		}
		
	    return ret;
	}
	public static boolean execHanaSql(String hostname, String hostport, String username, String password, String sql){
		String url = "jdbc:sap://" + hostname + ":" +hostport;
		Properties info = new java.util.Properties();
		info.put("databaseName", "DBA");
		info.put("user", username);
		info.put("password", password);
		info.put("reconnect", true);
		Connection conn = null;
		PreparedStatement  pstmt = null;

		try{
			conn  = DriverManager.getConnection(url, info);
		}catch(Exception ex){
			System.err.println("Connection Failed. User/Password Error?");
			Assert.fail("Connection Failed. User/Password Error?");
			return false;
		}
		
		if(conn != null){
			try{
				System.out.println("Connection to HANA successful!");
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(SQLException e){
				System.err.println("Query failed!");
				Assert.fail("Execute hana sql query: \"" + sql + "\" show an error: " + e.toString());
				return false;
			}finally{
				 try { if (pstmt != null) pstmt.close(); } catch (Exception e) {};
				 try { if (conn != null) conn.close(); } catch (Exception e) {};
			}
			
		}
		
		return true;
	}
	 //write in windows vm
	
    public static void writeDBName(String dbNameFile, String dbNameString){
    	
    	try{
    		File f = new File(dbNameFile);
    		if(!f.exists()){
    			String fp = f.getParent();
    			File path = new File(fp);
    			if(!path.exists()){
    				path.mkdirs();
    			}
    			
    			f.createNewFile();
    		}
    		FileWriter fw = new FileWriter(dbNameFile, true);
    		fw.write(dbNameString + System.getProperty( "line.separator" ));
    		fw.flush();
    		fw.close();
    	}catch(Exception ex){
    		System.err.println("Failed to write DB Name with: " + ex.toString());
    	}
    }
    
    /**
     * 
     * @return	new company db name
     */
	public static String restoreInitCompany(HanaServer hanaServer, ThinClientServer tcServer, CompanyInfo company, 
			boolean isLinuxDbbackupSrcPath, String writeDBNameFile){
		String newDBName = null;
		try{
			
			long startTime, endTime;
			startTime = System.currentTimeMillis();
			String strRandom = Long.toString(startTime);
		    strRandom = strRandom.substring(strRandom.length() - 7);
		    newDBName = company.getNewDbName() + strRandom;
		   
		    // for deleting company db
		    
		     if(writeDBNameFile.equalsIgnoreCase("yes")){
		    	 writeDBName("c:\\HANA\\DBName.txt", company.getDbName() +":" + newDBName);
		     }
		     
		     Session session = null;
		     try{
		    	 String dbbackupDestPath = hanaServer.getDbbackupSrcPath();
		    	 JSch jsch=new JSch();  
				 System.out.println("Trying to connect.....");
				 session = jsch.getSession(hanaServer.getUsername(), hanaServer.getHostIp(), 22);
			     session.setConfig("StrictHostKeyChecking", "no");
			     session.setPassword(hanaServer.getPassword());
			     session.connect();
			     System.out.println("is server connected? " + session.isConnected());
			     
		    	 if(!isLinuxDbbackupSrcPath){
		    		 dbbackupDestPath = "/tmp/db_backup/";
		    		 System.out.println("start to copy db backup......");
				     Channel channel = session.openChannel("sftp");
				     channel.connect();
				     ChannelSftp sftpChannel = (ChannelSftp) channel;
				     sftpChannel.put(hanaServer.getDbbackupSrcPath() + company.getDbBackupName(), 
				    		 "/tmp/db_backup/" + company.getDbBackupName(),monitor, ChannelSftp.OVERWRITE);
				     sftpChannel.exit();
				     channel.disconnect();
				
		    	 }
		    	 String dbbackupFullPath = dbbackupDestPath + company.getDbName();
		    	 String cmd = "test -d " + dbbackupFullPath + " && echo \"exist\" || echo \"does not exist\"";
			     String tempIsExist = Schema.execCmdOnLinuxRetValue(session, cmd);
			     if(!tempIsExist.replaceAll("\\n", "").equalsIgnoreCase("exist")){
			    	//uncompress backup file
				     cmd = "mkdir " + dbbackupFullPath + "; chmod 777 " + dbbackupFullPath +
				    		 "; tar -xf " + dbbackupDestPath + company.getDbBackupName() +" -C " + dbbackupFullPath;
				     if(Schema.execCmdOnLinuxRetStatusCode(session, cmd) == 0){
				    	 System.out.println("Success to uncompress \"" + company.getDbBackupName() + "\"!" );
				     }else{
				    	 System.err.println("Fail to uncompress \"" + company.getDbBackupName() + "\"!");
				    	 Assert.fail("Fail to uncompress \"" + company.getDbBackupName() + "\"!" );
				     }
			     }
			     //get the schema name, then rename
			     String schemaName = Schema.execCmdOnLinuxRetValue(session, "ls -t "+ dbbackupFullPath +"/export | head -1");
			     String tmpSchemaName = schemaName.replaceAll("\\n", "");
				 String sql =  "import " + tmpSchemaName + ".\"*\" as binary from '"+ dbbackupFullPath
							+ "' with ignore existing threads 10 rename schema " + "\"" + tmpSchemaName + "\" to \"" + newDBName + "\"";
				 if(Schema.execHanaSql(hanaServer.getHostIp(), hanaServer.getPort(), hanaServer.getDbUsername(), 
			    			hanaServer.getDbPassword(), sql)){
					    System.out.println("Success to import the company db: \"" + newDBName + "\"!");
//					    if(tcCodeLine.equalsIgnoreCase("dev")){
//					    	cmd = "cd " + tcbuildPath + "; " + sapJvmPath + "java -jar install.jar -s https://" + hanaServerHost + ":50000 -c " + 
//					    			newDBName + " -u " + companyDbusername + " -p " + companyDbpassword+ " -h " + hanaServerHost + ":" + hanaServerPort + " -x " + 
//					    			hanaDBUsername + " -w " + hanaDBPassword; 
//					    }else{
//					    	cmd = "cd " + tcbuildPath + "; " + sapJvmPath + "java -jar install.jar -s https://" + hanaServerHost + ":50000 -c " + 
//					    			newDBName + " -u " + companyDbusername + " -p " + companyDbpassword+ " -h " + hanaServerHost + ":" + hanaServerPort + " -x " + 
//					    			hanaDBUsername + " -w " + hanaDBPassword; 
//					    }
//					    
//			    		if(Schema.execCmdOnLinuxRetStatusCode(tcServerHost, tcServerUsername, tcServerPassword, cmd) == 0){
//			    			System.out.println("Success to initialize the company db: \"" + newDBName +"\" on thin client.");
//			    		}else{
//			    			System.out.println("Fail to initialize the company db: \"" + newDBName +"\" on thin client.");
//			    			Assert.assertTrue(false, "Fail to initialize the company db: \"" + newDBName +"\" on thinc client.");
//			    		}
			    	}else{
			    		System.err.println("Faile to restore the company db: \"" + newDBName + "\"");
			    		Assert.assertTrue(false, "Faile to restore the company db: \"" + newDBName + "\"");
			    	}  	
		
		     }catch(Exception ex){
		    	 System.err.println("is server connected? " + session.isConnected());
		    	 Assert.fail("is server connected? " + session.isConnected());
		    	 throw(ex);
		     }finally{
		    	 session.disconnect(); 
		     }
		     
		     endTime = System.currentTimeMillis();
			System.out.println("Took:" + (endTime - startTime)/1000 + " ms.");
			//waiting the tomcat refresh, show the company
			Thread.sleep(10000);
		}catch(Exception ex){
			System.err.println("Faile to restore the company db with: \"" + ex.toString() + "\"");
    		Assert.assertTrue(false, "Faile to restore the company db with: \"" + ex.toString() + "\"");
    		
		}
		return newDBName;
	}
	
	 /**
     * 
     * @return	new company db name
	 * @throws Exception 
     */
	public static String restoreInitCompanyNoAssert(HanaServer hanaServer, ThinClientServer tcServer, CompanyInfo company, 
			boolean isLinuxDbbackupSrcPath, String writeDBNameFile) throws Exception{
		String newDBName = null;
		try{
			
			long startTime, endTime;
			startTime = System.currentTimeMillis();
			String strRandom = Long.toString(startTime);
		    strRandom = strRandom.substring(strRandom.length() - 7);
		    newDBName = company.getNewDbName() + strRandom;
		   
		    // for deleting company db
		    
		     if(writeDBNameFile.equalsIgnoreCase("yes")){
		    	 writeDBName("c:\\HANA\\DBName.txt", company.getDbName() +":" + newDBName);
		     }
		     
		     Session session = null;
		     try{
		    	 String dbbackupDestPath = hanaServer.getDbbackupSrcPath();
		    	 JSch jsch=new JSch();  
				 System.out.println("Trying to connect.....");
				 session = jsch.getSession(hanaServer.getDbUsername(), hanaServer.getHostIp(), 22);
			     session.setConfig("StrictHostKeyChecking", "no");
			     session.setPassword(hanaServer.getPassword());
			     session.connect();
			     System.out.println("is server connected? " + session.isConnected());
			     
		    	 if(!isLinuxDbbackupSrcPath){
		    		 dbbackupDestPath = "/tmp/db_backup/";
		    		 System.out.println("start to copy db backup......");
				     Channel channel = session.openChannel("sftp");
				     channel.connect();
				     ChannelSftp sftpChannel = (ChannelSftp) channel;
				     sftpChannel.put(hanaServer.getDbbackupSrcPath() + company.getDbBackupName(), "/tmp/db_backup/" + company.getDbBackupName(),
				    		 monitor, ChannelSftp.OVERWRITE);
				     sftpChannel.exit();
				     channel.disconnect();
				
		    	 }
		    	 String dbbackupFullPath = dbbackupDestPath + company.getDbName();
		    	 String cmd = "test -d " + dbbackupFullPath + " && echo \"exist\" || echo \"does not exist\"";
			     String tempIsExist = Schema.execCmdOnLinuxRetValue(session, cmd);
			     if(!tempIsExist.replaceAll("\\n", "").equalsIgnoreCase("exist")){
			    	//uncompress backup file
				     cmd = "mkdir " + dbbackupFullPath + "; chmod 777 " + dbbackupFullPath +
				    		 "; tar -xf " + dbbackupDestPath + company.getDbBackupName() +" -C " + dbbackupFullPath;
				     if(Schema.execCmdOnLinuxRetStatusCode(session, cmd) == 0){
				    	 System.out.println("Success to uncompress \"" + company.getDbBackupName() + "\"!" );
				     }else{
				    	 System.err.println("Fail to uncompress \"" + company.getDbBackupName() + "\"!");
				    	 throw new Exception("Fail to uncompress \"" + company.getDbBackupName() + "\"!");
				     }
			     }
			     //get the schema name, then rename
			     String schemaName = Schema.execCmdOnLinuxRetValue(session, "ls -t "+ dbbackupFullPath +"/export | head -1");
			     String tmpSchemaName = schemaName.replaceAll("\\n", "");
				 String sql =  "import " + tmpSchemaName + ".\"*\" as binary from '"+ dbbackupFullPath
							+ "' with ignore existing threads 10 rename schema " + "\"" + tmpSchemaName + "\" to \"" + newDBName + "\"";
				 if(Schema.execHanaSql(hanaServer.getHostIp(), hanaServer.getPort(), hanaServer.getDbUsername(), 
			    			hanaServer.getDbPassword(), sql)){
					    System.out.println("Success to import the company db: \"" + newDBName + "\"!");
//					    if(tcCodeLine.equalsIgnoreCase("dev")){
//					    	cmd = "cd " + tcbuildPath + "; " + sapJvmPath + "java -jar install.jar -s https://" + hanaServerHost + ":50000 -c " + 
//					    			newDBName + " -u " + companyDbusername + " -p " + companyDbpassword+ " -h " + hanaServerHost + ":" + hanaServerPort + " -x " + 
//					    			hanaDBUsername + " -w " + hanaDBPassword; 
//					    }else{
//					    	cmd = "cd " + tcbuildPath + "; " + sapJvmPath + "java -jar install.jar -s https://" + hanaServerHost + ":50000 -c " + 
//					    			newDBName + " -u " + companyDbusername + " -p " + companyDbpassword+ " -h " + hanaServerHost + ":" + hanaServerPort + " -x " + 
//					    			hanaDBUsername + " -w " + hanaDBPassword; 
//					    }
//					    
//			    		if(Schema.execCmdOnLinuxRetStatusCode(tcServerHost, tcServerUsername, tcServerPassword, cmd) == 0){
//			    			System.out.println("Success to initialize the company db: \"" + newDBName +"\" on thin client.");
//			    		}else{
//			    			System.out.println("Fail to initialize the company db: \"" + newDBName +"\" on thin client.");
//			    			throw new Exception("Fail to initialize the company db: \"" + newDBName +"\" on thinc client.");
//			    		}
			    	}else{
			    		System.err.println("Faile to restore the company db: \"" + newDBName + "\"");
			    		throw new Exception("Faile to restore the company db: \"" + newDBName + "\"");
			    	}  	
		
		     }catch(Exception ex){
		    	 System.err.println("is server connected? " + session.isConnected());
		    	 throw(ex);
		     }finally{
		    	 session.disconnect(); 
		     }
		     
		     endTime = System.currentTimeMillis();
			System.out.println("Took:" + (endTime - startTime)/1000 + " ms.");
			//waiting the tomcat refresh, show the company
			Thread.sleep(10000);
		}catch(Exception ex){
			System.err.println("Faile to restore the company db with: \"" + ex.toString() + "\"");
			throw(ex);
    		
		}
		return newDBName;
	}
	
	//----------- refresh SLD schema ------------
	public static int refreshSLDSchema(String url, String reqBody){
		int retCode = 0;
		try{
						
			URL urlHttp = new URL(url); 
			HttpURLConnection  conn = (HttpURLConnection) urlHttp.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.connect();
			
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(reqBody);
			out.flush();
			out.close();
			
			retCode = conn.getResponseCode();
			
			conn.disconnect();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return retCode;
	}
	public static int refreshSLDSchema(SldServer sldServer, HanaServer hanaServer){
		int retCode = 0;
		try{
			
			//refresh SLD Schema
			JsonObject jo = new JsonObject();
		    jo.addProperty("server", hanaServer.getHostIp());
		    jo.addProperty("hanaPort", hanaServer.getPort());
		    jo.addProperty("sldPort", sldServer.getPort());
		    jo.addProperty("account", sldServer.getAccount());
		    jo.addProperty("password", sldServer.gettPassword());
		    
		    
			URL urlHttp = new URL(sldServer.getRefApiUrl()); 
			HttpURLConnection  conn = (HttpURLConnection) urlHttp.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.connect();
			
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(jo.toString());
			out.flush();
			out.close();
			
			retCode = conn.getResponseCode();
			
			conn.disconnect();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return retCode;
	}
}
