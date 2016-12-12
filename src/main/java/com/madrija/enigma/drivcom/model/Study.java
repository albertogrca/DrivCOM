package com.madrija.enigma.drivcom.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.malaguna.cmdit.model.DomainObject;

public class Study extends DomainObject<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Patient patient = null;
	private Date date = null;
	private Time time = null;
	private String physician = null;
	private String accesionNumber = null;
	
	private List<Series> series = null;
	
	public void addSerie(Series serie){
		if(series == null)
			series = new ArrayList<Series>();
		series.add(serie);
		serie.setStudy(this);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

	public String getAccesionNumber() {
		return accesionNumber;
	}

	public void setAccesionNumber(String accesionNumber) {
		this.accesionNumber = accesionNumber;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}
}
