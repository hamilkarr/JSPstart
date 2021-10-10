package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDAO;
import com.model.dto.Blog;

public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html charset=utf-8");
		BlogDAO dao = new BlogDAO();
		ArrayList<Blog> list = dao.getList();
		req.setAttribute("list", list);		
		
		RequestDispatcher rd = req.getRequestDispatcher("/blog/list.jsp");
		rd.include(req, resp);
	}

}
