package server;

import Bitacora.Bitacora;
import Negocio.Proveedor;
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
        IngresoController ingresoController = new IngresoController();
        AuthMiddleware authMiddleware = new AuthMiddleware();
        ProductoController productoController = new ProductoController();
        PresupuestoController presupuestoController = new PresupuestoController();
        MensajeController mensajeController = new MensajeController();
        CategoriaController categoriaController = new CategoriaController();
        ProyectoController proyectoController = new ProyectoController();
        OperacionController operacionController = new OperacionController();
        ProveedorController proveedorController = new ProveedorController();
        Bitacora bitacora = new Bitacora();

        Spark.get("/", loginController::inicio, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

        Spark.get("/menu_inicio", loginController::inicio, Router.engine);

        Spark.get("/menu_login", loginController::menu_login, Router.engine);

        Spark.get("/menu_logueado", usuarioController::menu_logueado, Router.engine);

        Spark.post("/registrar_empresa",empresaController ::guardar,Router.engine);

        Spark.post("/registrar_base",entidadBaseController ::guardar);

        Spark.get("/cambiar_usuario",usuarioController :: mostrar,Router.engine);

        Spark.post("/cambiar_usuario",usuarioController :: modificar);

        Spark.post("/confirmar_asociacion_prod_cate",categoriaController :: asociarProductoACategoria);

        Spark.get("/asociar_egreso_o_presepuesto_a_categoria",categoriaController :: mostrarTodosParaAsociar,Router.engine);

        Spark.get("/listado_por_categoria",categoriaController :: mostrarTodosParaVisualizar,Router.engine);

        Spark.get("/buscar_productos_de_categoria",categoriaController :: mostrarProductosDeLasCategoria,Router.engine);

        Spark.get("/mostrar_productos_del_proveedor",proveedorController :: mostrarProductosDelProveedor,Router.engine);

        Spark.post("/loguearse", loginController::login);

        Spark.get("/cargar_egreso", egresoController:: cargarEgreso, Router.engine);

        Spark.get("/reiniciar_egreso", egresoController:: reiniciarEgreso);

        Spark.post("/confirmar_egreso", egresoController :: guardarEgreso);

        Spark.post("/guardar_item_egreso", egresoController :: guardarItem);

        Spark.post("/guardar_usuario_revisor_egreso", egresoController :: guardarUsuarioRevisor);

        Spark.post("/guardar_presupuesto_egreso", egresoController :: guardarPresupuesto);

        Spark.post("/guardar_documento_egreso", egresoController :: guardarDocumento);

        Spark.post("/vincular", egresoController :: vincularEgreso);

        Spark.get("/asociar_egreso_a_ingreso", egresoController :: mostrarTodos, Router.engine);

        Spark.get("/presupuestos", presupuestoController::mostrarTodos,Router.engine); //CAMBIAR CONTROLLER NO SE COMO SE HACE

        Spark.get("/bandeja_mensajes", mensajeController::mostrarTodos,Router.engine);

        Spark.get("/cargar_proyecto", proyectoController::cargarProyectoVista,Router.engine);

        Spark.post("/crear_proyecto", proyectoController :: guardar);

        Spark.get("/vincular_proyecto", proyectoController::vincularProyectosEIngreso,Router.engine);

        Spark.post("/vincular_proy", proyectoController :: vincularProyecto);

        Spark.get("/bitacora_operaciones", operacionController:: bitacora,Router.engine);

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