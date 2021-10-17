package marko.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import marko.ip.dto.User;

public class UserDAO {
	
	public List<User> getAllUsers(){
		List<User> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, firstName, lastName, email, password, country, region, city, avatar, status, "
				+ "createdAt, notificationApp, notificationEmail "
				+ "from user";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getTimestamp(12), rs.getBoolean(13), rs.getBoolean(14)));
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}
	
	public User getUserById(int id) {
		User retVal = null;
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String query = "select id, firstName, lastName, avatar "
				+ "from user where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal; 
	}
	
	public User getUserByUsernamePassword(String username, String password) {
		User retVal = null;
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String query = "select id, firstName, lastName, username, password, email, country, region, city, avatar, status, "
				+ "createdAt, notificationApp, notificationEmail "
				+ "from user where username=? and password=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getTimestamp(12), rs.getBoolean(13), rs.getBoolean(14));
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal; 
	}
	
	public boolean addUser(User user) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "insert into user (firstName, lastName, username, password, email) values (?, ?, ?, ?, ?)";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getEmail());
			retVal = ps.executeUpdate() == 1;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public boolean isUsernameUsed(String username) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, username from user where username=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = true;
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public boolean isEmailUsed(String email) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, username from user where email=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = true;
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public boolean updateUser(User user) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = "update user set "
				+ "firstName=?, "
				+ "lastName=?, "
				+ "username=?, "
				+ "password=?, "
				+ "email=?, "
				+ "country=?, "
				+ "region=?, "
				+ "city=?, "
				+ "avatar=?, "
				+ "notificationApp=?, "
				+ "notificationEmail=? "
				+ "where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getCountry());
			ps.setString(7, user.getRegion());
			ps.setString(8, user.getCity());
			ps.setString(9, user.getAvatar());
			ps.setBoolean(10, user.isNotificationApp());
			ps.setBoolean(11, user.isNotificationEmail());
			ps.setInt(12, user.getId());
			retVal = ps.executeUpdate() == 1;
			
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}
	
	public List<String> getRecipients(){
		List<String> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select email from user "
				+ "where notificationEmail=1";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(rs.getString(1));
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return retVal;
	}
}
