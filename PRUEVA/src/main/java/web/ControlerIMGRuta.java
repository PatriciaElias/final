package web;

import datos.RutaDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGRuta")
public class ControlerIMGRuta extends HttpServlet {

    RutaDaoJDBC ruta=new RutaDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruta=Integer.parseInt(request.getParameter("id_ruta"));
        ruta.listarImg(id_ruta,response);    
    }
}
