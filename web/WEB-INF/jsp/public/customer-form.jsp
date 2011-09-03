<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>

<strong><spring:message code="public.input.add-customer"/></strong>

<form:form modelAttribute="customer">
    <table>
        <tr>
            <td><spring:message code="label.first-name"/></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><spring:message code="label.last-name"/></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><spring:message code="label.email"/></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><spring:message code="label.phone-number"/></td>
            <td><form:input path="phoneNo"/></td>
        </tr>
        <tr>
            <td>
                <hr/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="label.username"/></td>
            <td><form:input path="user.username"/></td>
        </tr>
        <tr>
            <td><spring:message code="label.password"/></td>
            <td><form:password path="user.password"/></td>
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
