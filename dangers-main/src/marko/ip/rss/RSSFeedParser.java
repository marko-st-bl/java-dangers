package marko.ip.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;


public class RSSFeedParser {
	
	static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String LINK = "link";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
	
	private URL url;
	
	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public RSSFeed readFeed() {
		RSSFeed feed = null;
		try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                    case ITEM:
                        if (isFeedHeader) {
                            isFeedHeader = false;
                            feed = new RSSFeed(title, link, description, pubdate);
                        }
                        event = eventReader.nextEvent();
                        break;
                    case TITLE:
                        title = getCharacterData(event, eventReader);
                        break;
                    case DESCRIPTION:
                        description = getCharacterData(event, eventReader);
                        break;
                    case LINK:
                        link = getCharacterData(event, eventReader);
                        break;
                    case GUID:
                        guid = getCharacterData(event, eventReader);
                        break;

                    case PUB_DATE:
                        pubdate = getCharacterData(event, eventReader);
                        break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        RSSFeedMessage message = new RSSFeedMessage(guid, title, description, 
                        		link, pubdate);
                        feed.getEntries().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
	}
	
	private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

}
