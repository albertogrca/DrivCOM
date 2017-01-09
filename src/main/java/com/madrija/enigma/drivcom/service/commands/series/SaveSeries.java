package com.madrija.enigma.drivcom.service.commands.series;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.malaguna.cmdit.service.reflection.ReflectionUtils;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.SeriesDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Series;
import com.madrija.enigma.drivcom.service.BeanNames;

public class SaveSeries extends ResultCommand<Series> {
	private ReflectionUtils ru = ReflectionUtils.getInstance();
	private SeriesDAO dao = null; 
	private Series series= null;

	public SaveSeries(BeanFactory bf) {
		super(bf);
		dao = (SeriesDAO) bf.getBean(BeanNames.SERIES_DAO);
		setAction(ActionHelper.SERIES_MGT);
		setReadOnly(false);
		setCanLog(true);
	}
	
	@Override
	public boolean isValid(){
		return super.isValid() && dao != null && series != null;
	}

	@Override
	public ResultCommand<Series> runCommand() throws Exception {

		if(series.getPid() == null){
			dao.persist(series);
			createLogComment("seriesMgt.newSeries.log", series);
			this.setResult(series);
		}else{
			StringBuffer msgBuf = new StringBuffer();
			String[] attrs = new String[] {"study", "number", "modality", "images"};
			
			Series aux = dao.findById(series.getPid());
			ru.compareAndUpdateAttributes(aux, series, attrs, true, msgBuf);
			dao.persist(aux);
			
			createLogComment("seriesMgt.updSeries.log", series, msgBuf);
			this.setResult(aux);
		}
		
		return this;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
}
