<%-- 
    Document   : index
    Created on : 2/04/2025, 11:41:00 p. m.
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
        <%
        response.sendRedirect("EmpleadoControlador?accion=listar");
        
        
        %>
    </body>
</html>
