<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<%@ include file="/pages/layout/headers.jsp" %>
    <title>Listagem de tarefas</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/boostrap-sortable/css/bootstrap-sortable.css">
    <style>
    	.border {
    		border-bottom: 1px solid black;
    	}
    </style>
</head>

<body>

    <div id="wrapper">
		<%@include file="/pages/layout/header.jsp" %>
	
		<div id="page-wrapper">
            <div class="row">
				
				<h2>Listagem de Livros</h2>
				
				<table class="table table-striped table-bordered table-hover sortable">
			        <thead>
			          <tr>
			            <th style="width: 10%;">Titulo</th>
			            <th style="width: 10%;">Sub Titulo</th>
			            <th style="width: 10%;">Nª Páginas</th>
			            <th>Editora</th>
			            <th style="width: 13%;">Data Publicação</th>
			            <th style="width: 12%;">ISBN</th>
			            <th>Autores</th>
			            <th>Revisores</th>
			            <th>Sintese</th>
			            <th data-defaultsort="disabled">Ações</th>
			          </tr>
			        </thead>
			        
			        <tbody>
			        	<c:forEach items="${list}" var="livro">
			        		<tr>
			        			<td>${livro.titulo}</td>
			        			<td>${livro.subTitulo}</td>
			        			<td>${livro.numPaginas}</td>
			        			<td>${livro.editora}</td>
			        			<td>
			        				<fmt:formatDate value="${livro.dataPublicacao}" pattern="dd/MM/yyyy"/>
			        			</td>
			        			<td>${livro.isbn}</td>
			        			<td>
			        				<c:forEach items="${livro.autores}" var="autor">
			        					<p class="border">${autor.nome}</p>
			        				</c:forEach>
			        			</td>
			        			<td>
			        				<c:forEach items="${livro.revisores}" var="revisor">
			        					<p class="border">${revisor.nome}</p>
			        				</c:forEach>
			        			</td>
			        			<td>${livro.sintese}</td>
			        			<td>
			        				<a href="${pageContext.servletContext.contextPath}/CadastroLivro?action=edit&id=${livro.id}">
										<button type="button" class="btn btn-default" aria-label="Left Align">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Editar
										</button>
			        				</a>
			        				<a href="${pageContext.servletContext.contextPath}/CadastroLivro?action=remove&id=${livro.id}">
			        					<button type="button" class="btn btn-default" aria-label="Left Align">
										  <span class="glyphicon glyphicon-remove" aria-hidden="false"></span> Deletar
										</button>
			        				</a>
			        			</td>
			        		</tr>
			        	</c:forEach>
			        </tbody>
				</table>
            </div>
        </div>
	</div>
	
	<%@ include file="/pages/layout/footer.jsp" %>
	
	<script src="${pageContext.servletContext.contextPath}/resources/boostrap-sortable/js/bootstrap-sortable.js"></script>
</body>

</html>
