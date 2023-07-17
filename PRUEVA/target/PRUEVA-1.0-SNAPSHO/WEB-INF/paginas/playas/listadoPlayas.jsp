<%-- 
    Document   : listadoPlayas
    Created on : 26 oct 2022, 20:55:32
    Author     : Andersson
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card-header table-responsive">
                    <center><h3>Listado Playas</h3></center>
                    <table class="table table-striped align-middle">
                        <thead class="thead-dark">
                            <tr>
                            <th scope="col">#</th>
                            <!--<th scope="col">ID Categoria</th>-->
                            <th scope="col">Nombre</th>
                            <th scope="col">Departamento</th>
                            <th scope="col">Municipio</th>
                            <th scope="col">Direccion</th>
                            <th scope="col">Descripcion</th>
                            </tr>
                        </thead>
                        <tbody class="abs-center">
                            <!--Iterando cada elemento de la lista de clientes-->
                            <c:forEach var="pla" items="${lista}" varStatus="status">
                                <tr>
                                <td scope="row">${status.count}</td> 
                                <!--<td ><fmt:formatNumber value="${pla.id_categorias}"/></td> -->
                                <td >${pla.nombre_playa}</td>
                                <td >${pla.nombre_departamento}</td>
                                <td >${pla.municipio}</td>
                                <td >${pla.direccion}</td>
                                <td align="justify">${pla.descripcion}</td> 
                                <td><img src="ControlerIMGPlaya?id_playas=${pla.id_playas}" width="150" height="100" class="rounded-3">
                                
                                <td>
                                    <a href="${pageContext.request.contextPath}//ServletControladorPlaya?accion=editar&id_playas=${pla.id_playas}"
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
<!--AGREGA LA PLAYA MODAL-->
<jsp:include page="/WEB-INF/paginas/playas/agregarPlaya.jsp"/>
