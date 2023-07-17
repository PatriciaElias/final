package web;

import datos.AccesoDAOJDBC;
import dominio.Acceso;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletControladorAcceso")
public class ServletControladorAcceso extends HttpServlet {

    Acceso acceso = new Acceso();
    AccesoDAOJDBC daoAcceso = new AccesoDAOJDBC();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nivel = 0;
        RequestDispatcher rd;

        if (request.getParameter("btnIniciar") != null) {

            String nombre = request.getParameter("usuario");
            String contra = request.getParameter("password");

            System.out.println("USUARIO=" + nombre);
            System.out.println("CONTRASEÑA=" + contra);

            acceso.setUsername(nombre);
            acceso.setPass(contra);
            nivel = daoAcceso.validar(acceso);

            request.setAttribute("nivel", nivel);
            request.setAttribute("nombre", nombre);

            rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
