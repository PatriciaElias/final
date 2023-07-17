package web;

import datos.LagosDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGLago")
public class ControlerIMGLago extends HttpServlet {

    LagosDaoJDBC lago=new LagosDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_lagos=Integer.parseInt(request.getParameter("id_lagos"));
        lago.listarImg(id_lagos,response);    
    }
}
