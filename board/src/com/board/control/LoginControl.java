package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;
import com.board.vo.User;

public class LoginControl implements PageControl, DataBinding{

	UserDao userDao;
	BoardDao boardDao;
	
	public LoginControl setDao(UserDao userDao,BoardDao boardDao){
		
		this.userDao = userDao;
		this.boardDao = boardDao;
		
		return this;
		
	}
	
	@Override
	public Object[] getDataBinders() {
		
		return new Object[]{"user",com.board.vo.User.class};
	}

	@Override
	public String excute(HashMap<String,Object> model) {
		// TODO Auto-generated method stub
		
		HttpSession		session    = (HttpSession)model.get("session");
		User			userInfo   = (User)userDao.userLogin((User)model.get("user"));
		
		if(userInfo != null){
			
			session.setAttribute("userInfo",userInfo);
			//게시글 리스트는 항상보여줘야 하므로 모든 리퀘스트에 처리 해주어야한다.
			model.put("userInfo",  userInfo);
			model.put("boardList", (ArrayList<Board>)boardDao.getBoardList());
			
			return "foward:view/main.jsp";
			
		}else{
			
			return "sendRedirect:view/error.jsp";
		}
		
		
	}
	
}
