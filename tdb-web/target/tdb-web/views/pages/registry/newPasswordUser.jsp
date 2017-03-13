<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="ntt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="msg.notBlank" var="msgValidation"/>

<ntt:messages messages="${command.showMessages}"></ntt:messages>
<script type="text/javascript" src="${pageContext.request.contextPath }/views/pages/js/registry/newPasswordUser.js" ></script>

<div class="margin-legend">
	<h4><spring:message code="page.user.label.header.newpass"/></h4>
</div>

<!-- Mensagens de erros -->
	<div class="row" align="center">
		<div id="errorIdenticalFields" style="display: none">
			<div class="input-size alert alert-error">
				<spring:message code="msg.notIdentical"/>
			</div>
		</div>

<form:form class=" form-horizontal" id="formData" modelAttribute="command" method="post" action="save-newpass-user.htm" enctype="multipart/form-data" autocomplete="off">
	<form:hidden path="id" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idUser}"/>
	
	<div class="well row margin">
		<div class="row-fluid control-row">
			<div class="span4" style="display: block;">
				<label><span class="requiredField">*</span><spring:message code="page.user.label.newpass"/></label>
				<form:input type="password" class="input-size" path="password" maxlength="255"></form:input>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<label><span class="requiredField">*</span><spring:message code="page.user.label.confirmpass"/></label>
				<input type="password" class="input-size" id="confirpassowrd" maxlength="255"></input>
				
			</div>
		</div>
	</div>
	<div class="form-actions margin"  align="center" >
		<input type="button" onclick="compareFields()" class="btn btn-primary" value="<spring:message code="page.btn.save"/>" />
		<input type="reset" value="<spring:message code="page.btn.clear"/>" class="btn">
		<input type="button" class="btn" value="<spring:message code="page.btn.back"/>" onclick="javascript:history.back();" />
	</div>
</form:form>



