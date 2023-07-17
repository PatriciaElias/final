package dominio;

public class Recomendacion {
    private int id_recomendacion;
    private int id_categorias;
    private String nombre_cat;
    private String nombre_lugar;
    private int cod_departamento;
    private String nombre_departamento;
    private String municipio;
    private String resenia;
    private int cod_estado;
    private String nombre_estado;

    public Recomendacion() {
    }

    public Recomendacion(int id_recomendacion) {
        this.id_recomendacion = id_recomendacion;
    }

    public Recomendacion(int id_recomendacion, int id_categorias, String nombre_cat, String nombre_lugar, int cod_departamento, String nombre_departamento, String municipio, String resenia, int cod_estado, String nombre_estado) {
        this.id_recomendacion = id_recomendacion;
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_lugar = nombre_lugar;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.resenia = resenia;
        this.cod_estado = cod_estado;
        this.nombre_estado = nombre_estado;
    }

    public Recomendacion(int id_categorias, String nombre_cat, String nombre_lugar, int cod_departamento, String nombre_departamento, String municipio, String resenia, int cod_estado, String nombre_estado) {
        this.id_categorias = id_categorias;
        this.nombre_cat = nombre_cat;
        this.nombre_lugar = nombre_lugar;
        this.cod_departamento = cod_departamento;
        this.nombre_departamento = nombre_departamento;
        this.municipio = municipio;
        this.resenia = resenia;
        this.cod_estado = cod_estado;
        this.nombre_estado = nombre_estado;
    }

    public int getId_recomendacion() {
        return id_recomendacion;
    }

    public void setId_recomendacion(int id_recomendacion) {
        this.id_recomendacion = id_recomendacion;
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

    public String getNombre_lugar() {
        return nombre_lugar;
    }

    public void setNombre_lugar(String nombre_lugar) {
        this.nombre_lugar = nombre_lugar;
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

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
}
