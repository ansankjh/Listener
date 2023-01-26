package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CounterService;

/**
 * Servlet implementation class GlobalHello
 */
@WebServlet("/hello")
public class GlobalHello extends HttpServlet {
	private CounterService counterService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.counterService = new CounterService();
		// 오늘 접속자수
		int todayCount = counterService.getSelectTodayCount();
		request.setAttribute("todayCount", todayCount);
		
		// 총 접속자수
		int totalCount = counterService.getSelectTotalCount();
		request.setAttribute("totalCount", totalCount);
		
		
		request.getRequestDispatcher("/WEB-INF/view/hello.jsp").forward(request, response);
	}
}
