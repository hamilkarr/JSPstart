package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.model.dao.*;
import com.model.dto.*;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = -7420891502735916594L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html; charset=utf-8");
		
		Member member = new Member();
		ArrayList<MemberBean> list = member.getMembers();
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request, response);
	}
}
