<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.util.ResourceBundle" %> 
<% 	ResourceBundle resource = ResourceBundle.getBundle("tdb-web");
	String version=resource.getString("web.version"); %>

<div id="footer" class="footer">
	<p align="center">
		Copyright © Everis Brazil - TDB Web Version: <%= version %>
	</p>
</div>