package com.drm.sample.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter2 implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		writer.println("<h1>");
		writer.println("Filter2 was here");
		writer.println("</h1>");

		System.out.println("before servlert execution Filter2");
		
		chain.doFilter(request, response);
		
	}

	public void destroy() {
	}

}
