package com.board.control;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;

public class BoardViewControl implements PageControl {

	UserDao userDao;
	BoardDao boardDao;
	
	
	public BoardViewControl setDao(UserDao userDao,BoardDao boardDao){
		
		this.userDao = userDao;
		this.boardDao = boardDao;
		
		return this;
	}
	
	@Override
	public String excute(HashMap<String, Object> model) {
		System.out.println("BoardViewControl...");
		
		HttpServletRequest request = (HttpServletRequest)model.get("request");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println(boardNo+"번 게시글 보기");
		
		Board board = boardDao.getBoard(boardNo);
		
		if(board != null){
			
			request.setAttribute("board", board);
			return "foward:view/boardViewForm.jsp";
			
		}else{
			
			return "sendRedirect:view/error.jsp";
		}
		
		
	}

}
