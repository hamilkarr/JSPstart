package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BlogMainFilter implements Filter {
	/**
	 * Default constructor.
	 */
	/**
	public BlogMainFilter() {
		// TODO Auto-generated constructor stub
	}
	*/

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 전처리
		outlineHeader(req, resp);
		System.out.println("전처리!");
		
		chain.doFilter(req, resp);
		
		outlineFooter(req, resp);
		System.out.println("후처리!");
		// 후처리
	}
	
	public void outlineHeader(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		RequestDispatcher header = req.getRequestDispatcher("/outline/header.jsp");
		header.include(req, resp);
	}
	
	public void outlineFooter(ServletRequest req, ServletResponse resp) throws ServletException,IOException {
		resp.setContentType("text/html; charset=utf-8");
		RequestDispatcher footer = req.getRequestDispatcher("/outline/footer.jsp");
		footer.include(req, resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init 실행");
	}

}
