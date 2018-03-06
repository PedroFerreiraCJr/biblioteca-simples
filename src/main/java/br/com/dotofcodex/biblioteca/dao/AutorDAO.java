package br.com.dotofcodex.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl;
import br.com.dotofcodex.biblioteca.model.Autor;
import br.com.dotofcodex.biblioteca.model.Livro;

public class AutorDAO implements DataAccessObject<Autor> {

	private DataSourceImpl datasource;

	public AutorDAO(DataSourceImpl datasource) {
		this.datasource = datasource;
	}

	@Override
	public Autor create(Autor object) throws Exception {

		String sql = "INSERT INTO autores(nome, livro) VALUES (?,?)";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, object.getNome());
			pstm.setLong(2, object.getLivroID());

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
	public Autor retrieve(Autor object) throws Exception {

		String sql = "SELECT a.* FROM autores a WHERE id = ?";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, object.getId());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				object.setId(rs.getLong("id"));
				object.setNome(rs.getString("nome"));
				object.setLivroID(rs.getLong("livro"));
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
	public void update(Autor object) throws Exception {

		String sql = "UPDATE autores SET nome = ?, livro = ? WHERE id = ?";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, object.getNome());
			pstm.setLong(2, object.getLivroID());
			pstm.setLong(3, object.getId());

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
	public void delete(Autor object) throws Exception {

		String sql = "DELETE FROM autores WHERE id = ?";

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
	public List<Autor> listAll() {
		List<Autor> result = new ArrayList<Autor>();

		String sql = "SELECT a.* FROM autores a";

		try (Connection conn = datasource.getConnection();) {

			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Autor autor = new Autor();
				autor.setId(rs.getLong("id"));
				autor.setNome(rs.getString("nome"));
				autor.setLivroID(rs.getLong("livro"));

				result.add(autor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Autor> getAutoresFromLivro(Livro object) throws Exception {

		List<Autor> result = new ArrayList<Autor>();

		String sql = "SELECT a.* FROM autores a join livros l on (l.id = a.livro) where l.id = ?;";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, object.getId());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Autor autor = new Autor();

				autor.setId(rs.getLong("id"));
				autor.setNome(rs.getString("nome"));
				autor.setLivroID(rs.getLong("livro"));

				result.add(autor);
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

		return result;

	}

}
