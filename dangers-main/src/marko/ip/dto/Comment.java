package marko.ip.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int postId;
	private User author;
	private String text;
	private String url;
	private Timestamp createdAt;
	
	public Comment() {
		super();
	}
	
	/*
	 * Constructor for getting Comments
	 */
	public Comment(int id, User author, String text, String url, Timestamp createdAt) {
		super();
		this.id = id;
		this.author = author;
		this.text = text;
		this.url = url;
		this.createdAt = createdAt;
	}
	
	/*
	 * Constructor for creating new comment
	 */
	public Comment(int postId, User author, String text, String url) {
		this.postId = postId;
		this.author = author;
		this.text = text;
		this.url = url;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
}
