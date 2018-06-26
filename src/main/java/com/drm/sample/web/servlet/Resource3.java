package com.drm.sample.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Resource3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cookie myCookie = new Cookie("testCookie", "cookieValue");
		myCookie.setMaxAge(24 * 60 * 60);
		resp.addCookie(myCookie);

		req.getSession().setAttribute("authorized", Boolean.TRUE);

	}

}
