package marko.ip.dto;

import java.io.Serializable;

public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5926435629369962320L;
	
	private int id;
	private String name;
	
	public Category() {
		super();
	}
	public Category(int id, String name) {
		this.setId(id);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
