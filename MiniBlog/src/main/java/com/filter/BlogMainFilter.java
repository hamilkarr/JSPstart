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

/**
 * 사이트 전역 필터
 */
public class BlogMainFilter implements Filter {
	private FilterConfig filterConfig;
	private String[] staticDirs = { "resources" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

		// 데이터베이스 설정 초기화
		DB.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 사이트 root URL
		String siteURL = req.getServletContext().getContextPath();
		req.setAttribute("siteURL", siteURL);

		// 헤더 출력
		outlineHeader(req, resp);
		chain.doFilter(req, resp);
		// 푸터 출력
		outlineFooter(req, resp);
	}

	// 공통 헤더 처리
	public void outlineHeader(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		if (isOutlineRequired(req)) {
			resp.setContentType("text/html; charset=utf-8");
			RequestDispatcher header = req.getRequestDispatcher("/outline/header.jsp");
			header.include(req, resp);
		}
	}

	// 공통 푸터 처리
	public void outlineFooter(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		if (isOutlineRequired(req)) {
			resp.setContentType("text/html; charset=utf-8");
			RequestDispatcher footer = req.getRequestDispatcher("/outline/footer.jsp");
			footer.include(req, resp);
		}
	}

	/**
	 * 헤더 푸터가 필요한지를 체크
	 * 
	 * @param
	 * @return
	 */
	public boolean isOutlineRequired(ServletRequest req) {
		if (req instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) req;
			String method = request.getMethod().toUpperCase();
			if (!method.equals("GET"))
				return false;

			String URI = request.getRequestURI();
			for (String dir : staticDirs) {
				if (URI.indexOf("/" + dir) != -1) {
					return false;
				}
			}
		}
		return true;
	}

}
