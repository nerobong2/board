package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;

public class MainControl implements PageControl{

	UserDao userDao;
	BoardDao boardDao;
	
	
	public MainControl setUserDao(UserDao userDao){
		
		this.userDao = userDao;
		return this;
	}
	public MainControl setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
		return this;
	}


	@Override
	public String excute(HashMap<String,Object> model) {
		
		System.out.println("mainControl...");
		
		//�Խñ� ����Ʈ�� �׻󺸿���� �ϹǷ� ��� ������Ʈ�� ó�� ���־���Ѵ�.
		model.put("boardList", (ArrayList<Board>)boardDao.getBoardList());
		
		return "foward:view/main.jsp";
	}
}
