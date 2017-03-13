<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Menu ================================================== -->
        <div class="row">
            <div class="bsa well"></div>
        </div>
        <div class="subnav ">
            <ul class="nav nav-pills">
           		<li><a href="home.htm"><spring:message code="page.menu.label.dashboard"/></a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="page.menu.label.registry"/><b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="userlist"><spring:message code="page.login.label.user"/></a></li>
                  </ul>
                </li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="page.menu.label.report"/><b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="reportapp"><spring:message code="page.menu.label.aging"/></a></li>
                    <li><a href="reportinfra"><spring:message code="page.menu.label.infra"/></a></li>
                  </ul>
                </li>
               	<li class="dropdown pull-right"  >
			      <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
			       <span class="icon-user icon-white"></span>
			       <span class="color-login">
			      ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
			       </span> 
			       <b class="caret"></b>
			      </a>
			      <ul class="dropdown-menu" style="right: 0">
			       <li><a href="newPasswordUser.htm"><spring:message code="page.menu.label.change.password"/></a></li>
			       <li><a href="/tdb-web/logout"><spring:message code="page.menu.label.logout"/></a></li>
			      </ul>
	    	 	</li>
              </ul>
        </div>
