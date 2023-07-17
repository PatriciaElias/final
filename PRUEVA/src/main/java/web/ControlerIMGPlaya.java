package web;

import datos.PlayaDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGPlaya")
public class ControlerIMGPlaya extends HttpServlet {

    PlayaDaoJDBC playa=new PlayaDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_playas=Integer.parseInt(request.getParameter("id_playas"));
        playa.listarImg(id_playas,response);    
    }
}
