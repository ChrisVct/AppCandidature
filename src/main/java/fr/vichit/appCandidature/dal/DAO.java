package fr.vichit.appCandidature.dal;

import java.util.List;

import fr.vichit.appCandidature.BusinessException;

public interface DAO<T> {
	public void insert(T t) throws BusinessException ;
	public List<T> selectAll() throws BusinessException;
	public T selectByID(int id);
	public void update(T t) throws BusinessException;
	public void delete(int id);
	
}
