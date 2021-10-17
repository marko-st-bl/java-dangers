package marko.ip.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class Post implements Serializable, Comparable<Post>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User author;
	private String description;
	private String type;
	private String url;
	private Timestamp createdAt;
	private List<Comment> comments;
	

	public Post() {
		super();
	}
	
	public Post(int id, User author, String description, String type, String url, Timestamp createdAt) {
		super();
		this.id = id;
		this.author = author;
		this.description = description;
		this.type = type;
		this.url = url;
		this.createdAt = createdAt;
	}

	public Post(User author, String description, String type) {
		this.author = author;
		this.description = description;
		this.type = type;
	}

	public Post(int id, String description, Timestamp createdAt, String type) {
		this.id = id;
		this.description = description;
		this.createdAt = createdAt;
		this.type = type;
	}

	public Post(String description, String link, String pubDate, String type) {
		this.description = description;
		this.url = link;
		this.type = type;
		try {
			DateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
			System.out.println(pubDate);
			this.createdAt = new Timestamp(sdf.parse(pubDate).getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getFormattedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
		return sdf.format(createdAt.getTime());
		
	}
	


	@Override
	public int compareTo(Post o) {
		if(o.createdAt == null)
			return -1;
		else if(this.createdAt == null)
			return 1;
		else
			return this.createdAt.compareTo(o.createdAt);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
