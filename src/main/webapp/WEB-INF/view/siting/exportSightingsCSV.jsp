<%@ page contentType="text/csv; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<table border="1">
<c:forEach items="${sitings}" var="siting">
<tr>
	<td>${siting.species.name}</td>
    <td>${siting.longitude}</td>
    <td>${siting.latitude}</td>
    <td>${siting.timestamp}</td>
    <td>${siting.submittedBy.firstName} ${siting.submittedBy.lastName}</td>
</tr>
</c:forEach>
</table>
