<%-- 
    Document   : customertag
    Created on : 04.okt.2016, 10:05:26
    Author     : mikael
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
            </tr>
            
            <sql:query dataSource="jdbc/sample" var="result">
                select customer_id,name,phone from customer
            </sql:query>
            <c:forEach var="row" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.CUSTOMER_ID}"/></td>
                    <td><c:out value="${row.NAME}"/></td>
                    <td><c:out value="${row.PHONE}"/></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
