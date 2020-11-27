package controllers;

import Negocio.Compras.*;
import Negocio.Compras.Vinculacion.*;
import Negocio.Documento;
import Negocio.Entidad.Empresa.Empresa;
import Negocio.Entidad.EntidadBase;
import Negocio.Entidad.EntidadJuridica;
import Negocio.MedioDePago;
import Negocio.Proveedor;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EgresoController {

    private Repositorio<Egreso> repo;

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public Repositorio<Egreso> getRepo() {
        return repo;
    }

    public EgresoController() {
        this.repo = FactoryRepositorio.get(Egreso.class);
    }

    public Response reiniciarEgreso(Request request, Response response){
        gestorDeEgresos.reiniciarDatosEgreso();
        response.redirect("/menu_logueado");

        return null;
    }

    public Response guardarItem(Request request, Response response){

        Item item = new Item();
        Producto producto = new Producto();

        if (request.queryParams("productos_id") != null) {
            int idProducto = Integer.valueOf(request.queryParams("productos_id"));
            Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
            producto = repoProducto.buscar(idProducto);
        }

        if(producto != null){
            if (request.queryParams("productos_cantidad") != null) {
                int cantidad = Integer.valueOf(request.queryParams("productos_cantidad"));
                item.setProducto(producto);
                item.setCantidad(cantidad);

                gestorDeEgresos.agregarItemAListaDeItemDeEgreso(item);
            }
        }
        response.redirect("/cargar_egreso");
        return null;
    }

    public Response guardarUsuarioRevisor(Request request, Response response){

        Usuario usuario = new Usuario();

        if (request.queryParams("usuario_id") != null) {
            int idUsuario = Integer.valueOf(request.queryParams("usuario_id"));
            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            usuario = repoUsuario.buscar(idUsuario);

            if(usuario != null){
                gestorDeEgresos.agregarUsuarioRevisorAListaDeUsuarioRevisoresDeEgreso(usuario);
            }
        }
        response.redirect("/cargar_egreso");
        return null;
    }

    public Response guardarPresupuesto(Request request, Response response){

        Presupuesto presupuesto = new Presupuesto();

        if (request.queryParams("presupuesto_id") != null) {
            int idPresupuesto = Integer.valueOf(request.queryParams("presupuesto_id"));
            Repositorio<Presupuesto> repoPresupuestos = FactoryRepositorio.get(Presupuesto.class);
            presupuesto = repoPresupuestos.buscar(idPresupuesto);

            if(presupuesto != null){
                gestorDeEgresos.agregarPresupuestoALaListaDePresupuestosDelEgreso(presupuesto);
            }
        }

        response.redirect("/cargar_egreso");
        return null;
    }

    public Response guardarDocumento(Request request, Response response){

        Documento documento = new Documento();

        if (request.queryParams("documento_id") != null) {
            int idDocumento = Integer.valueOf(request.queryParams("documento_id"));
            Repositorio<Documento> repoDocumentos = FactoryRepositorio.get(Documento.class);
            documento = repoDocumentos.buscar(idDocumento);

            if(documento != null){
                gestorDeEgresos.agregarDocumentoALaListaDeDocumentosDelEgreso(documento);
            }
        }

        response.redirect("/cargar_egreso");
        return null;
    }

    private Map<String, Object> parametrosEgresos(Request request, Response response){
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

        Repositorio<Documento> repoDocumentos = FactoryRepositorio.get(Documento.class);
        List<Documento> documentos = repoDocumentos.buscarTodos();
        parametros.put("documentos", documentos);

        Repositorio<Empresa> repoEmpresas = FactoryRepositorio.get(Empresa.class);
        List<Empresa> empresas = repoEmpresas.buscarTodos();
        parametros.put("empresas", empresas);

        Repositorio<EntidadBase> repoEntidadBase = FactoryRepositorio.get(EntidadBase.class);
        List<EntidadBase> entidadBases = repoEntidadBase.buscarTodos();
        parametros.put("entidadBases", entidadBases);

        gestorDeEgresos.guardarEntidadesProvisoriamente(empresas,entidadBases);

        Repositorio<Proveedor> repoProveedor = FactoryRepositorio.get(Proveedor.class);
        List<Proveedor> proveedores = repoProveedor.buscarTodos();
        parametros.put("proveedores", proveedores);
        return parametros;
    }

    public ModelAndView cargarEgreso(Request request, Response response){

        Map<String, Object> parametros = this.parametrosEgresos(request,response);

        return new ModelAndView(parametros,"GESOC_CargaEgresos.hbs");
    }

    public Response guardar(Request request, Response response){

        System.out.println("ENTRE AL GUARDAR EGRESO");

        Egreso egreso = new Egreso();
        Compra compra = new Compra();

        Item item = new Item();
        Producto producto = new Producto();

        if (request.queryParams("productos_id") != null) {
            int idProducto = Integer.valueOf(request.queryParams("productos_id"));
            Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
            producto = repoProducto.buscar(idProducto);
        }

        if(producto != null){
            if (request.queryParams("productos_cantidad") != null) {
                int cantidad = Integer.valueOf(request.queryParams("productos_cantidad"));
                item.setProducto(producto);
                item.setCantidad(cantidad);

                gestorDeEgresos.agregarItemAListaDeItemDeEgreso(item);
            }
        }

        Usuario usuario = new Usuario();

        if (request.queryParams("usuario_id") != null) {
            int idUsuario = Integer.valueOf(request.queryParams("usuario_id"));
            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            usuario = repoUsuario.buscar(idUsuario);

            if(usuario != null){
                gestorDeEgresos.agregarUsuarioRevisorAListaDeUsuarioRevisoresDeEgreso(usuario);
            }
        }

        Presupuesto presupuesto = new Presupuesto();

        if (request.queryParams("presupuesto_id") != null) {
            int idPresupuesto = Integer.valueOf(request.queryParams("presupuesto_id"));
            Repositorio<Presupuesto> repoPresupuestos = FactoryRepositorio.get(Presupuesto.class);
            presupuesto = repoPresupuestos.buscar(idPresupuesto);

            if(presupuesto != null){
                gestorDeEgresos.agregarPresupuestoALaListaDePresupuestosDelEgreso(presupuesto);
            }
        }

        Documento documento = new Documento();

        if (request.queryParams("documento_id") != null) {
            int idDocumento = Integer.valueOf(request.queryParams("documento_id"));
            Repositorio<Documento> repoDocumentos = FactoryRepositorio.get(Documento.class);
            documento = repoDocumentos.buscar(idDocumento);

            if(documento != null){
                gestorDeEgresos.agregarDocumentoALaListaDeDocumentosDelEgreso(documento);
            }
        }

        compra.setItems(gestorDeEgresos.getItemsAAgregarAUnEgreso());
        compra.setUsuariosRevisores(gestorDeEgresos.getUsuariosRevisoresDeUnEgreso());
        compra.setPresupuestos(gestorDeEgresos.getPresupuestosDelEgreso());
        compra.setDocumentosComerciales(gestorDeEgresos.getDocumentosDelEgreso());


        if (request.queryParams("presupuestoElegido") != null) {
            Presupuesto presupuestoElegido = new Presupuesto();
            int idPresupuestoElegido = Integer.valueOf(request.queryParams("presupuestoElegido"));
            Repositorio<Presupuesto> repoPresupuestos = FactoryRepositorio.get(Presupuesto.class);
            presupuestoElegido = repoPresupuestos.buscar(idPresupuestoElegido);

            if(presupuestoElegido != null){
                compra.setPresupuestoElegido(presupuestoElegido);
            }
        }

        if (request.queryParams("tipoDePago") != null) {
            MedioDePago medioDePago = new MedioDePago();
            medioDePago.setTipoDePago(request.queryParams("tipoDePago"));

           compra.setMedioDePago(medioDePago);
        }

        if (request.queryParams("entidad_id") != null) {
            Presupuesto presupuestoElegido = new Presupuesto();
            int idEntidad = Integer.valueOf(request.queryParams("entidad_id"));

            for(int i = 0; i < gestorDeEgresos.getEmpresasDelEgreso().size();i++){
                if(gestorDeEgresos.getEmpresasDelEgreso().get(i).getId() == idEntidad){
                    Empresa empresa = new Empresa();
                    empresa = gestorDeEgresos.getEmpresasDelEgreso().get(i);
                    compra.setEntidad(empresa);
                }
            }
            for(int i = 0; i < gestorDeEgresos.getEntidadBasesDelEgreso().size();i++){
                if(gestorDeEgresos.getEntidadBasesDelEgreso().get(i).getId() == idEntidad){
                    EntidadBase entidadBase = new EntidadBase();
                    entidadBase = gestorDeEgresos.getEntidadBasesDelEgreso().get(i);
                    compra.setEntidad(entidadBase);
                }
            }

        }

        if (request.queryParams("proveedor_id") != null) {
            Proveedor proveedor = new Proveedor();
            int idProveedor = Integer.valueOf(request.queryParams("proveedor_id"));
            Repositorio<Proveedor> repoProveedor = FactoryRepositorio.get(Proveedor.class);
            proveedor = repoProveedor.buscar(idProveedor);

            if(proveedor != null){
                compra.setProveedor(proveedor);
            }
        }

        if (request.queryParams("requierePresupuesto") != null) {
            int valorBool = Integer.valueOf(request.queryParams("requierePresupuesto"));
            if(valorBool == 1){
                compra.setRequierePresupuesto(true);
            }
            else if(valorBool == 2){
                compra .setRequierePresupuesto(false);
            }

        }

        egreso.setCompra(compra);
        egreso.setFechaDeOperacion(new Date());
        egreso.setValorTotal(compra.valorTotal());

        this.repo.agregar(egreso);

        gestorDeEgresos.reiniciarDatosEgreso();
        System.out.println("SALI DEL GUARDAR EGRESO");

        response.redirect("/menu_logueado");
        return response;

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

    public Response vincularEgreso (Request request, Response response){

        if (request.queryParams("criterio") != null) {
            String criterioRecibido = request.queryParams("criterio");

            CriterioDeVinculacion criterioElegido = new OrdenValorPrimeroIngreso();
            switch (criterioRecibido){
                case "1":
                    criterioElegido = new OrdenValorPrimeroEgreso();
                    break;
                case "2":
                    criterioElegido = new OrdenValorPrimeroIngreso();
                    break;
                case "3":
                    criterioElegido = new PorFecha();
                    break;
                case "4":
                    criterioElegido = new Mix();
                    break;
                default:

            }
            criterioElegido.vincular();


        }
        System.out.println(("Criterio" + request.queryParams("criterio") ));
        response.redirect("/menu_logueado");
        return response;
    }

    private void asignarAtributosA(Egreso unEgreso, Request request) {
//        if (request.queryParams("RazonSocial") != null) {
//            unEgreso.setRazonSocial(request.queryParams("RazonSocial"));
//        }
//
//        if (request.queryParams("NombreFicticio") != null) {
//            unaEmpresa.setNombreFicticio(request.queryParams("NombreFicticio"));
//        }
//
//        if (request.queryParams("CUIT") != null) {
//            long cuit = Long.valueOf(request.queryParams("CUIT"));
//            unaEmpresa.setCUIT(cuit);
//        }
//
//        if (request.queryParams("CodigoDeInscripcion") != null) {
//            long codigoIGJ = Long.valueOf(request.queryParams("CodigoDeInscripcion"));
//            unaEmpresa.setCodigoDeInscripcion(codigoIGJ);
//        }
//
//        if (request.queryParams("seleccionTipoDeSector") != null) { //SECTOR
//            unaEmpresa.setSeleccionTipoDeSector(request.queryParams("seleccionTipoDeSector"));
//        }
//
//        if (request.queryParams("cantidadDePersonal") != null) {
//            int cantidad = Integer.valueOf(request.queryParams("cantidadDePersonal"));
//            unaEmpresa.setCantidadDePersonal(cantidad);
//        }
//
//        if (request.queryParams("promedioDeVentasAnuales") != null) {
//            double cantidad = new Double (request.queryParams("promedioDeVentasAnuales"));
//            unaEmpresa.setPromedioDeVentasAnuales(cantidad);
//        }
    }

/*
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
