<%@page import="com.drm.sample.web.db.dao.impl.UserProfileDaoImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.drm.sample.web.db.model.UserProfile" %>

<jsp:include page="menu.jsp" />
<h1>User Profiles page</h1>

<% 
List<UserProfile> userProfilesList = UserProfileDaoImpl.INSTANCE.getAll();
pageContext.setAttribute("userProfilesList", userProfilesList);
%>

<table border="1px solid black">
	<c:forEach items="${userProfilesList}" var="user_profile">
		<tr>
			<td><c:out value="${user_profile.id}" /></td>
			<td><c:out value="${user_profile.name}" /></td>
		</tr>
	</c:forEach>
</table>