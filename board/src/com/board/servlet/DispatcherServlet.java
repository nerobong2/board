package com.board.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.control.PageControl;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DispatcherServlet...");
		
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		ServletContext ctx = request.getServletContext();
		HttpSession session  = request.getSession();
		
		
		model.put("session", session);
		model.put("request", request);
		model.put("response", response);
		
		String requestUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		String servletPath		= request.getServletPath();
		System.out.println(requestUrl);
		System.out.println(contextPath);
		System.out.println(servletPath);
		
		String controlInfo = servletPath.split("/")[1];
		
		PageControl pageUrl = (PageControl) ctx.getAttribute(controlInfo);
		
		String processResult = pageUrl.excute(model);
		
		
		
		if(processResult.startsWith("foward:")){
			
			System.out.println(processResult.split(":")[1] + " -- > fowarding");
			RequestDispatcher rd = request.getRequestDispatcher(processResult.split(":")[1]);
			
			rd.forward(request, response);
			
			
		}else{
			//다른페이지로 전환시
			System.out.println(contextPath+"/"+processResult.split(":")[1] + " -- > sendRedirect");
			response.sendRedirect(contextPath+"/"+processResult.split(":")[1]);
			
		}
		
	}

	
}
