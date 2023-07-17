package dominio;

import java.io.InputStream;

public class Ruta {
    private int id_ruta;
    private int id_categorias;
    private String nombre_cat;
    private String nombre;
    private String duracion;
    private String descripcion;
    private InputStream foto;

    public Ruta() {
    }

    public Ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public Ruta(int id_ruta, int id_categorias, String nombre_cat, String nombre, String duracion, String descripcion, InputStream foto) {
        this.id_ruta = id_ruta;
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre = nombre;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Ruta(int id_categorias, String nombre_cat, String nombre, String duracion, String descripcion, InputStream foto) {
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre = nombre;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public int getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(int id_categorias) {
        this.id_categorias = id_categorias;
    }

    public String getNombre_cat() {
        return nombre_cat;
    }

    public void setNombre_cat(String nombre_cat) {
        this.nombre_cat = nombre_cat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
}
