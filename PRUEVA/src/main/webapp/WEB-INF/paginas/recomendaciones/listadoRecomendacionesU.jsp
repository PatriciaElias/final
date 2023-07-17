<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br><br><br><br>
<section >
    <div class="col-12">
        <img src="imagenes/cabecera_recomendacion.jpg"class=" mx-auto img-fluid">
    </div><br>
    <div class="container" >
        <div class="row">
            <div class="col-md-12">
                <div class="card-header text-white table-responsive">

                    <div>
                        <center>
                            <section id="actions" class="py-4 mb-4 bg-ligth">
                                <div class="col md-3" >
                                    <a href="#" class="btn btn-primary btn-block"
                                       data-bs-toggle="modal" data-bs-target="#agregarRecomendacionModal">
                                        <i class="fa fa-plus"></i>Agregar Recomendacion
                                    </a>
                                </div>
                            </section>
                        </center>
                    </div>

                    <!--Iterando cada elemento de la lista de clientes-->
                    <table class="table table-striped align-middle">
                        <thead class="thead-dark">
                            <tr>
                            <th scope="col" class="col-2 text-info"></th>
                            <th scope="col" class="col-2 text-info">Categoria</th>
                            <th scope="col" class="col-2 text-info">Nombre</th>
                            <th scope="col" class="col-2 text-info">Departamento</th>
                            <th scope="col" class="col-2 text-info">Municipio</th>
                            <th scope="col" class="col-2 text-info">Reseña</th>
                            </tr>
                        </thead>
                        <c:forEach var="rec" items="${lista}" varStatus="status">
                            <table class="table table-borderless  abs-center  align-middle" style="background-color: rgba(0, 0, 0, 0.05);
                                   -moz-border-radius: 25px;
                                   -webkit-border-radius: 25px;
                                   border-radius: 25px;
                                   -webkit-box-shadow: 0 3px 3px #ccc;
                                   -moz-box-shadow: 0 3px 3px #ccc;
                                   box-shadow: 0 3px 3px #ccc;">
                                <tbody>
                                    <tr>
                                    <td class="col-2" >
                                        <h6>${status.count}. <i class="fa-solid fa-pen fa-spin"></i></h6>                                    
                                    </td>
                                    <td  class="col-2">
                                        <h6>${rec.nombre_cat}</h6>
                                    </td>
                                    <td  class="col-2">
                                        <h6>${rec.nombre_lugar}</h6>
                                    </td>
                                    <td class="col-2">
                                        <h6>${rec.nombre_departamento}</h6>
                                    </td>
                                    <td class="col-2">
                                        <h6>${rec.municipio}</h6>
                                    </td>
                                    <td class="col-2">
                                        <h6>${rec.resenia}</h6>
                                    </td>
                                    </tr>                                
                                </tbody>
                            </table> 
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>          
    </div>
</section>
