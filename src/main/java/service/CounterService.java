package service;

import java.sql.Connection;

import dao.CounterDao;
import util.DBUtil;

public class CounterService {
	private CounterDao counterDao;
	
	// 오늘 첫번째 접속자가 발생 insert : selectTodayCount() 호출의 결과가 0일때
	public void insertCounter() {
		// 리턴할게 없어서 객체 생성x
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao불러오기
			this.counterDao = new CounterDao();
			counterDao.insertCounter(conn);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 추가 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 오늘 첫번째 접속자가 아닐때 update : : selectTodayCount() 호출의 결과가 0이 아닐때
	public void updateCounter() {
		// 리턴할게 없어서 객체 생성x
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao불러오기
			this.counterDao = new CounterDao();
			counterDao.updateCounter(conn);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 추가 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 오늘 접속자 수 확인 select
	public int getSelectTodayCount() {
		// 객체 생성
		int todayCnt = 0;
		// 드라이버 초기화
		Connection conn = null;
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao불러오기
			this.counterDao = new CounterDao();
			todayCnt = counterDao.selectTodayCount(conn);
			// 커밋하기
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return todayCnt;
	}
	// 전체 접속 수 확인 select
	public int getSelectTotalCount() {
		// 객체 생성
		int totalCnt = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao불러오기
			this.counterDao = new CounterDao();
			totalCnt = counterDao.selectTotalCount(conn);
			// 커밋하기
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return totalCnt;
	}
}
