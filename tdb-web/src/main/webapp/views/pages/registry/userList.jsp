<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="ntt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" src="${pageContext.request.contextPath}/views/pages/js/registry/userList.js"></script>
	<ntt:messages messages="${command.showMessages}"></ntt:messages>
	
	<div class="margin-legend">
		<h4><spring:message code="page.user.label.user"/></h4>
	</div>

	<form:form class=" form-horizontal" id="formData" modelAttribute="command"  action="search-user.htm" method="post" enctype="multipart/form-data" autocomplete="off">
		<div class="well row margin">
			<div class="row-fluid">
				<div class="span2">
					<label><spring:message code="page.user.label.matriculation"/></label>
					 <form:input type="text" class="large-size" path="matriculation" id="matriculation" />
				</div>
				<div class="span5">
					<label><spring:message code="page.user.label.name"/></label>
					<form:input type="text" class="large-size" path="username"/>
				</div>
			</div>
			<div class="row-fluid" align="right">
				<input type="reset" class="btn" value="<spring:message code="page.btn.clear"/>"  onclick="location.href='userlist'" />
				<input type="submit" class="btn btn-primary" value="<spring:message code="page.btn.search"/> "/>
			</div>
		</div>

		<div class="well row margin">
				<div class="table-scroll">
				
					<table class="table table-bordered back-white"  id="tb">
						<thead>
							<th width="14%">
								<spring:message code="page.user.label.matriculation"/>
							</th>
							<th width="14%">
								<spring:message code="page.user.label.name"/>
							</th>	
							<th width="14%">
								<spring:message code="page.user.label.area"/>
							</th>
							<th width="14%">
								<spring:message code="page.user.label.status"/>
							</th>		
							<th align="center" width="5%"><spring:message code="page.edit"/></th>
						</thead>					
						<c:forEach  var="item" items="${userList}" >
							<tr class="ui-widget-content pui-datatable-even">
								<td align="center">${item.matriculation}</td>
								<td align="center">${item.username}</td>
								<td align="center">${item.area}</td>
						          <c:choose>
                                       <c:when test="${item.active=='true'}">
                                          <td align="center">Ativo</td>      
                                        </c:when>    
                                        <c:otherwise>
                                         <td align="center">Desabilitado</td>
                                        </c:otherwise>
                                  </c:choose>
							 <td><a href="<c:url value='/page/edit-user.htm?id=${item.id}'/>"class="icon-edit" /></td> 
								<%-- <td><a href="<c:url value='/edit-user.htm'/>" class="icon-edit"></a></td> --%>
							</tr>
							</c:forEach>
					</table>
					</div>
		</div>

	   <div class="form-actions margin" align="right">
	   		<input type="button" class="btn btn-primary" value="<spring:message code="page.user.btn.registry"/>" onclick="location.href='usernew'" />
		</div>
</form:form>



