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
    <!-- INVOCACION DE ELEMENTOS REQUERIDOS -->
    <head>
        <title>Menu Administracion</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--IMPORTAR ESTILOS CSS DE FONT-AWESOME-->
        <script src="https://kit.fontawesome.com/893526ba97.js" crossorigin="anonymous"></script>

        <!-- IMPORTAR BOOSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

        <!--IMPORTAR ESTILOS CSS PROPIOS DE LA PAGINA -->
        <link href="Recursos/carousel.css" rel="stylesheet">
    </head>

    <body>
        <!--BARRA DE NAVEGACION-->
        <header></header>
        <main>
            <center class="fixed-top titfondo" style="background: rgb(8, 63, 129);
                    background: linear-gradient(90deg,
                    rgba(8, 63, 129, 1) 0%,
                    rgba(50, 190, 251, 1) 50%,
                    rgba(8, 63, 129, 1) 100%);">
                <br>

                <h1 class="fw-normal lh-1">Administracion de Sitios de<span class="text-info"> Atractivos SV</span></h1>


                Bienvenid@ <%= sesion.getAttribute("nombre")%>

                <!--<i class="fa-solid fa-lock"></i>
                <a href="../login.jsp?cerrar=true">Cerrar Sesion</a>-->

                <a href="login.jsp?cerrar=true"
                   class="btn btn-primary btn-block">
                    <i class="fa-solid fa-lock"></i> Cerrar Sesion
                </a>

            </center>
            <br><br><br>



            <!--BLOQUES DE CATEGORIAS Y PUBLICIDAD-->
            <div class="container marketing">
                <!-- CATEGORIAS PRIMERA FILA-->
                <div class="row">
                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_playa.jpg" alt="categoria"/>
                        <a href="ServletControladorPlaya" class="btn btn-primary">
                            <i class="fa-solid fa-list "></i>  Playas
                        </a>
                    </div>

                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_balneario.jpg" alt="categoria"/>   
                        <a href="ServletControladorBalnearios" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Balnearios
                        </a>
                    </div>

                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_lago.jpg" alt="categoria"/>   
                        <a href="ServletControladorLago" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Lagos
                        </a>
                    </div>
                </div>
                <hr class="featurette-dividerM">

                <!-- CATEGORIAS SEGUNDA FILA-->
                <div class="row">
                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_catedral.jpg" alt="categoria"/>  
                        <a href="ServletControladorCatedral" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Catedrales
                        </a>
                    </div>

                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_rutas.jpg" alt="categoria"/>  
                        <a href="ServletControladorRuta" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Rutas
                        </a>

                    </div>

                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_ruina.jpg" alt="categoria"/>    
                        <a href="ServletControladorRuina" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Ruinas
                        </a>
                    </div>
                </div>

                <hr class="featurette-dividerM">

                <!-- CATEGORIAS TERCERA FILA-->
                <div class="row">
                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_volcan.jpg" alt="categoria"/>    
                        <a href="ServletControladorVolcan" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Volcanes
                        </a>
                    </div>

                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_mirador.jpg" alt="categoria"/>   
                        <a href="ServletControladorMirador" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Miradores
                        </a>
                    </div>

                    <div class="col-lg-4">
                        <img class="imgRedonda" src="imagenes/c_montania.jpg" alt="categoria"/>    
                        <a href="ServletControladorMontania" class="btn btn-primary">
                            <i class="fa-solid fa-list"></i>  Montañas
                        </a>
                    </div>
                </div>
                <br><br>
                <div class="col-lg-4 position-absolute start-50 translate-middle">
                    <a class="btn btn-primary" href="ServletControladorRecomendacion"><i class="fa-solid fa-eye"></i> Ver Recomendaciones</a>
                </div>

                <hr class="featurette-dividerM">
            </div>
        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    </body>
    <jsp:include page="WEB-INF/paginas/comunes/footerAdmin.jsp"/>
</html>
