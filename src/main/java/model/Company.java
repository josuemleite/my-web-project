package model;

import java.util.Calendar;
import java.util.Date;

public class Company {
	private int id;
	private String name;
	private String role;
	private Date start;
	private Date end;
	private User user;
	
	public Company() {
		this(0);
	}
	
	public Company(int id) {
		this.id = id;
		setName("");
		setRole("");
		setStart(Calendar.getInstance().getTime());
		setEnd(null);
		setUser(null);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
