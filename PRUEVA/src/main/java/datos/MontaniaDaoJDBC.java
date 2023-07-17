package datos;

import dominio.Montania;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class MontaniaDaoJDBC {

    private static final String SQL_CONSULTA = "SELECT  montanias.id_montanias,\n"
            + "		montanias.nombre, \n"
            + "		categorias_lugares.id_categorias,\n"
            + "		categorias_lugares.nombre_cat, \n"
            + "		departamentos.cod_departamento,\n"
            + "		departamentos.nombre_departamento, \n"
            + "		montanias.municipio,\n"
            + "		montanias.direccion, \n"
            + "		montanias.descripcion, \n"
            + "		montanias.foto\n"
            + "FROM montanias inner join categorias_lugares\n"
            + "on montanias.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on montanias.cod_departamento=departamentos.cod_departamento order by  id_montanias asc";

    private static final String SQL_POR_ID = "SELECT montanias.id_montanias,\n"
            + "montanias.nombre, \n"
            + "categorias_lugares.id_categorias,\n"
            + "categorias_lugares.nombre_cat, \n"
            + "departamentos.cod_departamento,\n"
            + "departamentos.nombre_departamento, \n"
            + "montanias.municipio,\n"
            + "montanias.direccion, \n"
            + "montanias.descripcion, \n"
            + "montanias.foto\n"
            + "FROM montanias inner join categorias_lugares\n"
            + "on montanias.id_categorias=categorias_lugares.id_categorias\n"
            + "inner join departamentos on montanias.cod_departamento=departamentos.cod_departamento\n"
            + "where montanias.id_montanias = ?;";

    private static final String SQL_INSERTAR = "INSERT INTO montanias (id_categorias, nombre, cod_departamento, municipio, direccion,descripcion,foto)"
            + "VALUES( ?, ?, ? ,?, ?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE montanias \n"
            + "SET id_categorias=?, nombre=?, cod_departamento=?, municipio=?, direccion=?, descripcion=?, foto=? WHERE id_montanias=? ";

    private static final String SQL_BORRAR = "DELETE FROM montanias WHERE id_montanias=?";

    //METODO PARA LISTAR(MOSTRAR)
    public List listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Montania> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONSULTA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Montania montania = new Montania();
                montania.setId_montanias(rs.getInt("id_montanias"));
                montania.setId_categorias(rs.getInt("id_categorias"));
                montania.setNombre(rs.getString("nombre"));
                montania.setCod_departamento(rs.getInt("cod_departamento"));
                montania.setNombre_departamento(rs.getString("nombre_departamento"));
                montania.setMunicipio(rs.getString("municipio"));
                montania.setDireccion(rs.getString("direccion"));
                montania.setDescripcion(rs.getString("descripcion"));
                montania.setFoto(rs.getBinaryStream("foto"));

                lista.add(montania);
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

    public void listarImg(int id_montanias, HttpServletResponse response) {
        String sql = "SELECT * FROM montanias WHERE id_montanias = " + id_montanias;
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
    public Montania encontrar(Montania montania) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_POR_ID);
            stmt.setInt(1, montania.getId_montanias());
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

            montania.setId_categorias(id_categorias);
            montania.setNombre_cat(nombre_cat);
            montania.setNombre(nombre);
            montania.setCod_departamento(cod_departamento);
            montania.setNombre_departamento(nombre_departamento);
            montania.setMunicipio(municipio);
            montania.setDireccion(direccion);
            montania.setDescripcion(descripcion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return montania;
    }

    //METODO PARA INSERTAR
    public int insertar(Montania montania) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, montania.getId_categorias());
            stmt.setString(2, montania.getNombre());
            stmt.setInt(3, montania.getCod_departamento());
            stmt.setString(4, montania.getMunicipio());
            stmt.setString(5, montania.getDireccion());
            stmt.setString(6, montania.getDescripcion());
            stmt.setBlob(7, montania.getFoto());

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
    public int actualizar(Montania montania) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setInt(1, montania.getId_categorias());
            stmt.setString(2, montania.getNombre());
            stmt.setInt(3, montania.getCod_departamento());
            stmt.setString(4, montania.getMunicipio());
            stmt.setString(5, montania.getDireccion());
            stmt.setString(6, montania.getDescripcion());
            stmt.setBlob(7, montania.getFoto());
            stmt.setInt(8, montania.getId_montanias());

            rows = stmt.executeUpdate();
            System.out.println("LLEGAMOS AL UPDATE");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ERROR EN EL UPDATE");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //METODO PARA ELIMINAR
    public int eliminar(Montania montania) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR);
            stmt.setInt(1, montania.getId_montanias());
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
