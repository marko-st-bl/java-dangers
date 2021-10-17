package net.etfbl.ip.marko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.marko.dto.Aid;

public class AidDAO {
	
	public boolean addAid(Aid aid) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "insert into aid (title, description, date, location, image, categoryId) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, aid.getTitle());
			ps.setString(2, aid.getDescription());
			ps.setTimestamp(3, new java.sql.Timestamp(aid.getDate().getTime()));
			ps.setString(4, aid.getAddress());
			ps.setString(5, aid.getImageUrl());
			ps.setInt(6, new CategoryDAO().getCategoryByName(aid.getCategory()).getId());
			
			retVal = ps.executeUpdate() == 1;
			
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public List<Aid> getAids() {
		List<Aid> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select a.id, title, description, location, date, image, name, createdAt, reportedAsFalse "
				+ "from aid a inner join category c on a.categoryId=c.id "
				+ "where status='valid'";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Aid(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new java.util.Date(rs.getTimestamp(5).getTime()), rs.getString(6),
						rs.getString(7), rs.getTimestamp(8), rs.getBoolean(9)));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

	public Aid getAidById(int id) {
		Aid retVal = null;
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String query = "select a.id, title, description, location, date, image, name, createdAt, reportedAsFalse "
				+ "from aid a inner join category c on a.categoryId=c.id "
				+ "where a.id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new Aid(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getBoolean(9));
			}
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal; 
	}

	public boolean blockAid(int id) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = "update aid set status='blocked' "
				+ "where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps= conn.prepareStatement(query);
			ps.setInt(1, id);
			
			if(ps.executeUpdate() == 1) {
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

	public boolean reportAid(int id) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = "update aid set reportedAsFalse=1 "
				+ "where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps= conn.prepareStatement(query);
			ps.setInt(1, id);
			
			if(ps.executeUpdate() == 1) {
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

	public boolean deleteAid(int id) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps =null;
		
		String query = "delete from aid where id=?";
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			retVal = ps.executeUpdate() == 1;
			
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return retVal;
	}

}
