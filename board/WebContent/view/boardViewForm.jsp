<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.vo.Board" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Board board = (Board)request.getAttribute("board");

%>


<jsp:include page="header.jsp"></jsp:include>
	<%
	if(board != null){
	%>
		<div>
		<input id="boardNo" value=<%=board.getBoardNo()%> readonly="readonly"><br>
		
		<input id="boardSubject" value=<%=board.getBoardSubject()%> readonly="readonly"><br>
		<input id="boardContent" value=<%=board.getBoardContent()%> readonly="readonly"><br>
		<input id="boardWriterName" value=<%=board.getWriterName()%> readonly="readonly"><br>
		<input id="boardRegDate"	value=<%=board.getRegDate()%> readonly="readonly">
		
	</div>
	<%
	}
	%>
	
	<a href="/board/main.do">목록으로</a>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>