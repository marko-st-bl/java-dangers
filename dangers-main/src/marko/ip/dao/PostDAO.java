package marko.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import marko.ip.dto.Post;

public class PostDAO {
	
	public List<Post> getAllPosts(){
		List<Post> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, author, description, type, url, createdAt "
				+ "from post where type!='warning'";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Post post = new Post(rs.getInt(1), new UserDAO().getUserById(rs.getInt(2)), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6));
				post.setComments(new CommentDAO().getAllCommentsByPostId(post.getId()));
				retVal.add(post);
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public boolean addPost(Post post) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "insert into post (author, description, type, url) values (?, ?, ?, ?)";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, post.getAuthor().getId());
			ps.setString(2, post.getDescription());
			ps.setString(3, post.getType());
			ps.setString(4, post.getUrl());
			retVal = ps.executeUpdate() == 1;
			
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

}
