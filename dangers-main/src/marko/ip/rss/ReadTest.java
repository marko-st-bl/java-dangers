package marko.ip.rss;

public class ReadTest {

	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser("https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151");
		RSSFeed feed = parser.readFeed();
		System.out.println(feed);
		for(RSSFeedMessage message: feed.getEntries()) {
			System.out.println(message);
		}

	}

}
