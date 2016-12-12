package com.madrija.enigma.drivcom.model;

import java.io.Serializable;

import org.malaguna.cmdit.model.DomainObject;

public class Image extends DomainObject<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Series series = null;
	private String number = null;
	private String type = null;
	
	public Series getSeries() {
		return series;
	}
	public void setSeries(Series series) {
		this.series = series;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
