package com.board.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.board.control.LoginControl;
import com.board.control.LogoutControl;
import com.board.control.MainControl;
import com.board.control.SignUpControl;
import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.db.DBConnectionPool;

public class WebAppListener implements ServletContextListener{

	
	DBConnectionPool conPool;
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		
		System.out.println("¾ÛÁ¾·á db Ä¿³Ø¼Ç °´Ã¼ ÀÚ¿ø ÇØÁ¦");
		ArrayList<Connection> list =  conPool.getConnList();
		
		for(Connection con : list){
			
			try {
				
				con.close();
				
			} catch (SQLException e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		
		System.out.println("contextInitialized...");
		
		ServletContext ctx = e.getServletContext();
		
		String driver	 = ctx.getInitParameter("driver");
		String dburl	 = ctx.getInitParameter("dburl");
		String id		 = ctx.getInitParameter("id");
		String password	 = ctx.getInitParameter("password");
		
		try {
			
			conPool = new DBConnectionPool(driver,dburl,id,password);
			
		} catch (ClassNotFoundException e1) {
			
			
			e1.printStackTrace();
		}
		
		UserDao userDao = new UserDao(conPool);
		BoardDao boardDao = new BoardDao(conPool);
		
		ctx.setAttribute("login.do", new LoginControl().setDao(userDao, boardDao));
		ctx.setAttribute("signUp.do", new SignUpControl().setDao(userDao,boardDao));
		ctx.setAttribute("main.do", new MainControl().setDao(userDao,boardDao));
		ctx.setAttribute("logout.do", new LogoutControl().setDao(userDao,boardDao));
	}

}
