package com.drm.sample.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Resource1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String p1 = req.getParameter("param1");
		String p2 = req.getParameter("param2");
		String p3 = req.getParameter("isactive");
		String p4 = req.getParameter("date");

		PrintWriter writer = resp.getWriter();
		writer.println(
				String.format("returned cars found by params  p1=%s. p2=%s. p3=%s. p4=%s", p1, p2, p3, p4));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String p1 = req.getParameter("param1");
		String p2 = req.getParameter("param2");
		String p3 = req.getParameter("isactive");
		String p4 = req.getParameter("date");

		PrintWriter writer = resp.getWriter();
		writer.println(
				String.format("returned cars found by params  p1=%s. p2=%s. p3=%s. p4=%s", p1, p2, p3, p4));
	}

}
