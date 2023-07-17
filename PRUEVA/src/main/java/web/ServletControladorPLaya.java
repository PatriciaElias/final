package web;

import datos.PlayaDaoJDBC;
import dominio.Playa;
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
@WebServlet("/ServletControladorPlaya")
public class ServletControladorPLaya extends HttpServlet {

    PlayaDaoJDBC daoPlaya = new PlayaDaoJDBC();
    Playa playa = new Playa();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarPlaya(request, response);
                    break;
                case "eliminar":
                    this.eliminarPlaya(request, response);
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
        List<Playa> lista = daoPlaya.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("playas.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Playa> listaPlaya = daoPlaya.listar();
        request.setAttribute("listaPlaya", listaPlaya);
        request.getRequestDispatcher("playasListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarPlaya(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_playas = Integer.parseInt(request.getParameter("id_playas"));
        Playa playa = new PlayaDaoJDBC().encontrar(new Playa(id_playas));
        request.setAttribute("playa", playa);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/playas/editarPlaya.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarPlaya(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_playas = Integer.parseInt(request.getParameter("id_playas"));
        Playa playa = new Playa(id_playas);
        int registrosmodificados = new PlayaDaoJDBC().eliminar(playa);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarPlaya(request, response);
                    break;
                case "modificar":
                    this.modificarPlaya(request, response);
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
    public void insertarPlaya(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=1;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre_playa = request.getParameter("nombre_playa");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        playa.setId_categorias(id_categorias);
        playa.setNombre_playa(nombre_playa);
        playa.setCod_departamento(cod_departamento);
        playa.setMunicipio(municipio);
        playa.setDireccion(direccion);
        playa.setDescripcion(descripcion);
        playa.setFoto(foto);

        daoPlaya.insertar(playa);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarPlaya(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_playas = Integer.parseInt(request.getParameter("id_playas"));
        int id_categorias=1;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre_playa = request.getParameter("nombre_playa");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        playa.setId_categorias(id_categorias);
        playa.setNombre_playa(nombre_playa);
        playa.setCod_departamento(cod_departamento);
        playa.setMunicipio(municipio);
        playa.setDireccion(direccion);
        playa.setDescripcion(descripcion);
        playa.setFoto(foto);
        playa.setId_playas(id_playas);

        daoPlaya.actualizar(playa);
        this.accionDefault(request, response);
    }
}
