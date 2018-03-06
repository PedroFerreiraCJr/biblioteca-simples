package br.com.dotofcodex.biblioteca.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DataSourceImpl implements br.com.dotofcodex.biblioteca.utils.DataSource {

	protected javax.sql.DataSource datasource;
	private static DataSourceImpl instance;

	private DataSourceImpl() {
		super();
	}

	public static class JdbcDataSourceImpl extends DataSourceImpl {
		
		private JdbcDataSourceImpl() {
			super();
		}
		
		@Override
		public Connection getConnection() throws Exception {

			Connection conn = null;

			try {
				Class.forName("org.postgresql.Driver");

				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "pedro", "pedro");

			} catch (SQLException e) {

				throw new Exception(e);
			}

			return conn;
		}
	}
	
	public static class JndiDataSourceImpl extends DataSourceImpl {
		
		private JndiDataSourceImpl() {
			super();
		}
		
		@Override
		public Connection getConnection() throws Exception {

			if (this.datasource == null) {

				try {
					InitialContext context = new InitialContext();

					this.datasource = (javax.sql.DataSource) context.lookup("java:comp/env/jdbc/default");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}

			return this.datasource.getConnection();
		}
	}
	
	@Override
	public Connection getConnection() throws Exception {

		return this.datasource.getConnection();
	}

	public static DataSourceImpl getInstance(Class<? extends DataSourceImpl> clazz) {

		synchronized (DataSourceImpl.class) {

			if (instance == null) {

				if (clazz.equals(JdbcDataSourceImpl.class)) {
					instance = new JdbcDataSourceImpl();
				}
				else if (clazz.equals(JndiDataSourceImpl.class)) {
					instance = new JndiDataSourceImpl();
				}
			}
		}

		return instance;
	}
}
