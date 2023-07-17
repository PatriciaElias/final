package web;

import datos.CatedralDaoJDBC;
import dominio.Catedral;
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
@WebServlet("/ServletControladorCatedral")
public class ServletControladorCatedral extends HttpServlet {

    CatedralDaoJDBC daoCatedral = new CatedralDaoJDBC();
    Catedral catedral = new Catedral();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCatedral(request, response);
                    break;
                case "eliminar":
                    this.eliminarCatedral(request, response);
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
        List<Catedral> lista = daoCatedral.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("catedrales.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Catedral> listaCatedral = daoCatedral.listar();
        request.setAttribute("listaCatedral", listaCatedral);
        request.getRequestDispatcher("catedralesListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarCatedral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_catedrales = Integer.parseInt(request.getParameter("id_catedrales"));
        Catedral catedral = new CatedralDaoJDBC().encontrar(new Catedral(id_catedrales));
        request.setAttribute("catedral", catedral);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/catedrales/editarCatedral.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarCatedral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_catedrales = Integer.parseInt(request.getParameter("id_catedrales"));
        Catedral catedral = new Catedral(id_catedrales);
        int registrosmodificados = new CatedralDaoJDBC().eliminar(catedral);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCatedral(request, response);
                    break;
                case "modificar":
                    this.modificarCatedral(request, response);
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
    public void insertarCatedral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=4;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre_catedral = request.getParameter("nombre_catedral");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        catedral.setId_categorias(id_categorias);
        catedral.setNombre(nombre_catedral);
        catedral.setCod_departamento(cod_departamento);
        catedral.setMunicipio(municipio);
        catedral.setDireccion(direccion);
        catedral.setDescripcion(descripcion);
        catedral.setFoto(foto);

        daoCatedral.insertar(catedral);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarCatedral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_catedrales = Integer.parseInt(request.getParameter("id_catedrales"));
        int id_categorias=4;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre_catedral = request.getParameter("nombre");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        catedral.setId_categorias(id_categorias);
        catedral.setNombre(nombre_catedral);
        catedral.setCod_departamento(cod_departamento);
        catedral.setMunicipio(municipio);
        catedral.setDireccion(direccion);
        catedral.setDescripcion(descripcion);
        catedral.setFoto(foto);
        catedral.setId_catedrales(id_catedrales);

        daoCatedral.actualizar(catedral);
        this.accionDefault(request, response);
    }
}
