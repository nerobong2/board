package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.sql.DataSource;

import com.board.db.DBConnectionPool;
import com.board.vo.User;

public class UserDao {
	
	
	DataSource ds;
	
	
	//DBConnectionPool connPool;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	//회원 가입 여부 확인
	
	public User isSignUp(User user) {
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		ResultSet 			rs     = null;
		
		String 				sql = " select * from user"
								+ " where userId = ?";
			try{
				
				conn  = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					user.setUserNo(rs.getInt("userNo"))
						.setUserId(rs.getString("userId"))
						.setUserName(rs.getString("userName"))
						.setUserPassword(rs.getString("userPassword"))
						.setRegDate(rs.getDate("regDate"));
					
				}
				
			
			}catch(Exception e){
				
				e.printStackTrace();
			}finally{
				
				try{rs.close();pstmt.close();conn.close();}catch(Exception e){}
			}
		
		return user;
	}
	//회원 가입 기능
	public void userSignUp(User user) {
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		
		String				sql	= " insert into user(userId,userName,userPassword,regDate)"
								 +" values (?,?,?,now())";
		try{
			
			conn  = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserPassword());
			
			pstmt.execute();
			System.out.println("회원가입 성공!!");
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			try{pstmt.close();conn.close();}catch(Exception e){}
		}
		
			
		
	}
	//로그인 기능 
	public User userLogin(User user) {
		
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		ResultSet 			rs     = null;
		
		
		
		String 				sql = " select * from user"
								+ " where userId = ? and userPassword = ? ";
			try{
				
				conn  = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getUserPassword());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					user.setUserNo(rs.getInt("userNo"))
							.setUserId(rs.getString("userId"))
							.setUserName(rs.getString("userName"))
							.setUserPassword(rs.getString("userPassword"))
							.setRegDate(rs.getDate("regDate"));
					
					
				}
				
			
			}catch(Exception e){
				
				e.printStackTrace();
			}finally{
				
				try{rs.close();pstmt.close();conn.close();}catch(Exception e){}
			}
		
			
		return user;
	}
	
}
