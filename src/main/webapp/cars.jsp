<%@page import="com.drm.sample.web.db.dao.impl.CarDaoImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.drm.sample.web.db.model.Car" %>

<jsp:include page="menu.jsp" />
<h1>Cars page</h1>

<% 
List<Car> carsList = new CarDaoImpl().getAll();
pageContext.setAttribute("carsList", carsList);
%>

<table border="1px solid black">
	<c:forEach items="${carsList}" var="car">
		<tr>
			<td><c:out value="${car.id}" /></td>
			<td><c:out value="${car.modelId}" /></td>
			<td><c:out value="${car.engineType}" /></td>
			<td><c:out value="${car.year}" /></td>
		</tr>
	</c:forEach>
</table>