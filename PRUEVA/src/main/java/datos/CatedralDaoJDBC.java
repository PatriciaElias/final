package datos;

import dominio.Catedral;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class CatedralDaoJDBC {

    private static final String SQL_CONSULTA = "SELECT  catedrales.id_catedrales,\n"
            + "		catedrales.nombre, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		catedrales.municipio,\n"
            + "		catedrales.direccion, \n"
            + "		catedrales.descripcion, \n"
            + "		catedrales.foto\n"
            + "FROM catedrales inner join categorias_lugares\n"
            + "on catedrales.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on catedrales.cod_departamento=departamentos.cod_departamento order by  id_catedrales asc";

    private static final String SQL_POR_ID = "SELECT catedrales.id_catedrales,\n"
            + "catedrales.nombre, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "catedrales.municipio,\n"
            + "catedrales.direccion, \n"
            + "catedrales.descripcion, \n"
            + "catedrales.foto\n"
            + "FROM catedrales inner join categorias_lugares\n"
            + "on catedrales.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on catedrales.cod_departamento=departamentos.cod_departamento\n"
            + "where catedrales.id_catedrales = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO catedrales (id_categorias, nombre, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE catedrales "
            + "SET id_categorias=?, nombre=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_catedrales=? ";

    private static final String SQL_BORRAR = "DELETE FROM catedrales WHERE id_catedrales=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Catedral> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Catedral catedral = new Catedral();
                catedral.setId_catedrales(rs.getInt("id_catedrales"));
                catedral.setId_categorias(rs.getInt("id_categorias"));
                catedral.setNombre(rs.getString("nombre"));
                catedral.setCod_departamento(rs.getInt("cod_departamento"));
                catedral.setNombre_departamento(rs.getString("nombre_departamento"));
                catedral.setMunicipio(rs.getString("municipio"));
                catedral.setDireccion(rs.getString("direccion"));
                catedral.setDescripcion(rs.getString("descripcion"));
                catedral.setFoto(rs.getBinaryStream("foto"));

                lista.add(catedral);
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

    public void listarImg(int id_catedrales, HttpServletResponse response) {
        String sql = "SELECT * FROM catedrales WHERE id_catedrales = " + id_catedrales;
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
    public Catedral encontrar(Catedral catedral) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, catedral.getId_catedrales());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_catedral = rs.getString("nombre");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            catedral.setId_categorias(id_categorias);
            catedral.setNombre_cat(nombre_cat);
            catedral.setNombre(nombre_catedral);
            catedral.setCod_departamento(cod_departamento);
            catedral.setNombre_departamento(nombre_departamento);
            catedral.setMunicipio(municipio);
            catedral.setDireccion(direccion);
            catedral.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return catedral;
    }

    //METODO PARA INSERTAR
    public int insertar(Catedral catedral) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, catedral.getId_categorias());
            stmt.setString(2, catedral.getNombre());
            stmt.setInt(3, catedral.getCod_departamento());
            stmt.setString(4, catedral.getMunicipio());
            stmt.setString(5, catedral.getDireccion());
            stmt.setString(6, catedral.getDescripcion());
            stmt.setBlob(7, catedral.getFoto());

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
    public int actualizar(Catedral catedral) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, catedral.getId_categorias());
            stmt.setString(2, catedral.getNombre());
            stmt.setInt(3, catedral.getCod_departamento());
            stmt.setString(4, catedral.getMunicipio());
            stmt.setString(5, catedral.getDireccion());
            stmt.setString(6, catedral.getDescripcion());
            stmt.setBlob(7, catedral.getFoto());
            stmt.setInt(8, catedral.getId_catedrales());

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
    public int eliminar(Catedral catedral) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, catedral.getId_catedrales());
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
