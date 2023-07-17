package web;

import datos.RutaDaoJDBC;
import dominio.Ruta;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/ServletControladorRuta")
public class ServletControladorRuta extends HttpServlet {

    RutaDaoJDBC daoRuta = new RutaDaoJDBC();
    Ruta ruta = new Ruta();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarRuta(request, response);
                    break;
                case "eliminar":
                    this.eliminarRuta(request, response);
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
        List<Ruta> lista = daoRuta.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("rutas.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ruta> listaRuta = daoRuta.listar();
        request.setAttribute("listaRuta", listaRuta);
        request.getRequestDispatcher("rutasListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarRuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruta = Integer.parseInt(request.getParameter("id_ruta"));
        Ruta ruta = new RutaDaoJDBC().encontrar(new Ruta(id_ruta));
        request.setAttribute("ruta", ruta);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/rutas/editarRuta.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarRuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruta = Integer.parseInt(request.getParameter("id_ruta"));
        Ruta ruta = new Ruta(id_ruta);
        int registrosmodificados = new RutaDaoJDBC().eliminar(ruta);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarRuta(request, response);
                    break;
                case "modificar":
                    this.modificarRuta(request, response);
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
    public void insertarRuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=5;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre = request.getParameter("nombre");
        String duracion = request.getParameter("duracion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        ruta.setId_categorias(id_categorias);
        ruta.setNombre(nombre);
        ruta.setDuracion(duracion);
        ruta.setDescripcion(descripcion);
        ruta.setFoto(foto);

        daoRuta.insertar(ruta);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarRuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruta = Integer.parseInt(request.getParameter("id_ruta"));
        int id_categorias=5;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre= request.getParameter("nombre");
        String duracion = request.getParameter("duracion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        ruta.setId_categorias(id_categorias);
        ruta.setNombre(nombre);
        ruta.setDuracion(duracion);
        ruta.setDescripcion(descripcion);
        ruta.setFoto(foto);
        ruta.setId_ruta(id_ruta);

        daoRuta.actualizar(ruta);
        this.accionDefault(request, response);
    }
}
