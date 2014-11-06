package com.board.control;

import java.util.HashMap;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;

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
		return "foward:view/main.jsp";
	}
	
	
}
