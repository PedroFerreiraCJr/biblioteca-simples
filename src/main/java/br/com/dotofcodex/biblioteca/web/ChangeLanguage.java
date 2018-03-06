package br.com.dotofcodex.biblioteca.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet(name="ChangeLanguage",urlPatterns={"/ChangeLanguage"})
public class ChangeLanguage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String language = req.getParameter("language");
		
		Locale locale = new Locale(language);
		
		Config.set(req.getSession(), Config.FMT_LOCALE, locale);
		Config.set(req.getSession(), Config.FMT_FALLBACK_LOCALE, locale);

		resp.sendRedirect(req.getContextPath() + "/pages/livros/home.jsp");
	}
	
}