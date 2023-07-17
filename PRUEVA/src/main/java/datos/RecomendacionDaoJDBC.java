package datos;

import dominio.Recomendacion;
import java.sql.*;
import java.util.*;

public class RecomendacionDaoJDBC {

    //CONSULTA PARA EL ADMIN
    private static final String SQL_CONSULTA = "SELECT  recomendaciones.id_recomendacion,\n" 
            + "            recomendaciones.nombre_lugar, \n" 
            + "            categorias_lugares.id_categorias,\n" 
            + "            categorias_lugares.nombre_cat, \n" 
            + "            departamentos.cod_departamento,\n" 
            + "            departamentos.nombre_departamento, \n" 
            + "            recomendaciones.municipio,\n" 
            + "            recomendaciones.resenia,\n" 
            + "       	  estados.cod_estado, \n" 
            + "            estados.nombre_estado \n" 
            + "FROM recomendaciones \n"
            + "inner join categorias_lugares\n" 
            + "on recomendaciones.id_categorias=categorias_lugares.id_categorias\n" 
            + "inner join departamentos \n" 
            + "on recomendaciones.cod_departamento=departamentos.cod_departamento \n" 
            + "inner join estados\n" 
            + "on recomendaciones.cod_estado=estados.cod_estado ORDER BY  id_recomendacion asc\n";

    //CONSULTA PARA EL TURISTA
    private static final String SQL_CONSULTA_ESTADO = "SELECT  recomendaciones.id_recomendacion,\n" 
            + "            recomendaciones.nombre_lugar, \n" 
            + "            categorias_lugares.id_categorias,\n" 
            + "            categorias_lugares.nombre_cat, \n" 
            + "            departamentos.cod_departamento,\n" 
            + "            departamentos.nombre_departamento, \n" 
            + "            recomendaciones.municipio,\n" 
            + "            recomendaciones.resenia,\n" 
            + "       	  estados.cod_estado, \n" 
            + "            estados.nombre_estado \n" 
            + "FROM recomendaciones \n"
            + "inner join categorias_lugares\n" 
            + "on recomendaciones.id_categorias=categorias_lugares.id_categorias\n" 
            + "inner join departamentos \n" 
            + "on recomendaciones.cod_departamento=departamentos.cod_departamento \n" 
            + "inner join estados\n" 
            + "on recomendaciones.cod_estado=estados.cod_estado\n" 
            + "WHERE recomendaciones.cod_estado=1 order by  id_recomendacion asc";
    
    //CONSULTA PARA EL ADMIN-MODIFICAR
    private static final String SQL_POR_ID = "SELECT  recomendaciones.id_recomendacion,\n" 
            + "            recomendaciones.nombre_lugar, \n" 
            + "            categorias_lugares.id_categorias,\n" 
            + "            categorias_lugares.nombre_cat, \n" 
            + "            departamentos.cod_departamento,\n" 
            + "            departamentos.nombre_departamento, \n" 
            + "            recomendaciones.municipio,\n" 
            + "            recomendaciones.resenia,\n" 
            + "       	  estados.cod_estado, \n" 
            + "            estados.nombre_estado \n" 
            + "FROM recomendaciones \n"
            + "inner join categorias_lugares\n" 
            + "on recomendaciones.id_categorias=categorias_lugares.id_categorias\n" 
            + "inner join departamentos \n" 
            + "on recomendaciones.cod_departamento=departamentos.cod_departamento \n" 
            + "inner join estados\n" 
            + "on recomendaciones.cod_estado=estados.cod_estado\n"
            + "WHERE recomendaciones.id_recomendacion=?";

    private static final String SQL_INSERTAR = "INSERT INTO recomendaciones (id_categorias, nombre_lugar, cod_departamento, municipio,resenia,cod_estado)"
            + "VALUES( ?, ?, ? ,?, ?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE recomendaciones "
            + "SET id_categorias=?, nombre_lugar=?, cod_departamento=?, municipio=?, resenia=?, cod_estado=? WHERE id_recomendacion=? ";

    private static final String SQL_BORRAR = "DELETE FROM recomendaciones WHERE id_recomendacion=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Recomendacion> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();
           
            while (rs.next()) {
                Recomendacion recomendacion = new Recomendacion();
                recomendacion.setId_recomendacion(rs.getInt("id_recomendacion"));
                recomendacion.setId_categorias(rs.getInt("id_categorias"));
                recomendacion.setNombre_cat(rs.getString("nombre_cat"));
                recomendacion.setNombre_lugar(rs.getString("nombre_lugar"));
                recomendacion.setCod_departamento(rs.getInt("cod_departamento"));
                recomendacion.setNombre_departamento(rs.getString("nombre_departamento"));
                recomendacion.setMunicipio(rs.getString("municipio"));
                recomendacion.setResenia(rs.getString("resenia"));
                recomendacion.setCod_estado(rs.getInt("cod_estado"));
                recomendacion.setNombre_estado(rs.getString("nombre_estado"));

                lista.add(recomendacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lista;
    }
    //CONSULTA PARA EL TURISTA
    public List listarU() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Recomendacion> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA_ESTADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Recomendacion recomendacion = new Recomendacion();
                recomendacion.setId_recomendacion(rs.getInt("id_recomendacion"));
                recomendacion.setId_categorias(rs.getInt("id_categorias"));
                recomendacion.setNombre_cat(rs.getString("nombre_cat"));
                recomendacion.setNombre_lugar(rs.getString("nombre_lugar"));
                recomendacion.setCod_departamento(rs.getInt("cod_departamento"));
                recomendacion.setNombre_departamento(rs.getString("nombre_departamento"));
                recomendacion.setMunicipio(rs.getString("municipio"));
                recomendacion.setResenia(rs.getString("resenia"));

                lista.add(recomendacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lista;
    }

    //METODO PARA ENCONTRAR(BUSCAR)
    public Recomendacion encontrar(Recomendacion recomendacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, recomendacion.getId_recomendacion());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_lugar = rs.getString("nombre_lugar");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String resenia = rs.getString("resenia");
            int cod_estado = rs.getInt("cod_estado");
            String nombre_estado = rs.getString("nombre_estado");

            recomendacion.setId_categorias(id_categorias);
            recomendacion.setNombre_cat(nombre_cat);
            recomendacion.setNombre_lugar(nombre_lugar);
            recomendacion.setCod_departamento(cod_departamento);
            recomendacion.setNombre_departamento(nombre_departamento);
            recomendacion.setMunicipio(municipio);
            recomendacion.setResenia(resenia);
            recomendacion.setCod_estado(cod_estado);
            recomendacion.setNombre_estado(nombre_estado);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR ENCONTRAR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return recomendacion;
    }

    //METODO PARA INSERTAR
    public int insertar(Recomendacion recomendacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, recomendacion.getId_categorias());
            stmt.setString(2, recomendacion.getNombre_lugar());
            stmt.setInt(3, recomendacion.getCod_departamento());
            stmt.setString(4, recomendacion.getMunicipio());
            stmt.setString(5, recomendacion.getResenia());
            stmt.setInt(6, recomendacion.getCod_estado());

            rows = stmt.executeUpdate();
            System.out.println("LLEGAMOS AL INSERT");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("NO LLEGAMOS AL INSERT");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //METODO PARA ACTUALIZAR
    public int actualizar(Recomendacion recomendacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

             stmt.setInt(1, recomendacion.getId_categorias());
            stmt.setString(2, recomendacion.getNombre_lugar());
            stmt.setInt(3, recomendacion.getCod_departamento());
            stmt.setString(4, recomendacion.getMunicipio());
            stmt.setString(5, recomendacion.getResenia());
            stmt.setInt(6, recomendacion.getCod_estado());
            stmt.setInt(7, recomendacion.getId_recomendacion());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //METODO PARA ELIMINAR
    public int eliminar(Recomendacion recomendacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, recomendacion.getId_recomendacion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
