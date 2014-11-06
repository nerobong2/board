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
	
	
	public MainControl setDao(UserDao userDao,BoardDao boardDao){
		
		this.userDao = userDao;
		this.boardDao = boardDao;
		
		return this;
	}


	@Override
	public String excute(HashMap<String,Object> model) {
		
		System.out.println("mainControl...");
		
		ArrayList<Board> boardList = (ArrayList<Board>)boardDao.getBoardList();
		
		HttpServletRequest request = (HttpServletRequest)model.get("request");
		request.setAttribute("boardList", boardList);
		
		return "foward:view/main.jsp";
	}
}
