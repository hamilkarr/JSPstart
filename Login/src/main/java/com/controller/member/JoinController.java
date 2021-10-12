package com.controller.member;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.models.dao.MemberDao;
import com.exception.AlertException;

/**
 * GET - 가입 양식, POST - 가입 처리
 * 
 *
 */

public class JoinController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 회원 가입 양식
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/member/form.jsp");
		rd.include(request, response);
	}

	// 회원 가입 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			MemberDao dao = new MemberDao();
			boolean result = dao.join(request);
			if (!result) {// 회원 가입 실패
				throw new AlertException("회원 가입 실패!");
			}			
			out.print("<script>parent.location.href='login';</script>");
		} catch(AlertException e) {
			out.print("<script>alert('" + e.getMessage() + "');</script>");
			return;
		}
	}
}
