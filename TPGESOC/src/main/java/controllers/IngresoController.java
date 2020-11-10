package controllers;

import Negocio.Compras.Ingreso;
import Negocio.Compras.Producto;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngresoController {

    private Repositorio<Ingreso> repo;

    public Repositorio<Ingreso> getRepo() {
        return repo;
    }

    public IngresoController() {
        this.repo = FactoryRepositorio.get(Ingreso.class);
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Ingreso> ingresos = this.repo.buscarTodos();
        parametros.put("ingresos", ingresos);

        return new ModelAndView(parametros, "GESOC_AsociarIngresoAEgreso.hbs");
    }
}
