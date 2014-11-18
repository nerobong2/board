package com.board.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.board.control.BoardViewControl;
import com.board.control.BoardWriteControl;
import com.board.control.LoginControl;
import com.board.control.LogoutControl;
import com.board.control.MainControl;
import com.board.control.SignUpControl;
import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.db.DBConnectionPool;

public class WebAppListener implements ServletContextListener{

	
	DBConnectionPool conPool;
	static ApplicationContext context;
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		
		/*System.out.println("¾ÛÁ¾·á db Ä¿³Ø¼Ç °´Ã¼ ÀÚ¿ø ÇØÁ¦");
		ArrayList<Connection> list =  conPool.getConnList();
		
		for(Connection con : list){
			
			try {
				
				con.close();
				
			} catch (SQLException e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}*/
		
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent e)  {
		
		System.out.println("contextInitialized...");
		
		
		
		try{
			
			ServletContext ctx = e.getServletContext();
			String propertiesPath = ctx.getRealPath(ctx.getInitParameter("propertiesCongifLocation"));
			System.out.println(propertiesPath);
			context = new ApplicationContext(propertiesPath);
					
			
			/*InitialContext initailContext = new InitialContext();
			
			DataSource ds = (DataSource)initailContext.lookup("java:comp/env/jdbc/boarddb");
			
			UserDao userDao = new UserDao(ds);
			
			BoardDao boardDao = new BoardDao(ds);

			ctx.setAttribute("login.do", new LoginControl().setDao(userDao, boardDao));
			ctx.setAttribute("signUp.do", new SignUpControl().setDao(userDao,boardDao));
			ctx.setAttribute("main.do", new MainControl().setDao(userDao,boardDao));
			ctx.setAttribute("logout.do", new LogoutControl().setDao(userDao,boardDao));
			ctx.setAttribute("view.do", new BoardViewControl().setDao(userDao,boardDao));
			ctx.setAttribute("write.do", new BoardWriteControl().setDao(userDao,boardDao));*/
			
			
		}catch(Exception e2){
			
		}
		
		
	/*	String driver	 = ctx.getInitParameter("driver");
		String dburl	 = ctx.getInitParameter("dburl");
		String id		 = ctx.getInitParameter("id");
		String password	 = ctx.getInitParameter("password");
		
		try {
			
			conPool = new DBConnectionPool(driver,dburl,id,password);
			
		} catch (ClassNotFoundException e1) {
			
			
			e1.printStackTrace();
		}
		*/
		
		
		
		
	}

}
