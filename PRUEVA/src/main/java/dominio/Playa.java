package dominio;

import java.io.InputStream;

public class Playa {
    private int id_playas;
    private int id_categorias;
    private String nombre_cat;
    private String nombre_playa;
    private int cod_departamento;
    private String nombre_departamento;
    private String municipio;
    private String direccion;
    private String descripcion;
    private InputStream foto;

    public Playa() {
    }

    public Playa(int id_playas) {
        this.id_playas = id_playas;
    }

    public Playa(int id_playas, int id_categorias, String nombre_cat, String nombre_playa, int cod_departamento, String nombre_departamento, String municipio, String direccion, String descripcion, InputStream foto) {
        this.id_playas = id_playas;
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_playa = nombre_playa;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Playa(int id_categorias, String nombre_cat, String nombre_playa, int cod_departamento, String nombre_departamento, String municipio, String direccion, String descripcion, InputStream foto) {
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_playa = nombre_playa;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public int getId_playas() {
        return id_playas;
    }

    public void setId_playas(int id_playas) {
        this.id_playas = id_playas;
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

    public String getNombre_playa() {
        return nombre_playa;
    }

    public void setNombre_playa(String nombre_playa) {
        this.nombre_playa = nombre_playa;
    }

    public int getCod_departamento() {
        return cod_departamento;
    }

    public void setCod_departamento(int cod_departamento) {
        this.cod_departamento = cod_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
