package com.madrija.enigma.drivcom.service.commands.series;

import org.malaguna.cmdit.service.commands.Command;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.SeriesDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Series;
import com.madrija.enigma.drivcom.service.BeanNames;

public class DeleteSeries extends Command {
	private Series series = null;
	private SeriesDAO seriesDAO = null;
	
	public DeleteSeries(BeanFactory bf){
		super(bf);
		setAction(ActionHelper.SERIES_MGT);
		this.seriesDAO = (SeriesDAO) this.getBeanFactory().getBean(BeanNames.SERIES_DAO);
	}
	
	@Override
	public boolean isValid(){
		return super.isValid() && series!=null && seriesDAO!=null;
	}

	@Override
	public Command runCommand() throws Exception {
		this.seriesDAO.delete(getSeries());
		return this;
	}


	public Series getSeries() {
		return series;
	}


	public void setSeries(Series series) {
		this.series = series;
	}

}
