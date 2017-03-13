<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form name='f' id="frmData" action="../j_spring_security_check" method='POST' class="span12" autocomplete="off" style="margin-top:10%; margin-left:32%; ">
<script type="text/javascript" src="${pageContext.request.contextPath }/views/pages/js/resetSenha.js" ></script>
		<div class="row-fluid">
			<div class="span6">
				<fieldset class="well" style="margin-bottom: 0px;">
					<c:if test="${not empty error}">
						<div class="span12">
							<div class="error">
								<spring:message code="msg.error.auth" />
								<br />
							</div>
						</div>
					</c:if>
					<c:if test="${not empty loginlocked}">
						<div class="span12">
							<div class="error">
								<spring:message code="msg.error.auth.loginlocked" />
								<br />
							</div>
						</div>
					</c:if>
						<p><legend align="center"><spring:message code="page.login.label.login"/></legend></p>
						<label><spring:message code="page.login.label.user"/></label>
						<input type='text' name='j_username' value=''class="large-size" "/>
						<label><spring:message code="page.login.label.password"/></label>
						<input type='password' name='j_password' value='' class="large-size"/>
						<a href="javascript:redirectReset();">Esqueci Minha Senha!</a>
						<p style="margin-top: 1%" align="center">
							<input type="submit"  class="btn btn-primary" value="<spring:message code="page.btn.enter"/>" />
							<input type="button" class="btn" value="<spring:message code="page.btn.clear"/>" onclick="location.href='login'"  />
						</p>
				</fieldset>
			</div>
		</div>
</form:form>

