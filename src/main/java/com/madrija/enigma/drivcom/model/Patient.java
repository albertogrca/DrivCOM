package com.madrija.enigma.drivcom.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.malaguna.cmdit.model.DomainObject;

public class Patient extends DomainObject<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name = null;
	private String surname = null;
	private Date birthDate = new Date();
	private String gender = null;
	
	private List<Study> studies = null;
	
	public Patient() {
		super();
	}
	
	public int getAge(){
		Integer age = 0;
		if(birthDate != null){
			Calendar c = Calendar.getInstance();
			c.setTime(birthDate);
			LocalDate birth = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
			LocalDate now = LocalDate.now();
			age = (int)ChronoUnit.YEARS.between(birth, now);
		}
		return age;
	}
	
	public void addStudy(Study study){
		if(studies == null)
			studies = new ArrayList<Study>();
		study.setPatient(this);
		studies.add(study);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}
	
}
