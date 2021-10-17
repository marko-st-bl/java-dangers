package marko.ip.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import marko.ip.beans.PostBean;
import marko.ip.beans.UserBean;
import marko.ip.util.FormValidator;

/**
 * Servlet implementation class NewPost
 */
@MultipartConfig
@WebServlet("/NewPost")
public class NewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPost() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post request");
		HttpSession session = request.getSession();
		
		UserBean userBean = ((UserBean)session.getAttribute("userBean"));
		
		if(userBean != null && userBean.isLoggedIn()) {	
			PrintWriter out = response.getWriter();
			
			String text = request.getParameter("text");
			String type = request.getParameter("type");
			String youtube = request.getParameter("youtube");
			String link = request.getParameter("link");
			Part imgPart = request.getPart("img");
			Part videoPart = request.getPart("video");
			
			String validationResult = FormValidator.validateNewPostForm(type, text, youtube, link, imgPart, videoPart);
			
			if(validationResult.equals("OK")) {
				
				InputStream input;
				String url = "";
				
				PostBean postBean = new PostBean();
				postBean.getPost().setDescription(text);
				postBean.getPost().setType(type);
				System.out.println();
				postBean.getPost().setAuthor(((UserBean)session.getAttribute("userBean")).getUser());
				
				if(type.equals("image")) {
					input = imgPart.getInputStream();
					String name = new Date().getTime() + ".jpg";
					url = "http://127.0.0.1:8080/dangers-main/assets/img/post/" +  name;
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					String contextPath = getServletContext().getRealPath("/assets/img/post/");
					System.out.println(contextPath);
					byte[] buffer = new byte[1024 * 4];
					int read;
					while ((read = input.read(buffer)) != -1) {
						baos.write(buffer, 0, read);
					}
					try (OutputStream outputStream = new FileOutputStream(contextPath + name)) {
						baos.writeTo(outputStream);
					}
					postBean.getPost().setUrl(url);
					input.close();
				} else if(type.equals("video")) {
					input = videoPart.getInputStream();
					String name = new Date().getTime() + ".mp4";
					url = "http://127.0.0.1:8080/dangers-main/assets/video/post/" +  name;
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					String contextPath = getServletContext().getRealPath("/assets/video/post/");
					System.out.println(contextPath);
					byte[] buffer = new byte[1024 * 4];
					int read;
					while ((read = input.read(buffer)) != -1) {
						baos.write(buffer, 0, read);
					}
					try (OutputStream outputStream = new FileOutputStream(contextPath + name)) {
						baos.writeTo(outputStream);
					}
					input.close();
					postBean.getPost().setUrl(url);
				} else if(type.equals("youtube")) {
					postBean.getPost().setUrl(request.getParameter("youtube"));
				} else if(type.equals("link")) {
					postBean.getPost().setUrl(request.getParameter("link"));
				}
				/*		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
				 */
				response.setContentType("text/html");
				if(postBean.addPost()) {
					out.print("200");
				} else {
					out.print("401");
				}
				out.flush();
			} else {
				out.print(validationResult);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
