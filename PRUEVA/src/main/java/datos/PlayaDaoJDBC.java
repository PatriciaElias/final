package datos;

import dominio.Playa;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class PlayaDaoJDBC {

    private static final String SQL_CONSULTA = "SELECT  playas.id_playas,\n"
            + "		playas.nombre_playa, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		playas.municipio,\n"
            + "		playas.direccion, \n"
            + "		playas.descripcion, \n"
            + "		playas.foto\n"
            + "FROM playas inner join categorias_lugares\n"
            + "on playas.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on playas.cod_departamento=departamentos.cod_departamento order by  id_playas asc";

    private static final String SQL_POR_ID = "SELECT playas.id_playas,\n"
            + "playas.nombre_playa, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "playas.municipio,\n"
            + "playas.direccion, \n"
            + "playas.descripcion, \n"
            + "playas.foto\n"
            + "FROM playas inner join categorias_lugares\n"
            + "on playas.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on playas.cod_departamento=departamentos.cod_departamento\n"
            + "where playas.id_playas = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO playas (id_categorias, nombre_playa, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE playas "
            + "SET id_categorias=?, nombre_playa=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_playas=? ";

    private static final String SQL_BORRAR = "DELETE FROM playas WHERE id_playas=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Playa> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Playa playa = new Playa();
                playa.setId_playas(rs.getInt("id_playas"));
                playa.setId_categorias(rs.getInt("id_categorias"));
                playa.setNombre_playa(rs.getString("nombre_playa"));
                playa.setCod_departamento(rs.getInt("cod_departamento"));
                playa.setNombre_departamento(rs.getString("nombre_departamento"));
                playa.setMunicipio(rs.getString("municipio"));
                playa.setDireccion(rs.getString("direccion"));
                playa.setDescripcion(rs.getString("descripcion"));
                playa.setFoto(rs.getBinaryStream("foto"));

                lista.add(playa);
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

    public void listarImg(int id_playas, HttpServletResponse response) {
        String sql = "SELECT * FROM playas WHERE id_playas = " + id_playas;
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
    public Playa encontrar(Playa playa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, playa.getId_playas());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_playa = rs.getString("nombre_playa");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            playa.setId_categorias(id_categorias);
            playa.setNombre_cat(nombre_cat);
            playa.setNombre_playa(nombre_playa);
            playa.setCod_departamento(cod_departamento);
            playa.setNombre_departamento(nombre_departamento);
            playa.setMunicipio(municipio);
            playa.setDireccion(direccion);
            playa.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return playa;
    }

    //METODO PARA INSERTAR
    public int insertar(Playa playa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, playa.getId_categorias());
            stmt.setString(2, playa.getNombre_playa());
            stmt.setInt(3, playa.getCod_departamento());
            stmt.setString(4, playa.getMunicipio());
            stmt.setString(5, playa.getDireccion());
            stmt.setString(6, playa.getDescripcion());
            stmt.setBlob(7, playa.getFoto());

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
    public int actualizar(Playa playa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, playa.getId_categorias());
            stmt.setString(2, playa.getNombre_playa());
            stmt.setInt(3, playa.getCod_departamento());
            stmt.setString(4, playa.getMunicipio());
            stmt.setString(5, playa.getDireccion());
            stmt.setString(6, playa.getDescripcion());
            stmt.setBlob(7, playa.getFoto());
            stmt.setInt(8, playa.getId_playas());

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
    public int eliminar(Playa playa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, playa.getId_playas());
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
