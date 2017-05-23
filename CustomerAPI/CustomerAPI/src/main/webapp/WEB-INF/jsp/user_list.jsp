<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1><spring:message code="Customer.list"/></h1>
<ul>
    <%--@elvariable id="Customers" type="java.util.List"--%>
    <c:forEach items="${Customers}" var="Customer">
        <li>
            <c:out value="${Customer.getId()}"/>
        </li>
    </c:forEach>
</ul>

<a href="<spring:url value="/Customer_create.html" />"><spring:message code="Customer.create"/></a>
</body>
</html>