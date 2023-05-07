package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.ModelException;
import model.User;

public class MySQLCompanyDAO implements CompanyDAO {

	@Override
	public boolean save(Company company) throws ModelException {

		DBHandler db = new DBHandler();

		String sqlInsert = "INSERT INTO companies VALUES " 
				+ " (DEFAULT, ?, ?, ?, ?, ?)";

		db.prepareStatement(sqlInsert);
		db.setString(1, company.getName());
		db.setString(2, company.getRole());
		db.setDate(3, new java.sql.Date(company.getStart().getTime()));
		if (company.getEnd() != null) {
			db.setDate(4, new java.sql.Date(company.getEnd().getTime()));
		} else {
			db.setNullDate(4);
		}
		db.setInt(5, company.getUser().getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Company company) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE companies "
				         	+ "SET name = ?, "
				         	+ "role = ?, "
				         	+ "start = ?, "
				         	+ "end = ?, "
				         	+ "user_id = ? "
				         	+ "WHERE id = ?";
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, company.getName());
		db.setString(2, company.getRole());
		db.setDate(3, new java.sql.Date(company.getStart().getTime()));
		if (company.getEnd() != null) {
			db.setDate(4, new java.sql.Date(company.getEnd().getTime()));
		} else {
			db.setNullDate(4);
		}
		db.setInt(5, company.getUser().getId());
		db.setInt(6, company.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Company company) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM companies "
		         + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, company.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Company> listAll() throws ModelException {
		
		DBHandler db = new DBHandler();
		
		List<Company> companies = new ArrayList<>();
			
		String sqlQuery = " SELECT u.id AS user_id, c.*, "
						+ " c.name "
				        + " FROM users u "
				        + " INNER JOIN companies c "
				        + " ON u.id = c.user_id "
				        + " ORDER BY name";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Company c = createCompany(db);
			
			companies.add(c);
		}
		
		return companies;
	}

	@Override
	public Company findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sql = "SELECT * FROM companies WHERE id = ?";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Company c = null;
		while (db.next()) {
			c = createCompany(db);
			break;
		}
		
		return c;
	}
	
	private Company createCompany(DBHandler db) throws ModelException {
		
		Company c = new Company(db.getInt("id"));
		c.setName(db.getString("name"));
		c.setRole(db.getString("role"));
		c.setStart(db.getDate("start"));
		c.setEnd(db.getDate("end"));
		
		UserDAO userDAO = DAOFactory.createDAO(UserDAO.class); 
		
		User user = userDAO.findById(db.getInt("user_id"));
		c.setUser(user);
		
		return c;
	}
}
