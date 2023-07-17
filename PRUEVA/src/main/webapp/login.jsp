<%@page session="true"%>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--ESTILOS LOCALES DEL LOGIN-->
        <link rel="stylesheet" href="Recursos/EstilosLogin.css"/>
        <!--FONT AWESOME-->
        <script src="https://kit.fontawesome.com/893526ba97.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <form name="form1" action="ServletControladorAcceso" method="POST">
            <div class="container">
                <div class="card">
                    <div class="top-row "> <i class="fa fa-user-circle" aria-hidden="true"></i> </div>
                    <div class="content"> <i class="fa fa-lock fa-spin" aria-hidden="true"></i>
                        <h1>Login</h1>

                        <p>User</p>
                        <input type="text" class="text" name="usuario" required>

                        <p>Password</p>
                        <input type="password" class="text" name="password" required> 

                        <button href="" class="button " name="btnIniciar">Iniciar</button>

                    </div>
                    <div class="networks"> 
                        <a class="redes" href="https://www.facebook.com" target="_blank"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                        <a class="redes" href="mailto:atractivos.sv@gmail.com" target="_blank"><i class="fa fa-envelope-o" aria-hidden="true"></i></a>
                        <a class="redes" href="https://www.instagram.com" target="_blank" ><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>
            </div>
        </form>
        <%
            HttpSession sesion = request.getSession();
            int nivel = 0;
            if (request.getAttribute("nivel") != null) {
                nivel = (Integer) request.getAttribute("nivel");
                if (nivel == 1) {
                    sesion.setAttribute("nombre", request.getAttribute("nombre"));
                    sesion.setAttribute("nivel", nivel);
                    response.sendRedirect("menuAdmin.jsp");
                }
            }
            if(request.getParameter("cerrar")!=null){
                session.invalidate();
            }
        %>
    </body>
</html>

