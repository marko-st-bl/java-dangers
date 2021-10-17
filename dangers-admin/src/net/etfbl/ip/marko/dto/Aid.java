package net.etfbl.ip.marko.dto;

import java.io.Serializable;


public class Aid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3810972682307113943L;

	private int id;
	private String title;
	private String description;
	private String imageUrl;
	private String date;
	private String address;
	private String status;
	private String category;
	private boolean reportedAsFalse;

	public Aid() {
		super();
	}
	
	public Aid(int id, String title, String description, String imageUrl, String address,  String category, boolean reported) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.address = address;
		this.category = category;
		this.reportedAsFalse = reported;
	}

	public Aid(String title, String description, String imgUrl, String location, String category) {
		this.title = title;
		this.description = description;
		this.imageUrl = imgUrl;
		this.address = location;
		this.category = category;
	}

	public Aid(int id, String title, String description, String location, String date, String image, String category,
			boolean reportedAsFalse) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.address = location;
		this.date = date;
		this.imageUrl = image;
		this.category = category;
		this.reportedAsFalse = reportedAsFalse;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imgUrl) {
		this.imageUrl = imgUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean getReportedAsFalse() {
		return reportedAsFalse;
	}

	public void setReportedAsFalse(boolean reportedAsFalse) {
		this.reportedAsFalse = reportedAsFalse;
	}

}

