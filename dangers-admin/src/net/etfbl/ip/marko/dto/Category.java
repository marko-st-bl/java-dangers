package net.etfbl.ip.marko.dto;

import java.io.Serializable;

public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8742500554814981495L;
	
	private int id;
	private String name;
	
	public Category() {
		super();
	}
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
