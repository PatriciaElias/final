
package datos;

import dominio.Ruina;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class RuinaDaoJDBC {
  
    
    private static final String SQL_CONSULTA = "SELECT  ruinas.id_ruinas,\n"
            + "		ruinas.nombre_ruina, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		ruinas.municipio,\n"
            + "		ruinas.direccion, \n"
            + "		ruinas.descripcion, \n"
            + "		ruinas.foto\n"
            + "FROM ruinas inner join categorias_lugares\n"
            + "on ruinas.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on ruinas.cod_departamento=departamentos.cod_departamento order by  id_ruinas asc";

    private static final String SQL_POR_ID = "SELECT ruinas.id_ruinas,\n"
            + "ruinas.nombre_ruina, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "ruinas.municipio,\n"
            + "ruinas.direccion, \n"
            + "ruinas.descripcion, \n"
            + "ruinas.foto\n"
            + "FROM ruinas inner join categorias_lugares\n"
            + "on ruinas.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on ruinas.cod_departamento=departamentos.cod_departamento\n"
            + "where ruinas.id_ruinas = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO ruinas (id_categorias, nombre_ruina, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE ruinas "
            + "SET id_categorias=?, nombre_ruina=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_ruinas=? ";

    private static final String SQL_BORRAR = "DELETE FROM ruinas WHERE id_ruinas=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Ruina> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ruina ruina = new Ruina();
                ruina.setId_ruinas(rs.getInt("id_ruinas"));
                ruina.setId_categorias(rs.getInt("id_categorias"));
                ruina.setNombre_ruina(rs.getString("nombre_ruina"));
                ruina.setCod_departamento(rs.getInt("cod_departamento"));
                ruina.setNombre_departamento(rs.getString("nombre_departamento"));
                ruina.setMunicipio(rs.getString("municipio"));
                ruina.setDireccion(rs.getString("direccion"));
                ruina.setDescripcion(rs.getString("descripcion"));
                ruina.setFoto(rs.getBinaryStream("foto"));

                lista.add(ruina);
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

    public void listarImg(int id_ruina, HttpServletResponse response) {
        String sql = "SELECT * FROM ruinas WHERE id_ruinas = " + id_ruina;
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
    public Ruina encontrar(Ruina ruina) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, ruina.getId_ruinas());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_ruina = rs.getString("nombre_ruina");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            ruina.setId_categorias(id_categorias);
            ruina.setNombre_cat(nombre_cat);
            ruina.setNombre_ruina(nombre_ruina);
            ruina.setCod_departamento(cod_departamento);
            ruina.setNombre_departamento(nombre_departamento);
            ruina.setMunicipio(municipio);
            ruina.setDireccion(direccion);
            ruina.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ruina;
    }

    //METODO PARA INSERTAR
    public int insertar(Ruina ruina) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, ruina.getId_categorias());
            stmt.setString(2, ruina.getNombre_ruina());
            stmt.setInt(3, ruina.getCod_departamento());
            stmt.setString(4, ruina.getMunicipio());
            stmt.setString(5, ruina.getDireccion());
            stmt.setString(6, ruina.getDescripcion());
            stmt.setBlob(7, ruina.getFoto());

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
    public int actualizar(Ruina ruina) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, ruina.getId_categorias());
            stmt.setString(2, ruina.getNombre_ruina());
            stmt.setInt(3, ruina.getCod_departamento());
            stmt.setString(4, ruina.getMunicipio());
            stmt.setString(5, ruina.getDireccion());
            stmt.setString(6, ruina.getDescripcion());
            stmt.setBlob(7, ruina.getFoto());
            stmt.setInt(8, ruina.getId_ruinas());

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
    public int eliminar(Ruina ruina) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, ruina.getId_ruinas());
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
