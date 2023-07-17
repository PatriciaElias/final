<div class="modal fade" id="agregarRutaModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary bg-gradient text-white">
                <i class="fa fa-map-signs pe-3"></i><h5 class="modal-title">. Agregar Ruta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletControladorRuta?accion=insertar"
                  method="post" class="was-validated" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id_categorias">Categoria:</label>
                        <!--EL VALUE DE LA CATEGORIA CAMBIA SEGUN LA TABLA-->
                        <input type="text" class="form-control" name="id_categorias"  readonly  placeholder="Rutas"required>
                    </div>
                    <div class="form-group">
                        <label for="nombre_playa">Nombre:</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="direccion">Duracion:</label>
                        <input type="text" class="form-control" name="duracion" required >
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

