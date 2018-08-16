package com.drm.sample.web.filter;

import com.drm.sample.web.User;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//https://developer.mozilla.org/ru/docs/Web/HTTP/%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F
public class AuthorizationFilter implements Filter {

	private final Map<String, User> USER_DB = new HashMap<String, User>();

	public void init(FilterConfig filterConfig) throws ServletException {
		User basic = new User();
		basic.setPassword("basicPassword");
		basic.getAllowedResources().add("/r1");

		User admin = new User();
		admin.setPassword("adminPassword");
		admin.getAllowedResources().add("/r1");
		admin.getAllowedResources().add("/r2");
		admin.getAllowedResources().add("/r3");
		admin.getAllowedResources().add("/r4");

		USER_DB.put("admin", admin);
		USER_DB.put("basic", basic);
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null) {
			send401(response, "Authorization header is missing");
			return;
		}

		String[] authHeaderValues = authHeader.split(" ");

		if (authHeaderValues.length != 2) {
			send401(response, "something wrong with header content");
			return;
		}

		String authScheme = authHeaderValues[0];
		String credsString = authHeaderValues[1];

		if (!authScheme.equalsIgnoreCase("Basic")) {
			send401(response, "unsupported authorization scheme");
			return;
		}

		String credentials = new String(Base64.decodeBase64(credsString), "UTF-8");
		String[] userAndPass = credentials.split(":");

		if (userAndPass.length != 2) {
			send401(response, "something wrong with decoded token");
			return;
		}

		String login = userAndPass[0];
		String password = userAndPass[1];

		User user = USER_DB.get(login);

		if (user == null) {
			send401(response, "unknown username");
			return;
		}

		if (!user.getPassword().equals(password)) {
			send401(response, "invalid password");
			return;
		}

		List<String> allowedResources = user.getAllowedResources();
		String currentPath = request.getRequestURI();

		if (!allowedResources.contains(currentPath)) {
			// attempt to get disallowed resource
			send403(response);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private void send401(HttpServletResponse response, String message)
			throws IOException {
		response.setHeader("WWW-Authenticate", "Basic realm=test");
		response.sendError(401, message);
	}

	private void send403(HttpServletResponse response) throws IOException {
		response.sendError(403);
	}

	public void destroy() {
	}

}
