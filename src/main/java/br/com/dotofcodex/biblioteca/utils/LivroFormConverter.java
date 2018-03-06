package br.com.dotofcodex.biblioteca.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.dotofcodex.biblioteca.model.Autor;
import br.com.dotofcodex.biblioteca.model.Livro;
import br.com.dotofcodex.biblioteca.model.Revisor;

public class LivroFormConverter {

	public static Livro convert(HttpServletRequest request) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String subTitulo = request.getParameter("sub_titulo");
		String numPaginas = request.getParameter("num_paginas");
		String editora = request.getParameter("editora");
		String dataPublicacao = request.getParameter("data_publicacao");
		String isbn = request.getParameter("isbn");
		String[] autores = request.getParameterValues("autores");
		String[] revisores = request.getParameterValues("revisores");
		String sintese = request.getParameter("sintese");
		
		Livro.Builder builder = new Livro.Builder(titulo, subTitulo);
		try {
			Long idLivro = id!=null?Long.valueOf(id):null;
			
			builder
				.id(idLivro)
				.numPaginas(!numPaginas.isEmpty()?Integer.valueOf(numPaginas):null)
				.editora(editora);
			try {
				
				Date data = sdf.parse(dataPublicacao);
				builder.dataPublicacao(data);
			} catch(Exception e) {
				builder.dataPublicacao(null);
			}
				
			builder
				.isbn(isbn)
				.sintese(sintese);

			for (int index = 0; index<autores.length; index++) {
				String a = autores[index].trim();
				if (a != null && !a.isEmpty()) {
					Autor au = new Autor();
					au.setLivroID(idLivro);
					au.setNome(a);
					builder.adicionarAutor(au);
				}
			}
			
			for (int index = 0; index<revisores.length; index++) {
				String r = revisores[index].trim();
				if (r != null && !r.isEmpty()) {
					Revisor re = new Revisor();
					re.setLivroID(idLivro);
					re.setNome(r);
					builder.adicionarRevisor(re);
				}
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return builder.build();
	}
	
}
