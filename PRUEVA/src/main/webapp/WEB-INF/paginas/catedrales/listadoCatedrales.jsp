<%-- 
    Document   : listadoCatedrales
    Created on : 11-13-2022, 01:06:27 PM
    Author     : hp2022
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card-header table-responsive">
                    <center><h3>Listado Catedrales</h3></center>
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
                            <c:forEach var="cated" items="${lista}" varStatus="status">
                                <tr>
                                <td scope="row">${status.count}</td> 
                                <!--<td ><fmt:formatNumber value="${cated.id_categorias}"/></td> -->
                                <td >${cated.nombre}</td>
                                <td >${cated.nombre_departamento}</td>
                                <td >${cated.municipio}</td>
                                <td >${cated.direccion}</td>
                                <td align="justify">${cated.descripcion}</td> 
                                <td><img src="ControlerIMGCatedral?id_catedrales=${cated.id_catedrales}" width="150" height="100" class="rounded-3">
                                
                                <td>
                                    <a href="${pageContext.request.contextPath}//ServletControladorCatedral?accion=editar&id_catedrales=${cated.id_catedrales}"
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
<!--AGREGA LA CATEDRAL MODAL-->
<jsp:include page="/WEB-INF/paginas/catedrales/agregarCatedral.jsp"/>
