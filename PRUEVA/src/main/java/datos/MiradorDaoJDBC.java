package datos;

import dominio.Mirador;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class MiradorDaoJDBC {
    
    private static final String SQL_CONSULTA = "SELECT  miradores.id_mirador,\n"
            + "		miradores.nombre_mirador, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		miradores.municipio,\n"
            + "		miradores.direccion, \n"
            + "		miradores.descripcion, \n"
            + "		miradores.foto\n"
            + "FROM miradores inner join categorias_lugares\n"
            + "on miradores.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on miradores.cod_departamento=departamentos.cod_departamento order by  id_mirador asc";

    private static final String SQL_POR_ID = "SELECT miradores.id_mirador,\n"
            + "miradores.nombre_mirador, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "miradores.municipio,\n"
            + "miradores.direccion, \n"
            + "miradores.descripcion, \n"
            + "miradores.foto\n"
            + "FROM miradores inner join categorias_lugares\n"
            + "on miradores.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on miradores.cod_departamento=departamentos.cod_departamento\n"
            + "where miradores.id_mirador = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO miradores (id_categorias, nombre_mirador, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE miradores "
            + "SET id_categorias=?, nombre_mirador=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_mirador=? ";

    private static final String SQL_BORRAR = "DELETE FROM miradores WHERE id_mirador=? ";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Mirador> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Mirador mirador = new Mirador();
                mirador.setId_mirador(rs.getInt("id_mirador"));
                mirador.setId_categorias(rs.getInt("id_categorias"));
                mirador.setNombre_mirador(rs.getString("nombre_mirador"));
                mirador.setCod_departamento(rs.getInt("cod_departamento"));
                mirador.setNombre_departamento(rs.getString("nombre_departamento"));
                mirador.setMunicipio(rs.getString("municipio"));
                mirador.setDireccion(rs.getString("direccion"));
                mirador.setDescripcion(rs.getString("descripcion"));
                mirador.setFoto(rs.getBinaryStream("foto"));

                lista.add(mirador);
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

    public void listarImg(int id_mirador, HttpServletResponse response) {
        String sql = "SELECT * FROM miradores WHERE id_mirador = " + id_mirador;
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
    public Mirador encontrar(Mirador mirador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, mirador.getId_mirador());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_mirador = rs.getString("nombre_mirador");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            mirador.setId_categorias(id_categorias);
            mirador.setNombre_cat(nombre_cat);
            mirador.setNombre_mirador(nombre_mirador);
            mirador.setCod_departamento(cod_departamento);
            mirador.setNombre_departamento(nombre_departamento);
            mirador.setMunicipio(municipio);
            mirador.setDireccion(direccion);
            mirador.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return mirador;
    }

    //METODO PARA INSERTAR
    public int insertar(Mirador mirador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, mirador.getId_categorias());
            stmt.setString(2, mirador.getNombre_mirador());
            stmt.setInt(3, mirador.getCod_departamento());
            stmt.setString(4, mirador.getMunicipio());
            stmt.setString(5, mirador.getDireccion());
            stmt.setString(6, mirador.getDescripcion());
            stmt.setBlob(7, mirador.getFoto());

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
    public int actualizar(Mirador mirador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, mirador.getId_categorias());
            stmt.setString(2, mirador.getNombre_mirador());
            stmt.setInt(3, mirador.getCod_departamento());
            stmt.setString(4, mirador.getMunicipio());
            stmt.setString(5, mirador.getDireccion());
            stmt.setString(6, mirador.getDescripcion());
            stmt.setBlob(7, mirador.getFoto());
            stmt.setInt(8, mirador.getId_mirador());

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
    public int eliminar(Mirador mirador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, mirador.getId_mirador());
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
