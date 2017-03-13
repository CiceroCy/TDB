<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ attribute name="messages" required="true" type="java.util.Map" %>

<c:forEach var="message" items="${messages}">
    <div class="row" align="center">
        <div class="input-size alert ${message.key}">
            ${message.value}
        </div>
    </div>
</c:forEach>