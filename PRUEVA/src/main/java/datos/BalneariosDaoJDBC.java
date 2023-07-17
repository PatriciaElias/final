package datos;

import dominio.Balneario;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class BalneariosDaoJDBC {

    private static final String SQL_CONSULTA = "SELECT  balnearios.id_balnearios,\n"
            + "		balnearios.nombre, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		balnearios.municipio,\n"
            + "		balnearios.direccion, \n"
            + "		balnearios.descripcion, \n"
            + "		balnearios.foto\n"
            + "FROM balnearios inner join categorias_lugares\n"
            + "on balnearios.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on balnearios.cod_departamento=departamentos.cod_departamento order by  id_balnearios asc";

    private static final String SQL_POR_ID = "SELECT balnearios.id_balnearios,\n"
            + "balnearios.nombre, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "balnearios.municipio,\n"
            + "balnearios.direccion, \n"
            + "balnearios.descripcion, \n"
            + "balnearios.foto\n"
            + "FROM balnearios inner join categorias_lugares\n"
            + "on balnearios.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on balnearios.cod_departamento=departamentos.cod_departamento\n"
            + "where balnearios.id_balnearios = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO balnearios (id_categorias, nombre, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE balnearios "
            + "SET id_categorias=?, nombre=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_balnearios=? ";

    private static final String SQL_BORRAR = "DELETE FROM balnearios WHERE id_balnearios=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Balneario> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Balneario balneario = new Balneario();
                balneario.setId_balnearios(rs.getInt("id_balnearios"));
                balneario.setId_categorias(rs.getInt("id_categorias"));
                balneario.setNombre(rs.getString("nombre"));
                balneario.setCod_departamento(rs.getInt("cod_departamento"));
                balneario.setNombre_departamento(rs.getString("nombre_departamento"));
                balneario.setMunicipio(rs.getString("municipio"));
                balneario.setDireccion(rs.getString("direccion"));
                balneario.setDescripcion(rs.getString("descripcion"));
                balneario.setFoto(rs.getBinaryStream("foto"));

                lista.add(balneario);
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

    public void listarImg(int id_balnearios, HttpServletResponse response) {
        String sql = "SELECT * FROM balnearios WHERE id_balnearios = " + id_balnearios;
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
    public Balneario encontrar(Balneario balneario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, balneario.getId_balnearios());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre = rs.getString("nombre");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            balneario.setId_categorias(id_categorias);
            balneario.setNombre_cat(nombre_cat);
            balneario.setNombre(nombre);
            balneario.setCod_departamento(cod_departamento);
            balneario.setNombre_departamento(nombre_departamento);
            balneario.setMunicipio(municipio);
            balneario.setDireccion(direccion);
            balneario.setDescripcion(descripcion);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return balneario;
    }

    //METODO PARA INSERTAR
    public int insertar(Balneario balneario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, balneario.getId_categorias());
            stmt.setString(2, balneario.getNombre());
            stmt.setInt(3, balneario.getCod_departamento());
            stmt.setString(4, balneario.getMunicipio());
            stmt.setString(5, balneario.getDireccion());
            stmt.setString(6, balneario.getDescripcion());
            stmt.setBlob(7, balneario.getFoto());

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
    public int actualizar(Balneario balneario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, balneario.getId_categorias());
            stmt.setString(2, balneario.getNombre());
            stmt.setInt(3, balneario.getCod_departamento());
            stmt.setString(4, balneario.getMunicipio());
            stmt.setString(5, balneario.getDireccion());
            stmt.setString(6, balneario.getDescripcion());
            stmt.setBlob(7, balneario.getFoto());
            stmt.setInt(8, balneario.getId_balnearios());

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
    public int eliminar(Balneario balneario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, balneario.getId_balnearios());
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

