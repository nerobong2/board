package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;

public class LogoutControl implements PageControl{

	UserDao userDao;
	BoardDao boardDao;
	
	public LogoutControl setUserDao(UserDao userDao){
		
		this.userDao = userDao;
		return this;
	}
	public LogoutControl setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
		return this;
	}
	@Override
	public String excute(HashMap<String, Object> model) {
		
		System.out.println("LogoutControl...");
		
		//���� �ʱ�ȭ
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		
		
		//�Խñ� ����Ʈ�� �׻󺸿���� �ϹǷ� ��� ������Ʈ�� ó�� ���־���Ѵ�.
		model.put("boardList", (ArrayList<Board>)boardDao.getBoardList());
		
		
		
		return "foward:view/main.jsp";
		
	}

}
