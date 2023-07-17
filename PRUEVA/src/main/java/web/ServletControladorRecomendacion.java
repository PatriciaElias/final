package web;

import datos.RecomendacionDaoJDBC;
import dominio.Recomendacion;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
@WebServlet("/ServletControladorRecomendacion")
public class ServletControladorRecomendacion extends HttpServlet {

    RecomendacionDaoJDBC daoRecomendacion = new RecomendacionDaoJDBC();
    Recomendacion recomendacion = new Recomendacion();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarRecomendacion(request, response);
                    break;
                case "eliminar":
                    this.eliminarRecomendacion(request, response);
                    break;
                case "listarU":
                    this.accionListarUser(request, response);
                    break;
                default:
                    //pagina inicial
                    this.accionDefault(request, response);
            }
        } else {
            //Pagina inicial
            this.accionDefault(request, response);
        }
    }

    //ACCION QUE REALIZA POR DEFECTO AL CARGAR LA PAGINA
    protected void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recomendacion> lista = daoRecomendacion.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        response.sendRedirect("recomendaciones.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recomendacion> lista = daoRecomendacion.listarU();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        response.sendRedirect("recomendacionesListaU.jsp");
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarRecomendacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_recomendacion = Integer.parseInt(request.getParameter("id_recomendacion"));
        Recomendacion recomendacion = new RecomendacionDaoJDBC().encontrar(new Recomendacion(id_recomendacion));
        request.setAttribute("recomendacion", recomendacion);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/recomendaciones/editarRecomendacion.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarRecomendacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_recomendacion = Integer.parseInt(request.getParameter("id_recomendacion"));
        Recomendacion recomendacion = new Recomendacion(id_recomendacion);
        int registrosmodificados = new RecomendacionDaoJDBC().eliminar(recomendacion);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarRecomendacion(request, response);
                    break;
                case "modificar":
                    this.modificarRecomendacion(request, response);
                    break;
                default:
                    //pagina inicial
                    this.accionDefault(request, response);
            }
        } else {  //Pagina inicial
            this.accionDefault(request, response);
        }
    }

    //METODO QUE INSERTA DATOS
    public void insertarRecomendacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=Integer.parseInt(request.getParameter("id_categorias"));
        String nombre_lugar = request.getParameter("nombre_lugar");
        int cod_departamento= Integer.parseInt(request.getParameter("departamento"));
        String municipio = request.getParameter("municipio");
        String resenia = request.getParameter("resenia");        
        int cod_estado=2;

        recomendacion.setId_categorias(id_categorias);
        recomendacion.setNombre_lugar(nombre_lugar);
        recomendacion.setCod_departamento(cod_departamento);
        recomendacion.setMunicipio(municipio);
        recomendacion.setResenia(resenia);
        recomendacion.setCod_estado(cod_estado);

        daoRecomendacion.insertar(recomendacion);
        this.accionListarUser(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarRecomendacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id_recomendacion = Integer.parseInt(request.getParameter("id_recomendacion"));
        int id_categorias= Integer.parseInt(request.getParameter("id_categorias"));        
        String nombre_lugar = request.getParameter("nombre_lugar");        
        int cod_departamento=Integer.parseInt(request.getParameter("departamento"));        
        String municipio = request.getParameter("municipio");
        String resenia = request.getParameter("resenia");
        int cod_estado= Integer.parseInt(request.getParameter("estado"));

        recomendacion.setId_categorias(id_categorias);
        recomendacion.setNombre_lugar(nombre_lugar);
        recomendacion.setCod_departamento(cod_departamento);
        recomendacion.setMunicipio(municipio);
        recomendacion.setResenia(resenia);
        recomendacion.setCod_estado(cod_estado);
        recomendacion.setId_recomendacion(id_recomendacion);        

        daoRecomendacion.actualizar(recomendacion);
        this.accionDefault(request, response);
    }
}
