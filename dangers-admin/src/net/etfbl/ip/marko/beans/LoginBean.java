package net.etfbl.ip.marko.beans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.google.gson.Gson;

import net.etfbl.ip.marko.dao.LoginDAO;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1273339461352698650L;
	private final Gson gson = new Gson();
	
	private int numberOfOnlineUsers;
	private String activity;
	
	
	
	public LoginBean() {
		super();
		this.numberOfOnlineUsers=new LoginDAO().getNumberOfOnlineUsers();
		Map<Integer, Integer> usersPerHour = new LoginDAO().activity();
		activity = gson.toJson(usersPerHour);
		System.out.println(activity);
	}
	
	public int getNumberOfOnlineUsers() {
		return numberOfOnlineUsers;
	}

	public void setNumberOfOnlineUsers(int numberOfOnlineUsers) {
		this.numberOfOnlineUsers = numberOfOnlineUsers;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}
