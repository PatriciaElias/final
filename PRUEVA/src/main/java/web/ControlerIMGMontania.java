package web;

import datos.MontaniaDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControlerIMGMontania")
public class ControlerIMGMontania extends HttpServlet {

    MontaniaDaoJDBC montania=new MontaniaDaoJDBC(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_montanias=Integer.parseInt(request.getParameter("id_montanias"));
        montania.listarImg(id_montanias,response);    
    }
}
