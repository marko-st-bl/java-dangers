package marko.ip.beans;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSessionBindingListener;

import marko.ip.dao.LoginDAO;
import marko.ip.dao.UserDAO;
import marko.ip.dto.User;

public class UserBean implements Serializable, HttpSessionBindingListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isLoggedIn = false;
	private User user = new User();

	public boolean login(String username, String password) {
		user = new UserDAO().getUserByUsernamePassword(username, password);
		if (user != null) {
			return true;
		}
		return false;
	}
	
	public void logut() {
		new LoginDAO().addLogut(user.getId());
	}

	public boolean addLogin() {
		return new LoginDAO().addUserLogin(user.getId());
	}

	public int getNumOfLogins() {
		return new LoginDAO().getNumberOfLoginsById(user.getId());
	}

	public boolean isUsernameUsed(String username) {
		return new UserDAO().isUsernameUsed(username);
	}

	public boolean isEmailUsed(String email) {
		return new UserDAO().isEmailUsed(email);
	}

	public boolean addUser() {
		return new UserDAO().addUser(user);
	}

	public void readUser() {
		user = new UserDAO().getUserByUsernamePassword(user.getUsername(), user.getPassword());
	}

	public boolean updateUser() {
		return new UserDAO().updateUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public List<String> getRecipients(){
		return new UserDAO().getRecipients();
	}

	/*
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("User logged out");
		System.out.println(user.getId());
		new LoginDAO().addLogut(user.getId());
	}
	*/

}
