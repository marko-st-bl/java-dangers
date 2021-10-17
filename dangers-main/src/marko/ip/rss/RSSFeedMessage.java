package marko.ip.rss;

import java.io.Serializable;

import marko.ip.dto.Post;


public class RSSFeedMessage extends Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String guid;
	
	public RSSFeedMessage() {
		super();
	}
	
	public RSSFeedMessage(String guid, String title, String description, String link, String pubDate) {
		super(description, link, pubDate, "rss");
		this.title = title;
		//this.guid = guid.replaceAll("[^a-zA-Z]", "");
		this.guid = guid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
