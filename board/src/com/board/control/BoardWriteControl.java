package com.board.control;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.board.dao.BoardDao;
import com.board.dao.UserDao;
import com.board.vo.Board;

public class BoardWriteControl implements PageControl , DataBinding{

	
	UserDao userDao;
	BoardDao boardDao;
	
	
	public BoardWriteControl setDao(UserDao userDao,BoardDao boardDao){
		
		this.userDao = userDao;
		this.boardDao = boardDao;
		
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[]{"board",com.board.vo.Board.class};
	}
	@Override
	public String excute(HashMap<String, Object> model) {
		
		boardDao.writeBoard((Board)model.get("board"));
		model.put("boardList", (ArrayList<Board>)boardDao.getBoardList());
		
		
		return "foward:view/main.jsp";
	}

}
