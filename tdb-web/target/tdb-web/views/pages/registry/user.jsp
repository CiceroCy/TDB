<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="ntt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="margin-legend">
	<h4><spring:message code="page.user.label.header"/></h4>
</div>
<spring:message code="msg.notBlank" var="msgValidation"/>
<ntt:messages messages="${command.showMessages}"></ntt:messages>

<form:form class="form-horizontal" id="formData" modelAttribute="command" method="post" action="save-user.htm" enctype="multipart/form-data" autocomplete="off">
    
    <form:hidden path="id"/>
    <form:hidden path="password"/>
    <c:forEach  var="item" items="${command.roles}" varStatus="indexObjec" >
     	<form:hidden path="roles[${indexObjec.index}].id" />
   		<form:hidden path="roles[${indexObjec.index}].description" />
    </c:forEach>
       
	<div class="well row margin">
		<div class="row-fluid control-row">		
			<div class="span6">
				<label><span class="requiredField">*</span><spring:message code="page.user.label.name"/></label>
				<form:input type="text" class="input-size" path="username" maxlength="255"></form:input>
				<form:errors title="${msgValidation}" path="username" cssClass="validation"/>
			</div>
			<div class="span4">
				<label><span class="requiredField">*</span><spring:message code="page.user.label.matriculation"/></label>
				<form:input  type="text" class="input-size" path="matriculation" maxlength="255"/>
				<form:errors title="${msgValidation}" path="matriculation" cssClass="validation"/>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6 ">
				<label><span class="requiredField">*</span><spring:message code="page.user.label.email"/></label>
				<form:input class="input-size"  type="text" path="email" maxlength="255" />
				<form:errors title="${msgValidation}" path="email" cssClass="validation"/>

			</div>
			<div class="span4">
				<label><span class="requiredField">*</span><spring:message code="page.user.label.area"/></label>
				<form:input  type="text" class="input-size" path="area" maxlength="255" />
				<form:errors title="${msgValidation}" path="area" cssClass="validation"/>
			</div>
		</div>
		<div style="margin-top: 1%">
			<label class="radio-inline"><span class="requiredField">*</span><form:radiobutton  name="habilitado" path="active" value ="true"/><spring:message  code="page.user.label.able"/></label>
			<label class="radio-inline"><form:radiobutton  name="desabilitado"  path="active" value="false"/><spring:message  code="page.user.label.disable"/></label>
			
		</div>
	</div>
	<div class="form-actions margin"  align="center" >
		<input type="submit" class="btn btn-primary" value="<spring:message code="page.btn.save"/>" />
		<input type="reset" value="<spring:message code="page.btn.clear"/>" class="btn">
		<input type="button" class="btn" value="<spring:message code="page.btn.back"/>" onclick="location.href='userlist'" />
	</div>
</form:form>



