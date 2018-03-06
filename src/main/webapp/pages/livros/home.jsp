<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<%@ include file="/pages/layout/headers.jsp" %>
	<title><fmt:message key="home.title"/></title>
	<style>
		.required {
			color: red;
		}
		textarea {
			resize: none;
		}
	</style>
</head>
<body>
    <div id="wrapper">
		<%@ include file="/pages/layout/header.jsp" %>
	
		<div id="page-wrapper">
            <div class="row">
				
				<h2>
					<fmt:message key="home.title"/>
				</h2>
				<c:if test="${not empty param.lingua}">
				  <fmt:setLocale value="${param.lingua}" scope="session"/>
				</c:if>
				
				<c:if test="${param.cadastro eq 'ok'}">
					<div class="alert alert-success fade in">
				      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				      <strong>${message_success}</strong>
				    </div>
				</c:if>
				<c:if test="${param.cadastro eq 'fail'}">
					<div class="alert alert-warning fade in">
				      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				      <strong>${message_error}</strong>
				    </div>
				</c:if>
				
				<form action="${pageContext.servletContext.contextPath}/CadastroLivro" method="POST">
					<div class="form-group">
			          <label for="titulo">
			          	<fmt:message key="home.entity.titulo"/> <span class="required">*</span>
			          </label>
			          <input id="titulo" name="titulo" type="text" class="form-control" placeholder="Titulo" title="Titulo do livro" required>
			        </div>
			        <div class="form-group">
			          <label for="sub_titulo">
			          	<fmt:message key="home.entity.sub_titulo"/>
			          </label>
			          <input id="sub_titulo" name="sub_titulo" type="text" class="form-control" placeholder="Sub titulo" title="Sub titulo do livro">
			        </div>
			        <div class="row">
			       		<div class="col-md-6">
				       		<div class="form-group">
					          <label for="num_paginas">
					          	<fmt:message key="home.entity.num_paginas"/>
					          </label>
					          <input id="num_paginas" name="num_paginas" type="number" class="form-control" placeholder="Nª paginas" title="Número de páginas do livro" min="0">
					        </div>
			       		</div>
			       		<div class="col-md-6">
				       		<div class="form-group">
					          <label for="editora">
					          	<fmt:message key="home.entity.editora"/>
					          </label>
					          <input id="editora" name="editora" type="text" class="form-control" placeholder="Editora" title="Editora do livro">
					        </div>
			       		</div>
			       	</div>
			       	<div class="row">
			       		<div class="col-md-6">
			       			<div class="form-group">
					          <label for="data_publicacao">
					          	<fmt:message key="home.entity.data_publicacao"/>
					          </label>
					          <div class="input-group date">
				              	<input id="data_publicacao" name="data_publicacao" type="text" class="form-control datepicker" placeholder="Data Publicação" title="Data Publicação"/>
			                	<span class="input-group-addon">
			               			<span class="glyphicon glyphicon-calendar"></span>
			              		</span>
				              </div>
					        </div>
			       		</div>
			       		<div class="col-md-6">
			       			<div class="form-group">
					          <label for="isbn">
					          	<fmt:message key="home.entity.isbn"/>
					          </label>
					          <input id="isbn" name="isbn" type="text" class="form-control" placeholder="ISBN" title="ISBN do livro">
					        </div>
			       		</div>
			        </div>
			        <div class="row">
			       		<div class="col-md-6">
				       		<div class="form-group autores">
					          <label for="autores">
					          	<fmt:message key="home.entity.autores"/>
					          </label>
					          <div class="input-group autor">
						          <input id="autores" name="autores" type="text" class="form-control" placeholder="Autores" title="Autores do livro">
						          <span class="input-group-addon autor_add">
			               			<a><span class="glyphicon glyphicon-plus"></span></a>
								  </span>
								  <span class="input-group-addon autor_remove">
			               			<a><span class="glyphicon glyphicon-minus"></span></a>
								  </span>
					          </div>
					        </div>        
			       		</div>
			       		<div class="col-md-6">
				       		<div class="form-group revisores">
					          <label for="revisores">
					          	<fmt:message key="home.entity.revisores"/>
					          </label>
					          <div class="input-group autor">
						          <input id="revisores" name="revisores" type="text" class="form-control" placeholder="Revisores" title="Revisores do livro">
						          <span class="input-group-addon revisor_add">
			               			<a><span class="glyphicon glyphicon-plus"></span></a>
								  </span>
								  <span class="input-group-addon revisor_remove">
			               			<a><span class="glyphicon glyphicon-minus"></span></a>
								  </span>
					          </div>
					        </div>
			       		</div>
			       	</div>
			        <div class="form-group">
			          <label for="editora">
			          	<fmt:message key="home.entity.sintese"/>
			          </label>
			          <textarea id="sintese" name="sintese" rows="5" class="form-control" placeholder="Síntese" title="Síntese do livro"></textarea>
			        </div>
			        <button type="submit" class="btn btn-primary"><fmt:message key="home.save"/></button>
				</form>
            </div>
        </div>
	</div>

	<%@ include file="/pages/layout/footer.jsp" %>
	<script src="${pageContext.servletContext.contextPath}/resources/custom/js/add_autores_revisores.js"></script>
</body>
</html>
