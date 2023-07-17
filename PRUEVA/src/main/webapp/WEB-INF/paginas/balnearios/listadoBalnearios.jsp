<%-- 
    Document   : listadoBalnearios
    Created on : 10 nov. 2022, 13:46:27
    Author     : MINEDUCYT
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card-header table-responsive">
                    <center><h3>Listado Balnearios</h3></center>
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
                            <c:forEach var="bal" items="${lista}" varStatus="status">
                                <tr>
                                <td scope="row">${status.count}</td> 
                                <!--<td ><fmt:formatNumber value="${bal.id_categorias}"/></td> -->
                                <td >${bal.nombre}</td>
                                <td >${bal.nombre_departamento}</td>
                                <td >${bal.municipio}</td>
                                <td >${bal.direccion}</td>
                                <td align="justify">${bal.descripcion}</td> 
                                <td><img src="ControlerIMGBalneario?id_balnearios=${bal.id_balnearios}" width="150" height="100" class="rounded-3">
                                
                                <td>
                                    <a href="${pageContext.request.contextPath}//ServletControladorBalnearios?accion=editar&id_balnearios=${bal.id_balnearios}"
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
<jsp:include page="/WEB-INF/paginas/balnearios/agregarBalneario.jsp"/>
