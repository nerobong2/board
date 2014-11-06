package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;
import com.board.vo.User;

public class LoginControl implements PageControl{

	UserDao userDao;
	BoardDao boardDao;
	
	public LoginControl setDao(UserDao userDao,BoardDao boardDao){
		
		this.userDao = userDao;
		this.boardDao = boardDao;
		
		return this;
		
	}
	@Override
	public String excute(HashMap<String,Object> model) {
		// TODO Auto-generated method stub
		System.out.println("LoginControl...");
		HttpServletRequest request = (HttpServletRequest)model.get("request");
		HttpSession		   session = (HttpSession)model.get("session");
		
		String userId = (String)request.getParameter("userId");
		String userPassword = (String)request.getParameter("userPassword");
		User user = new User().setUserId(userId).setUserPassword(userPassword);
		
		
		Boolean result = userDao.loginValidationCheck(user);
		
		if(result){
			System.out.println(result);
			User userInfo = userDao.getUserInfo(user);
			session.setAttribute("userInfo", userInfo);
			
		}
		
		ArrayList<Board> boardList = (ArrayList<Board>)boardDao.getBoardList();
		request.setAttribute("boardList", boardList);
		
		return "foward:view/main.jsp";
	}

}
