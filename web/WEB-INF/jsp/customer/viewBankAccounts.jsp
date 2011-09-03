<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<p><spring:message code="customer.view.bank-accounts"/></p>

<c:forEach var="accountIter" items="${bankAccounts}">
    ${accountIter.value.bankAccountNo} <a href="/app/customer/viewBankAccount.task?id=${accountIter.key}"><spring:message code="label.view"/></a>
    <a href="/app/customer/modifyBankAccount.task?id=${accountIter.key}"><spring:message code="label.modify"/> </a>
    <br/>
</c:forEach>
<a href="/app/customer/welcome.task"><spring:message code="customer.home"/></a>