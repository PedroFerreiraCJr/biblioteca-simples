package br.com.dotofcodex.biblioteca.utils;

import java.sql.Connection;

public interface DataSource {

	public abstract Connection getConnection() throws Exception;
	
}
