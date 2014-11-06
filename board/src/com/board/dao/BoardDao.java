package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.board.db.DBConnectionPool;
import com.board.vo.Board;


public class BoardDao {

	DBConnectionPool connPool;
	
	public BoardDao(DBConnectionPool connPool) {
		System.out.println("BoardDao()...");
		this.connPool = connPool;
		
	}

	public ArrayList<Board> getBoardList() {
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null ;			
		
		String sql = " select t1.boardNo, t1.boardSubject, t1.regDate, t2.userName "
				+ " from  board t1, user t2"
				+ " where t1.writerNo = t2.userNo"
				+ " order by t1.boardNo asc";
		
		ArrayList<Board> boardList = new ArrayList<Board>();
		
		try{
			
			conn 	= connPool.getConnection();
			pstmt 	= conn.prepareStatement(sql);
			rs 		= pstmt.executeQuery();
			
			while(rs.next()){
				Board board = new Board().setBoardNo(rs.getInt("boardNo"))
										.setBoardSubject(rs.getString("boardSubject"))
										.setWriterName(rs.getString("userName"))
										.setRegDate(rs.getDate("regDate"));
						
				boardList.add(board);
			}
			
			
			
		}catch(Exception e){
			
			
			
		}finally{
			try{rs.close();pstmt.close();connPool.returnConnection(conn);}catch(Exception e){}
		}
		
		return boardList;
		
	}
}