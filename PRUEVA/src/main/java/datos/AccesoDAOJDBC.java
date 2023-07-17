package datos;

import dominio.Acceso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoDAOJDBC {

    private static final String SQL_USER = "SELECT id_rol FROM usuario WHERE username=? and pass=?";

    public AccesoDAOJDBC() {
    }

    public int validar(Acceso acceso) {
        int nivel = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_USER);
            stmt.setString(1, acceso.getUsername());
            stmt.setString(2, acceso.getPass());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                nivel =rs.getInt(1);//EL 1 DEL INDICE DE LA CONSULTA
            }

            System.out.println("NO HAY PROBLEMA EN EL VALIDAR");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ENTRAMOS EN EL ERROR DEL LOGIN");
            return nivel;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return nivel;
    }
}
