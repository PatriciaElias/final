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
        <title>Formulario Catedral</title>
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
                    <div class="card-heading targetCatedral"></div>
                    <div class="card-body">
                        <h2 class="title">Editar Catedral</h2>
                        <form action="${pageContext.request.contextPath}/ServletControladorCatedral?accion=modificar&id_catedrales=${catedral.id_catedrales}"
                              method="post" class="was-validated" enctype="multipart/form-data">
                            <div class="row row-space">
                                <div class="col-2"><label>Categoria:</label>
                                    <div class="input-group">
                                        <!--EL VALUE DE LA CATEGORIA CAMBIA SEGUN LA TABLA-->
                                        <input type="text" class="form-control" name="id_categorias"  readonly  value="${catedral.nombre_cat}" required>
                                    </div>
                                </div>

                                <div class="col-2">
                                    <label>Nombre:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Nombre" name="nombre" required value="${catedral.nombre}">
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <label>Departamento:</label>
                                    <div class="input-group">
                                        <select id="inputState" class="form-control" name="departamento" required>
                                            <option readonly selected="selected" value="${catedral.cod_departamento}">${catedral.nombre_departamento}</option>
                                            <option value="1">San Salvador</option>
                                            <option value="2">Ahuachapán</option>
                                            <option value="3">Cabañas</option>
                                            <option value="4">Chalatenango</option>
                                            <option value="5">Cuscatlán</option>
                                            <option value="6">La Libertad</option>
                                            <option value="7">Morazán</option>
                                            <option value="8">La Paz</option>
                                            <option value="9">Santa Ana</option>
                                            <option value="10">San Miguel</option>
                                            <option value="11">San Vicente</option>
                                            <option value="12">Sonsonate</option>
                                            <option value="13">La Unión</option>
                                            <option value="14">Usulután</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-2">
                                    <label>Municipio:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Municipio" name="municipio" required value="${catedral.municipio}">
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <label>Direccion:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Direccion" name="direccion" required value="${catedral.direccion}">
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
                                        <textarea class="form-control" rows="3" placeholder="Descripcion" name="descripcion" required>${catedral.descripcion}</textarea>
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
                                    <a href="${pageContext.request.contextPath}/ServletControladorCatedral?accion=eliminar&id_catedrales=${catedral.id_catedrales}"
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
