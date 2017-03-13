<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="ntt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form:form name='f' id="frmData" modelAttribute="command"  action="verifica-user"  method='post' class="span12" autocomplete="off" style="margin-top:10%; margin-left:32%; ">

	
		<div class="row-fluid">
			<div class="span6">
				<ntt:messages messages="${command.showMessages}"></ntt:messages>
				<fieldset class="well" style="margin-bottom: 0px;">		
						<p><legend align="center"><spring:message code="page.login.label.reset"/></legend></p>
						<label><spring:message code="page.user.label.id"/></label>
						<form:input type="text" path="id" class="large-size" ></form:input>
						<label><spring:message code="page.user.label.email"/></label>
						<form:input type="text" path="email" value='' class="large-size"></form:input>
						<p style="margin-top: 1%" align="center">
							<input type="submit"  class="btn btn-primary" value="<spring:message code="page.btn.resetsenha"/>" />
							<input type="button" class="btn" value="<spring:message code="page.btn.clear"/>" onclick="location.href='reset'"  />
						</p>
				</fieldset>
			</div>
		</div>
</form:form>

