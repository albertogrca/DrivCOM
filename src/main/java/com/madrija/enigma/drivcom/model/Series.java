package com.madrija.enigma.drivcom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.malaguna.cmdit.model.DomainObject;

public class Series extends DomainObject<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Study study = null;
	
	private String number = null;
	private String modality = null;
	
	private List<Image> images = null;
	
	public void addImage(Image image){
		if(images == null)
			images = new ArrayList<Image>();
		images.add(image);
		image.setSeries(this);
	}
	
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
}
