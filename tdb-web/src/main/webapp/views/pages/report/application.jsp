<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="ntt" tagdir="/WEB-INF/tags"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="${pageContext.request.contextPath }/views/pages/js/home.js" ></script>

<div class="margin-legend">
	<h4><spring:message code="page.report.label.tittle.app"/></h4>
</div>
	<form:form class=" form-horizontal" id="formData" modelAttribute="command" method="post" enctype="multipart/form-data" autocomplete="off">
		<div ng-app="" class="well row margin">
			<div class="grafico_relatorio margin" style="float: left"></div>
		<div class="table_rel">      
		  <table class="table table-bordered back-white">
		    <thead>
		    	<tr>
		    		<td colspan="5"><spring:message code="page.report.label.tickets.nr"/></td>
		    	</tr>
		      <tr>
		        <th><spring:message code="page.report.label.area"/></th>
		        <th><spring:message code="page.report.label.low"/></th>
		        <th><spring:message code="page.report.label.medium"/></th>
		        <th><spring:message code="page.report.label.high"/></th>
		        <th><spring:message code="page.report.label.total"/></th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td bgcolor="#F5F5F5">b.10 days</td><td bgcolor="#F5F5F5">1</td><td bgcolor="#F5F5F5">5</td><td bgcolor="#F5F5F5">20</td><td bgcolor="#F5F5F5">26</td>
		      </tr>
		      <tr>
		        <td>IN</td><td></td><td></td><td>1</td><td>1</td>
		      </tr>
		      <tr>
		        <td>PB</td><td>1</td><td>1</td><td>18</td><td>20</td>
		      </tr>
		      <tr>
		        <td>IN</td><td></td><td>4</td><td>1</td><td>5</td>
		      </tr>
		      
		      <tr>
		        <td bgcolor="#F5F5F5">b.10 days</td><td bgcolor="#F5F5F5">1</td><td bgcolor="#F5F5F5">5</td><td bgcolor="#F5F5F5">20</td><td bgcolor="#F5F5F5">26</td>
		      </tr>
		      <tr>
		        <td>IN</td><td></td><td></td><td>1</td><td>1</td>
		      </tr>
		      <tr>
		        <td>PB</td><td>1</td><td>1</td><td>18</td><td>20</td>
		      </tr>
		      <tr>
		        <td>IN</td><td></td><td>4</td><td>1</td><td>5</td>
		      </tr>
		      
		      <tr>
		        <td bgcolor="#F5F5F5">b.10 days</td><td bgcolor="#F5F5F5">1</td><td bgcolor="#F5F5F5">5</td><td bgcolor="#F5F5F5">20</td><td bgcolor="#F5F5F5">26</td>
		      </tr>
		      <tr>
		        <td>IN</td><td></td><td></td><td>1</td><td>1</td>
		      </tr>
		      <tr>
		        <td>PB</td><td>1</td><td>1</td><td>18</td><td>20</td>
		      </tr>
		      <tr>
		        <td>IN</td><td></td><td>4</td><td>1</td><td>5</td>
		      </tr>
		      
		       <tr>
		        <td>b.10 days</td><td>1</td><td>5</td><td>20</td><td>26</td>
		      </tr>
		      
		     
		    </tbody>
		  </table>
		</div>
	</div>		
			
	<div ng-app="" class="well row margin"> 	
		<div class="container" style="width: 100%">       
		  <table class="table table-bordered back-white">
		    <thead>
		      <tr>
		        <th><spring:message code="page.report.label.ticket.id"/></th>
		        <th><spring:message code="page.report.label.creation.date"/></th>
		        <th><spring:message code="page.report.label.role"/></th>
		        <th><spring:message code="page.report.label.analist"/></th>
		        <th><spring:message code="page.report.label.aging"/></th>
		        <th><spring:message code="page.report.label.justify"/></th>
		        <th><spring:message code="page.report.label.priority"/></th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>SR000001</td>
		        <td>09/11/2015 00:00:00</td>
		        <td>Biling</td>
		        <td>Ana Paula Muchinski</td>
		        <td>121</td>
		        <td>Justificativa 01</td>
		        <td>Baixa</td>
		      </tr>
		      <tr>
		        <td>SR000002</td>
		        <td>09/11/2015 00:00:00</td>
		        <td>Biling</td>
		        <td>Ana Paula Muchinski</td>
		        <td>232</td>
		        <td>Justificativa 02</td>
		        <td>Média</td>
		      </tr>
		      <tr>
		        <td>SR000003</td>
		        <td>09/11/2015 00:00:00</td>
		        <td>Biling</td>
		        <td>Ana Paula Muchinski</td>
		        <td>54</td>
		        <td>Justificativa 03</td>
		        <td>Alta</td>
		      </tr>
		    </tbody>
		  </table>
		</div>
		</div>
	</form:form>


