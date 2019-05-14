package com.cafe24.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	private String enCoding;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* request 처리 */
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request,response);
		
		
		/* response 처리 */
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		enCoding =filterConfig.getInitParameter("encoding");
		if ( enCoding == null ) {
			enCoding = "utf-8";
		}
	}

}
