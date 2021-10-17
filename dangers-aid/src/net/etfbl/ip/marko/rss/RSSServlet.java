package net.etfbl.ip.marko.rss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import net.etfbl.ip.marko.dao.AidDAO;
import net.etfbl.ip.marko.dto.Aid;

/**
 * Servlet implementation class RSSServlet
 */
@WebServlet("/rss")
public class RSSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RSSServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("Calls for aid");
		feed.setDescription("Some people might need your help. If u eager to help others feel free to reach to them.");
		feed.setLink("https://localhost:8080/dangers-aid");
		feed.setCopyright("Marko Stojanovic 2020");
		feed.setLanguage("english");
		List<SyndEntry> entries = new ArrayList<>();
		List<Aid> aids = new AidDAO().getAids();
		for (Aid aid: aids) {
			SyndContent syndItemDescription = new SyndContentImpl();
			List<SyndCategory> syndCategories = new ArrayList<>();
			syndItemDescription.setValue(aid.getDescription() + " Adress: "  + aid.getAddress()
					+ " Time: " + aid.getDate());
			SyndCategoryImpl syndCategory = new SyndCategoryImpl();
			syndCategory.setName(aid.getCategory());
			syndCategories.add(syndCategory);
			SyndEntry item = new SyndEntryImpl();
			item.setTitle(aid.getTitle());
			item.setLink(aid.getImageUrl());
			item.setPublishedDate(aid.getCreatedAt());
			item.setDescription(syndItemDescription);
			item.setCategories(syndCategories);
			entries.add(item);
		}
		feed.setEntries(entries);
		try {
			response.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			System.out.println(ex.toString());
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
