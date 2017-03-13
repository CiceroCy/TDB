<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="ntt" tagdir="/WEB-INF/tags"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<ntt:messages messages="${command.showMessages}"></ntt:messages>

	<!-- Mensagens de erros -->
	<div class="row" align="center">
		<div id="errorRequiredField" style="display: none">
			<div class="input-size alert alert-error">
				<spring:message code="msg.notBlank"/>
			</div>
		</div>
		<div id="errorUntilLowerThanFrom" style="display: none">
			<div class="input-size alert alert-error">
				<spring:message code="msg.untilLowerThanFrom"/>
			</div>
		</div>		
	</div>

	<form:form class="form-horizontal" id="formData">
		<div ng-app="" class="well row margin"> <%-- ng-controller="myController" --%>
			<div class="row-fluid">
				<div class="span2">
					<label><span class="requiredField">*</span><spring:message code="page.dashboard.label.from"/></label>
					<div class="input-append date dateCalendarNtt">
			        	<input type="text" class="dateMask input-size" id="dateFrom" /><span class="add-on"><i class="icon-calendar"></i></span>
				     </div>
			    </div>
			    <div class="span5">
				    <label><span class="requiredField">*</span><spring:message code="page.dashboard.label.until"/></label>
					<div class="input-append date dateCalendarNtt">
			        	<input type="text" class="dateMask input-size" id="dateUntil" /><span class="add-on"><i class="icon-calendar"></i></span>
				     </div>
			     </div>
		     </div>
			 <div class="row-fluid" align="right">
			 	<input type="button" class="btn" value="<spring:message code="page.btn.clear"/>" onclick="location.href='home.htm'" />
			 	<input type="button" onclick="drawChart()" class="btn btn-primary" value="<spring:message code="page.btn.search"/>"/> 
			 </div>
		</div>	

		<div ng-app="" class="well row margin">
			<div class="table-scroll">
				<table class="table table-bordered back-white"  id="tb">
					<thead>
						<tr>
							<th/>

							<th colspan="3">out/2015</th>
							<th colspan="3" bgcolor="#F5F5F5">nov/2015</th>
							<th colspan="3">dez/2015</th>
							<th colspan="3" bgcolor="#F5F5F5">jan/2016</th>
							<th colspan="3">fev/2016</th>
							<th colspan="3" bgcolor="#F5F5F5">mar/2016</th>
						<tr>	
						<tr>
							<th/>
							<th><spring:message code="page.dashboard.label.pb"/></th>
							<th><spring:message code="page.dashboard.label.in"/></th>
							<th><spring:message code="page.dashboard.label.sr"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.pb"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.in"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.sr"/></th>
							<th><spring:message code="page.dashboard.label.pb"/></th>
							<th><spring:message code="page.dashboard.label.in"/></th>
							<th><spring:message code="page.dashboard.label.sr"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.pb"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.in"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.sr"/></th>
							<th><spring:message code="page.dashboard.label.pb"/></th>
							<th><spring:message code="page.dashboard.label.in"/></th>
							<th><spring:message code="page.dashboard.label.sr"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.pb"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.in"/></th>
								<th bgcolor="#F5F5F5"><spring:message code="page.dashboard.label.sr"/></th>
						<tr>
					</thead>
						<tr class="ui-widget-content pui-datatable-even">
							<td align="center"><spring:message code="page.dashboard.label.assigned"/></td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center" bgcolor="#F5F5F5">12</td><td align="center" bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center" bgcolor="#F5F5F5">12</td><td align="center" bgcolor="#F5F5F5">215</td>
						</tr>
							<tr class="ui-widget-content pui-datatable-even">
							<td align="center"><spring:message code="page.dashboard.label.resolved"/></td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
						</tr>
							<tr class="ui-widget-content pui-datatable-even">
							<td align="center"><spring:message code="page.dashboard.label.sla.ok"/></td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
						</tr>
							<tr class="ui-widget-content pui-datatable-even">
							<td align="center"><spring:message code="page.dashboard.label.sla.nok"/></td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
						</tr>
							<tr class="ui-widget-content pui-datatable-even">
							<td align="center"><spring:message code="page.dashboard.label.percent.sla.ok"/></td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
								<td align="center">566</td><td align="center">12</td><td align="center">215</td>
								<td align="center" bgcolor="#F5F5F5">566</td><td align="center"bgcolor="#F5F5F5">12</td><td align="center"bgcolor="#F5F5F5">215</td>
					</table>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<div id="barchart"></div>
			    </div>
			    <div class="span8">
					<div id="columnchart"></div>
			     </div>
		     </div>
		</div>
	</form:form>

<script type="text/javascript" src="${pageContext.request.contextPath }/views/pages/js/home.js" ></script>