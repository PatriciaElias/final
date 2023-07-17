package web;

import datos.VolcanDaoJDBC;
import dominio.Volcan;
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
@WebServlet("/ServletControladorVolcan")
public class ServletControladorVolcan extends HttpServlet {
    
    VolcanDaoJDBC daoVolcan = new VolcanDaoJDBC();
    Volcan volcan = new Volcan();

    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarVolcan(request, response);
                    break;
                case "eliminar":
                    this.eliminarVolcan(request, response);
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
        List<Volcan> lista = daoVolcan.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("Volcanes.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Volcan> listaVolcan = daoVolcan.listar();
        request.setAttribute("listaVolcan", listaVolcan);
        request.getRequestDispatcher("VolcanesListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarVolcan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_volcanes = Integer.parseInt(request.getParameter("id_volcanes"));
        Volcan volcan = new VolcanDaoJDBC().encontrar(new Volcan(id_volcanes));
        request.setAttribute("volcan", volcan);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/volcanes/editarVolcan.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarVolcan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_volcanes = Integer.parseInt(request.getParameter("id_volcanes"));
        Volcan volcan = new Volcan(id_volcanes);
        int registrosmodificados = new VolcanDaoJDBC().eliminar(volcan);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarVolcan(request, response);
                    break;
                case "modificar":
                    this.modificarVolcan(request, response);
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
    public void insertarVolcan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=7;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre_volcan = request.getParameter("nombre_volcan");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        volcan.setId_categorias(id_categorias);
        volcan.setNombre_volcan(nombre_volcan);
        volcan.setCod_departamento(cod_departamento);
        volcan.setMunicipio(municipio);
        volcan.setDireccion(direccion);
        volcan.setDescripcion(descripcion);
        volcan.setFoto(foto);

        daoVolcan.insertar(volcan);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarVolcan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_volcanes = Integer.parseInt(request.getParameter("id_volcanes"));
        int id_categorias=7;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre_volcan = request.getParameter("nombre_volcan");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        volcan.setId_categorias(id_categorias);
        volcan.setNombre_volcan(nombre_volcan);
        volcan.setCod_departamento(cod_departamento);
        volcan.setMunicipio(municipio);
        volcan.setDireccion(direccion);
        volcan.setDescripcion(descripcion);
        volcan.setFoto(foto);
        volcan.setId_volcanes(id_volcanes);

        daoVolcan.actualizar(volcan);
        this.accionDefault(request, response);
    }
}
