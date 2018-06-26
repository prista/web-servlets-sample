package com.drm.sample.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Resource4 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String cookieName = "testCookie";
		String cookieValue = null;

		Cookie[] myCookies = req.getCookies();

		if (myCookies != null) {
			for (int i = 0; i < myCookies.length; i++) {
				Cookie cookie = myCookies[i];
				if (cookieName.equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}

		PrintWriter writer = resp.getWriter();
		writer.println("I got the cookie:" + cookieValue);

		Boolean isAuthorized = (Boolean) req.getSession().getAttribute("authorized");
		if (isAuthorized != null && isAuthorized) {
			writer.println("you are logged in");
		} else {
			writer.println("you are anonymous");
		}
	}
}
