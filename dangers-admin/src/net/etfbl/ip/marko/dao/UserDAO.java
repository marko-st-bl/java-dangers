package net.etfbl.ip.marko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.marko.dto.User;

public class UserDAO {

	public List<User> getAllUsers(){
		List<User> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, firstName, lastName, username, email, country, region, city, status "
				+ "from user";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public void resetPassword(int userId, String password) {
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "update user set password=? where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, password);
			ps.setInt(2, userId);
			
			ps.executeUpdate();
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}

	public void blockUser(int id) {
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "update user set status='blocked' where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
	}

	public void activate(int id) {
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "update user set status='active' where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
}
