package controllers;

import Negocio.Usuario.Mensaje;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
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
        Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
        Usuario usuario = repoUsuario.buscar(Integer.valueOf(request.params("id")));

        List<Mensaje> mensajes = this.repo.buscarTodos();
        parametros.put("mensajes", mensajes);

        return new ModelAndView(parametros, "GESOC_CargaEgresos.hbs");
    }
}
