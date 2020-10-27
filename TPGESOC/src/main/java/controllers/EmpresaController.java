package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Usuario.DireccionPostal;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;
import spark.Response;

public class EmpresaController {

    private Repositorio<Empresa> repo;

    public EmpresaController() {
        this.repo = FactoryRepositorio.get(Empresa.class);
    }

    private void asignarAtributosA(Empresa unaEmpresa, Request request) {
        if (request.queryParams("RazonSocial") != null) {
            unaEmpresa.setRazonSocial(request.queryParams("RazonSocial"));
        }

        if (request.queryParams("NombreFicticio") != null) {
            unaEmpresa.setRazonSocial(request.queryParams("NombreFicticio"));
        }

        if (request.queryParams("CUIT") != null) {
            int cuit = new Integer(request.queryParams("CUIT"));
            unaEmpresa.setCUIT(cuit);
        }

        if (request.queryParams("CodigoDeInscripcion") != null) {
            int codigoIGJ = new Integer(request.queryParams("CodigoDeInscripcion"));
            unaEmpresa.setCUIT(codigoIGJ);
        }

        if (request.queryParams("seleccionTipoDeSector") != null) { //SECTOR
            unaEmpresa.setSeleccionTipoDeSector(request.queryParams("seleccionTipoDeSector"));
        }

        if (request.queryParams("cantidadDePersonal") != null) {
            int cantidad = new Integer(request.queryParams("cantidadDePersonal"));
            unaEmpresa.setCUIT(cantidad);
        }

        if (request.queryParams("promedioDeVentasAnuales") != null) {
            int cantidad = new Integer(request.queryParams("promedioDeVentasAnuales"));
            unaEmpresa.setCUIT(cantidad);
        }

        if (request.queryParams("Email") != null) {
            unaEmpresa.getUsuario().setMail(request.queryParams("Email"));
        }

        if (request.queryParams("Usuario") != null) {
            unaEmpresa.getUsuario().setUsuario(request.queryParams("Usuario"));
        }

        if (request.queryParams("Password") != null) {
            //String passwordIngresada = request.queryParams("Password");

            unaEmpresa.getUsuario().setPassword(request.queryParams("Password"));
        }

        if (request.queryParams("altura") != null) {
            int altura = new Integer(request.queryParams("altura"));
            unaEmpresa.getUsuario().getDireccionPostal().setAltura(altura);
        }

        if (request.queryParams("calle") != null) {
            unaEmpresa.getUsuario().getDireccionPostal().setCalle(request.queryParams("calle"));
        }

        if (request.queryParams("piso") != null) {
            int piso = new Integer(request.queryParams("piso"));
            unaEmpresa.getUsuario().getDireccionPostal().setPiso(piso);
        }

        if (request.queryParams("departamento") != null) {
            unaEmpresa.getUsuario().getDireccionPostal().setDepartamento(request.queryParams("departamento"));
        }

        if (request.queryParams("pais") != null) {
            unaEmpresa.getUsuario().getDireccionPostal().setPais(request.queryParams("pais"));
        }
        if (request.queryParams("provincia") != null) {
            unaEmpresa.getUsuario().getDireccionPostal().setProvincia(request.queryParams("provincia"));
        }
        if (request.queryParams("ciudad") != null) {
            unaEmpresa.getUsuario().getDireccionPostal().setCiudad(request.queryParams("ciudad"));
        }

    }

    public Response guardar(Request request, Response response){

        Empresa unaEmpresa = new Empresa();
        asignarAtributosA(unaEmpresa,request);
        this.repo.agregar(unaEmpresa);

       // response.redirect("/usuarios");
        return response;
    }
}
