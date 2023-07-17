package datos;

import dominio.Ruta;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class RutaDaoJDBC {

    private static final String SQL_CONSULTA = "SELECT * FROM rutas order by  id_ruta asc";

    private static final String SQL_POR_ID = "SELECT  rutas.id_ruta, \n" 
            +"		rutas.nombre,\n" 
            +"		categorias_lugares.id_categorias,\n" 
            +"		categorias_lugares.nombre_cat,\n" 
            +"		rutas.duracion, \n" 
            +"		rutas.descripcion, \n" 
            +"		rutas.foto\n" 
            +"		FROM rutas inner join categorias_lugares\n" 
            +"		on rutas.id_categorias=categorias_lugares.id_categorias\n" 
            +"		where rutas.id_ruta = ?";

    private static final String SQL_INSERTAR = "INSERT INTO rutas (id_categorias, nombre, duracion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?)";

    private static final String SQL_ACTUALIZAR = "UPDATE rutas "
            + "SET id_categorias=?, nombre=?, duracion=?, descripcion=?, foto=? WHERE id_ruta=? ";

    private static final String SQL_BORRAR = "DELETE FROM rutas WHERE id_ruta=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Ruta> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ruta ruta = new Ruta();
                ruta.setId_ruta(rs.getInt("id_ruta"));
                ruta.setId_categorias(rs.getInt("id_categorias"));
                ruta.setNombre(rs.getString("nombre"));
                ruta.setDuracion(rs.getString("duracion"));
                ruta.setDescripcion(rs.getString("descripcion"));
                ruta.setFoto(rs.getBinaryStream("foto"));
                lista.add(ruta);
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

    public void listarImg(int id_rutas, HttpServletResponse response) {
        String sql = "SELECT * FROM rutas WHERE id_ruta = " + id_rutas;
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
    public Ruta encontrar(Ruta ruta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, ruta.getId_ruta());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre = rs.getString("nombre");
            String duracion = rs.getString("duracion");
            String descripcion = rs.getString("descripcion");

            ruta.setId_categorias(id_categorias);
            ruta.setNombre_cat(nombre_cat);
            ruta.setNombre(nombre);
            ruta.setDuracion(duracion);
            ruta.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ruta;
    }

    //METODO PARA INSERTAR
    public int insertar(Ruta ruta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, ruta.getId_categorias());
            stmt.setString(2, ruta.getNombre());
            stmt.setString(3, ruta.getDuracion());
            stmt.setString(4, ruta.getDescripcion());
            stmt.setBlob(5, ruta.getFoto());

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
    public int actualizar(Ruta ruta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, ruta.getId_categorias());
            stmt.setString(2, ruta.getNombre());
            stmt.setString(3, ruta.getDuracion());
            stmt.setString(4, ruta.getDescripcion());
            stmt.setBlob(5, ruta.getFoto());
            stmt.setInt(6, ruta.getId_ruta());

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
    public int eliminar(Ruta ruta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, ruta.getId_ruta());
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
