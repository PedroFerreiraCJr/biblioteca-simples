package br.com.dotofcodex.biblioteca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Livro {

	/*
	  create database biblioteca;
	  
	  create table livros (
	  	id serial,
	  	titulo varchar(50) not null,
	   	sub_titulo varchar(50),
	   	editora varchar(50),
	   	num_paginas integer,
	   	data_publicacao DATE,
	   	isbn varchar(20),
	   	sintese text,
	   	constraint pk_livro_id primary key (id)
	  );
	  
	  create table autores (
	  	id serial,
	  	nome varchar(50) not null,
	   	livro integer,
	   	constraint pk_autores_id primary key (id),
	   	constraint fk_autores_livro foreign key (livro) references livros (id) on delete cascade on update cascade 
	  );
	  
	  create table revisores (
	  	id serial,
	  	nome varchar(50) not null,
	   	livro integer,
	   	constraint pk_revisores_id primary key (id),
	   	constraint fk_revisores_livro foreign key (livro) references livros (id) on delete cascade on update cascade 
	  );
	 */
	
	private Long id;
	private final String titulo;
	private String subTitulo;
	private Integer numPaginas;
	private String editora;
	private Date dataPublicacao;
	private String isbn;
	private List<Autor> autores;
	private List<Revisor> revisores;
	private String sintese;

	public Livro(String titulo, String subTitulo) {
		this.titulo = titulo;
		this.subTitulo = subTitulo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Integer numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Revisor> getRevisores() {
		return revisores;
	}

	public void setRevisores(List<Revisor> revisores) {
		this.revisores = revisores;
	}

	public String getSintese() {
		return sintese;
	}

	public void setSintese(String sintese) {
		this.sintese = sintese;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Livro [id=").append(id).append(", titulo=").append(titulo).append(", subTitulo=")
				.append(subTitulo).append(", numPaginas=").append(numPaginas).append(", editora=").append(editora)
				.append(", dataPublicacao=").append(dataPublicacao).append(", isbn=").append(isbn).append(", autores=")
				.append(autores).append(", revisores=").append(revisores).append(", sintese=").append(sintese)
				.append("]");
		return builder.toString();
	}

	public static class Builder implements br.com.dotofcodex.biblioteca.utils.Builder<Livro> {
		
		private Long id;
		private final String titulo;
		private String subTitulo;
		private Integer numPaginas;
		private String editora;
		private Date dataPublicacao;
		private String isbn;
		private List<Autor> autores;
		private List<Revisor> revisores;
		private String sintese;

		public Builder(String titulo, String subTitulo) {
			this.titulo = titulo;
			this.subTitulo = subTitulo;
			this.autores = new ArrayList<>();
			this.revisores = new ArrayList<>();
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder numPaginas(Integer numPaginas) throws Exception {
			this.numPaginas = numPaginas;
			return this;
			
		}
		
		public Builder editora(String editora) throws Exception  {
			this.editora = editora;
			return this;
		}
		
		public Builder dataPublicacao(Date data) throws Exception {
			this.dataPublicacao = data;
			return this;
		}

		public Builder isbn(String isbn) throws Exception {
			this.isbn = isbn;
			return this;
		}

		public Builder adicionarAutor(Autor autor) throws Exception {
			this.autores.add(autor);
			return this;
		}
		
		public Builder adicionarAutor(String nome) throws Exception {
			Autor autor = new Autor(nome);
			this.autores.add(autor);
			return this;
		}

		public Builder adicionarRevisor(String nome) throws Exception {
			Revisor revisor = new Revisor(nome);
			this.revisores.add(revisor);
			return this;
		}

		public Builder adicionarRevisor(Revisor revisor) throws Exception {
			this.revisores.add(revisor);
			return this;
		}
		
		public Builder sintese(String sintese) throws Exception {
			this.sintese = sintese;
			return this;
		}
		
		@Override
		public Livro build() {
			
			Livro livro = new Livro(this.titulo, this.subTitulo);
			livro.setId(this.id);
			livro.setNumPaginas(this.numPaginas);
			livro.setEditora(this.editora);
			livro.setDataPublicacao(this.dataPublicacao);
			livro.setIsbn(this.isbn);
			livro.setAutores(this.autores);
			livro.setRevisores(this.revisores);
			livro.setSintese(this.sintese);
			
			return livro;
		}

	}

}
