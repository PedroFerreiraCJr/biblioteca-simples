package br.com.dotofcodex.biblioteca.dao;

import java.util.List;

public interface DataAccessObject<T> {

	public abstract T create(T object) throws Exception;

	public abstract T retrieve(T object) throws Exception;

	public abstract void update(T object) throws Exception;

	public abstract void delete(T object) throws Exception;

	public abstract List<T> listAll();
}
