package controllers;

import Negocio.Usuario.Mensaje;
import Negocio.Usuario.Usuario;
//import Negocio.Usuario.Usuario_x_mensaje;
import Negocio.Usuario.Usuario_x_mensaje;
import repositories.Repositorio;
//import repositories.RepositorioMensajesPorUsuario;
import repositories.RepositorioMensajesPorUsuario;
import repositories.factories.FactoryRepositorio;
//import repositories.factories.FactoryRepositorioMensajesPorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
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

        Repositorio<Usuario_x_mensaje> repoUserXmsg = FactoryRepositorio.get(Usuario_x_mensaje.class);
        List<Usuario_x_mensaje> usuario_x_mensajes = repoUserXmsg.buscarTodos();

        List<Mensaje> mensajes = this.repo.buscarTodos();

        List <Mensaje> mensajesDelUsuario = new ArrayList<>();

        for(int i = 0 ; i< mensajes.size(); i++){
            for(int j = 0 ; j < usuario_x_mensajes.size();j++){
                if(mensajes.get(i).getId() == usuario_x_mensajes.get(j).getMensaje_id() && usuario_x_mensajes.get(j).getUsuario_id() == usuario.getId()){
                    mensajesDelUsuario.add(mensajes.get(i));
                }
            }
        }

        parametros.put("mensajes", mensajesDelUsuario);

        return new ModelAndView(parametros, "GESOC_Bandeja_Mensajes.hbs");
    }
}
