<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row" align="center">
	<div class="input-size alert alert-error"><spring:message code="msg.error.no.authorized"/></div>
</div>
