<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>  
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card-header table-responsive">
                    <br><br><br><br><br>
                    <center class=""><h3>Listado Recomendaciones</h3></center><br>
                    <table class="table table-striped align-middle">
                        <thead class="thead-dark">
                            <tr>
                            <th scope="col">#</th>
                            <th scope="col">Categoria</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Departamento</th>
                            <th scope="col">Municipio</th>
                            <th scope="col">Reseña</th>
                            <th scope="col">Estado</th>
                            </tr>
                        </thead>
                        <tbody class="abs-center">
                            <!--Iterando cada elemento de la lista de Recomendaciones-->
                            <c:forEach var="rec" items="${lista}" varStatus="status">
                                <tr>
                                <td scope="row">${status.count}</td> 
                                <td >${rec.nombre_cat}</td>
                                <td >${rec.nombre_lugar}</td>
                                <td >${rec.nombre_departamento}</td>
                                <td >${rec.municipio}</td>
                                <td align="justify">${rec.resenia}</td>
                                <td >${rec.nombre_estado}</td>
                                
                                <td>
                                    <a href="${pageContext.request.contextPath}//ServletControladorRecomendacion?accion=editar&id_recomendacion=${rec.id_recomendacion}"
                                       class="btn btn-secondary">
                                        <i class="fa-solid fa-pen-to-square"></i> Editar
                                    </a>   
                                </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>  
                </div>
            </div>
        </div>          
    </div>
</section>
