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
		
		
		//result = true : ���� ����, result = false : ���� �Ұ��� 
		if(result){
			
			
			//�Խñ� ����Ʈ�� �׻󺸿���� �ϹǷ� ��� ������Ʈ�� ó�� ���־���Ѵ�.
			ArrayList<Board> boardList = (ArrayList<Board>)boardDao.getBoardList();
			
			request.setAttribute("boardList", boardList);
			userDao.userSignUp(user);
			return "foward:view/main.jsp";
			
		}else{
			
			return "sendRedirect:view/error.jsp";
		}
		
	}
	
	
}
