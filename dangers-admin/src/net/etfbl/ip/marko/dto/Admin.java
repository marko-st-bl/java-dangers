package net.etfbl.ip.marko.dto;

import java.io.Serializable;

public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8242080033103723036L;
	
	private int id;
	private String username;
	private String password;
	
	public Admin() {
		super();
	}
	
	public Admin(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
