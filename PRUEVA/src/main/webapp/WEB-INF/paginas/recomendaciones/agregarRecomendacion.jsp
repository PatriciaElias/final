<div class="modal fade" id="agregarRecomendacionModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success bg-gradient text-white">
                <i class="fa-solid fa-pen fa-spin"></i><h5 class="modal-title">. Agregar Recomendacion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>            
            <form action="${pageContext.request.contextPath}/ServletControladorRecomendacion?accion=insertar"
                  method="post" class="was-validated" enctype="multipart/form-data">
                <div class="modal-body">
                    <div>
                        <p align="justify">
                            Si deseas recomendar un nuevo lugar puedes hacerlo desde aqui y nosotros proximamente 
                            lo estaremos agregando a nuestra plataforma, tu opinion es muy importante para Atractivos SV.
                            Recuerda que tu recomendacion no sera vista de inmediato en el sitio web ya que debe de ser 
                            aprobada por el personal autorizado.
                        </p>
                    </div>
                    <div class="form-group">
                        <label for="id_categorias">Categoria:</label>
                        <!--EL VALUE DE LA CATEGORIA CAMBIA SEGUN LA TABLA-->
                        <select id="inputState" class="form-control" name="id_categorias" required>
                            <option disabled="disabled" selected="selected"></option>
                            <option value="1">Playa</option>
                            <option value="2">Balneario</option>
                            <option value="3">Lago</option>
                            <option value="4">Catedral</option>
                            <option value="5">Ruta</option>
                            <option value="6">Ruina</option>
                            <option value="7">Volcan</option>
                            <option value="8">Montania</option>
                            <option value="9">Mirador</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="nombre_playa">Nombre:</label>
                        <input type="text" class="form-control" name="nombre_lugar" required>
                    </div>
                    <div class="form-group">
                        <label for="departamento">Departamento:</label>

                        <select class="form-control" name="departamento" required>
                            <option disabled="disabled" selected="selected"></option>
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
                    <div class="form-group">
                        <label for="municipio">Municipio:</label>
                        <input type="text" class="form-control" name="municipio" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Reseña: </label>
                        <textarea class="form-control" name="resenia" required></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success" type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

