package br.com.dotofcodex.biblioteca.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dotofcodex.biblioteca.dao.LivroDAO;
import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl;
import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl.JndiDataSourceImpl;
import br.com.dotofcodex.biblioteca.model.Livro;
import br.com.dotofcodex.biblioteca.model.Livro.Builder;
import br.com.dotofcodex.biblioteca.service.LivroService;
import br.com.dotofcodex.biblioteca.utils.LivroFormConverter;

@WebServlet(name = "LivroServlet", urlPatterns = { "/CadastroLivro" }, loadOnStartup = 1)
public class LivrosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action.equals("create")) {

			response.sendRedirect(getServletContext().getContextPath() + "/pages/livros/home.jsp");
		} else if (action.equals("list")) {

			LivroDAO livroDAO = new LivroDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));
			List<Livro> list = livroDAO.listAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/livros/list.jsp").forward(request, response);
		} else if (action.equals("edit")) {

			String id = request.getParameter("id");

			LivroService service = new LivroService();
			
			Livro.Builder builder = new Builder(null, null);
			builder.id(Long.valueOf(id));
			Livro livro = builder.build();
			try {

				livro = service.retrieve(livro);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("livro", livro);

			request.getRequestDispatcher("/pages/livros/edit.jsp").forward(request, response);
		} else if (action.equals("remove")) {
			String id = request.getParameter("id");

			LivroDAO livroDAO = new LivroDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class));

			Livro.Builder builder = new Builder(null, null);
			builder.id(Long.valueOf(id));
			Livro livro = builder.build();
			try {

				livroDAO.delete(livro);

			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Livro> list = livroDAO.listAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/livros/list.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean validFields = false;
		boolean update = false;

		String action = request.getParameter("action");
		if (action != null) {
			update = true;
			System.out.println(action);
		}

		Livro livro = null;

		try {
			livro = LivroFormConverter.convert(request);
			validFields = true;
		} catch (Exception e) {
			e.printStackTrace();
			validFields = false;
			request.setAttribute("message_error", e.getMessage());
		}

		if (validFields) {

			String titulo = livro.getTitulo();

			if (titulo != null) {
				titulo = titulo.trim();
				if (titulo.length() < 3) {

					request.setAttribute("message_error", "Titulo do livro muito curto. Tamanho mínimo 5 caracteres.");
					request.setAttribute("livro", livro);
				} else {
					request.setAttribute("livro", livro);
					validFields = true;
				}
			}
		}

		try {

			if (validFields) {
				LivroService service = new LivroService(
						new LivroDAO(DataSourceImpl.getInstance(JndiDataSourceImpl.class)));

				if (update) {

					service.update(livro);
					request.setAttribute("message_success", "Livro atualizado com sucesso");
				} else {

					service.create(livro);
					request.setAttribute("message_success", "Livro criado com sucesso");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("message_error", e.getMessage());
		}

		RequestDispatcher rd = null;

		if (update) {
			if (validFields) {
				rd = request.getRequestDispatcher("/pages/livros/edit.jsp?cadastro=ok");
			} else {
				rd = request.getRequestDispatcher("/pages/livros/edit.jsp?cadastro=fail");
			}
		} else {
			if (livro.getId() != null) {
				rd = request.getRequestDispatcher("/pages/livros/home.jsp?cadastro=ok");
			} else {
				rd = request.getRequestDispatcher("/pages/livros/home.jsp?cadastro=fail");
			}
		}

		rd.forward(request, response);

	}
}
