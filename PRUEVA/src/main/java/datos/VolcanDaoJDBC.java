package datos;

import dominio.Volcan;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class VolcanDaoJDBC {
    
    private static final String SQL_CONSULTA = "SELECT  volcanes.id_volcanes,\n"
            + "		volcanes.nombre_volcan, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		volcanes.municipio,\n"
            + "		volcanes.direccion, \n"
            + "		volcanes.descripcion, \n"
            + "		volcanes.foto\n"
            + "FROM volcanes inner join categorias_lugares\n"
            + "on volcanes.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on volcanes.cod_departamento=departamentos.cod_departamento order by  id_volcanes asc";

    private static final String SQL_POR_ID = "SELECT volcanes.id_volcanes,\n"
            + "volcanes.nombre_volcan, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "volcanes.municipio,\n"
            + "volcanes.direccion, \n"
            + "volcanes.descripcion, \n"
            + "volcanes.foto\n"
            + "FROM volcanes inner join categorias_lugares\n"
            + "on volcanes.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on volcanes.cod_departamento=departamentos.cod_departamento\n"
            + "where volcanes.id_volcanes = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO volcanes (id_categorias, nombre_volcan, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE volcanes "
            + "SET id_categorias=?, nombre_volcan=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_volcanes=? ";

    private static final String SQL_BORRAR = "DELETE FROM volcanes WHERE id_volcanes=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Volcan> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Volcan volcan = new Volcan();
                volcan.setId_volcanes(rs.getInt("id_volcanes"));
                volcan.setId_categorias(rs.getInt("id_categorias"));
                volcan.setNombre_volcan(rs.getString("nombre_volcan"));
                volcan.setCod_departamento(rs.getInt("cod_departamento"));
                volcan.setNombre_departamento(rs.getString("nombre_departamento"));
                volcan.setMunicipio(rs.getString("municipio"));
                volcan.setDireccion(rs.getString("direccion"));
                volcan.setDescripcion(rs.getString("descripcion"));
                volcan.setFoto(rs.getBinaryStream("foto"));

                lista.add(volcan);
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

    public void listarImg(int id_volcanes, HttpServletResponse response) {
        String sql = "SELECT * FROM volcanes WHERE id_volcanes = " + id_volcanes;
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
    public Volcan encontrar(Volcan volcan) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, volcan.getId_volcanes());
            rs = stmt.executeQuery();

            rs.next();

            int id_categorias = rs.getInt("id_categorias");
            String nombre_cat = rs.getString("nombre_cat");
            String nombre_volcan = rs.getString("nombre_volcan");
            int cod_departamento = rs.getInt("cod_departamento");
            String nombre_departamento = rs.getString("nombre_departamento");
            String municipio = rs.getString("municipio");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");

            volcan.setId_categorias(id_categorias);
            volcan.setNombre_cat(nombre_cat);
            volcan.setNombre_volcan(nombre_volcan);
            volcan.setCod_departamento(cod_departamento);
            volcan.setNombre_departamento(nombre_departamento);
            volcan.setMunicipio(municipio);
            volcan.setDireccion(direccion);
            volcan.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return volcan;
    }

    //METODO PARA INSERTAR
    public int insertar(Volcan volcan) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, volcan.getId_categorias());
            stmt.setString(2, volcan.getNombre_volcan());
            stmt.setInt(3, volcan.getCod_departamento());
            stmt.setString(4, volcan.getMunicipio());
            stmt.setString(5, volcan.getDireccion());
            stmt.setString(6, volcan.getDescripcion());
            stmt.setBlob(7, volcan.getFoto());

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
    public int actualizar(Volcan volcan) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, volcan.getId_categorias());
            stmt.setString(2, volcan.getNombre_volcan());
            stmt.setInt(3, volcan.getCod_departamento());
            stmt.setString(4, volcan.getMunicipio());
            stmt.setString(5, volcan.getDireccion());
            stmt.setString(6, volcan.getDescripcion());
            stmt.setBlob(7, volcan.getFoto());
            stmt.setInt(8, volcan.getId_volcanes());

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
    public int eliminar(Volcan volcan) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, volcan.getId_volcanes());
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
