package com.madrija.enigma.drivcom.service.commands.images;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.malaguna.cmdit.service.reflection.ReflectionUtils;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.ImageDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Image;
import com.madrija.enigma.drivcom.service.BeanNames;

public class SaveImage extends ResultCommand<Image> {
	private ReflectionUtils ru = ReflectionUtils.getInstance();
	private ImageDAO dao = null; 
	private Image image= null;

	public SaveImage(BeanFactory bf) {
		super(bf);
		dao = (ImageDAO) bf.getBean(BeanNames.IMAGE_DAO);
		setAction(ActionHelper.IMAGE_MGT);
		setReadOnly(false);
		setCanLog(true);
	}
	
	@Override
	public boolean isValid(){
		return super.isValid() && dao != null && image != null;
	}

	@Override
	public ResultCommand<Image> runCommand() throws Exception {

		if(image.getPid() == null){
			dao.persist(image);
			createLogComment("imageMgt.newImage.log", image);
			this.setResult(image);
		}else{
			StringBuffer msgBuf = new StringBuffer();
			String[] attrs = new String[] {"series", "number", "type"};
			
			Image aux = dao.findById(image.getPid());
			ru.compareAndUpdateAttributes(aux, image, attrs, true, msgBuf);
			dao.persist(aux);
			
			createLogComment("imageMgt.updImage.log", image, msgBuf);
			this.setResult(aux);
		}
		
		return this;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
