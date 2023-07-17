package web;

import datos.VolcanDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGVolcan")
public class ControlerIMGVolcan extends HttpServlet {
    
    VolcanDaoJDBC volcan=new VolcanDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_volcanes=Integer.parseInt(request.getParameter("id_volcanes"));
        volcan.listarImg(id_volcanes,response);    
    }
}
