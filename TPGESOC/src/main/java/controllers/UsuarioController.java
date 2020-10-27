package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;

public class UsuarioController {

    private Repositorio<Usuario> repo;

    public UsuarioController() {
        this.repo = FactoryRepositorio.get(Usuario.class);
    }

    private void asignarAtributosA(Usuario unUsuario, Request request, Empresa unaEmpresa) {
        if (request.queryParams("Email") != null) {
            unaEmpresa.getUsuario().setMail(request.queryParams("Email"));
        }

        if (request.queryParams("Usuario") != null) {
            unUsuario.setUsuario(request.queryParams("Usuario"));
        }

        if (request.queryParams("Password") != null) {
            //String passwordIngresada = request.queryParams("Password");

            unUsuario.setPassword(request.queryParams("Password"));
        }
    }
}