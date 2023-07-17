package web;

import datos.MiradorDaoJDBC;
import dominio.Mirador;
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
@WebServlet("/ServletControladorMirador")
public class ServletControladorMirador extends HttpServlet {
    
    MiradorDaoJDBC daoMirador = new MiradorDaoJDBC();
    Mirador mirador = new Mirador();

    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarMirador(request, response);
                    break;
                case "eliminar":
                    this.eliminarMirador(request, response);
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
        List<Mirador> lista = daoMirador.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("miradores.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Mirador> listaMirador = daoMirador.listar();
        request.setAttribute("listaMirador", listaMirador);
        request.getRequestDispatcher("miradoresListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarMirador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_mirador = Integer.parseInt(request.getParameter("id_mirador"));
        Mirador mirador = new MiradorDaoJDBC().encontrar(new Mirador(id_mirador));
        request.setAttribute("mirador", mirador);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/miradores/editarMirador.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarMirador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_mirador = Integer.parseInt(request.getParameter("id_mirador"));
        Mirador mirador = new Mirador(id_mirador);
        int registrosmodificados = new MiradorDaoJDBC().eliminar(mirador);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarMirador(request, response);
                    break;
                case "modificar":
                    this.modificarMirador(request, response);
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
    public void insertarMirador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=9;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre_mirador = request.getParameter("nombre_mirador");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        mirador.setId_categorias(id_categorias);
        mirador.setNombre_mirador(nombre_mirador);
        mirador.setCod_departamento(cod_departamento);
        mirador.setMunicipio(municipio);
        mirador.setDireccion(direccion);
        mirador.setDescripcion(descripcion);
        mirador.setFoto(foto);

        daoMirador.insertar(mirador);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarMirador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_mirador= Integer.parseInt(request.getParameter("id_mirador"));
        int id_categorias=9;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre_mirador = request.getParameter("nombre_mirador");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        mirador.setId_categorias(id_categorias);
        mirador.setNombre_mirador(nombre_mirador);
        mirador.setCod_departamento(cod_departamento);
        mirador.setMunicipio(municipio);
        mirador.setDireccion(direccion);
        mirador.setDescripcion(descripcion);
        mirador.setFoto(foto);
        mirador.setId_mirador(id_mirador);

        daoMirador.actualizar(mirador);
        this.accionDefault(request, response);
    }
}
