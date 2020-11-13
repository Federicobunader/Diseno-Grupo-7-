package controllers;

import Negocio.Usuario.Mensaje;
import Negocio.Usuario.Usuario;
//import Negocio.Usuario.Usuario_x_mensaje;
import repositories.Repositorio;
//import repositories.RepositorioMensajesPorUsuario;
import repositories.factories.FactoryRepositorio;
//import repositories.factories.FactoryRepositorioMensajesPorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MensajeController {

    private Repositorio<Mensaje> repo;

    public MensajeController(){
        this.repo = FactoryRepositorio.get(Mensaje.class);
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        LoginController.ensureUserIsLoggedIn(request, response);

        Usuario usuario = LoginController.getCurrentUser(request);



        List<Mensaje> mensajes = this.repo.buscarTodos();
        parametros.put("mensajes", mensajes);

        return new ModelAndView(parametros, "GESOC_Bandeja_Mensajes.hbs");
    }
}
