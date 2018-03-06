<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <li>
                <a href="#">
                	<i class="fa fa-files-o fa-fw"></i>
                	<fmt:message key="menu.livros"/>
                	<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/CadastroLivro?action=create">
                        	<fmt:message key="menu.livros.create"/>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/CadastroLivro?action=list">
                        	<fmt:message key="menu.livros.list"/>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
