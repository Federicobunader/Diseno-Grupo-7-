package server;

import controllers.*;
import middleware.AuthMiddleware;
import spark.ResponseTransformer;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){

        EmpresaController empresaController = new EmpresaController();
        LoginController loginController = new LoginController();
        UsuarioController usuarioController = new UsuarioController();
        EntidadBaseController entidadBaseController = new EntidadBaseController();
        EgresoController egresoController = new EgresoController();
        AuthMiddleware authMiddleware       = new AuthMiddleware();

        Spark.get("/", loginController::inicio, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

        Spark.get("/menu_login", loginController::menu_login, Router.engine);

        Spark.get("/menu_logueado", usuarioController::menu_logueado, Router.engine);

        Spark.post("/registrar_empresa",empresaController ::guardar,Router.engine);

        Spark.post("/registrar_base",entidadBaseController ::guardar);

        Spark.get("/cambiar_usuario/:id",usuarioController :: mostrar,Router.engine);

        Spark.post("/cambiar_usuario/:id",usuarioController :: modificar);

        Spark.get("/asociar_egreso_o_presepuesto_a_categoria",usuarioController :: asociar_egreso_o_presepuesto_a_categoria,Router.engine);

        Spark.get("/listado_por_categoria",usuarioController :: listado_por_categoria,Router.engine);

        Spark.post("/loguearse", loginController::login);

        Spark.get("/cargar_egreso", egresoController :: cargarEgreso,  Router.engine);

        Spark.post("/cargar_egreso", egresoController :: cargarEgreso);


        //Spark.get("/GESOC_Menu",empresaController::guardar,Router.engine);
       // Spark.get("/saludo/:nombre/:apellido",((request, response) -> "HOLA " + request.params("nombre") + request.params("apellido") ));


        /*
        UsuarioRestControllerEjemplo usuarioRestControllerEjemplo = new UsuarioRestControllerEjemplo();
        UsuarioController usuarioController = new UsuarioController();
        LoginController loginController     = new LoginController();
        AuthMiddleware authMiddleware       = new AuthMiddleware();

        Spark.get("/", loginController::inicio, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

        Spark.post("/login", loginController::login);

        Spark.get("/logout", loginController::logout);

        Spark.get("/usuarios", usuarioController::mostrarTodos, Router.engine);

        Spark.get("/usuario/:id", usuarioController::mostrar, Router.engine);

        Spark.get("/usuario", usuarioController::crear, Router.engine);

        Spark.post("/usuario/:id", usuarioController::modificar);

        Spark.post("/usuario", usuarioController::guardar);

        Spark.delete("/usuario/:id", usuarioController::eliminar);

        Spark.get("/api/usuario/:id", usuarioRestControllerEjemplo::mostrar);

         */
    }
}