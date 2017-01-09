package com.madrija.enigma.drivcom.service.commands.series;

import org.malaguna.cmdit.service.commands.ResultCommand;
import org.malaguna.cmdit.service.commands.generic.LoadAbstractObjCmd;
import org.malaguna.cmdit.service.reflection.HibernateProxyUtils;
import org.springframework.beans.factory.BeanFactory;

import com.madrija.enigma.drivcom.dao.SeriesDAO;
import com.madrija.enigma.drivcom.model.Series;
import com.madrija.enigma.drivcom.service.BeanNames;

public class LoadSeries extends LoadAbstractObjCmd<Series, Long> {
	private HibernateProxyUtils hpu = HibernateProxyUtils.getInstance();

	public LoadSeries(BeanFactory bf) {
		super(bf);
		setDao((SeriesDAO) bf.getBean(BeanNames.SERIES_DAO));
	}

	@Override
	public ResultCommand<Series> runCommand() throws Exception{
		ResultCommand<Series> result = super.runCommand();
		
		if(result != null && result.getResult() != null){
			hpu.unproxy(result.getResult());
		}
		
		return result;
	}
}
