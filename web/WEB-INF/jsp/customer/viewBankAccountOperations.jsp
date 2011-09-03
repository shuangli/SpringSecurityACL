<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<p><spring:message code="customer.view.bank-account-operations"/>:</p>
<br/>
<table>
    <tr>
        <td><spring:message code="label.bank-account"/> </td>
        <td><spring:message code="label.operation-type"/></td>
        <td><spring:message code="label.issue-time"/></td>
        <td colspan="2"></td>        
    </tr>
    <c:forEach var="accountOpIter" items="${bankAccountOperations}">
        <tr>
            <td>${accountOpIter.value.bankAccount.bankAccountNo}</td>
            <td><spring:message code="label.operation-type-${accountOpIter.value.type}"/></td>
            <td>${accountOpIter.value.issueTime}</td>
            <td><a href="/app/customer/viewBankAccountOperation.task?id=${accountOpIter.key}"><spring:message
                    code="label.view"/></a></td>
            <td><a href="/app/customer/modifyBankAccountOperation.task?id=${accountOpIter.key}"><spring:message
                    code="label.modify"/> </a></td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <a href="/app/customer/welcome.task"><spring:message code="customer.home"/></a>
        </td>
    </tr>
</table>