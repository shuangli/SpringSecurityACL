<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<html>
<head><title><spring:message code="customer.bank-account-page"/></title></head>
<body>

<p><spring:message code="customer.view.bank-account"/></p>
<table>
    <tr>
        <td><spring:message code="label.bank-account-number"/></td>
        <td>${bankAccount.bankAccountNo}</td>
    </tr>
    <tr>
        <td><spring:message code="label.creation-date"/></td>
        <td>${bankAccount.creationDate}</td>
    </tr>
    <tr>
        <td><spring:message code="label.balance"/></td>
        <td>${bankAccount.balance}</td>
    </tr>
    <tr>
        <td>
            <a href="/app/customer/welcome.task"><spring:message code="customer.home"/></a>
        </td>
    </tr>
</table>
</body>
</html>