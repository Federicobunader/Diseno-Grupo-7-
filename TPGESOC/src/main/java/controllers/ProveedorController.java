package controllers;

import Negocio.Proveedor;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProveedorController {

    private Repositorio<Proveedor> repo;

    public Repositorio<Proveedor> getRepo() {
        return repo;
    }

    public ProveedorController() {
        this.repo = FactoryRepositorio.get(Proveedor.class);
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Proveedor> productos = this.repo.buscarTodos();
        parametros.put("productos", productos);

        return new ModelAndView(parametros, "GESOC_CargarProductos.hbs");
    }
}
