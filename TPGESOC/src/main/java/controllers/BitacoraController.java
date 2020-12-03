package controllers;

import Bitacora.Bitacora;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BitacoraController {

    private Repositorio<Bitacora> repo;

    public Repositorio<Bitacora> getRepo() {
        return repo;
    }

    public BitacoraController() {
        this.repo = FactoryRepositorio.get(Bitacora.class);
    }

    public ModelAndView bitacora(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros,"GESOC_Bitacora.hbs");
    }

}
