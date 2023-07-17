<%-- 
    Document   : ruinasListaU
    Created on : 11 nov 2022, 20:58:43
    Author     : Edwin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ruinas SV</title>

        <!--BOSTRAP-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

        <!--AWESOME-->
        <script src="https://kit.fontawesome.com/893526ba97.js" crossorigin="anonymous"></script>
    </head>
    <body style="background: rgb(8, 63, 129);
          background: linear-gradient(90deg,
          rgba(8, 63, 129, 1) 0%,
          rgba(50, 190, 251, 1) 50%,
          rgba(8, 63, 129, 1) 100%);">

        <!--INCLUYE EL JSP DEL HEADER DE USUARIO COMUN-->
        <jsp:include page="WEB-INF/paginas/comunes/headerUser.jsp"/>
        <br><br><br><br>
        <div class="col-12">
            <img src="imagenes/cabecera_ruina.jpg"class=" mx-auto img-fluid">
        </div><br>

        <!--INCLUYE EL JSP DEL LISTADO DE PLAYAS-->
        <jsp:include page="WEB-INF/paginas/ruinas/listadoRuinasU.jsp"/>

        <!--INCLUYE EL JSP DEL FOOTER DE USUARIO COMUN-->
        <jsp:include page="WEB-INF/paginas/comunes/footerUser.jsp"/>
    </body>
</html>


