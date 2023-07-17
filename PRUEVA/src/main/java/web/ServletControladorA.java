package web;

import datos.BalneariosDaoJDBC;
import datos.CatedralDaoJDBC;
import datos.LagosDaoJDBC;
import datos.MiradorDaoJDBC;
import datos.MontaniaDaoJDBC;
import datos.PlayaDaoJDBC;
import datos.RuinaDaoJDBC;
import datos.RutaDaoJDBC;
import datos.VolcanDaoJDBC;
import dominio.Balneario;
import dominio.Catedral;
import dominio.Lago;
import dominio.Mirador;
import dominio.Montania;
import dominio.Playa;
import dominio.Ruina;
import dominio.Ruta;
import dominio.Volcan;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/ServletControladorA")
public class ServletControladorA extends HttpServlet {

    PlayaDaoJDBC daoPlaya = new PlayaDaoJDBC();
    BalneariosDaoJDBC daoBalneario = new BalneariosDaoJDBC();
    LagosDaoJDBC daoLago = new LagosDaoJDBC();
    CatedralDaoJDBC daoCatedral = new CatedralDaoJDBC();
    RutaDaoJDBC daoRuta = new RutaDaoJDBC();
    RuinaDaoJDBC daoRuina = new RuinaDaoJDBC();
    VolcanDaoJDBC daoVolcan = new VolcanDaoJDBC();
    MontaniaDaoJDBC daoMontania = new MontaniaDaoJDBC();
    MiradorDaoJDBC daoMirador = new MiradorDaoJDBC();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "listarTodo":
                    this.listarTodo(request, response);
                    break;
                default:
                    //pagina inicial
                    this.listarTodo(request, response);
            }
        } else {
            //Pagina inicial
            this.listarTodo(request, response);
        }
    }

    protected void listarTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Playa> listaPlaya = daoPlaya.listar();
        request.setAttribute("listaPlaya", listaPlaya);
        
        List<Balneario> listaBalneario = daoBalneario.listar();
        request.setAttribute("listaBalneario", listaBalneario);
        
        List<Lago> listaLago = daoLago.listar();
        request.setAttribute("listaLago", listaLago);
        
        List<Catedral> listaCatedral = daoCatedral.listar();
        request.setAttribute("listaCatedral", listaCatedral);
        
        List<Ruta> listaRuta = daoRuta.listar();
        request.setAttribute("listaRuta", listaRuta);
        
        List<Ruina> listaRuina = daoRuina.listar();
        request.setAttribute("listaRuina", listaRuina);
        
        List<Volcan> listaVolcan = daoVolcan.listar();
        request.setAttribute("listaVolcan", listaVolcan);
        
        List<Mirador> listaMirador = daoMirador.listar();
        request.setAttribute("listaMirador", listaMirador);
        
        List<Montania> listaMontania = daoMontania.listar();
        request.setAttribute("listaMontania", listaMontania);
        
        request.getRequestDispatcher("listaTotal.jsp").forward(request, response);
    }

}
