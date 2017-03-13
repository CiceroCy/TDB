package com.nttdata.tdb.services.dashboard.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;
import com.nttdata.tdb.dao.dashboard.DashboardDao;
import com.nttdata.tdb.services.dashboard.DashboardService;

@Service
class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardDao dashboardDao;
	
	private static final Logger LOG = Logger.getLogger(DashboardServiceImpl.class.getName());

	@Override
	public DataTable getDasboardChart(String dateFrom, String dateUntil) throws TypeMismatchException {
		
		LOG.log(Level.INFO, "Executing DashboardServiceImpl - getDasboardChart method");
		
		//Realiza chamada ao DAO para buscar dados do chart
		DataTable result = dashboardDao.searchChartData(dateFrom, dateUntil);
		
		LOG.log(Level.INFO, "Finishing DashboardServiceImpl - getDasboardChart method");
		
		return result;
	}

}
