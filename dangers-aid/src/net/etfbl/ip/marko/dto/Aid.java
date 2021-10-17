package net.etfbl.ip.marko.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Aid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3810972682307113943L;

	private int id;
	private String title;
	private String description;
	private String imageUrl;
	private Date date;
	private String address;
	private String status;
	private String category;
	private Timestamp createdAt;
	private boolean reportedAsFalse;

	public Aid() {
		super();
	}

	public Aid(String title, String description, String imgUrl, String location, String category) {
		this.title = title;
		this.description = description;
		this.imageUrl = imgUrl;
		this.address = location;
		this.category = category;
	}

	public Aid(int id, String title, String description, String imgUrl, String location, String status,
			String category) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.imageUrl = imgUrl;
		this.address = location;
		this.status = status;
		this.category = category;
	}

	public Aid(int id, String title, String description, String location, Date date, String image, String category, 
			Timestamp createdAt, boolean reported) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.address = location;
		this.date = date;
		this.imageUrl = image;
		this.category = category;
		this.createdAt = createdAt;
		this.reportedAsFalse = reported;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getReportedAsFalse() {
		return reportedAsFalse;
	}

	public void setReportedAsFalse(boolean reportedAsFalse) {
		this.reportedAsFalse = reportedAsFalse;
	}

}
