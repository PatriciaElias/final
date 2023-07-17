package web;

import datos.RuinaDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGRuina")
public class ControlerIMGRuina extends HttpServlet {
    
    RuinaDaoJDBC ruina=new RuinaDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruinas=Integer.parseInt(request.getParameter("id_ruinas"));
        ruina.listarImg(id_ruinas,response);    
    }
}
