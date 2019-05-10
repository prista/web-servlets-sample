<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.drm.sample.web.db.dao.impl.ModelDaoImpl" %>
<%@ page import="com.drm.sample.web.db.model.Model" %>

<jsp:include page="menu.jsp" />
<h1>Models page</h1>

<% 
List<Model> modelsList = new ModelDaoImpl().getAll();
pageContext.setAttribute("modelsList", modelsList);
%>

<table border="1px solid black">
	<c:forEach items="${modelsList}" var="model">
		<tr>
			<td><c:out value="${model.id}" /></td>
			<td><c:out value="${model.name}" /></td>
		</tr>
	</c:forEach>
</table>