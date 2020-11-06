package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Usuario.DireccionPostal;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.RepositorioDeUsuarios;
import repositories.factories.FactoryRepositorio;
import repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
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
        asignarUsuarioSiEstaLogueado(request,parametros);
        System.out.println("ID EN EL MENU LOGUEADO : " + (request.params("id")));
        return new ModelAndView(parametros,"GESOC_Menu_Logueado.hbs");
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repo.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }

    public ModelAndView editar_usuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_Cambiar_Usuario.hbs");
    }

    public Response modificar(Request request, Response response){
        Usuario usuario = this.repo.buscar(Integer.valueOf(request.params("id")));
        DireccionPostalController direccionPostalController = new DireccionPostalController();

        direccionPostalController.modificar(request,response,usuario.getDireccionPostal().getId());

        asignarAtributosA(usuario, request);
        this.repo.modificar(usuario);
        response.redirect("/menu_logueado");
        return response;
    }

    public ModelAndView mostrar(Request request, Response response){
        Usuario usuario = this.repo.buscar(Integer.valueOf(request.params("id")));
        //Repositorio<DireccionPostal> repoDireccion = FactoryRepositorio.get(DireccionPostal.class);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", usuario);
        //parametros.put("direccionpostal",repoDireccion.buscar(usuario.getDireccionPostal().getId()));
        return new ModelAndView(parametros, "GESOC_Cambiar_Usuario.hbs");
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Usuario> usuarios = this.repo.buscarTodos();
        parametros.put("usuarios", usuarios);

        return new ModelAndView(parametros, "GESOC_UsuariosRevisores.hbs");
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
        String passwordFinal = "";
        GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();
        if(this.elUsuarioSePuedeRegistrarCorrectamente(unUsuario,request)) {
            String passwordIngresada = request.queryParams("password");
            passwordFinal = gestorDePasswords.chequearEspaciosSeguidos(passwordIngresada);

            unUsuario.setUsuario(request.queryParams("usuario"));
            unUsuario.setPassword(gestorDePasswords.hashearPassword(passwordFinal));
            unUsuario.setMail(request.queryParams("mail"));
        }
        else{

        }

    }

    public boolean elUsuarioSePuedeRegistrarCorrectamente (Usuario unUsuario, Request request) {
        String error;
        GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();
        RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();
        String passwordFinal = "";

        if (request.queryParams("usuario") != null) {
            String nombreUsuario = request.queryParams("usuario");

            if (request.queryParams("password") != null) {
                String passwordIngresada = request.queryParams("password");
                String passwordSinEspacios = gestorDePasswords.chequearEspaciosSeguidos(passwordIngresada);

                if(gestorDePasswords.laPasswordNoCumpleLosRequisitos(passwordSinEspacios, nombreUsuario)){

                    return false;
                }

                if (repoUsuarios.existe(nombreUsuario, gestorDePasswords.hashearPassword(passwordSinEspacios))) {
                    return false;
                }

            } else {
                return false;
            }
        } else {
            return false;
        }

        if (request.queryParams("mail") == null) {
            return false;
        }

        return true;
    }
}