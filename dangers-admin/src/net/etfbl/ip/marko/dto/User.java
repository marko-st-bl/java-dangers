package net.etfbl.ip.marko.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6550722551385142237L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private String country;
	private String region;
	private String city;
	private String status;
	private Timestamp createdAt;
	private boolean notificationApp;
	private boolean notificationEmail;
	
	public User() {
		super();
	}
	
	public User(int id, String firstName, String lastName, String username, String email, String country, String region,
			String city, String status) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.country = country;
		this.region = region;
		this.city = city;
		this.status = status;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isNotificationApp() {
		return notificationApp;
	}

	public void setNotificationApp(boolean notificationApp) {
		this.notificationApp = notificationApp;
	}

	public boolean isNotificationEmail() {
		return notificationEmail;
	}

	public void setNotificationEmail(boolean notificationEmail) {
		this.notificationEmail = notificationEmail;
	}

	
}
