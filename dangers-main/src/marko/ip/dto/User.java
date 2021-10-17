package marko.ip.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private String country;
	private String region;
	private String city;
	private String avatar;
	private String status;
	private Timestamp createdAt;
	private boolean notificationApp;
	private boolean notificationEmail;

	public User() {
		super();
	}
	
	// Constructor that takes all parameters
	public User(int id, String firstName, String lastName, String username, String password, 
			String email, String country, String region,String city, String avatar, String status, 
			Timestamp createdAt, boolean notificationApp, boolean notificationEmail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.country = country;
		this.region = region;
		this.city = city;
		this.avatar = avatar;
		this.status = status;
		this.createdAt = createdAt;
		this.notificationApp=notificationApp;
		this.notificationEmail=notificationEmail;
	}
	
	/**	Constructor for registration
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param email
	 * @param password
	 */
	public User(String firstName, String lastName, String username, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	/*
	 * Constructor for getting users info in post
	 */
	public User(int id, String firstName, String lastName, String avatar) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.avatar = avatar;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
