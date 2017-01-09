package com.madrija.enigma.drivcom.service;

import org.dcm4che3.tool.storescp.StoreSCP;

public class StoreSCPReceive {
	private String aet;
	private Integer port;
	
	public Boolean startReceive(){
		//"C:\\Users\\Mountain\\git\\DrivCOM\\data\\d1I00001.dcm"
		if(checkData()){
			String address ="-b "+ this.getAet()+":"+this.getPort();
			String[] args = {address};	
			StoreSCP.main(args);
			return true;
		}else{
			return false;
		}
	}
	
	private Boolean checkData(){
		if(this.getAet()!=null && this.getPort()!=null)
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
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

}
