<div class="modal fade" id="agregarBalnearioModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary bg-gradient text-white">
                <i class="fa-solid fa-water-ladder"></i><h5 class="modal-title">. Agregar Balneario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletControladorBalnearios?accion=insertar"
                  method="post" class="was-validated" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id_categorias">Categoria:</label>
                        <!--EL VALUE DE LA CATEGORIA CAMBIA SEGUN LA TABLA-->
                        <input type="text" class="form-control" name="id_categorias"  readonly  placeholder="Balnearios"required>
                    </div>
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" name="nombre" required>
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
                        <label for="direccion">Direccion:</label>
                        <input type="text" class="form-control" name="direccion" required >
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripcion</label>
                        <textarea class="form-control" name="descripcion" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="foto">Fotografia:</label>
                        <input type="file" class="form-control" name="foto" accept="image/*" required >
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

