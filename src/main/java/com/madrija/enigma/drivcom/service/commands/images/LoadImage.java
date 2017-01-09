package com.madrija.enigma.drivcom.service.commands.images;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.malaguna.cmdit.service.commands.generic.LoadAbstractObjCmd;
import org.malaguna.cmdit.service.reflection.HibernateProxyUtils;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.ImageDAO;
import com.madrija.enigma.drivcom.model.Image;
import com.madrija.enigma.drivcom.service.BeanNames;

public class LoadImage extends LoadAbstractObjCmd<Image, Long> {
	private HibernateProxyUtils hpu = HibernateProxyUtils.getInstance();

	public LoadImage(BeanFactory bf) {
		super(bf);
		setDao((ImageDAO) bf.getBean(BeanNames.IMAGE_DAO));
	}

	@Override
	public ResultCommand<Image> runCommand() throws Exception{
		ResultCommand<Image> result = super.runCommand();
		
		if(result != null && result.getResult() != null){
			hpu.unproxy(result.getResult());
		}
		
		return result;
	}
}
