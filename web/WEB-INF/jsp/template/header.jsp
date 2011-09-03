<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

[header]
<table width="100%">
    <tr>
        <td><sec:authorize ifAnyGranted="ROLE_CUSTOMER,ROLE_CLERK">
            <spring:message code="label.logged-in-as"/>
            <sec:authentication property="principal.userInfo.firstName"/>
            <sec:authentication property="principal.userInfo.lastName"/>
        </sec:authorize>
        </td>
    </tr>
    <tr>        
        <td align="right">
            <sec:authorize ifAnyGranted="ROLE_CUSTOMER,ROLE_CLERK">
                <a href="../logout"><spring:message code="label.logout"/></a>
            </sec:authorize>
        </td>
    </tr>
</table>