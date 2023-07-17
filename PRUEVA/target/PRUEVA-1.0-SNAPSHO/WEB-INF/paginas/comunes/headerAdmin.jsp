<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark ">
        <div class="container-fluid">

            <img class="" src="imagenes/loguito.png" alt="categoria" width="75" height="75"/>
            <a class="navbar-brand " href="index.jsp">AtractivosSV</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <!--ITEMS DE LA BARRA-->
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                   
                    <li class="nav-item">
                        <a class="nav-link" href="menuAdmin.jsp">MENU</a>
                    </li>
                    <!--DROPDOWN CATEGORIAS-->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            CATEGORIAS
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark ">
                            <li><a class="dropdown-item" href="ServletControladorPlaya">Playas</a></li>
                            <li><a class="dropdown-item" href="ServletControladorBalnearios">Balnearios</a></li>
                            <li><a class="dropdown-item" href="ServletControladorLago">Lagos</a></li>
                            <li><a class="dropdown-item" href="ServletControladorCatedral">Catedrales</a></li>
                            <li><a class="dropdown-item" href="ServletControladorRuta">Rutas</a></li>
                            <li><a class="dropdown-item" href="ServletControladorRuina">Ruinas</a></li>
                            <li><a class="dropdown-item" href="ServletControladorVolcan">Volcanes</a></li>
                            <li><a class="dropdown-item" href="ServletControladorMirador">Miradores</a></li>
                            <li><a class="dropdown-item" href="ServletControladorMontania">Montaña</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="recomendaciones.jsp">RECOMENDACIONES</a>
                    </li>
                    <li class="float-right">
                        <a href="login.jsp?cerrar=true"
                           class="btn btn-secondary btn-block">
                            <i class="fa-solid fa-lock"></i>
                        </a>
                    </li>
                    
                </ul>
            </div>
        </div>

    </nav>
</header>