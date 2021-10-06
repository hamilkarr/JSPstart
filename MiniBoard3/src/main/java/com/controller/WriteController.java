package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 게시글 작성 컨트롤러(서블릿)
 * 
 * GET 접근 - 게시글 작성양식(doget)
 * POST 접근 - 게시글 등록 처리 (doPost)
 */

public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// 게시글 양식 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/board/form.jsp");
		rd.include(request, response);
		
	}

	// 게시글 저장 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
