package com.nttdata.tdb.services.dashboard;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

/**
 * @author jean.lorenzini
 *
 */
public interface DashboardService {

    /**
     * @param params
     * @return DataTable
     */
    public DataTable getDasboardChart(String dateFrom, String dateUntil) throws TypeMismatchException;
}
