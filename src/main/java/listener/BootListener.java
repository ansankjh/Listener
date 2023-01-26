package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class BootListener
 *
 */
@WebListener
public class BootListener implements ServletContextListener {

	// 톰캣 동작시 자동으로 실행되는 코드
	 public void contextInitialized(ServletContextEvent sce)  { 
         // context(application)영역에 현재 접속자 수를 저장할 속성변수(attribute) 생성(초기화)
		 sce.getServletContext().setAttribute("currentCount", 0);
		 System.out.println("context currentCount속성값 초기화");
		 
		 // 드라이버 로딩 Class.forName("org.mariadb.jdbc.Driver")
		 try {
			 Class.forName("org.mariadb.jdbc.Driver");
			 System.out.println("BootListener에서 드라이버 로딩 성공");
		 } catch(Exception e) {
			 System.out.println("BootListener에서 드라이버 로딩 실패");
			 e.printStackTrace();
		 }
		 
    }
	
	 // 톰캣 정지시 자동으로 실행되는 코드
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

   
	
}
