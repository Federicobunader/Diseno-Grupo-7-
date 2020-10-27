package controllers;

import Negocio.Usuario.DireccionPostal;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;

public class DireccionPostalController {

    private Repositorio<DireccionPostal> repo;

    public DireccionPostalController(){
        this.repo = FactoryRepositorio.get(DireccionPostal.class);
    }

    private void asignarAtributosA(DireccionPostal direccionPostal, Request request) {
        if (request.queryParams("altura") != null) {
            int altura = new Integer(request.queryParams("altura"));
            direccionPostal.setAltura(altura);
        }

        if (request.queryParams("calle") != null) {
            direccionPostal.setCalle(request.queryParams("calle"));
        }

        if (request.queryParams("piso") != null) {
            int piso = new Integer(request.queryParams("piso"));
            direccionPostal.setPiso(piso);
        }

        if (request.queryParams("nombreDeUsuario") != null) {
            direccionPostal.setNombreDeUsuario(request.queryParams("nombreDeUsuario"));
        }

        if (request.queryParams("apellido") != null) {
            direccionPostal.setApellido(request.queryParams("apellido"));
        }
    }
}
