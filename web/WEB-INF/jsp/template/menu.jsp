<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

[menu]
<sec:authorize ifAnyGranted="ROLE_CUSTOMER" ifNotGranted="ROLE_CLERK">
    <br/>
    <a href="/app/customer/accountOperation-flow"><spring:message code="customer.input.bank-account-operation"/></a>
    <br/>
    <a href="/app/customer/viewBankAccounts.task"><spring:message code="customer.view-bank-accounts"/></a>
    <br/>
    <a href="/app/customer/viewBankAccountOperations.task"><spring:message
            code="customer.view-bank-account-operations"/></a>
    <br/>
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_CLERK">
    <br/>
    <a href="<c:url value="/app/clerk/clerk-flow"/>"><spring:message code="clerk.register-clerk"/> </a>
    <br/>
    <a href="<c:url value="/app/clerk/account-flow"/>"><spring:message code="clerk.add-customer-account"/></a>
    <br/>
    <a href="<c:url value="/app/customer/viewBankAccounts.task"/>"><spring:message
            code="customer.view-bank-accounts"/></a>
    <br/>
</sec:authorize>

<sec:authorize ifNotGranted="ROLE_CLERK,ROLE_CUSTOMER">
    <br/><a href="/app/clerk/welcome.task"><spring:message code="clerk.home"/></a>

    <br/><a href="/app/customer/welcome.task"><spring:message code="customer.home"/></a>

    <br/><a href="/app/public/welcome.task"><spring:message code="public.home"/></a>

    <br/><a href="/app/public/registerCustomer-flow"><spring:message code="public.register-customer"/></a>

    <br/><a href="/app/public/populate.task"><spring:message code="public.populate-database"/></a>
</sec:authorize>