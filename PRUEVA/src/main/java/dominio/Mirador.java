package dominio;

import java.io.InputStream;

public class Mirador {
    
    private int id_mirador;
    private int id_categorias;
    private String nombre_cat;
    private String nombre_mirador;
    private int cod_departamento;
    private String nombre_departamento;
    private String municipio;
    private String direccion;
    private String descripcion;
    private InputStream foto;

    public Mirador() {
    }

    public Mirador(int id_mirador) {
        this.id_mirador = id_mirador;
    }

    public Mirador(int id_mirador, int id_categorias, String nombre_cat, String nombre_mirador, int cod_departamento, String nombre_departamento, String municipio, String direccion, String descripcion, InputStream foto) {
        this.id_mirador = id_mirador;
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_mirador = nombre_mirador;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Mirador(int id_categorias, String nombre_cat, String nombre_mirador, int cod_departamento, String nombre_departamento, String municipio, String direccion, String descripcion, InputStream foto) {
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_mirador = nombre_mirador;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public int getId_mirador() {
        return id_mirador;
    }

    public void setId_mirador(int id_mirador) {
        this.id_mirador = id_mirador;
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

    
    
    public String getNombre_mirador() {
        return nombre_mirador;
    }

    public void setNombre_mirador(String nombre_mirador) {
        this.nombre_mirador = nombre_mirador;
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