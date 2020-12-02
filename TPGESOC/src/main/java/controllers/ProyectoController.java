package controllers;

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

    private void asignarAtributosA(Proyecto proyecto, Request request) {

        Usuario usuario = new Usuario();

        if (request.queryParams("id_usuario") != null) {
            int idUsuario = Integer.valueOf(request.queryParams("id_usuario"));
            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            usuario = repoUsuario.buscar(idUsuario);
            proyecto.setDirectorResponsable(usuario);
        }

        if (request.queryParams("cantidad_presupuestos") != null) {
            int cantidadPresupuestos = Integer.valueOf(request.queryParams("cantidad_presupuestos"));
            proyecto.setCantidadPresupuestosExigibles(cantidadPresupuestos);
        }

    }

    public Response guardar(Request request, Response response){

        Proyecto proyecto = new Proyecto();

        Map<String, Object> parametros = new HashMap<>();
        this.asignarAtributosA(proyecto,request);
        this.repo.agregar(proyecto);
        response.redirect("/menu_logueado");

        return response;
    }
}
