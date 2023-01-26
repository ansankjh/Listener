package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterDao {
	// 오늘 첫번째 접속자가 발생 insert : selectTodayCount() 호출의 결과가 0일때
	public void insertCounter(Connection conn) throws Exception {
		// 리턴할게 없어서 객체 없음
		// 쿼리문 작성
		String sql = "INSERT INTO site_counter(counter_date, counter_num) values(CURDATE(), 1)";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		stmt.executeUpdate();
	}
	// 오늘 첫번째 접속자가 아닐때 update : : selectTodayCount() 호출의 결과가 0이 아닐때
	public void updateCounter(Connection conn) throws Exception {
		// 리턴할게 없어서 객체 없음
		// 쿼리문 작성
		String sql = "UPDATE site_counter SET counter_num = counter_num+1"
					+ " WHERE counter_date = CURDATE()";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		stmt.executeUpdate();
	}
	
	// 오늘 접속자 수 확인 select
	public int selectTodayCount(Connection conn) throws Exception {
		// 객체 생성
		int todayCount = 0;
		// 쿼리문 작성
		String sql = "SELECT counter_num counterNum FROM site_counter "
					+ "WHERE counter_date = CURDATE()";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			todayCount = rs.getInt("counterNum");
		}
		
		stmt.close();
		rs.close();
		return todayCount;
	}
	// 전체 접속 수 확인 select
	public int selectTotalCount(Connection conn) throws Exception {
		// 객체 생성
		int totalCount = 0;
		// 쿼리문 작성
		String sql = "SELECT SUM(counter_num) totalCount FROM site_counter";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("totalCount");
		}
		stmt.close();
		rs.close();
		return totalCount;
	}
}
