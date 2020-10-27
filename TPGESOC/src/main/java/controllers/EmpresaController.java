package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Usuario.DireccionPostal;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;

public class EmpresaController {

    private Repositorio<Empresa> repo;

    public EmpresaController(){
        this.repo = FactoryRepositorio.get(Empresa.class);
    }

    private void asignarAtributosA(Empresa unaEmpresa, Request request){
        if(request.queryParams("RazonSocial") != null){
           unaEmpresa.setRazonSocial(request.queryParams("RazonSocial"));
        }

        if(request.queryParams("NombreFicticio") != null){
            unaEmpresa.setRazonSocial(request.queryParams("NombreFicticio"));
        }

        if(request.queryParams("CUIT") != null){
            int cuit = new Integer(request.queryParams("CUIT"));
            unaEmpresa.setCUIT(cuit);
        }

        if(request.queryParams("CodigoDeInscripcion") != null){
            int codigoIGJ = new Integer(request.queryParams("CodigoDeInscripcion"));
            unaEmpresa.setCUIT(codigoIGJ);
        }

        if(request.queryParams("seleccionTipoDeSector") != null){ //SECTOR
            unaEmpresa.setSeleccionTipoDeSector(request.queryParams("seleccionTipoDeSector"));
        }

        if(request.queryParams("cantidadDePersonal") != null){
            int cantidad = new Integer(request.queryParams("cantidadDePersonal"));
            unaEmpresa.setCUIT(cantidad);
        }

        if(request.queryParams("promedioDeVentasAnuales") != null){
            int cantidad = new Integer(request.queryParams("promedioDeVentasAnuales"));
            unaEmpresa.setCUIT(cantidad);
        }






            Rol unRol = repoRol.buscar(new Integer(request.queryParams("rol")));
            usuario.setRol(unRol);
        }
    }
}
