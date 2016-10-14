<%-- 
    Document   : customer
    Created on : 04.okt.2016, 09:50:23
    Author     : mikael
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello from JSP</h1>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
            </tr>
            
            <% 
                DataSource ds = InitialContext.doLookup("jdbc/sample");
                Connection c = null;
                try {
                    c = ds.getConnection();
                    Statement st = c.createStatement();
                    ResultSet rs = st.executeQuery(
                            "select customer_id,name,phone from customer");
                    while(rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("CUSTOMER_ID") %></td>
                <td><%= rs.getString("NAME") %></td>
                <td><%= rs.getString("PHONE") %></td>
            </tr>
            <%            
                    }
                } catch(SQLException e) {
                    
                } finally {
                    if(c != null) {
                        try {c.close();} catch(SQLException e) {}
                    }
                }
            %>
            
        </table>
        
    </body>
</html>
