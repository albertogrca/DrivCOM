package com.madrija.enigma.drivcom.service.commands.studies;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.malaguna.cmdit.service.commands.generic.LoadAbstractObjCmd;
import org.malaguna.cmdit.service.reflection.HibernateProxyUtils;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.StudyDAO;
import com.madrija.enigma.drivcom.model.Study;
import com.madrija.enigma.drivcom.service.BeanNames;

public class LoadStudy extends LoadAbstractObjCmd<Study, Long> {
	private HibernateProxyUtils hpu = HibernateProxyUtils.getInstance();

	public LoadStudy(BeanFactory bf) {
		super(bf);
		setDao((StudyDAO) bf.getBean(BeanNames.STUDY_DAO));
	}

	@Override
	public ResultCommand<Study> runCommand() throws Exception{
		ResultCommand<Study> result = super.runCommand();
		
		if(result != null && result.getResult() != null){
			hpu.unproxy(result.getResult());
		}
		
		return result;
	}
}
