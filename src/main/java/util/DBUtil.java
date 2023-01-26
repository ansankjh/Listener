package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		
		String dbUrl = "jdbc:mariadb://localhost:3306/shop";
		String dbUser = "root";
		String dbPw = "java1234";
		
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		conn.setAutoCommit(false);
		
		return conn;
	}
}
