package com.madrija.enigma.drivcom.service.commands.series;

import java.util.List;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.SeriesDAO;
import com.madrija.enigma.drivcom.model.ActionHelper;
import com.madrija.enigma.drivcom.model.Series;
import com.madrija.enigma.drivcom.service.BeanNames;

public class FindAllSeries extends ResultCommand<List<Series>> {
	private SeriesDAO dao = null;
	
	public FindAllSeries(BeanFactory bf){
		super(bf);
		dao = (SeriesDAO) bf.getBean(BeanNames.SERIES_DAO);
		setAction(ActionHelper.SERIES_MGT);
	}

	@Override
	public ResultCommand<List<Series>> runCommand() throws Exception {
		List<Series> result = null;
		
		result = dao.findAll();
		
		this.setResult(result);
		return null;
	}

}
