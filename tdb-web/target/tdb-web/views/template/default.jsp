<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>


<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <!-- <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>  -->
    <meta http-equiv="Cache-Control" content="no-transform, max-age=0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${pageContext.request.contextPath}/views/core/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/views/core/bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/views/core/bootstrap/css/datepicker.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/views/core/bootstrap/css/bootstrap-multiselect.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/views/core/css/default.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/views/core/jquery/loader/css/loader.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/jquery/angular.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/bootstrap/js/bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/bootstrap/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/bootstrap/js/locales/bootstrap-datepicker.pt-BR.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/views/core/jquery/meiomask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/core/jquery/loader/jquery.nimble.loader.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/views/template/js/ntt-custom-date.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/views/template/js/ntt-core.js"></script>

    <dec:head />
</head>
<body class="preview" id="top" data-spy="scroll" data-target=".subnav" data-offset="80">

    <div id="container">

		<jsp:include page="../fragment/header.jsp" />

		<jsp:include page="../fragment/menu.jsp" />

		<dec:body />

		<jsp:include page="../fragment/footer.jsp" />

	</div>

</body>
</html>



