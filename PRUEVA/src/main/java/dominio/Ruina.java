package dominio;

import java.io.InputStream;

public class Ruina {
    private int id_ruinas;
    private int id_categorias;
    private String nombre_cat;
    private String nombre_ruina;
    private int cod_departamento;
    private String nombre_departamento;
    private String municipio;
    private String direccion;
    private String descripcion;
    private InputStream foto;

    public Ruina() {
    }

    public Ruina(int id_ruinas) {
        this.id_ruinas = id_ruinas;
    }

    public Ruina(int id_ruinas, int id_categorias, String nombre_cat, String nombre_ruina, int cod_departamento, String nombre_departamento, String municipio, String direccion, String descripcion, InputStream foto) {
        this.id_ruinas = id_ruinas;
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_ruina = nombre_ruina;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Ruina(int id_categorias, String nombre_cat, String nombre_ruina, int cod_departamento, String nombre_departamento, String municipio, String direccion, String descripcion, InputStream foto) {
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_ruina = nombre_ruina;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public int getId_ruinas() {
        return id_ruinas;
    }

    public void setId_ruinas(int id_ruinas) {
        this.id_ruinas = id_ruinas;
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

    public String getNombre_ruina() {
        return nombre_ruina;
    }

    public void setNombre_ruina(String nombre_ruina) {
        this.nombre_ruina = nombre_ruina;
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
