package web;

import datos.LagosDaoJDBC;
import dominio.Lago;
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
@WebServlet("/ServletControladorLago")
public class ServletControladorLago extends HttpServlet {

    LagosDaoJDBC daoLago = new LagosDaoJDBC();
    Lago lago = new Lago();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarLago(request, response);
                    break;
                case "eliminar":
                    this.eliminarLago(request, response);
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
        List<Lago> lista = daoLago.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("lagos.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lago> listaLago = daoLago.listar();
        request.setAttribute("listaLago", listaLago);
        request.getRequestDispatcher("lagosListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE Lago Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarLago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_lagos = Integer.parseInt(request.getParameter("id_lagos"));
        Lago lago = new LagosDaoJDBC().encontrar(new Lago(id_lagos));
        request.setAttribute("lago", lago);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/Lagos/editarLagos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarLago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_lagos = Integer.parseInt(request.getParameter("id_lagos"));
        Lago lago = new Lago(id_lagos);
        int registrosmodificados = new LagosDaoJDBC().eliminar(lago);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarLago(request, response);
                    break;
                case "modificar":
                    this.modificarLago(request, response);
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
    public void insertarLago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=3;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre_lago = request.getParameter("nombre_lago");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        lago.setId_categorias(id_categorias);
        lago.setNombre_lago(nombre_lago);
        lago.setCod_departamento(cod_departamento);
        lago.setMunicipio(municipio);
        lago.setDireccion(direccion);
        lago.setDescripcion(descripcion);
        lago.setFoto(foto);

        daoLago.insertar(lago);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarLago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_lagos = Integer.parseInt(request.getParameter("id_lagos"));
        int id_categorias=3;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre_lago = request.getParameter("nombre_lago");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        lago.setId_categorias(id_categorias);
        lago.setNombre_lago(nombre_lago);
        lago.setCod_departamento(cod_departamento);
        lago.setMunicipio(municipio);
        lago.setDireccion(direccion);
        lago.setDescripcion(descripcion);
        lago.setFoto(foto);
        lago.setId_lagos(id_lagos);

        daoLago.actualizar(lago);
        this.accionDefault(request, response);
    }
}
