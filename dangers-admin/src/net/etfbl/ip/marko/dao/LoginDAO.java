package net.etfbl.ip.marko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginDAO {

	public int getNumberOfOnlineUsers() {
		int retVal = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select count(*) " + "from login " + "where logoutTime is null";

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs.next()) {
				retVal = rs.getInt(1);
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return retVal;
	}

	public Map<Integer, Integer> activity() {
		Map<Integer, Integer> retVal = new HashMap<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select hour(loginTime) as h, count(*) as users "
				+ "from login "
				+ "where loginTime > date_sub(CURRENT_TIMESTAMP, INTERVAL 1 day) "
				+ "group by hour(loginTime)";

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal.put(rs.getInt(1), rs.getInt(2));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return retVal;
	}

}
