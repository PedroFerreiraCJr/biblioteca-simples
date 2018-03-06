package br.com.dotofcodex.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dotofcodex.biblioteca.datasource.DataSourceImpl;
import br.com.dotofcodex.biblioteca.model.Livro;
import br.com.dotofcodex.biblioteca.model.Revisor;

public class RevisorDAO implements DataAccessObject<Revisor> {

	private DataSourceImpl datasource;

	public RevisorDAO(DataSourceImpl datasource) {
		this.datasource = datasource;
	}

	@Override
	public Revisor create(Revisor object) throws Exception {
		String sql = "INSERT INTO revisores(nome, livro) VALUES (?,?)";

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
	public Revisor retrieve(Revisor object) throws Exception {

		String sql = "SELECT r.* FROM revisores r WHERE r.id = ?";

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
	public void update(Revisor object) throws Exception {
		
		String sql = "UPDATE revisores SET nome = ?, livro = ? WHERE id = ?";

		Connection conn = datasource.getConnection();
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, object.getNome());
			pstm.setLong(2, object.getLivroID());
			pstm.setLong(3, object.getId());
			
			pstm.executeUpdate();
			
			pstm.close();
		} catch(SQLException e) {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Revisor object) throws Exception {
		
		String sql = "DELETE FROM revisores WHERE id = ?";
		
		Connection conn = datasource.getConnection();
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, object.getId());
			
			pstm.executeUpdate();
			
			pstm.close();
		} catch(SQLException e) {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Revisor> listAll() {
		
		List<Revisor> result = new ArrayList<Revisor>();
		
		String sql = "SELECT r.* FROM revisores r";
		
		try(Connection conn = datasource.getConnection();) {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				Revisor revisor = new Revisor();
				revisor.setId(rs.getLong("id"));
				revisor.setNome(rs.getString("nome"));
				revisor.setLivroID(rs.getLong("livro"));
				
				result.add(revisor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Revisor> getRevisoresFromLivro(Livro object) throws Exception {

		List<Revisor> result = new ArrayList<Revisor>();

		String sql = "SELECT r.* FROM revisores r join livros l on (l.id = r.livro) where l.id = ?;";

		Connection conn = datasource.getConnection();

		try {

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, object.getId());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Revisor autor = new Revisor();

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
