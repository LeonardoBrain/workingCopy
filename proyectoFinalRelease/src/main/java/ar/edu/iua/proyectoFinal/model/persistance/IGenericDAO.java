package ar.edu.iua.proyectoFinal.model.persistance;



import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, ID extends Serializable>{

	public T save(T object);

	public T findById(int id) throws NotFoundException;

	public List<T> findAll();
	public List<T> findAllByDate();
	public List<T> findAllByPriority();

	public List<T> findAllByListName(String name) throws BusinessException;
	public List<T> findAllByListNameByDate(String name);
	public List<T> findAllByListNameByPriority(String name);



	public void delete(T object);

	public void update(T object);

}