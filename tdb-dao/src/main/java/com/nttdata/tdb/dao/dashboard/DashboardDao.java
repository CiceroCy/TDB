package com.nttdata.tdb.dao.dashboard;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

public interface DashboardDao {
	public DataTable searchChartData(String dateFrom, String dateUntil) throws TypeMismatchException;
}
