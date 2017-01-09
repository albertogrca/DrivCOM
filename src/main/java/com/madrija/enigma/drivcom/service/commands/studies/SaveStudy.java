package com.madrija.enigma.drivcom.service.commands.studies;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.malaguna.cmdit.service.reflection.ReflectionUtils;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.StudyDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Study;
import com.madrija.enigma.drivcom.service.BeanNames;

public class SaveStudy extends ResultCommand<Study> {
	private ReflectionUtils ru = ReflectionUtils.getInstance();
	private StudyDAO dao = null; 
	private Study study= null;

	public SaveStudy(BeanFactory bf) {
		super(bf);
		dao = (StudyDAO) bf.getBean(BeanNames.STUDY_DAO);
		setAction(ActionHelper.STUDY_MGT);
		setReadOnly(false);
		setCanLog(true);
	}
	
	@Override
	public boolean isValid(){
		return super.isValid() && dao != null && study != null;
	}

	@Override
	public ResultCommand<Study> runCommand() throws Exception {

		if(study.getPid() == null){
			dao.persist(study);
			createLogComment("studyMgt.newStudy.log", study);
			this.setResult(study);
		}else{
			StringBuffer msgBuf = new StringBuffer();
			String[] attrs = new String[] {"patient", "date", "time", "physician", "accesionNumber"};
			
			Study aux = dao.findById(study.getPid());
			ru.compareAndUpdateAttributes(aux, study, attrs, true, msgBuf);
			dao.persist(aux);
			
			createLogComment("studyMgt.updStudy.log", study, msgBuf);
			this.setResult(aux);
		}
		
		return this;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
}
