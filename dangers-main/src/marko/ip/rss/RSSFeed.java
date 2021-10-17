package marko.ip.rss;

import java.util.ArrayList;
import java.util.List;

public class RSSFeed {


	final String title;
    final String link;
    final String description;
    final String pubDate;
    
    final List<RSSFeedMessage> entries = new ArrayList<RSSFeedMessage>();
    
    /**
	 * @param title
	 * @param link
	 * @param description
	 * @param pubDate
	 */
	public RSSFeed(String title, String link, String description, String pubDate) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public List<RSSFeedMessage> getEntries() {
		return entries;
	}

	@Override
	public String toString() {
		return "RSSFeed [title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate
				+ "]";
	}
    
}
