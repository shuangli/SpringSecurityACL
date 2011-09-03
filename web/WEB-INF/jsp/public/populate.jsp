<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<html>
<head>
    <title><spring:message code="populated-database.title"/></title>
</head>
<body>
<c:if test="${populated==true}">
    <spring:message code="populated-database"/>
</c:if>
<c:if test="${populated==false}">
    <spring:message code="not-populated-database"/>
</c:if>
<br/><br/>
<table border="1">
    <tr>
        <td><spring:message code="label.username"/></td>
        <td><spring:message code="label.password"/></td>
        <td><spring:message code="label.role"/> </td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>
                <c:forEach var="role" items="${user.authorities}">
                    <spring:message code="label.role-${role.authority}"/>  
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<br/><br/>
<a href="/app/index.task"><spring:message code="home-page"/></a>
</body>
</html>

