<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<strong><spring:message code="clerk.input.bank-account"/></strong>
<form:form modelAttribute="account">
    <table>
        <tr>
            <td><spring:message code="label.bank-account-number"/></td>
            <td><form:input path="bankAccountNo"/></td>
        </tr>
        
        <tr>
            <td><spring:message code="label.customers"/></td>
            <td>
                <form:select path="customer.id" cssErrorClass="error-field">
                    <form:option value="0">
                        <spring:message code="dropdown.please-select-owner"/>
                    </form:option>

                    <c:forEach var="customerIter" items="${customers}">
                        <form:option value="${customerIter.id}">${customerIter.firstName} ${customerIter.lastName}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="_eventId_submit" value="Submit"/>
            </td>
        </tr>       
    </table>
</form:form>
