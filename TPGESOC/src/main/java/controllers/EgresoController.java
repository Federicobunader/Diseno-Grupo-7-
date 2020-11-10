package controllers;

import Negocio.Compras.Ingreso;
import Negocio.Compras.Egreso;
import Negocio.Compras.Presupuesto;
import Negocio.Compras.Producto;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EgresoController {

    private Repositorio<Egreso> repo;

    public Repositorio<Egreso> getRepo() {
        return repo;
    }

    public EgresoController() {
        this.repo = FactoryRepositorio.get(Egreso.class);
    }

    public ModelAndView cargarEgreso(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();

        ProductoController productoController = new ProductoController();
        Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
        List<Producto> productos = repoProducto.buscarTodos();
        parametros.put("productos", productos);


        UsuarioController usuarioController = new UsuarioController();
        Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
        List<Usuario> usuarios = repoUsuario.buscarTodos();
        parametros.put("usuarios", usuarios);

        PresupuestoController presupuestoController = new PresupuestoController();
        Repositorio<Presupuesto> repoPresupuesto = FactoryRepositorio.get(Presupuesto.class);
        List<Presupuesto> presupuestos = repoPresupuesto.buscarTodos();
        parametros.put("presupuestos", presupuestos);

        return new ModelAndView(parametros,"GESOC_CargaEgresos.hbs");
    }

    public ModelAndView mostrarTodos(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<Egreso> egresos = this.repo.buscarTodos();
        parametros.put("egresos", egresos);

        IngresoController ingresoController = new IngresoController();
        Repositorio<Ingreso> repoIngreso = FactoryRepositorio.get(Ingreso.class);
        List<Ingreso> ingresos = repoIngreso.buscarTodos();
        parametros.put("ingresos",ingresos);

        return new ModelAndView(parametros, "GESOC_AsociarIngresoAEgreso.hbs");
    }





/*
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


    public Response guardar(Request request, Response response){

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
            response.redirect("/menu_login");
        }

        return response;
    }
    */

}
