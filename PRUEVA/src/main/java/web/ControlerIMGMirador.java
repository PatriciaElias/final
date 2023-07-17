package web;

import datos.MiradorDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGMirador")
public class ControlerIMGMirador extends HttpServlet {
    
    MiradorDaoJDBC mirador=new MiradorDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_mirador=Integer.parseInt(request.getParameter("id_mirador"));
        mirador.listarImg(id_mirador,response);    
    }
}
