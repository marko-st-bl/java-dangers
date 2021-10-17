package marko.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import marko.ip.dto.Comment;

public class CommentDAO {
	
	public List<Comment> getAllCommentsByPostId(int postId){
		List<Comment> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, userId, text, url, createdAt "
				+ "from comment where postId=? "
				+ "order by createdAt asc";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Comment(rs.getInt(1), new UserDAO().getUserById(rs.getInt(2)), rs.getString(3), rs.getString(4),
						rs.getTimestamp(5)));
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public boolean addComment(Comment comment) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "insert into comment (postId, userId, text, url) values (?, ?, ?, ?)";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, comment.getPostId());
			ps.setInt(2, comment.getAuthor().getId());
			ps.setString(3, comment.getText());
			ps.setString(4, comment.getUrl());
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
