package com.madrija.enigma.drivcom.service.commands.images;

import java.util.List;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.ImageDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Image;
import com.madrija.enigma.drivcom.service.BeanNames;

public class FindAllImage extends ResultCommand<List<Image>> {
	private ImageDAO dao = null;
	
	public FindAllImage(BeanFactory bf){
		super(bf);
		dao = (ImageDAO) bf.getBean(BeanNames.IMAGE_DAO);
		setAction(ActionHelper.IMAGE_MGT);
	}

	@Override
	public ResultCommand<List<Image>> runCommand() throws Exception {
		List<Image> result = null;
		
		result = dao.findAll();
		
		this.setResult(result);
		return null;
	}

}
