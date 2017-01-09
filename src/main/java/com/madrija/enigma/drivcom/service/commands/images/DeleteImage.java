package com.madrija.enigma.drivcom.service.commands.images;

import org.malaguna.cmdit.service.commands.Command;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.ImageDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Image;
import com.madrija.enigma.drivcom.service.BeanNames;

public class DeleteImage extends Command {
	private Image image = null;
	private ImageDAO imageDAO = null;
	
	public DeleteImage(BeanFactory bf){
		super(bf);
		setAction(ActionHelper.IMAGE_MGT);
		this.imageDAO = (ImageDAO) this.getBeanFactory().getBean(BeanNames.IMAGE_DAO);
	}
	
	@Override
	public boolean isValid(){
		return super.isValid() && image!=null && imageDAO!=null;
	}

	@Override
	public Command runCommand() throws Exception {
		this.imageDAO.delete(getImage());
		return this;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}

}
