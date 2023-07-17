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

<html>
    <head>
        <title>Formulario Ruta</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--BOSTRAP-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <!--AWESOME-->
        <script src="https://kit.fontawesome.com/893526ba97.js" crossorigin="anonymous"></script>
        <!--ESTILOS LOCALES-->
        <link href="Recursos/EstilosForm.css" rel="stylesheet" media="all">      
    </head>
    <body class="bg-degradado">
        <div class="page-wrapper bg-red p-t-100 p-b-100 font-robo">
            <div class="wrapper wrapper--w960">
                <div class="card card-2">
                    <div class="card-heading targetRuta"></div>
                    <div class="card-body">
                        <h2 class="title">Editar Ruta</h2>
                        <form action="${pageContext.request.contextPath}/ServletControladorRuta?accion=modificar&id_ruta=${ruta.id_ruta}"
                              method="post" class="was-validated" enctype="multipart/form-data">
                            <div class="row row-space">
                                <div class="col-2"><label>Categoria:</label>
                                    <div class="input-group">
                                        <!--EL VALUE DE LA CATEGORIA CAMBIA SEGUN LA TABLA-->
                                        <input type="text" class="form-control" name="id_categorias"  readonly  value="${ruta.nombre_cat}" required>
                                    </div>
                                </div>

                                <div class="col-2">
                                    <label>Nombre:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Nombre" name="nombre" required value="${ruta.nombre}">
                                    </div>
                                </div>
                            </div>

                            <div class="row row-space">
                                <div class="col-2">
                                    <label>Duracion:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Duracion" name="duracion" required value="${ruta.duracion}">
                                    </div>
                                </div>

                                <div class="col-2">
                                    <label>Imagen:</label>
                                    <div class="input-group">
                                        <input class="form-control form-control-sm" type="file" name="foto" accept="image/*" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-12">
                                    <label>Descripcion:</label>
                                    <div class="input-group">
                                        <textarea class="form-control" rows="3" placeholder="Descripcion" name="descripcion" required>${ruta.descripcion}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <button type="submit" class="btn btn-success btn-block">
                                        <i class="fa-solid fa-floppy-disk"></i> Guardar
                                    </button>
                                </div>
                                <div class="col-2">
                                    <a href="${pageContext.request.contextPath}/ServletControladorRuta?accion=eliminar&id_ruta=${ruta.id_ruta}"
                                       class="btn btn-danger btn-block">
                                        <i class="fas fa-trash"></i> Eliminar
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--SCRIP DE BOOTSTRAP-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    </body>
</html>
