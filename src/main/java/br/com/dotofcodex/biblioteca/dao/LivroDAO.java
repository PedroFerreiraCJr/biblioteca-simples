package br.com.dotofcodex.biblioteca.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl;
import br.com.dotofcodex.biblioteca.model.Livro;
import br.com.dotofcodex.biblioteca.model.Livro.Builder;

public class LivroDAO implements DataAccessObject<Livro> {

	private DataSourceImpl datasource;

	public LivroDAO(DataSourceImpl datasource) {
		this.datasource = datasource;
	}

	@Override
	public Livro create(Livro object) throws Exception {

		String sql = "INSERT INTO livros (titulo, sub_titulo, num_paginas, editora, data_publicacao, isbn, sintese) VALUES (?,?,?,?,?,?,?)";

		Connection conn = datasource.getConnection();

		try {
			PreparedStatement pstm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			pstm.setString(1, object.getTitulo());
			pstm.setString(2, object.getSubTitulo());
			
			if (object.getNumPaginas()!=null) {
				
				pstm.setInt(3, object.getNumPaginas());
			}else {
				pstm.setNull(3, Types.INTEGER);
			}
			
			pstm.setString(4, object.getEditora());

			java.util.Date data = object.getDataPublicacao();

			if (data != null) {

				pstm.setDate(5, new Date(object.getDataPublicacao().getTime()));
			} else {
				pstm.setNull(5, Types.DATE);
			}

			pstm.setString(6, object.getIsbn());
			pstm.setString(7, object.getSintese());

			pstm.executeUpdate();

			pstm.getGeneratedKeys();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				object.setId(Long.valueOf(rs.getInt("id")));
			}

			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new Exception(e);
		} finally {
			if (conn != null) {
				try {

					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		return object;
	}

	@Override
	public Livro retrieve(Livro object) throws Exception {

		String sql = "SELECT l.* FROM livros l WHERE id = ?";

		Connection conn = datasource.getConnection();

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setLong(1, object.getId());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Livro.Builder builder = new Builder(rs.getString("titulo"), rs.getString("sub_titulo"));

				object = builder
					.id(rs.getLong("id"))
					.editora(rs.getString("editora"))
					.numPaginas(rs.getInt("num_paginas"))
					.isbn(rs.getString("isbn"))
					.sintese(rs.getString("sintese"))
					.dataPublicacao(null)
					.build();
				
				if (rs.getDate("data_publicacao")!=null) {
					object.setDataPublicacao(new Date(rs.getDate("data_publicacao").getTime()));
				}
				
				break;
			}

			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new Exception(e);
		} finally {
			if (conn != null) {
				try {

					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		return object;
	}

	@Override
	public void update(Livro object) throws Exception {

		String sql = "UPDATE livros SET titulo = ?, sub_titulo = ?, editora = ?, num_paginas = ?, data_publicacao = ?, isbn = ?, sintese = ? WHERE id = ?";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, object.getTitulo());
			pstm.setString(2, object.getSubTitulo());
			pstm.setString(3, object.getEditora());
			pstm.setInt(4, object.getNumPaginas());
			pstm.setDate(5, new Date(object.getDataPublicacao().getTime()));
			pstm.setString(6, object.getIsbn());
			pstm.setString(7, object.getSintese());
			pstm.setLong(8, object.getId());

			pstm.executeUpdate();

			pstm.close();
		} catch (SQLException e) {
			throw new Exception(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Livro object) throws Exception {

		String sql = "DELETE FROM livros WHERE id = ?";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, object.getId());

			pstm.executeUpdate();

			pstm.close();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<Livro> listAll() {

		List<Livro> result = new ArrayList<Livro>();

		String sql = "SELECT l.* FROM livros l";

		try (Connection conn = datasource.getConnection();) {

			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Livro.Builder builder = new Builder(rs.getString("titulo"), rs.getString("sub_titulo"));
				builder
					.id(rs.getLong("id"))
					.editora(rs.getString("editora"))
					.isbn(rs.getString("isbn"))
					.sintese(rs.getString("sintese"));

				if (rs.getInt("num_paginas")>0) {
					builder.numPaginas(rs.getInt("num_paginas"));	
				}else {
					builder.numPaginas(null);
				}
				
				
				if (rs.getDate("data_publicacao") != null) {

					builder.dataPublicacao(new Date(rs.getDate("data_publicacao").getTime()));
				}

				Livro livro = builder.build();

				AutorDAO autorDAO = new AutorDAO(datasource);
				livro.setAutores(autorDAO.getAutoresFromLivro(livro));

				RevisorDAO revisorDAO = new RevisorDAO(datasource);
				livro.setRevisores(revisorDAO.getRevisoresFromLivro(livro));

				result.add(livro);
			}

			rs.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
