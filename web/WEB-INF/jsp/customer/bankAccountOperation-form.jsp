<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<strong><spring:message code="customer.input.bank-account-operation"/> </strong>
<form:form modelAttribute="bankAccountOperation">
    <table>
        <tr>
            <td><spring:message code="label.operation-type"/></td>
            <td>
                <c:forEach var="currentType" items="${availableOperationTypes}">
                    <form:radiobutton path="type" value="${currentType}"/>
                    <spring:message code="label.operation-type-${currentType}"/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td><spring:message code="label.amount"/></td>
            <td><form:input path="amount"/></td>
        </tr>

        <tr>
            <td><spring:message code="label.bank-account"/></td>
            <td>
                <form:select path="bankAccount.id" cssErrorClass="error-field">
                    <form:option value="0">
                        <spring:message code="dropdown.please-select-account"/>
                    </form:option>
                    <c:forEach var="accountIter" items="${bankAccounts}">
                        <form:option value="${accountIter.key}">${accountIter.value.bankAccountNo}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="_eventId_submit" value="<spring:message code="button.submit"/> "/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="_eventId_cancel" value="<spring:message code="button.cancel"/> "/>
            </td>
        </tr>
    </table>
</form:form>
