<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/pages/livros/home.jsp">Bibli.edu</a>
    </div>

          <ul class="nav navbar-top-links navbar-right">
              <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                      <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-user">
                      <li><a href="#"><i class="fa fa-user fa-fw"></i><fmt:message key="header.user_profile"/></a>
                      </li>
                      <li><a href="#"><i class="fa fa-gear fa-fw"></i><fmt:message key="header.settings"/></a>
                      </li>
                      <li class="divider"></li>
                      <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i><fmt:message key="header.logout"/></a>
                      </li>
                  </ul>
              </li>
              <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="glyphicon glyphicon-cog"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul id="locale" class="dropdown-menu dropdown-user">
                        <li>
                        	<a href="${pageContext.servletContext.contextPath}/ChangeLanguage?language=pt" data-language="pt">
                        		<fmt:message key="header.portuguese"/>
                        	</a>
                        </li>
                        <li>
                        	<a href="${pageContext.servletContext.contextPath}/ChangeLanguage?language=en" data-language="en">
                        		<fmt:message key="header.english"/>
                        	</a>
                        </li>
                    </ul>
                </li>               
          </ul>

	<%@include file="menu.jsp" %>
</nav>