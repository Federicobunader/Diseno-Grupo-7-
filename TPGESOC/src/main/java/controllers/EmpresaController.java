package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Usuario.DireccionPostal;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
//import sun.rmi.transport.ObjectTable;

import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
            unaEmpresa.setNombreFicticio(request.queryParams("NombreFicticio"));
        }

        if (request.queryParams("CUIT") != null) {
            long cuit = Long.valueOf(request.queryParams("CUIT"));
            unaEmpresa.setCUIT(cuit);
        }

        if (request.queryParams("CodigoDeInscripcion") != null) {
            long codigoIGJ = Long.valueOf(request.queryParams("CodigoDeInscripcion"));
            unaEmpresa.setCodigoDeInscripcion(codigoIGJ);
        }

        if (request.queryParams("seleccionTipoDeSector") != null) { //SECTOR
            unaEmpresa.setSeleccionTipoDeSector(request.queryParams("seleccionTipoDeSector"));
        }

        if (request.queryParams("cantidadDePersonal") != null) {
            int cantidad = Integer.valueOf(request.queryParams("cantidadDePersonal"));
            unaEmpresa.setCantidadDePersonal(cantidad);
        }

        if (request.queryParams("promedioDeVentasAnuales") != null) {
            double cantidad = new Double (request.queryParams("promedioDeVentasAnuales"));
            unaEmpresa.setPromedioDeVentasAnuales(cantidad);
        }
    }

    public ModelAndView guardar(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        UsuarioController usuarioController = new UsuarioController();
        Usuario unUsuario = new Usuario();

        if(usuarioController.elUsuarioSePuedeRegistrarCorrectamente(unUsuario,request)){
            Empresa unaEmpresa = new Empresa();
            DireccionPostal direccionPostal = new DireccionPostal();

            DireccionPostalController direccionPostalController = new DireccionPostalController();
            direccionPostalController.asignarAtributosA(direccionPostal,request);

            Repositorio<DireccionPostal> repoDireccion = FactoryRepositorio.get(DireccionPostal.class);
            repoDireccion.agregar(direccionPostal);


            usuarioController.asignarAtributosA(unUsuario,request);

            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            repoUsuario.agregar(unUsuario);

            unUsuario.setDireccionPostal(direccionPostal);
            unaEmpresa.setUsuario(unUsuario);

            asignarAtributosA(unaEmpresa,request);
            this.repo.agregar(unaEmpresa);
            response.redirect("/menu_logueado");
        }
        else{
            parametros.put("falloAlRegistrarse",true);
            return new ModelAndView (parametros,"GESOC_Login.hbs");
        }

        return null;
    }

}
