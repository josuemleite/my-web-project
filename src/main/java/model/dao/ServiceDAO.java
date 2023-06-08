package model.dao;

import java.util.List;

import model.ModelException;
import model.Service;

public interface ServiceDAO {
	boolean save(Service service) throws ModelException ;
	boolean update(Service service) throws ModelException;
	boolean delete(Service service) throws ModelException;
	List<Service> listAll() throws ModelException;
	Service findById(int id) throws ModelException;
}
