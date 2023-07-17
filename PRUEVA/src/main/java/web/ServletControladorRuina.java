package web;

import datos.RuinaDaoJDBC;
import dominio.Ruina;
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
@WebServlet("/ServletControladorRuina")
public class ServletControladorRuina extends HttpServlet{
    
    RuinaDaoJDBC daoRuina = new RuinaDaoJDBC();
    Ruina ruina = new Ruina();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarRuina(request, response);
                    break;
                case "eliminar":
                    this.eliminarRuina(request, response);
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
        List<Ruina> lista = daoRuina.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("playas.jsp").forward(request, response);
        response.sendRedirect("ruinas.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ruina> listaRuina = daoRuina.listar();
        request.setAttribute("listaRuina", listaRuina);
        request.getRequestDispatcher("ruinasListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarRuina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruinas = Integer.parseInt(request.getParameter("id_ruinas"));
        Ruina ruina = new RuinaDaoJDBC().encontrar(new Ruina(id_ruinas));
        request.setAttribute("ruina", ruina);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/ruinas/editarRuina.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarRuina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruinas = Integer.parseInt(request.getParameter("id_ruinas"));
        Ruina ruina = new Ruina(id_ruinas);
        int registrosmodificados = new RuinaDaoJDBC().eliminar(ruina);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarRuinas(request, response);
                    break;
                case "modificar":
                    this.modificarRuinas(request, response);
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
    public void insertarRuinas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=6;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre_ruina = request.getParameter("nombre_ruina");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        ruina.setId_categorias(id_categorias);
        ruina.setNombre_ruina(nombre_ruina);
        ruina.setCod_departamento(cod_departamento);
        ruina.setMunicipio(municipio);
        ruina.setDireccion(direccion);
        ruina.setDescripcion(descripcion);
        ruina.setFoto(foto);

        daoRuina.insertar(ruina);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarRuinas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_ruinas = Integer.parseInt(request.getParameter("id_ruinas"));
        int id_categorias=6;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre_ruina = request.getParameter("nombre_ruina");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        ruina.setId_categorias(id_categorias);
        ruina.setNombre_ruina(nombre_ruina);
        ruina.setCod_departamento(cod_departamento);
        ruina.setMunicipio(municipio);
        ruina.setDireccion(direccion);
        ruina.setDescripcion(descripcion);
        ruina.setFoto(foto);
        ruina.setId_ruinas(id_ruinas);

        daoRuina.actualizar(ruina);
        this.accionDefault(request, response);
    }

    
}
