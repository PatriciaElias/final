package web;

import datos.CatedralDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGCatedral")
public class ControlerIMGCatedral extends HttpServlet {

    CatedralDaoJDBC catedral=new CatedralDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_catedrales=Integer.parseInt(request.getParameter("id_catedrales"));
        catedral.listarImg(id_catedrales,response);    
    }
}
