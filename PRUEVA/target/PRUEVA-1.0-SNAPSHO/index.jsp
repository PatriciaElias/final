<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<!DOCTYPE html>
<html>
    <!-- INVOCACION DE ELEMENTOS REQUERIDOS -->
    <head>
        <title>AtractivosSV</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--AWESOME-->
        <script src="https://kit.fontawesome.com/893526ba97.js" crossorigin="anonymous"></script>

        <!-- IMPORTAR BOOSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

        <!--IMPORTAR ESTILOS CSS PROPIOS DE LA PAGINA -->
        <link href="Recursos/carousel.css" rel="stylesheet">
    </head>

    <body>
        <!--INCLUYE EL JSP DEL HEADER ADMIN-->
        <jsp:include page="WEB-INF/paginas/comunes/headerUser.jsp"/>

    <main>
        <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
            <!--INDICADORES INFERIORES DEL CARRUCEL-->
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>

            <!-- BLOQUES DEL CARRUCEL 3IMG -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="foto" src="imagenes/ecVolcan.JPG" alt="encabezado"/>
                    <div class="container">
                        <div class="carousel-caption text-start">
                            <h1>Atractivos SV.</h1>
                            <p><h3>“El mundo es demasiado bonito como para viajar sólo por internet.”</h3></p>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <img class="foto" src="imagenes/ecLago.JPG" alt="encabezado"/>
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Atractivos SV.</h1>
                            <p><h3>“El auténtico viaje de descubrimiento no consiste en buscar nuevos paisajes, sino en tener una mirada nueva.”</h3></p>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <img class="foto" src="imagenes/ecPlaya.JPG" alt="encabezado"/>
                    <div class="container">
                        <div class="carousel-caption text-end">
                            <h1>Atractivos SV.</h1>
                            <p><h3>“Los viajes son como los atardeceres. Si esperas demasiado puedes no llegar a tiempo.</h3>"</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- BOTONES PREV-NEXT DEL CARRUCEL -->
            <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <!--BLOQUES DE CATEGORIAS Y PUBLICIDAD-->
        <div class="container marketing">

            <!-- BOTONES-->
            <div class="row">
                <div class="col-lg-4 ">
                    <a class="btn btn-success" href="ServletControladorA?accion=listarTodo"><i class="fa-solid fa-eye"></i>Ver todos los lugares</a>
                </div>
                <div class="col-lg-4 ">
                    <i class="fa-solid fa-sun fa-spin"></i></i>
                </div>
                <div class="col-lg-4 ">
                    <a class="btn btn-primary" href="ServletControladorRecomendacion?accion=listarU"><i class="fa-solid fa-eye"></i> Ver Recomendaciones</a>
                </div>               
            </div><br>

            <!-- CATEGORIAS PRIMERA FILA-->
            <div class="row">
                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_playa.jpg" alt="categoria"/>                 
                    <h2 class="fw-normal">Playas</h2>
                    <p>El disfrute verdadero está en la playa, en el encuentro con el agua y con la forma en que la arena sana el alma.</p>
                    <p><a class="btn btn-secondary " href="ServletControladorPlaya?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->

                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_balneario.jpg" alt="categoria"/> 
                    <h2 class="fw-normal">Balnearios</h2>
                    <p>Si el estrés no te deja en paz, rompe ya con la rutina y ve a darte un chapuzón en la piscina. Mejor después te sentirás.</p>
                    <p><a class="btn btn-secondary" href="ServletControladorBalnearios?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->

                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_lago.jpg" alt="categoria"/> 
                    <h2 class="fw-normal">Lagos</h2>
                    <p>"Si el lago ve el océano, querrá grandes olas. Si el océano ve el lago, querrá días tranquilos que duren años”. Mehmet Murat ildan</p>
                    <p><a class="btn btn-secondary" href="ServletControladorLago?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->

            <!-- CATEGORIAS SEGUNDA FILA-->
            <div class="row">
                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_catedral.jpg" alt="categoria"/>                 
                    <h2 class="fw-normal">Catedrales</h2>
                    <p>“Una pila de piedras deja de ser una pila de piedras en el momento en que un solo hombre la contempla, 
                        concibiendo por dentro la imagen de una catedral”- Antoine de Saint-Exupéry</p>
                    <p><a class="btn btn-secondary" href="ServletControladorCatedral?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->

                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_rutas.jpg" alt="categoria"/> 
                    <h2 class="fw-normal">Rutas</h2>
                    <p>"Presta atención a las cosas, que se te llama naturalmente. A menudo están conectados con tu camino,
                        tu pasión y tu propósito en la vida. Ten el valor de seguirlos." Rubén Chávez.</p>
                    <p><a class="btn btn-secondary" href="ServletControladorRuta?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->

                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_ruina.jpg" alt="categoria"/> 
                    <h2 class="fw-normal">Ruinas</h2>
                    <p>“Tu trabajo no es sobre el pasado ni el presente, sino sobre el futuro, para que las próximas generaciones sepan de dónde vienen. 
                        Lo que las relaciona con sus ancestros.” Monica Dolan</p>
                    <p><a class="btn btn-secondary" href="ServletControladorRuina?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->

            <!-- CATEGORIAS TERCERA FILA-->
            <div class="row">
                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_volcan.jpg" alt="categoria"/>                 
                    <h2 class="fw-normal">Volcanes</h2>
                    <p>"El amor es un volcán, el cráter del cual ningún sabio se acercará demasiado, no sea que … sea tragado". Charles Caleb Colton.</p>
                    <p><a class="btn btn-secondary" href="ServletControladorVolcan?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->

                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_mirador.jpg" alt="categoria"/> 
                    <h2 class="fw-normal">Miradores</h2>
                    <p>"Hagamos de los atardeceres algo inolvidable, de las mañanas algo hermoso, y de las noches algo que siempre queramos repetir".Leo Romsog</p>
                    <p><a class="btn btn-secondary" href="ServletControladorMirador?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->

                <div class="col-lg-4">
                    <img class="imgRedonda" src="imagenes/c_montania.jpg" alt="categoria"/> 
                    <h2 class="fw-normal">Montañas</h2>
                    <p>"Vive tu vida como si subieras una montaña. De vez en cuando mira la cumbre, pero más importante es admirar las cosas bellas del camino."
                        Harold V. Melchert</p>
                    <p><a class="btn btn-secondary" href="ServletControladorMontania?accion=listarU"><i class="fa-solid fa-eye"></i> Ver detalles</a></p>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->

            <!-- CARACTERISTICAS DEL SITIO -->
            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7">
                    <h2 class="featurette-heading fw-normal lh-1">Parque recreativo <span class="text-info">Cerro verde.</span></h2>
                    <p class="lead">Este cerro de “nubes” o “brumas” es una opción ideal para los amantes de la naturaleza y la aventura en la zona occidental de El Salvador. Paseando por el camino del parque de la mano de los guías turísticos locales podrás disfrutar de la biodiversidad de flora y fauna que adornan este lugar, así como de miradores y áreas de juegos para los niños.</p>
                </div>
                <div class="col-md-5">            
                    <img class="img-fluid rounded-3" src="imagenes/D_CerroVerde.jpg" alt="Descripcion" />                 
                </div>
            </div>

            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7 order-md-2">
                    <h2 class="featurette-heading fw-normal lh-1">El monumental <span class="text-info">Lago de Coatepeque</span></h2>
                    <p class="lead">Sus aguas azules y su alta oferta de ecoturismo hacen del lago de Coatepeque uno de los destinos preferidos por salvadoreños y extranjeros. En este lago de origen volcánico podrás practicar kayak, jet ski y buceo, y en sus alrededores senderismo o bicicleta de montaña.</p>
                </div>
                <div class="col-md-5">
                    <img class="img-fluid rounded-3" src="imagenes/D_Coatepeque.jpg" alt="Descripcion" /> 
                </div>
            </div>

            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7">
                    <h2 class="featurette-heading fw-normal lh-1">Incomparable <span class="text-info">Eramon.</span></h2>
                    <p class="lead">Esta joya ofrece una vista única que te permitirá apreciar multidestinos emblemáticos de El Salvador al mismo tiempo.. Este lugar es perfecto para hacer senderismo, camping, asói como disfrutar de amaneceres y atardecer...</p>
                </div>
                <div class="col-md-5">
                    <img class="img-fluid rounded-3" src="imagenes/D_eramon.jpg" alt="Descripcion" /> 
                </div>
            </div>
            <hr class="featurette-divider">
        </div>

    </main>
    <!--INCLUYE EL JSP DEL FOOTER ADMIN-->
    <jsp:include page="WEB-INF/paginas/comunes/footerUser.jsp"/>
</body>
</html>
