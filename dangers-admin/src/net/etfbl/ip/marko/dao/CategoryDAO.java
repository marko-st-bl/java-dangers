package net.etfbl.ip.marko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.marko.dto.Category;

public class CategoryDAO {
	
	public List<Category> getAllCategories(){
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

	public int addCategory(Category category) {
		int retVal = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "insert into category (name) values (?)";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, category.getName());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			rs.next();
			retVal = rs.getInt(1);
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}
	
	public void deleteCategory(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = "delete from category where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}

}
