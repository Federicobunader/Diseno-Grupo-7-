package server;

import controllers.EmpresaController;
import controllers.LoginController;
import controllers.UsuarioController;
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

        Spark.get("/", loginController::inicio, Router.engine);

        Spark.get("/menu_login", loginController::menu_login, Router.engine);

        Spark.get("/menu_logueado", usuarioController::menu_logueado, Router.engine);

        Spark.post("/guardar",empresaController ::guardar);


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