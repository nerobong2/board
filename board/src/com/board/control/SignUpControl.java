package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;
import com.board.vo.User;
public class SignUpControl implements PageControl{

	UserDao userDao;
	BoardDao boardDao;
	
	
	public SignUpControl setDao(UserDao userDao,BoardDao boardDao){
		
		this.userDao = userDao;
		this.boardDao = boardDao;
		
		return this;
	}


	@Override
	public String excute(HashMap<String,Object> model) {
		// TODO Auto-generated method stub
		System.out.println("SignUpControl...");
		
		HttpServletRequest request = (HttpServletRequest)model.get("request");
		String userId			   = (String)request.getParameter("userId");
		String userPassword		   = (String)request.getParameter("userPassword");
		String userName		       = (String)request.getParameter("userName");
		
		User user = new User().setUserId(userId).setUserPassword(userPassword). setUserName(userName);
		
		
		Boolean result = userDao.isSignUp(user);
		
		
		//result = true : 가입 가능, result = false : 가입 불가능 
		if(result){
			
			
			//게시글 리스트는 항상보여줘야 하므로 모든 리퀘스트에 처리 해주어야한다.
			ArrayList<Board> boardList = (ArrayList<Board>)boardDao.getBoardList();
			
			request.setAttribute("boardList", boardList);
			userDao.userSignUp(user);
			return "foward:view/main.jsp";
			
		}else{
			
			return "sendRedirect:view/error.jsp";
		}
		
	}
	
	
}
