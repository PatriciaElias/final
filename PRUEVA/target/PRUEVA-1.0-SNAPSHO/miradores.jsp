<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("nivel") == null) {
        response.sendRedirect("login.jsp");
    } else {
        String nivel = sesion.getAttribute("nivel").toString();
        if (!nivel.equals("1")) {
            response.sendRedirect("login.jsp");
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Miradores SV Admin</title>

        <!--BOSTRAP-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

        <!--AWESOME-->
        <script src="https://kit.fontawesome.com/893526ba97.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <!--INCLUYE EL JSP DEL HEADER ADMIN-->
        <jsp:include page="WEB-INF/paginas/comunes/headerAdmin.jsp"/>
        <br><br><br><br>
        <div>
            <center>
                <section id="actions" class="py-4 mb-4 bg-ligth">
                    <div class="col md-3" >
                        <a href="#" class="btn btn-primary btn-block"
                           data-bs-toggle="modal" data-bs-target="#agregarMiradorModal">
                            <i class="fa fa-plus"></i>Agregar Mirador
                        </a>
                    </div>
                </section>
            </center>
        </div>

        <!--INCLUYE EL JSP DEL LISTADO DE PLAYAS-->
        <jsp:include page="WEB-INF/paginas/miradores/listadoMiradores.jsp"/>
        <!--INCLUYE EL JSP DEL FOOTER ADMIN-->
        <jsp:include page="WEB-INF/paginas/comunes/footerAdmin.jsp"/>
    </body>
</html>

