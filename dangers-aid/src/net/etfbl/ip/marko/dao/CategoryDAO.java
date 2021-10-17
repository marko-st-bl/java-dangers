package net.etfbl.ip.marko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.marko.dto.Category;


public class CategoryDAO {

	public List<Category> getCategories(){
		List<Category> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, name "
				+ "from category";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Category(rs.getInt(1), rs.getString(2)));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}
	
	public Category getCategoryByName(String name) {
		Category retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select id, name "
				+ "from category where name=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new Category(rs.getInt(1), rs.getString(2));
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
