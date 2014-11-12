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
		
		HttpServletRequest request = (HttpServletRequest)model.get("request");
		HttpSession		session    = (HttpSession)model.get("session");
		User			user       = new User().setUserId(request.getParameter("userId"))
												.setUserPassword(request.getParameter("userPassword"));
		
		HashMap<String,Object> resultMap = userDao.userLogin(user);
		
		if(resultMap != null){
			
			session.setAttribute("userInfo", (User)resultMap.get("userInfo"));
			//게시글 리스트는 항상보여줘야 하므로 모든 리퀘스트에 처리 해주어야한다.
			ArrayList<Board> boardList = (ArrayList<Board>)boardDao.getBoardList();
			
			request.setAttribute("boardList", boardList);
			
			return "foward:view/main.jsp";
			
		}else{
			
			return "sendRedirect:view/error.jsp";
		}
		
		
	}

}
