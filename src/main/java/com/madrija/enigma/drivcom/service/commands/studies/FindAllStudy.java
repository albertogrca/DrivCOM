package com.madrija.enigma.drivcom.service.commands.studies;

import java.util.List;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.StudyDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Study;
import com.madrija.enigma.drivcom.service.BeanNames;

public class FindAllStudy extends ResultCommand<List<Study>> {
	private StudyDAO dao = null;
	
	public FindAllStudy(BeanFactory bf){
		super(bf);
		dao = (StudyDAO) bf.getBean(BeanNames.STUDY_DAO);
		setAction(ActionHelper.STUDY_MGT);
	}

	@Override
	public ResultCommand<List<Study>> runCommand() throws Exception {
		List<Study> result = null;
		
		result = dao.findAll();
		
		this.setResult(result);
		return null;
	}

}
