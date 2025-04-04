<%-- 
    Document   : Mensaje
    Created on : 3/04/2025, 11:29:32 a. m.
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<script>
    document.addEventListener("DOMContentLoaded", function() {
        <c:if test="${sessionScope.error != null}">
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: '${sessionScope.error}',
                confirmButtonColor: '#d33'
            });
            <c:remove var="error" scope="session"/>
        </c:if>

        <c:if test="${sessionScope.success != null}">
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: '${sessionScope.success}',
                confirmButtonColor: '#3085d6'
            });
            <c:remove var="success" scope="session"/>
        </c:if>
    });
</script>