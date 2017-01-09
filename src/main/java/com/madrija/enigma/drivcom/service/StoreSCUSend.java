package com.madrija.enigma.drivcom.service;

import org.dcm4che3.tool.storescu.StoreSCU;

public class StoreSCUSend {
	
	private String aet;
	private String host;
	private Integer port;
	private String path;
	
	public Boolean startSend(){
		//"C:\\Users\\Mountain\\git\\DrivCOM\\data\\d1I00001.dcm"
		if(checkData()){
			String address = "-c "+this.getAet()+"@"+this.getHost()+":"+this.getPort();
			String[] args = {address,this.getPath()};	
			StoreSCU.main(args);
			return true;
		}else{
			return false;
		}
	}
	
	private Boolean checkData(){
		if(this.getAet()!=null && this.getHost()!=null && this.getPort()!=null && this.getPath()!=null)
			return true;
		else
			return false;
	}
	public String getAet() {
		return aet;
	}
	public void setAet(String aet) {
		this.aet = aet;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
