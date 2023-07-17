package web;

import datos.BalneariosDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGBalneario")
public class ControlerIMGBalneario extends HttpServlet {

    BalneariosDaoJDBC playa=new BalneariosDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_balnearios=Integer.parseInt(request.getParameter("id_balnearios"));
        playa.listarImg(id_balnearios,response);    
    }
}
