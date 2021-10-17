package marko.ip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import marko.ip.beans.PostBean;
import marko.ip.beans.UserBean;
import marko.ip.beans.WarningBean;
import marko.ip.dto.Post;
import marko.ip.dto.Warning;
import marko.ip.rss.RSSFeed;
import marko.ip.rss.RSSFeedParser;

/**
 * Servlet implementation class Posts
 */
@WebServlet("/Posts")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String RSS_FEED_URL = "https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151";
	
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Posts() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean userBean = ((UserBean)session.getAttribute("userBean"));
		
		if(userBean != null && userBean.isLoggedIn()) {			
			List<Post> posts = new PostBean().getAllPosts();
			List<Warning> warnings = new WarningBean().getAllWarnings();
			posts.addAll(warnings);
			//RSSFeedParser rssParser = new RSSFeedParser(RSS_FEED_URL);
			//RSSFeed rssFeed = rssParser.readFeed();
			//posts.addAll(rssFeed.getEntries());
			Collections.sort(posts);
			String newsJSONString = this.gson.toJson(posts);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "1728000");
			out.print(newsJSONString);
			out.flush();
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			dispatcher.forward(request, response);
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
