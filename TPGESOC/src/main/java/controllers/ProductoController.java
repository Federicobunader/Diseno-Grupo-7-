package controllers;

import Negocio.Compras.Producto;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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


}
