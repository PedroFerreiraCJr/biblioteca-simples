package br.com.dotofcodex.biblioteca.service;

import java.util.List;

import br.com.dotofcodex.biblioteca.dao.AutorDAO;
import br.com.dotofcodex.biblioteca.dao.DataAccessObject;
import br.com.dotofcodex.biblioteca.dao.LivroDAO;
import br.com.dotofcodex.biblioteca.dao.RevisorDAO;
import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl;
import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl.JndiDataSourceImpl;
import br.com.dotofcodex.biblioteca.model.Autor;
import br.com.dotofcodex.biblioteca.model.Livro;
import br.com.dotofcodex.biblioteca.model.Revisor;

public class LivroService implements SimpleService {

	private DataAccessObject<Livro> livroDAO;

	public LivroService() {
		this.livroDAO = new LivroDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
	}

	public LivroService(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	@Override
	public synchronized Livro create(Livro object) throws Exception {

		Livro livro = livroDAO.create(object);

		AutorDAO autorDAO = new AutorDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
		for (Autor a : object.getAutores()) {
			a.setLivroID(livro.getId());
			a = autorDAO.create(a);
		}

		RevisorDAO revisorDAO = new RevisorDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
		for (Revisor r : object.getRevisores()) {
			r.setLivroID(livro.getId());
			r = revisorDAO.create(r);
		}

		return livro;
	}

	@Override
	public synchronized Livro retrieve(Livro object) throws Exception {
		
		Livro l = livroDAO.retrieve(object);
		
		AutorDAO autorDAO = new AutorDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
		l.setAutores(autorDAO.getAutoresFromLivro(l));

		RevisorDAO revisorDAO = new RevisorDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
		l.setRevisores(revisorDAO.getRevisoresFromLivro(l));
		
		return l;
	}

	@Override
	public synchronized void update(Livro object) throws Exception {
		
		livroDAO.update(object);
		
		AutorDAO autorDAO = new AutorDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
		RevisorDAO revisorDAO = new RevisorDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));

		// Deleção dos autores anteriores
		Livro l = new Livro(null, null);
		l.setId(object.getId());
		l = retrieve(l);

		for (Autor a : l.getAutores()) {
			autorDAO.delete(a);
		}
		
		for (Revisor r : l.getRevisores()) {
			revisorDAO.delete(r);
		}
		
		// Colocação dos autores atuais
		for (Autor a : object.getAutores()) {
			autorDAO.create(a);
		}
		
		for (Revisor r : object.getRevisores()) {
			revisorDAO.create(r);
		}

	}

	@Override
	public void delete(Livro object) throws Exception {
		
		livroDAO.delete(object);
	}

	@Override
	public List<Livro> listAll() {

		return livroDAO.listAll();
	}

}
