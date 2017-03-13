package com.nttdata.tdb.dao.dashboard.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.ibm.icu.text.SimpleDateFormat;
import com.nttdata.tdb.dao.dashboard.DashboardDao;
import com.nttdata.tdb.dao.user.UserDao;
import com.nttdata.tdb.dto.TableDataDTO;

/**
 *
 * @author jean.lorenzini
 *
 */
@Repository
class DashboardDaoImpl implements DashboardDao {
	
	private static final Logger LOG = Logger.getLogger(DashboardDaoImpl.class.getName());
		
	//TODO (MELHORIA) Carregar querys a partir de arquivo
	
	// Querys Base Microstrategy
//	private final static String SQL_ASSIGNED_INC = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_INC_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_INC_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_TI INCIDENTE' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED') ) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_RESOLVED_INC = "SELECT to_char(SUB.FROM_DATE, 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(SUB.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_INC_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE 1 <= ( SELECT count(DISTINCT(HIST.TICKET_ID)) FROM FATO_TICKET_INC_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID WHERE HIST.TICKET_ID = SUB.TICKET_ID AND LOGIN.SOLICITANTE_COD IN (:userList) ) AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_TI INCIDENTE' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED') AND SUB.FROM_DATE >= (:startDate) AND SUB.FROM_DATE <= (:endDate) GROUP BY to_char(SUB.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_SLA_INC_OK = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_INC_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID	JOIN FATO_TICKET_INC_DIA INC ON INC.TICKET_ID = HIST.TICKET_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_INC_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_TI INCIDENTE' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED') ) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) AND INC.SLA_ATENDIDO_NO_PRAZO_ID = '1' GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_SLA_INC_NOK = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_INC_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID	JOIN FATO_TICKET_INC_DIA INC ON INC.TICKET_ID = HIST.TICKET_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_INC_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_TI INCIDENTE' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED') ) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) AND INC.SLA_ATENDIDO_NO_PRAZO_ID = '2' GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//		
//	private final static String SQL_ASSIGNED_PB = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_PR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_PR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_PR PROBLEMA' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'IMPROCEDENTE')) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_RESOLVED_PB = "SELECT to_char(SUB.FROM_DATE, 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(SUB.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_PR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE 1 <= ( SELECT count(DISTINCT(HIST.TICKET_ID)) FROM FATO_TICKET_PR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID WHERE HIST.TICKET_ID = SUB.TICKET_ID AND LOGIN.SOLICITANTE_COD IN (:userList) ) AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_PR PROBLEMA' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'IMPROCEDENTE') AND SUB.FROM_DATE >= (:startDate) AND SUB.FROM_DATE <= (:endDate) GROUP BY to_char(SUB.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_SLA_PB_OK = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_PR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID JOIN FATO_PROBLEMA_DIA PB ON HIST.TICKET_ID = PB.TICKET_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_PR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_PR PROBLEMA' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'IMPROCEDENTE')) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) AND PB.SLA_ATENDIDO_NO_PRAZO_ID = '1' GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_SLA_PB_NOK = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_PR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID JOIN FATO_PROBLEMA_DIA PB ON HIST.TICKET_ID = PB.TICKET_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_PR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_PR PROBLEMA' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'IMPROCEDENTE')) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) AND PB.SLA_ATENDIDO_NO_PRAZO_ID = '2' GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	
//	private final static String SQL_ASSIGNED_SR = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT, count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_SR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_SR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_SR SERVICE REQUEST' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED')) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_RESOLVED_SR = "SELECT to_char(SUB.FROM_DATE, 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(SUB.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_SR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID	WHERE 1 <= ( SELECT count(DISTINCT(HIST.TICKET_ID)) FROM FATO_TICKET_SR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID WHERE HIST.TICKET_ID = SUB.TICKET_ID AND LOGIN.SOLICITANTE_COD IN (:userList) ) AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_SR SERVICE REQUEST' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED') AND SUB.FROM_DATE >= (:startDate) AND SUB.FROM_DATE <= (:endDate) GROUP BY to_char(SUB.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_SLA_SR_OK = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT,  count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_SR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID JOIN FATO_TICKET_SR_DIA SR ON SR.TICKET_ID = HIST.TICKET_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_SR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_SR SERVICE REQUEST' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED')) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) AND SR.SLA_ATENDIDO_NO_PRAZO = 'SIM' GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
//	private final static String SQL_SLA_SR_NOK = "SELECT to_char(HIST.FROM_DATE, 'MM/YYYY') DATA_TEXT,  count(DISTINCT(HIST.TICKET_ID)) QTD_TICKET FROM FATO_TICKET_SR_HIST_DIA HIST JOIN LKP_MSTR_SOLICITANTE LOGIN ON LOGIN.SOLICITANTE_ID = HIST.SOLICITANTE_ID JOIN FATO_TICKET_SR_DIA SR ON SR.TICKET_ID = HIST.TICKET_ID WHERE 1 <= ( SELECT COUNT(SUB.TICKET_ID) FROM FATO_TICKET_SR_HIST_DIA SUB JOIN LKP_MSTR_STATUS_TICKET STATUS ON SUB.TO_STATUS_TICKET_ID = STATUS.STATUS_TICKET_ID WHERE SUB.TICKET_ID = HIST.TICKET_ID AND STATUS.FORMULARIO_TICKET_DESC = 'TBR_SR SERVICE REQUEST' AND (STATUS.STATUS_TICKET_DESC = 'CLOSED' OR STATUS.STATUS_TICKET_DESC = 'CANCELLED')) AND HIST.FROM_DATE >= (:startDate) AND HIST.FROM_DATE <= (:endDate) AND LOGIN.SOLICITANTE_COD IN (:userList) AND SR.SLA_ATENDIDO_NO_PRAZO = 'NAO' GROUP BY to_char(HIST.FROM_DATE,'MM/YYYY')";
	
	// Querys Base Prod
//	private final static String SQL_ASSIGNED_INC = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_TI_INCIDENTE INC ON HIST.ID_YEAR = INC.ID_YEAR WHERE HIST.SCHEMA = 'TBR_TI Incidente' AND HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate) AND HIST.SUBMITTER IN (:userList) AND (INC.STATUS = '8' OR INC.STATUS = '9') GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
	private final static String SQL_RESOLVED_INC = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR WHERE HIST.SCHEMA = 'TBR_TI Incidente' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Cancelled') GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
//	private final static String SQL_SLA_INC_OK = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR JOIN TBR_TI_INCIDENTE INC ON HIST.ID_YEAR = INC.ID_YEAR WHERE HIST.SCHEMA = 'TBR_TI Incidente' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Cancelled') AND INC.TEMPO_ALEM_SLA IS NULL GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
//	private final static String SQL_SLA_INC_NOK = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR JOIN TBR_TI_INCIDENTE INC ON HIST.ID_YEAR = INC.ID_YEAR WHERE HIST.SCHEMA = 'TBR_TI Incidente' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Cancelled') AND INC.TEMPO_ALEM_SLA IS NOT NULL GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
		
//	private final static String SQL_ASSIGNED_PB = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_PR_PROBLEMA PB ON HIST.ID_YEAR = PB.ID_YEAR WHERE HIST.SCHEMA = 'TBR_PR Problema' AND HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate) AND HIST.SUBMITTER IN (:userList) AND (PB.STATUS = '8' OR PB.STATUS = '9') GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
	private final static String SQL_RESOLVED_PB = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR WHERE HIST.SCHEMA = 'TBR_PR Problema' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Improcedente') GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
//	private final static String SQL_SLA_PB_OK = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR JOIN TBR_PR_PROBLEMA PB ON HIST.ID_YEAR = PB.ID_YEAR WHERE HIST.SCHEMA = 'TBR_PR Problema' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Improcedente') AND PB.TEMPO_ALEM_SLA IS NULL GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
//	private final static String SQL_SLA_PB_NOK = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR JOIN TBR_PR_PROBLEMA PB ON HIST.ID_YEAR = PB.ID_YEAR WHERE HIST.SCHEMA = 'TBR_PR Problema' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Improcedente') AND PB.TEMPO_ALEM_SLA IS NOT NULL GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
	
//	private final static String SQL_ASSIGNED_SR = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_SR_SERVICE_REQUEST SR ON HIST.ID_YEAR = SR.ID_YEAR WHERE HIST.SCHEMA = 'TBR_SR Service Request' AND HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate) AND HIST.SUBMITTER IN (:userList) AND (SR.STATUS = '8' OR SR.STATUS = '9') GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
	private final static String SQL_RESOLVED_SR = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR WHERE HIST.SCHEMA = 'TBR_SR Service Request' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Cancelled') GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
//	private final static String SQL_SLA_SR_OK = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR JOIN TBR_SR_SERVICE_REQUEST SR ON HIST.ID_YEAR = SR.ID_YEAR WHERE HIST.SCHEMA = 'TBR_SR Service Request' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Cancelled') AND SR.TEMPO_ALEM_SLA IS NULL GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
//	private final static String SQL_SLA_SR_NOK = "SELECT to_char(SECS_TO_DATE(HIST.TO_DATE), 'MM/YYYY') DATA_TEXT, COUNT(DISTINCT(HIST2.ID_YEAR)) QTD_TICKET FROM TBR_HISTORY_TABLE HIST JOIN TBR_HISTORY_TABLE HIST2 ON HIST.ID_YEAR = HIST2.ID_YEAR JOIN TBR_SR_SERVICE_REQUEST SR ON HIST.ID_YEAR = SR.ID_YEAR WHERE HIST.SCHEMA = 'TBR_SR Service Request' AND (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND HIST2.SUBMITTER IN (:userList) AND (HIST.TO_STATUS = 'Closed' OR HIST.TO_STATUS = 'Cancelled') AND SR.TEMPO_ALEM_SLA IS NOT NULL GROUP BY to_char(SECS_TO_DATE(HIST.TO_DATE),'MM/YYYY')";
	
	private final static String SQL_GENERAL = "SELECT ALL_TICKETS.ID_YEAR, to_char(SECS_TO_DATE(ALL_TICKETS.DATA_TEXT), 'MM/YYYY') TICKET_ASS_DATE, ALL_TICKETS.SCHEMA SCHEMA, SR.TEMPO_ALEM_SLA SLA_SR, PB.TEMPO_ALEM_SLA SLA_PB, INC.TEMPO_ALEM_SLA SLA_INC FROM (SELECT DISTINCT(HIST.ID_YEAR)ID_YEAR, HIST.TO_DATE DATA_TEXT, HIST.SCHEMA FROM TBR_HISTORY_TABLE HIST WHERE (HIST.TO_DATE >= (:startDate) AND HIST.TO_DATE <= (:endDate)) AND (HIST.SCHEMA = 'TBR_SR Service Request' OR HIST.SCHEMA = 'TBR_PR Problema' OR HIST.SCHEMA =  'TBR_TI Incidente') AND HIST.SUBMITTER IN (:userList) AND HIST.FLAG_TICKET_FECHADO = '0') ALL_TICKETS LEFT JOIN TBR_SR_SERVICE_REQUEST SR ON SR.ID_YEAR = ALL_TICKETS.ID_YEAR LEFT JOIN TBR_PR_PROBLEMA PB ON PB.ID_YEAR = ALL_TICKETS.ID_YEAR LEFT JOIN TBR_TI_INCIDENTE INC ON INC.ID_YEAR = ALL_TICKETS.ID_YEAR";
	
	@Autowired
	private UserDao userDao;
	
	@Resource(name = "namedJdbcTemplate")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public DataTable searchChartData(String dateFrom, String dateUntil) throws TypeMismatchException {
		
		LOG.log(Level.INFO, "Starting DashboardRepoImpl - searchChartData method");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Map<String, TableDataDTO> table = new HashMap<String, TableDataDTO>();
		
		List<String> matriculationList = userDao.findAllUserMatriculation();
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String dateFromString = "";
		String dateUntilString = "";
		
		try {
			dateFromString = String.valueOf(format.parse(dateFrom).getTime());
			dateFromString = dateFromString.substring(0, dateFromString.length()-3);
			dateUntilString = String.valueOf(format.parse(dateUntil).getTime());
			dateUntilString = dateUntilString.substring(0, dateUntilString.length()-3);
		} catch (ParseException e) {
			LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - searchChartData method: ", e);
		}	
		
		parameters.addValue("startDate", dateFromString);
		parameters.addValue("endDate", dateUntilString);
		parameters.addValue("userList", matriculationList);
		
		DataTable result = new DataTable();
		
		ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
		cd.add(new ColumnDescription("MonthYear", ValueType.TEXT, "MonthYear")); //0
		cd.add(new ColumnDescription("QTD_ASSIGNED_IN", ValueType.NUMBER, "Assigned IN")); //1
		cd.add(new ColumnDescription("ASS_ANNOTATION_IN", ValueType.TEXT, null)); //2
		cd.add(new ColumnDescription("QTD_RESOLVED_IN", ValueType.NUMBER, "Resolved IN")); //3
		cd.add(new ColumnDescription("RES_ANNOTATION_IN", ValueType.TEXT, null)); //4
		cd.add(new ColumnDescription("QTD_ASSIGNED_PB", ValueType.NUMBER, "Assigned PB")); //5
		cd.add(new ColumnDescription("ASS_ANNOTATION_PB", ValueType.TEXT, null)); //6
		cd.add(new ColumnDescription("QTD_RESOLVED_PB", ValueType.NUMBER, "Resolved PB")); //7
		cd.add(new ColumnDescription("RES_ANNOTATION_PB", ValueType.TEXT, null)); //8
		cd.add(new ColumnDescription("QTD_ASSIGNED_SR", ValueType.NUMBER, "Assigned SR")); //9
		cd.add(new ColumnDescription("ASS_ANNOTATION_SR", ValueType.TEXT, null)); //10
		cd.add(new ColumnDescription("QTD_RESOLVED_SR", ValueType.NUMBER, "Resolved SR")); //11
		cd.add(new ColumnDescription("RES_ANNOTATION_SR", ValueType.TEXT, null)); //12
		cd.add(new ColumnDescription("VRG_SLA_OK", ValueType.NUMBER, "VRG_SLA_OK")); //13
		cd.add(new ColumnDescription("DATE", ValueType.NUMBER, "DATE")); //14
		result.addColumns(cd);
		
		List<Map<String, Object>> data = namedJdbcTemplate.queryForList(SQL_GENERAL, parameters);
		processResult(table, data);
		
		data = namedJdbcTemplate.queryForList(SQL_RESOLVED_INC, parameters);
		fetchCountData("InResolved",table,data);

		data = namedJdbcTemplate.queryForList(SQL_RESOLVED_PB, parameters);
		fetchCountData("PbResolved",table,data);

		data = namedJdbcTemplate.queryForList(SQL_RESOLVED_SR, parameters);
		fetchCountData("SrResolved",table,data);
		
		for (Entry<String, TableDataDTO> row : table.entrySet()) {
			TableDataDTO value = row.getValue();
			try {
				result.addRowFromValues(row.getKey(), value.getInAssigned(),  value.getInAssigned()+" IN", value.getInResolved(), value.getInResolved()+" IN",value.getPbAssigned(), value.getPbAssigned()+" PB",value.getPbResolved(), value.getPbResolved()+" PB", value.getSrAssigned(),  value.getSrAssigned()+" SR", value.getSrResolved(), value.getSrResolved()+" SR", value.getAvrSlaOk(), format.parse("01/"+row.getKey()).getTime());
			} catch (ParseException e) {
				LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - searchChartData method: ", e);
			}
		}
		
		LOG.log(Level.INFO, "Finish DashboardRepoImpl - searchExemploChartData method");
		
		return result;
	}
	
	private void processResult(Map<String, TableDataDTO> table, List<Map<String, Object>> data){
		LOG.log(Level.INFO, "Starting DashboardRepoImpl - processResult method");
		for (Map<String, Object> row : data) {
			
			String dataText = (String)row.get("TICKET_ASS_DATE");
			
			if(table.containsKey(dataText)){
				TableDataDTO tableData = table.get(dataText);
				fetchRowData(row, tableData);
			} else{
				TableDataDTO tableData = new TableDataDTO();
				fetchRowData(row, tableData);
				table.put(dataText, tableData);
			}
    	}
		LOG.log(Level.INFO, "Finish DashboardRepoImpl - processResult method");
	}

	private void fetchRowData(Map<String, Object> row, TableDataDTO tableData) {
		
		LOG.log(Level.INFO, "Starting DashboardRepoImpl - fetchRowData method");
		
		String schema = (String)row.get("SCHEMA");
		
		switch (schema) {
			case "TBR_PR Problema":
				tableData.addPBAssigned();
				if(((BigDecimal)row.get("SLA_PB")) != null){
					tableData.addPBSlaNok();
				} else {
					tableData.addInSlaOk();
				}
				break;
			case "TBR_TI Incidente":
				tableData.addInAssigned();
				if(((BigDecimal)row.get("SLA_INC")) != null){
					tableData.addInSlaNok();
				} else {
					tableData.addInSlaOk();
				}
				break;
			case "TBR_SR Service Request":
				tableData.addSrAssigned();
				if(((BigDecimal)row.get("SLA_SR")) != null){
					tableData.addSrSlaNok();
				} else {
					tableData.addSrSlaOk();
				}
				break;
			default:
				break;
		}
		
		LOG.log(Level.INFO, "Finish DashboardRepoImpl - fetchRowData method");
	}

	private void fetchCountData(String type, Map<String, TableDataDTO> table, List<Map<String, Object>> data){
		LOG.log(Level.INFO, "Starting DashboardRepoImpl - fetchCountData method for type: " + type);
		for (Map<String, Object> row : data) {
			if(table.containsKey((String)row.get("DATA_TEXT"))){
				genericTableDataDTOSet(type, table.get((String)row.get("DATA_TEXT")),((BigDecimal)row.get("QTD_TICKET")).intValueExact());
			}else{
				table.put((String)row.get("DATA_TEXT"), new TableDataDTO());
				genericTableDataDTOSet(type, table.get((String)row.get("DATA_TEXT")),((BigDecimal)row.get("QTD_TICKET")).intValueExact());
			}
    	}
		LOG.log(Level.INFO, "Finish DashboardRepoImpl - fetchCountData method for type: " + type);
	}
	
	private void genericTableDataDTOSet(String methodName, Object obj, Integer parameter){
		LOG.log(Level.INFO, "Starting DashboardRepoImpl - genericTableDataDTOSet method");
		try {
			Method m = TableDataDTO.class.getMethod("set"+methodName, Integer.TYPE);
			m.invoke(obj, parameter);
		} catch (SecurityException e) {
			LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - genericTableDataDTOSet method: ", e);
		} catch (NoSuchMethodException e) {
			LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - genericTableDataDTOSet method: ", e);
		} catch (IllegalArgumentException e) {
			LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - genericTableDataDTOSet method: ", e);
		} catch (IllegalAccessException e) {
			LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - genericTableDataDTOSet method: ", e);
		} catch (InvocationTargetException e) {
			LOG.log(Level.ERROR, "ERROR DashboardRepoImpl - genericTableDataDTOSet method: ", e);
		}
		LOG.log(Level.INFO, "Finish DashboardRepoImpl - genericTableDataDTOSet method");
	}
	
}
