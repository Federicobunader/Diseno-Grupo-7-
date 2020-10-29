package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class UsuarioController {

    private Repositorio<Usuario> repo;

    public Repositorio<Usuario> getRepo() {
        return repo;
    }

    public UsuarioController() {
        this.repo = FactoryRepositorio.get(Usuario.class);
    }

    public ModelAndView menu_logueado(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_Menu_Logueado.hbs");
    }

    public ModelAndView editar_usuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_Cambiar_Usuario.hbs");
    }

    public ModelAndView asociar_egreso_o_presepuesto_a_categoria(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_Asociar_A_Categoria.hbs");
    }

    public ModelAndView listado_por_categoria(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_ListadoXCategoria.hbs");
    }

    public void asignarAtributosA(Usuario unUsuario, Request request) {
        if (request.queryParams("mail") != null) {
            unUsuario.setMail(request.queryParams("mail"));
        }

        if (request.queryParams("usuario") != null) {
            unUsuario.setUsuario(request.queryParams("usuario"));
        }

        if (request.queryParams("password") != null) {
            //String passwordIngresada = request.queryParams("Password");

            unUsuario.setPassword(request.queryParams("password"));
        }
    }

}