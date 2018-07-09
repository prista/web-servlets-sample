<%@page import="com.drm.sample.web.db.dao.impl.ResourceDaoImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.drm.sample.web.db.model.Resource" %>

<jsp:include page="menu.jsp" />
<h1>Allowed Resources page</h1>

<% 
List<Resource> resourcesList = ResourceDaoImpl.INSTANCE.getAll();
pageContext.setAttribute("resourcesList", resourcesList);
%>

<table border="1px solid black">
	<c:forEach items="${resourcesList}" var="resource">
		<tr>
			<td><c:out value="${resource.id}" /></td>
			<td><c:out value="${resource.path}" /></td>
		</tr>
	</c:forEach>
</table>