package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;
import com.board.vo.User;
public class SignUpControl implements PageControl, DataBinding{

	UserDao userDao;
	BoardDao boardDao;
	
	
	public SignUpControl setUserDao(UserDao userDao){
		
		this.userDao = userDao;
		return this;
	}
	public SignUpControl setBoardDao(BoardDao boardDao){
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
		System.out.println("SignUpControl...");
		
		/*HttpServletRequest request = (HttpServletRequest)model.get("request");
		String userId			   = (String)request.getParameter("userId");
		String userPassword		   = (String)request.getParameter("userPassword");
		String userName		       = (String)request.getParameter("userName");*/
		
		User user = userDao.isSignUp((User)model.get("user"));
		
		
		//���̵� ���� ������ �����Ұ��
		if(user.getUserNo() != 0){
			
			return "sendRedirect:view/error.jsp";
			
		}else{
			
			userDao.userSignUp(user);
			//�Խñ� ����Ʈ�� �׻󺸿���� �ϹǷ� ��� ������Ʈ�� ó�� ���־���Ѵ�.
			model.put("boardList", (ArrayList<Board>)boardDao.getBoardList());
			
			
			return "foward:view/main.jsp";
			
		}
		
	}


	
	
	
}
