package model;

public class Service {
	private int id;
	private String typeOfService;
	private Double valuePerHour;
	private String expertiseLevel;
	private String portfolio;
	private User user;
	
	public Service() {
		this(0);
	}
	
	public Service(int id) {
		this.id = id;
		setTypeOfService("");
		setValuePerHour(0.0);
		setExpertiseLevel("");
		setPortfolio("");
		setUser(null);
	}
	
	public int getId() {
		return id;
	}

	public String getTypeOfService() {
		return typeOfService;
	}
	
	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
	
	public Double getValuePerHour() {
		return valuePerHour;
	}
	
	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}
	
	public String getExpertiseLevel() {
		return expertiseLevel;
	}
	
	public void setExpertiseLevel(String expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
	}
	
	public String getPortfolio() {
		return portfolio;
	}
	
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}	
}
