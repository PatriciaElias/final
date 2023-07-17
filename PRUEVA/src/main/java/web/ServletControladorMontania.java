package web;

import datos.MontaniaDaoJDBC;
import dominio.Montania;
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
@WebServlet("/ServletControladorMontania")
public class ServletControladorMontania extends HttpServlet {

    MontaniaDaoJDBC daoMontania = new MontaniaDaoJDBC();
    Montania montania = new Montania();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarMontania(request, response);
                    break;
                case "eliminar":
                    this.eliminarMontania(request, response);
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
        List<Montania> lista = daoMontania.listar();
        HttpSession sesion=request.getSession();
        sesion.setAttribute("lista", lista);
        //request.getRequestDispatcher("montanias.jsp").forward(request, response);
        response.sendRedirect("montanias.jsp");
    }
    
    protected void accionListarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Montania> listaMontania = daoMontania.listar();
        request.setAttribute("listaMontania", listaMontania);
        request.getRequestDispatcher("montaniasListaU.jsp").forward(request, response);
    }

    //DEL BOTON EDITAR OBTIENE EL CODIGO DE LA PLAYA Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void editarMontania(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_montanias = Integer.parseInt(request.getParameter("id_montanias"));
        Montania montania = new MontaniaDaoJDBC().encontrar(new Montania(id_montanias));
        request.setAttribute("montania", montania);
        //DEFINIR LA PAGINA JSP
        String jspEditar = "/WEB-INF/paginas/montanias/editarMontania.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //DEL BOTON ELIMINAR OBTIENE EL CODIGO DEL CLIENTE Y ENVIA LA INFO A LOS TXT DEL FORM
    protected void eliminarMontania(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_montanias = Integer.parseInt(request.getParameter("id_montanias"));
        Montania montania = new Montania(id_montanias);
        int registrosmodificados = new MontaniaDaoJDBC().eliminar(montania);
        System.out.print("registrosmodificados=" + registrosmodificados);
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarMontania(request, response);
                    break;
                case "modificar":
                    this.modificarMontania(request, response);
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
    public void insertarMontania(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_categorias=8;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)

        String nombre = request.getParameter("nombre");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");

        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        montania.setId_categorias(id_categorias);
        montania.setNombre(nombre);
        montania.setCod_departamento(cod_departamento);
        montania.setMunicipio(municipio);
        montania.setDireccion(direccion);
        montania.setDescripcion(descripcion);
        montania.setFoto(foto);

        daoMontania.insertar(montania);
        this.accionDefault(request, response);
    }

    //METODO QUE ACTUALIZAR DATOS
    public void modificarMontania(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_montanias = Integer.parseInt(request.getParameter("id_montanias"));
        int id_categorias=8;//EL VALOR CAMBIA SEGUN LA TABLA(la categoria se ingresesa automaticamente)
        
        String nombre = request.getParameter("nombre");
        
        int cod_departamento;
        cod_departamento = Integer.parseInt(request.getParameter("departamento"));
        
        String municipio = request.getParameter("municipio");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Part part = request.getPart("foto");
        InputStream foto = part.getInputStream();

        montania.setId_categorias(id_categorias);
        montania.setNombre(nombre);
        montania.setCod_departamento(cod_departamento);
        montania.setMunicipio(municipio);
        montania.setDireccion(direccion);
        montania.setDescripcion(descripcion);
        montania.setFoto(foto);
        montania.setId_montanias(id_montanias);

        daoMontania.actualizar(montania);
        this.accionDefault(request, response);
    }
}
