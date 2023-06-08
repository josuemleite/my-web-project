package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.Service;
import model.User;

public class MySQLServiceDAO implements ServiceDAO {

	@Override
	public boolean save(Service service) throws ModelException {

		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO services VALUES "
				+ " (DEFAULT, ?, ?, ?, ?, ?)";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, service.getTypeOfService());
		db.setDouble(2, service.getValuePerHour());
		db.setString(3, service.getExpertiseLevel());
		db.setString(4, service.getPortfolio());
		db.setInt(5, service.getUser().getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Service service) throws ModelException {

		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE services "
				         	+ "SET type_of_service = ?, "
				         	+ "value_per_hour = ?, "
				         	+ "expertise_level = ?, "
				         	+ "portfolio = ?, "
				         	+ "user_id = ? "
				         	+ "WHERE id = ?";
		
		db.prepareStatement(sqlUpdate);
		db.setString(1, service.getTypeOfService());
		db.setDouble(2, service.getValuePerHour());
		db.setString(3, service.getExpertiseLevel());
		db.setString(4, service.getPortfolio());
		db.setInt(5, service.getUser().getId());
		db.setInt(6, service.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Service service) throws ModelException {

		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM services "
		         + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, service.getId());
		
		return db.executeUpdate() > 0;

	}

	@Override
	public List<Service> listAll() throws ModelException {
		
		DBHandler db = new DBHandler();
		
		List<Service> services = new ArrayList<>();
			
		String sqlQuery = " SELECT u.id AS user_id, s.*, "
						+ " s.type_of_service "
				        + " FROM users u "
				        + " INNER JOIN services s "
				        + " ON u.id = s.user_id "
				        + " ORDER BY type_of_service";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Service s = createService(db);
			services.add(s);
		}
		
		return services;
	}

	@Override
	public Service findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sql = "SELECT * FROM services WHERE id = ?";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Service s = null;
		while (db.next()) {
			s = createService(db);
			break;
		}
		
		return s;
	}
	
	private Service createService(DBHandler db) throws ModelException {

		Service s = new Service(db.getInt("id"));
		s.setTypeOfService(db.getString("type_of_service"));
		s.setValuePerHour(db.getDouble("value_per_hour"));
		s.setExpertiseLevel(db.getString("expertise_level"));
		s.setPortfolio(db.getString("portfolio"));
		
		UserDAO userDAO = DAOFactory.createDAO(UserDAO.class); 
		
		User user = userDAO.findById(db.getInt("user_id"));
		s.setUser(user);
		
		return s;
	}
}
