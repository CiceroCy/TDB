<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>


<html>

<link href="${pageContext.request.contextPath }/views/template/css/default.css" rel="stylesheet"	type="text/css" />
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <!-- <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>  -->
    <meta http-equiv="Cache-Control" content="no-transform, max-age=0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
<%--     <link href="${pageContext.request.contextPath }/views/core/bootstrap/css/bootstrap.min.css" rel="stylesheet"> --%>
<%--     <link href="${pageContext.request.contextPath }/views/core/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"> --%>
<%--     <link href="${pageContext.request.contextPath }/views/core/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> --%>
    <link href="${pageContext.request.contextPath}/views/core/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/views/core/bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/views/core/bootstrap/css/bootswatch.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/views/core/css/default.css" rel="stylesheet" type="text/css" />

</head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/views/core/jquery/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/views/core/jquery/meiomask.js"></script>
<body class="composite">

	<div id="container">

		<jsp:include page="../fragment/header.jsp" />
		<dec:body/>
		<jsp:include page="../fragment/footer.jsp" />

	</div>

</body>
</html>



