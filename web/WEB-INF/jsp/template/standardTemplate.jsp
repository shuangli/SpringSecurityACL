<%-- Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/includes.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!-- We profusely apologize to DIV/CSS purists, we used a table-based layout here (*gasp*).
     In our real applications, we are DIV/CSS purits ourselves.
-->

<html>

<head>
    <link rel="stylesheet" href="<c:url value="/style/springstarter.css"/>" type="text/css">
</head>

<body>
<div id="main">
    <table align="center">
        <tr>
            <td colspan="2">
                <div id="header">
                    <tiles:insertAttribute name="header"/>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div id="navigation">
                    <tiles:insertAttribute name='navigation'/>
                </div>
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="menu">
                    <tiles:insertAttribute name='menu'/>
                </div>
            </td>
            <td valign="top">
                <div id="content">
                    <tiles:insertAttribute name='body'/>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="footer">
                    <tiles:insertAttribute name="footer"/>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>

</html>
