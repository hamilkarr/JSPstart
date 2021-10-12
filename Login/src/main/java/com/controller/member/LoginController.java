package com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exception.AlertException;
import com.models.dao.MemberDao;

/**
 * 로그인 GET - 로그인 양식, POST - 로그인 처리
 * 
 */

public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// 로그인 양식
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/member/login.jsp");
		rd.include(request, response);
	}

	// 로그인 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try { // 로그인 성공
			MemberDao dao = new MemberDao();
			dao.login(request);
			out.print("<script>parent.location.href='../';</script>"); // 
			
			
		} catch (AlertException e) { // 로그인 실패
			out.print("<script>alert('"+ e.getMessage() +"');</script>");
		}
	}

}
