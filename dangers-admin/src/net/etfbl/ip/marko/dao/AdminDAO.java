package net.etfbl.ip.marko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.ip.marko.dto.Admin;

public class AdminDAO {
	
	public Admin getAdminByUsernamePassword(String username, String password) {
		Admin retVal = null;
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String query = "select id, username "
				+ "from admin "
				+ "where username=? and password=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new Admin(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal; 
	}

}
