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

import marko.ip.beans.CommentBean;
import marko.ip.beans.UserBean;

/**
 * Servlet implementation class NewComment
 */
@WebServlet("/NewComment")
@MultipartConfig
public class NewComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewComment() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserBean userBean = ((UserBean)session.getAttribute("userBean"));
		
		if(userBean != null && userBean.isLoggedIn()) {		
			Part imgPart = request.getPart("image");
			String text = request.getParameter("text");
			int postId = Integer.parseInt(request.getParameter("postId"));
			InputStream input;
			CommentBean commentBean = new CommentBean();
			commentBean.getComment().setText(text);
			commentBean.getComment().setPostId(postId);
			commentBean.getComment().setAuthor(userBean.getUser());
			if(imgPart.getSize() != 0) {
				input = imgPart.getInputStream();
				String name = new Date().getTime() + ".jpg";
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				String contextPath = getServletContext().getRealPath("/assets/img/comment/");
				System.out.println(contextPath);
				byte[] buffer = new byte[1024 * 4];
				int read;
				while ((read = input.read(buffer)) != -1) {
					out.write(buffer, 0, read);
				}
				try (OutputStream outputStream = new FileOutputStream(contextPath + name)) {
					out.writeTo(outputStream);
				}
				input.close();
				commentBean.getComment().setUrl("http://localhost:8080/dangers-main/assets/img/comment/" + name);
			}	
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "1728000");
			
			PrintWriter out = response.getWriter();
			if(commentBean.addComment()) {
				out.print("200");
			}else {
				out.print("400");
			}
			out.flush();
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
