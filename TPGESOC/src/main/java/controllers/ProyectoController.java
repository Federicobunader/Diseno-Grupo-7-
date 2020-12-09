package controllers;

import Negocio.Compras.Ingreso;
import Negocio.Proyecto.Proyecto;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class ProyectoController {

    private Repositorio<Proyecto> repo;

    public Repositorio<Proyecto> getRepo() {
        return repo;
    }

    public ProyectoController() {
        this.repo = FactoryRepositorio.get(Proyecto.class);
    }

    public ModelAndView cargarProyectoVista(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();

        UsuarioController usuarioController = new UsuarioController();
        Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
        List<Usuario> usuarios = repoUsuario.buscarTodos();
        parametros.put("usuarios", usuarios);

        return new ModelAndView(parametros,"GESOC_CrearProyecto.hbs");
    }

    public ModelAndView vincularProyectosEIngreso(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();

        Repositorio<Proyecto> repoProyecto = FactoryRepositorio.get(Proyecto.class);
        List<Proyecto> proyectos = repoProyecto.buscarTodos();
        parametros.put("proyectos", proyectos);

        Repositorio<Ingreso> repoIngreso = FactoryRepositorio.get(Ingreso.class);
        List<Ingreso> ingresos = repoIngreso.buscarTodos();
        parametros.put("ingresos", ingresos);

        return new ModelAndView(parametros,"GESOC_VincularIngresoAProyecto.hbs");
    }

    public Response vincularProyecto(Request request, Response response){

        Proyecto proyecto = new Proyecto();
        Ingreso ingreso = new Ingreso();

        Repositorio<Proyecto> repoProyecto = FactoryRepositorio.get(Proyecto.class);
        Repositorio<Ingreso> repoIngreso = FactoryRepositorio.get(Ingreso.class);

        if (request.queryParams("proyecto_id") != null) {
            int idProyecto = Integer.valueOf(request.queryParams("proyecto_id"));
            proyecto = repoProyecto.buscar(idProyecto);
        }

        if (request.queryParams("ingreso_id") != null) {
            int idIngreso = Integer.valueOf(request.queryParams("ingreso_id"));
            ingreso = repoIngreso.buscar(idIngreso);
        }

        List <Ingreso> ingresosProyectos = proyecto.getIngresos();
        ingresosProyectos.add(ingreso);
        proyecto.setIngresos(ingresosProyectos);

        repoProyecto.modificar(proyecto);
        response.redirect("/menu_logueado");
        return response;
    }

    private void asignarAtributosA(Proyecto proyecto, Request request) {

        Usuario usuario = new Usuario();

        if (request.queryParams("usuario_id") != null) {
            int idUsuario = Integer.valueOf(request.queryParams("usuario_id"));
            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            usuario = repoUsuario.buscar(idUsuario);
            proyecto.setDirectorResponsable(usuario);
        }

        if (request.queryParams("cantidad_presupuestos") != null) {
            int cantidadPresupuestos = Integer.valueOf(request.queryParams("cantidad_presupuestos"));
            proyecto.setCantidadPresupuestosExigibles(cantidadPresupuestos);
        }

        if (request.queryParams("monto_definido") != null) {
            int monto = Integer.valueOf(request.queryParams("monto_definido"));
            proyecto.setMontoDefinido(monto);
        }

    }

    public Response guardar(Request request, Response response){

        Proyecto proyecto = new Proyecto();
        OperacionController operacionController = new OperacionController();

        Map<String, Object> parametros = new HashMap<>();
        this.asignarAtributosA(proyecto,request);
        this.repo.agregar(proyecto);
        operacionController.GuardarEnBitacora(proyecto,"ALTA");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.redirect("/menu_logueado");

        return response;
    }
}
