<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<%@ include file="/pages/layout/headers.jsp" %>
    <title>Listagem de tarefas</title>
</head>

<body>

    <div id="wrapper">
		<%@include file="/pages/layout/header.jsp" %>
	
		<div id="page-wrapper">
            <div class="row">
				
				<h2>Listagem de Livros</h2>
				
				  	<table class="table table-bordered ">
				  		<tbody>
				        	<c:forEach items="${list}" var="livro">
								<tr>
									<th style="width: 15%;">Título</th>
									<td>${livro.titulo}</td>
								</tr>
								<tr>
									<th>Sub Titulo</th>
									<td>${livro.subTitulo}</td>
								</tr>
								<tr>
									<th>Nª Páginas</th>
									<td>${livro.numPaginas}</td>
								</tr>
								<tr>
									<th>Editora</th>
									<td>${livro.editora}</td>
								</tr>
								<tr>
									<th>Data Publicação</th>
									<td>
				        				<fmt:formatDate value="${livro.dataPublicacao}" pattern="dd/MM/yyyy"/>
				        			</td>
								</tr>
								<tr>
									<th>ISBN</th>
									<td>${livro.isbn}</td>
								</tr>
								<tr>
									<th>Autores</th>
									<td>
				        				<c:forEach items="${livro.autores}" var="autor">
				        					<p>${autor.nome}</p>
				        				</c:forEach>
			        				</td>
								</tr>
								<tr>
									<th>Revisores</th>
									<td>
				        				<c:forEach items="${livro.revisores}" var="revisor">
											<p>${revisor.nome}</p>
				        				</c:forEach>
				        			</td>
								</tr>
								<tr>
									<th>Sintese</th>
									<td>${livro.sintese}</td>
								</tr>
								<tr style="text-align: right;">
									<td></td>
				        			<td>
				        				<a href="${pageContext.servletContext.contextPath}/CadastroLivro?action=edit&id=${livro.id}">
											<button type="button" class="btn btn-default" aria-label="Left Align">
												<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Editar
											</button>
				        				</a>
			        					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#removeModal" aria-label="Left Align">
										  <span class="glyphicon glyphicon-remove" aria-hidden="false"></span> Remover
										</button>
										<div id="removeModal" class="modal fade" role="dialog">
										  <div class="modal-dialog modal-sm">
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal">&times;</button>
										        <h4 class="modal-title">Aviso</h4>
										      </div>
										      <div class="modal-body">
										        <p>Deseja realmente apagar o livro?</p>
										      </div>
										      <div class="modal-footer">
										      	<button type="button" class="btn btn-default yes-modal" data-id="${livro.id} "data-dismiss="modal">Sim</button>
										        <button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
										      </div>
										    </div>
										  </div>
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td colspan="2">
				        			</td>
				        			<td></td>
				        		</tr>
				        	</c:forEach>
				        </tbody>
				  	</table>
            </div>
        </div>
	</div>
	
	<%@ include file="/pages/layout/footer.jsp" %>
	<script src="${pageContext.servletContext.contextPath}/resources/custom/js/modal-remove-ebook.js"></script>
</body>

</html>
