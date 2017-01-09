package com.madrija.enigma.drivcom.service.commands.studies;

import org.malaguna.cmdit.service.commands.Command;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.StudyDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Study;
import com.madrija.enigma.drivcom.service.BeanNames;

public class DeleteStudy extends Command {
	private Study study = null;
	private StudyDAO studyDAO = null;
	
	public DeleteStudy(BeanFactory bf){
		super(bf);
		setAction(ActionHelper.STUDY_MGT);
		this.studyDAO = (StudyDAO) this.getBeanFactory().getBean(BeanNames.STUDY_DAO);
	}
	
	@Override
	public boolean isValid(){
		return super.isValid() && study!=null && studyDAO!=null;
	}

	@Override
	public Command runCommand() throws Exception {
		this.studyDAO.delete(getStudy());
		return this;
	}


	public Study getStudy() {
		return study;
	}


	public void setStudy(Study study) {
		this.study = study;
	}

}
