package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import service.CounterService;

/**
 * Application Lifecycle Listener implementation class CounterListener
 *
 */
@WebListener
public class CounterListener implements HttpSessionListener {
	private CounterService counterService;
	// 세션이 생성될때
    public void sessionCreated(HttpSessionEvent se)  { 
        // 현재 서버의 접속자 수 -> 서버속성을 이용 가져오기
    	int num = (int)(se.getSession().getServletContext().getAttribute("currentCount"));
    	// 세션이 생성되면 가져온 num을 +1하기
    	se.getSession().getServletContext().setAttribute("currentCount", num+1);
    	System.out.println(se.getSession().getServletContext().getAttribute("currentCount") + "<-- setAttribute한 이후의 현재 접속자수");
    	
    	this.counterService = new CounterService();
    	int todayCount = counterService.getSelectTodayCount();
    	if(todayCount == 0) {
    		counterService.insertCounter();
    	} else {
    		counterService.updateCounter();
    	}
    }
    // 세션이 소멸될때
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// 반대로
    	// 현재 서버의 접속자 수 -> 서버속성을 이용 가져오기
    	int num = (int)(se.getSession().getServletContext().getAttribute("cuurentCount"));
    	se.getSession().getServletContext().setAttribute("currentCount", num-1);
    	System.out.println(se.getSession().getServletContext().getAttribute("currentCount") + "<-- setAttribute한 이후의 현재 접속자수");
    }
	
}
