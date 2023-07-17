package datos;

import dominio.Lago;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class LagosDaoJDBC {

    private static final String SQL_CONSULTA = "SELECT  lagos.id_lagos,\n"
            + "		lagos.nombre_lago, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		lagos.municipio,\n"
            + "		lagos.direccion, \n"
            + "		lagos.descripcion, \n"
            + "		lagos.foto\n"
            + "FROM lagos inner join categorias_lugares\n"
            + "on lagos.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on lagos.cod_departamento=departamentos.cod_departamento order by  id_lagos asc";

    private static final String SQL_POR_ID = "SELECT lagos.id_lagos,\n"
            + "lagos.nombre_lago, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "lagos.municipio,\n"
            + "lagos.direccion, \n"
            + "lagos.descripcion, \n"
            + "lagos.foto\n"
            + "FROM lagos inner join categorias_lugares\n"
            + "on lagos.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on lagos.cod_departamento=departamentos.cod_departamento\n"
            + "where lagos.id_lagos = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO lagos (id_categorias, nombre_lago, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE lagos "
            + "SET id_categorias=?, nombre_lago=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_lagos=? ";

    private static final String SQL_BORRAR = "DELETE FROM lagos WHERE id_lagos=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Lago> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Lago lago = new Lago();
                lago.setId_lagos(rs.getInt("id_lagos"));
                lago.setId_categorias(rs.getInt("id_categorias"));
                lago.setNombre_lago(rs.getString("nombre_lago"));
                lago.setCod_departamento(rs.getInt("cod_departamento"));
                lago.setNombre_departamento(rs.getString("nombre_departamento"));
                lago.setMunicipio(rs.getString("municipio"));
                lago.setDireccion(rs.getString("direccion"));
                lago.setDescripcion(rs.getString("descripcion"));
                lago.setFoto(rs.getBinaryStream("foto"));

                lista.add(lago);
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

    public void listarImg(int id_lagos, HttpServletResponse response) {
        String sql = "SELECT * FROM lagos WHERE id_lagos = " + id_lagos;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                inputStream = rs.getBinaryStream("foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    //METODO PARA ENCONTRAR(BUSCAR)
    public Lago encontrar(Lago Lago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, Lago.getId_lagos());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_lago = rs.getString("nombre_lago");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            Lago.setId_categorias(id_categorias);
            Lago.setNombre_cat(nombre_cat);
            Lago.setNombre_lago(nombre_lago);
            Lago.setCod_departamento(cod_departamento);
            Lago.setNombre_departamento(nombre_departamento);
            Lago.setMunicipio(municipio);
            Lago.setDireccion(direccion);
            Lago.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return Lago;
    }

    //METODO PARA INSERTAR
    public int insertar(Lago lago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, lago.getId_categorias());
            stmt.setString(2, lago.getNombre_lago());
            stmt.setInt(3, lago.getCod_departamento());
            stmt.setString(4, lago.getMunicipio());
            stmt.setString(5, lago.getDireccion());
            stmt.setString(6, lago.getDescripcion());
            stmt.setBlob(7, lago.getFoto());

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
    public int actualizar(Lago lago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, lago.getId_categorias());
            stmt.setString(2, lago.getNombre_lago());
            stmt.setInt(3, lago.getCod_departamento());
            stmt.setString(4, lago.getMunicipio());
            stmt.setString(5, lago.getDireccion());
            stmt.setString(6, lago.getDescripcion());
            stmt.setBlob(7, lago.getFoto());
            stmt.setInt(8, lago.getId_lagos());

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
    public int eliminar(Lago lago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, lago.getId_lagos());
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
