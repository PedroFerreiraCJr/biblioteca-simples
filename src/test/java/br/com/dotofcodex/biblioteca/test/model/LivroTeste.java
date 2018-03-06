package br.com.dotofcodex.biblioteca.test.model;

import java.util.Date;

import br.com.dotofcodex.biblioteca.dao.LivroDAO;
import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl;
import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl.JdbcDataSourceImpl;
import br.com.dotofcodex.biblioteca.model.Livro;
import br.com.dotofcodex.biblioteca.model.Livro.Builder;

public class LivroTeste {

	public static void main(String[] args) {

		update();
	}

	public static void update() {
		LivroDAO dao = new LivroDAO(DataSourceImpl.getInstance(JdbcDataSourceImpl.class));
		
		try {
		
			Livro livro = new Livro("Java OCA", null);
			livro.setId(1L);
			livro = dao.retrieve(livro);
			
			livro.setSintese("Livro voltado para programadores java.");
			dao.update(livro);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listAll() {
		LivroDAO dao = new LivroDAO(DataSourceImpl.getInstance(JdbcDataSourceImpl.class));
		
		try {
		
			for (Livro livro : dao.listAll()) {
				System.out.println(livro);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void retrieve() {
		
		LivroDAO dao = new LivroDAO(DataSourceImpl.getInstance(JdbcDataSourceImpl.class));
		
		Livro livro = new Livro(null, null);
		livro.setId(1L);
		
		try {
		
			livro = dao.retrieve(livro);
			
			System.out.println(livro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void create() {

		Livro.Builder builder = new Builder("Java OCA", "Java para Certificação");

		Livro livro = null;

		try {
			livro = builder
						.numPaginas(230)
						.editora("Caso do Codigo")
						.isbn("1231231232")
						.adicionarAutor("William Stallings")
						.adicionarAutor("Tanembaum")
						.adicionarRevisor("Pedro Ferreira")
						.adicionarRevisor("José Lima")
						.dataPublicacao(new Date())
					.build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LivroDAO dao = new LivroDAO(DataSourceImpl.getInstance(JdbcDataSourceImpl.class));
		
		try {

			dao.create(livro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void livro() {
		
		Livro.Builder builder = new Builder("Java OCA", "Java para Certificação");

		Livro livro = null;

		try {
			livro = builder
						.id(1L)
						.numPaginas(230)
						.editora("Caso do Codigo")
						.isbn("1231231232")
						.adicionarAutor("Pedro Ferreira")
						.adicionarRevisor("Pedro Ferreira")
						.dataPublicacao(new Date())
					.build();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(livro);
	}
}