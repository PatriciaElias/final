package web;

import datos.BalneariosDaoJDBC;
import dominio.Balneario;
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
@WebServlet("/ServletControladorBalnearios")
public class ServletControladorBalnearios extends HttpServlet {

    BalneariosDaoJDBC daoBalneario = new BalneariosDaoJDBC();
    Balneario balneario = new Balneario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarBalneario(request, response);
                    break;
                case "eliminar":
                    this.eliminarBalneario(request, response);
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
        List<Balneario> lista = daoBalneario.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("balnearios.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Balneario> listaBalneario = daoBalneario.listar();
        request.setAttribute("listaBalneario", listaBalneario);
        request.getRequestDispatcher("balneariosListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarBalneario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_balnearios = Integer.parseInt(request.getParameter("id_balnearios"));
        Balneario balneario = new BalneariosDaoJDBC().encontrar(new Balneario(id_balnearios));
        request.setAttribute("balneario", balneario);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/balnearios/editarBalneario.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarBalneario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_balnearios = Integer.parseInt(request.getParameter("id_balnearios"));
        Balneario balneario = new Balneario(id_balnearios);
        int registrosmodificados = new BalneariosDaoJDBC().eliminar(balneario);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarBalneario(request, response);
                    break;
                case "modificar":
                    this.modificarBalneario(request, response);
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
    public void insertarBalneario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=2;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre = request.getParameter("nombre");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        balneario.setId_categorias(id_categorias);
        balneario.setNombre(nombre);
        balneario.setCod_departamento(cod_departamento);
        balneario.setMunicipio(municipio);
        balneario.setDireccion(direccion);
        balneario.setDescripcion(descripcion);
        balneario.setFoto(foto);

        daoBalneario.insertar(balneario);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarBalneario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_balnearios = Integer.parseInt(request.getParameter("id_balnearios"));
        int id_categorias=2;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre = request.getParameter("nombre_balneario");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        balneario.setId_categorias(id_categorias);
        balneario.setNombre(nombre);
        balneario.setCod_departamento(cod_departamento);
        balneario.setMunicipio(municipio);
        balneario.setDireccion(direccion);
        balneario.setDescripcion(descripcion);
        balneario.setFoto(foto);
        balneario.setId_balnearios(id_balnearios);

        daoBalneario.actualizar(balneario);
        this.accionDefault(request, response);
    }
}
