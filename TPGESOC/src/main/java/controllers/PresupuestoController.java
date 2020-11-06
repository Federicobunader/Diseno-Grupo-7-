package controllers;

import Negocio.Compras.Presupuesto;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresupuestoController {

    private Repositorio<Presupuesto> repo;

    public Repositorio<Presupuesto> getRepo() {
        return repo;
    }

    public PresupuestoController() {
        this.repo = FactoryRepositorio.get(Presupuesto.class);
    }

    public Presupuesto buscar(Request request, Response response,int idPresupuesto){
        Repositorio<Presupuesto> repoPresupuesto = FactoryRepositorio.get(Presupuesto.class);
        return repoPresupuesto.buscar(idPresupuesto);
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Presupuesto> presupuestos = this.repo.buscarTodos();
        parametros.put("presupuestos", presupuestos);

        return new ModelAndView(parametros, "GESOC_presupuestos.hbs");
    }
}
