<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<c:if test="${not empty progressMap}">

    <c:forEach var="pi" items="${progressMap}" varStatus="status">
        <c:if test="${status.index != 0}">
            >
        </c:if>
        <strong>
            <spring:message code="progress-bar-flow.${pi.key}"/>
            <c:set var="ignoreLastState" value="${(status.index == 0) ? 0:-1}"/>
            <c:forEach var="i" begin="1" end="${pi.value.stateTotal + ignoreLastState}"><c:choose><c:when test="${i == 1}"><img src="<c:url value="/images/flow/progress-bar-on-start.png"/>" alt=""/></c:when><c:when test="${i<=pi.value.stateNumber && i > 1 && i != pi.value.stateTotal + ignoreLastState}"><img src="<c:url value="/images/flow/progress-bar-on-middle.png"/>" alt=""/></c:when><c:when test="${i > pi.value.stateNumber && i < pi.value.stateTotal + ignoreLastState}"><img src="<c:url value="/images/flow/progress-bar-off-middle.png"/>" alt=""/></c:when><c:when test="${i > pi.value.stateNumber && i == pi.value.stateTotal + ignoreLastState}"><img src="<c:url value="/images/flow/progress-bar-off-end.png"/>" alt=""/></c:when><c:when test="${i <= pi.value.stateNumber && i == pi.value.stateTotal + ignoreLastState}"><img src="<c:url value="/images/flow/progress-bar-on-end.png"/>" alt=""/></c:when></c:choose></c:forEach>
        </strong>
    </c:forEach>
</c:if>
