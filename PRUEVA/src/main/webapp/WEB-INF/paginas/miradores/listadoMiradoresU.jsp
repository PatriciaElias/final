<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section >
    <div class="container" >
        <div class="row">
            <div class="col-md-12">
                <div class="card-header text-white table-responsive">
                    <!--Iterando cada elemento de la lista de clientes-->
                    <c:forEach var="mir" items="${listaMirador}" varStatus="status">
                        <table class="table table-borderless  abs-center text-white align-middle " style="background-color: rgba(0, 0, 0, 0.1);
                               -moz-border-radius: 25px;
                               -webkit-border-radius: 25px;
                               border-radius: 25px;
                               -webkit-box-shadow: 0 3px 3px #ccc;
                               -moz-box-shadow: 0 3px 3px #ccc;
                               box-shadow: 0 3px 3px #ccc;">
                            <tbody class="">
                                <tr>
                                <td class="col-1" style>
                                    <h3>${status.count} <i class="fa-solid fa-binoculars"></i></h3>
                                    
                                </td>
                                <td colspan="2" class="">

                                    <h6 class="text-info">Nombre:</h6>
                                    <h4>${mir.nombre_mirador}</h4>
                                </td>
                                <td rowspan="4" class="col-5"><img src="ControlerIMGMirador?id_mirador=${mir.id_mirador}"class="rounded-3 mx-auto" width="450" height="300"></td>
                                </tr>
                                <tr>
                                <td colspan="2">
                                    <h6 class="text-info">Departamento:</h6>
                                    <h5>${mir.nombre_departamento}</h5>
                                </td>
                                <td>
                                    <h6 class="text-info">Municipio:</h6>
                                    <h5>${mir.municipio}</h5>
                                </td>
                                </tr>
                                <tr>
                                <td colspan="3">
                                    <h6 class="text-info">Direccion:</h6>
                                    <h5>${mir.direccion}</h5>
                                </td>
                                </tr>
                                <tr>
                                <td colspan="3" class="">
                                    <h6 class="text-info">Descripcion:</h6>
                                    <h5>${mir.descripcion}</h5>
                                </td>
                                </tr>
                            </tbody>
                        </table> 
                    </c:forEach>
                </div>
            </div>
        </div>          
    </div>
</section>
