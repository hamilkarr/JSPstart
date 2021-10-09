package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.core.DB;


public class CommonFilter implements Filter {

	private String[] staticDirs = {"public"};
	
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// web.xmi에 있는 init-param의 설정값을 불러옴
		DB.init(filterconfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 기준 URI
		String siteURL = request.getServletContext().getContextPath();
		request.setAttribute("siteURL", siteURL);
		
		
		// 헤더 출력
		printHeader(request, response);
		
		chain.doFilter(request, response);
		
		// 푸터 출력
		printFooter(request, response);
	}

	// 헤더 HTML 출력
	public void printHeader(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if(isPrintOK(request)) { // 출력 가능 조건일때만 출력
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/outline/header.jsp");
		// include, forward
		rd.include(request, response);
		}
	}

	// 푸터 HTML 출력
	public void printFooter(ServletRequest request, ServletResponse response) throws ServletException,IOException {
		if (isPrintOK(request)) {
		RequestDispatcher rd = request.getRequestDispatcher("/outline/footer.jsp");
		rd.include(request, response);
		}
	}
	
	// 헤더 푸터를 출력해도 되는지 체크하는 메서드
	public boolean isPrintOK(ServletRequest request) {
		/**
		 * 1. 요청 method가 GET이 아닌 경우 출력 제외(return false)
		 * 		HttpServletRequest - getMethod() 
		 * 2. 정적 경로인 경우 헤더 푸터 출력 제외(return false)
		 * 		URI에 정적 경로가 포함되어 있으면 false
		 * 		HttpServletRequest - getRequestURI();		 * 
		 */
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			if (!req.getMethod().toUpperCase().equals("GET")) {
				return false;
			}
			
			String URI = req.getRequestURI();
			for (String dir : staticDirs) {
				if(URI.indexOf("/" + dir) != -1) {
					return false;
				}
			}
		}
	
		return true;
	}
}
