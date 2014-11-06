package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.board.db.DBConnectionPool;
import com.board.vo.User;

public class UserDao {
	
	DBConnectionPool connPool;
	public UserDao(DBConnectionPool connPool) {
		System.out.println("UserDao()...");
		this.connPool = connPool;
		
	}

	public Boolean loginValidationCheck(User user) {
		
		Boolean isUser = false;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
	
		
		
		String query = " select * from user where userId = ? and userPassword = ?";
		
		try{
			conn = connPool.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			
			Boolean result = pstmt.execute();
			
			isUser  =  result;
			
		}catch(Exception e){
			
		}finally{
			
			try{pstmt.close();connPool.returnConnection(conn);}catch(Exception e){}
		}
		
		return isUser;
	}

	
	public User getUserInfo(User user){
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet	rs			= null;
		User		userInfo 	= null;
		
		String query = " select * from user where userId = ? and userPassword = ?";
		
		try{
			conn = connPool.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				userInfo.setUserNo(rs.getInt("userNo"))
								.setUserId(rs.getString("userId"))
								.setUserName(rs.getString("userName"))
								.setUserPassword(rs.getString("userPassword"))
								.setRegDate(rs.getDate("regDate"));
				
			}
			
			System.out.println(userInfo.getUserName()+"============================");
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			try{pstmt.close();connPool.returnConnection(conn);}catch(Exception e){}
		}
		
		return userInfo;
	};
}
