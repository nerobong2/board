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
%>

	<table border="1">
	<%
		
		if(boardList.size() > 0){
			for(Board board : boardList){
				%>
				<tr>
					<td><%=board.getBoardNo() %></td>
					<td><%=board.getBoardSubject() %></td>
					<td><%=board.getWriterName() %></td>
					<td><%=board.getRegDate() %></td>
				</tr>
				<%
			}
		}else{
			
			
		}
		
	%>
	</table>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>