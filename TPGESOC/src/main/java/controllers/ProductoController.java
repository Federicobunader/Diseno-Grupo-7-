package controllers;

import Negocio.Compras.Producto;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

    private Repositorio<Producto> repo;

    public Repositorio<Producto> getRepo() {
        return repo;
    }

    public ProductoController() {
        this.repo = FactoryRepositorio.get(Producto.class);
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Producto> productos = this.repo.buscarTodos();
        parametros.put("productos", productos);

        return new ModelAndView(parametros, "GESOC_CargaEgresos.hbs");
    }

    public ModelAndView mostrarTodosPorCategoria(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
/*
        if (request.queryParams("cantidadDePersonal") != null) {
            int cantidad = Integer.valueOf(request.queryParams("cantidadDePersonal"));
            unaEmpresa.setCantidadDePersonal(cantidad);
        }

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
*/
        return new ModelAndView(parametros, "GESOC_Bandeja_Mensajes.hbs");
    }


}
