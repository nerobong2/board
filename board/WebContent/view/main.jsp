<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.vo.User"%>
<%@ page import="com.board.vo.Board"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	
	function moveSignUpPage(){
		location.href="/board/view/signUpForm.jsp";
	}
	
</script>

<title>메인페이지</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<%
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");
	User userInfo			   = (User)session.getAttribute("userInfo");
%>

	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>일시</td>
		</tr>
		
	<%
		
		if(boardList.size() > 0){
			for(Board board : boardList){
				%>
				<tr>
					<td><%=board.getBoardNo() %></td>
					<td><a href="/board/view.do?boardNo=<%=board.getBoardNo() %>"><%=board.getBoardSubject() %></a></td>
					<td><%=board.getWriterName() %></td>
					<td><%=board.getRegDate() %></td>
				</tr>
				<%
			}
		}else{
			
			
		}
		
	%>
	</table>
	<%
	if(userInfo != null){
		%>
		
		<a href="/board/view/boardWriteForm.jsp">글쓰기</a><br>
		
		<%
	}
	%>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>