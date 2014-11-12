package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.board.db.DBConnectionPool;
import com.board.vo.User;

public class UserDao {
	
	DBConnectionPool connPool;
	public UserDao(DBConnectionPool connPool) {
		System.out.println("UserDao()...");
		this.connPool = connPool;
		
	}
	//회원 가입 여부 확인
	
	public Boolean isSignUp(User user) {
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		ResultSet 			rs     = null;
		
		String 				sql = " select userNo from user"
								+ " where userId = ?";
			try{
				
				conn  = connPool.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					return false;
				}
				
			
			}catch(Exception e){
				
				e.printStackTrace();
			}finally{
				
				try{rs.close();pstmt.close();connPool.returnConnection(conn);}catch(Exception e){}
			}
		
		return true;
	}
	//회원 가입 기능
	public void userSignUp(User user) {
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		
		String				sql	= " insert into user(userId,userName,userPassword,regDate)"
								 +" values (?,?,?,now())";
		try{
			
			conn  = connPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserPassword());
			
			pstmt.execute();
			System.out.println("회원가입 성공!!");
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			try{
				
				pstmt.close();connPool.returnConnection(conn);
				
			}catch(Exception e){
				
			}
		}
		
			
		
	}
	//로그인 기능 
	public HashMap<String,Object> userLogin(User user) {
		
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		ResultSet 			rs     = null;
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		int i = 1;
		
		String 				sql = " select * from user"
								+ " where userId = ? and userPassword = ? ";
			try{
				
				conn  = connPool.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getUserPassword());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					User userInfo = new User().setUserNo(rs.getInt("userNo"))
							.setUserId(rs.getString("userId"))
							.setUserName(rs.getString("userName"))
							.setUserPassword(rs.getString("userPassword"))
							.setRegDate(rs.getDate("regDate"));
					
					resultMap.put("userInfo", userInfo);
					resultMap.put("isLogin", true);
					System.out.println(i++);
					break;
				}
				
			
			}catch(Exception e){
				
				e.printStackTrace();
			}finally{
				
				try{rs.close();pstmt.close();connPool.returnConnection(conn);}catch(Exception e){}
			}
		
			
		return resultMap;
	}
	
}
