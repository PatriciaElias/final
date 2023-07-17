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
                    <!--BOTON LOGIN-->
                    <!--<li class="float-right">
                        <a href="login.jsp" target="_blank"
                           class="btn btn-secondary btn-block">
                            <i class="fa-solid fa-user"></i>
                        </a>
                    </li>-->        

                    <!--DROPDOWN CATEGORIAS-->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            CATEGORIAS
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark ">
                            <li><a class="dropdown-item" href="ServletControladorPlaya?accion=listarU">Playas</a></li>
                            <li><a class="dropdown-item" href="ServletControladorBalnearios?accion=listarU">Balnearios</a></li>
                            <li><a class="dropdown-item" href="ServletControladorLago?accion=listarU">Lagos</a></li>
                            <li><a class="dropdown-item" href="ServletControladorCatedral?accion=listarU">Catedrales</a></li>
                            <li><a class="dropdown-item" href="ServletControladorRuta?accion=listarU">Rutas</a></li>
                            <li><a class="dropdown-item" href="ServletControladorRuina?accion=listarU">Ruinas</a></li>
                            <li><a class="dropdown-item" href="ServletControladorVolcan?accion=listarU">Volcanes</a></li>
                            <li><a class="dropdown-item" href="ServletControladorMirador?accion=listarU">Miradores</a></li>
                            <li><a class="dropdown-item" href="ServletControladorMontania?accion=listarU">Montaña</a></li>
                        </ul>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="recomendacionesListaU.jsp">RECOMENDACIONES</a>
                    </li>
                    
                    <li class="nav-item">
                        <a href="login.jsp" target="_blank" class="text-white px-2 nav-link">
                            <i class="fa-solid fa-user"></i>
                        </a>
                    </li>                      
                </ul>
            </div>
        </div>

    </nav>
</header>