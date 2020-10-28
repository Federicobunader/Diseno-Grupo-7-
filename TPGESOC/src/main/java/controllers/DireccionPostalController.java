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

    public void asignarAtributosA(DireccionPostal direccionPostal, Request request) {
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

        if (request.queryParams("departamento") != null) {
            direccionPostal.setDepartamento(request.queryParams("departamento"));
        }

        if (request.queryParams("pais") != null) {
            direccionPostal.setPais(request.queryParams("pais"));
        }
        if (request.queryParams("provincia") != null) {
            direccionPostal.setProvincia(request.queryParams("provincia"));
        }
        if (request.queryParams("ciudad") != null) {
            direccionPostal.setCiudad(request.queryParams("ciudad"));
        }
    }
}