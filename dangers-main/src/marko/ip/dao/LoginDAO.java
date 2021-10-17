package marko.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	public int getNumberOfLoginsById(int userId) {
		int retVal = 0;
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String query = "select count(*) "
				+ "from login "
				+ "where userId=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = rs.getInt(1);
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal; 
	}
	
	public boolean addUserLogin(int userId) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "insert into login (userId) values (?)";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			
			retVal = ps.executeUpdate() == 1;
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public void addLogut(int userId) {
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "update login set logoutTime=current_timestamp() where userId=? and logoutTime is null";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			
			ps.executeUpdate();
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
	}
}
