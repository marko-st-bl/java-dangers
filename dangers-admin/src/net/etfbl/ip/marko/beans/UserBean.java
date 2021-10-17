package net.etfbl.ip.marko.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.etfbl.ip.marko.dao.UserDAO;
import net.etfbl.ip.marko.dto.User;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8366586646308610350L;
	
	private List<User> users = new ArrayList<>();
	
	
	private User selected;
	private int numberOfRegisteredUsers;
	
	public UserBean() {
		super();
		users = new UserDAO().getAllUsers();
		numberOfRegisteredUsers = users.size();
	}
	

	public User getSelected() {
		return selected;
	}

	public void setSelected(User user) {
		this.selected = user;
	}

	public int getNumberOfRegisteredUsers() {
		return numberOfRegisteredUsers;
	}

	public void setNumberOfRegisteredUsers(int numberOfRegisteredUsers) {
		this.numberOfRegisteredUsers = numberOfRegisteredUsers;
	}
	
	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public void resetPassword() {
		int leftLimit = 48; //  '0'
	    int rightLimit = 122; // 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    System.out.println(generatedString);
	    Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("id")) {
			int z = Integer.parseInt(reqMap.get("id"));
			System.out.println(z);
			new UserDAO().resetPassword(z, generatedString);
		}
	    
	    
	}
	
	public void blockUser() {
		System.out.println("blocked");
		    Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			if (reqMap.containsKey("id")) {
				int z = Integer.parseInt(reqMap.get("id"));
				System.out.println(z);
				for(User user: users) {
					if(user.getId() == z) {
						user.setStatus("blocked");
						new UserDAO().blockUser(z);
					}
				}
				
			}
	}
	
	public void activate() {
		System.out.println("activate");
	    Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("id")) {
			int z = Integer.parseInt(reqMap.get("id"));
			System.out.println(z);
			for(User user: users) {
				if(user.getId() == z) {
					user.setStatus("active");
					new UserDAO().activate(z);
				}
			}
			
		}
	}
	
}
