package com.nttdata.tdb.web.core.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.render.JsonRenderer;
import com.nttdata.tdb.services.dashboard.DashboardService;

/**
 * @author jean.lorenzini
 *
 */
@Controller
@RequestMapping(value = "/dashboard/action")
public class DashboardController {

	private static final Logger LOG = Logger.getLogger(DashboardController.class.getName());
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value = "/getTicketsData", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody String getTicketsData(@RequestParam(value = "dateFrom", defaultValue = "") String dateFrom, @RequestParam(value = "dateUntil", defaultValue = "") String dateUntil) {

		LOG.log(Level.INFO, "Executing DashController - getTicketsData method");
		
		DataTable data = new DataTable();
		
		try {
			data = dashboardService.getDasboardChart(dateFrom, dateUntil); 
		} catch (TypeMismatchException e) {
			LOG.log(Level.ERROR, "Error executing DashController - getTicketsData method: ", e);
		} 
				
		String result = JsonRenderer.renderDataTable(data, true, false, true).toString();
		LOG.log(Level.INFO, "Finishing DashController - getTicketsData method");
		
		return result;
	}

}
