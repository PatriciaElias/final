<%-- 
    Document   : listadoVolcanes
    Created on : 13 nov 2022, 17:47:13
    Author     : Edwin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card-header table-responsive">
                    <center><h3>Listado Volcanes</h3></center>
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
                            <c:forEach var="vol" items="${lista}" varStatus="status">
                                <tr>
                                <td scope="row">${status.count}</td> 
                                <!--<td ><fmt:formatNumber value="${vol.id_categorias}"/></td> -->
                                <td >${vol.nombre_volcan}</td>
                                <td >${vol.nombre_departamento}</td>
                                <td >${vol.municipio}</td>
                                <td >${vol.direccion}</td>
                                <td align="justify">${vol.descripcion}</td> 
                                <td><img src="ControlerIMGVolcan?id_volcanes=${vol.id_volcanes}" width="150" height="100"class="rounded-3">
                                
                                <td>
                                    <a href="${pageContext.request.contextPath}//ServletControladorVolcan?accion=editar&id_volcanes=${vol.id_volcanes}"
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
<jsp:include page="/WEB-INF/paginas/volcanes/agregarVolcan.jsp"/>
