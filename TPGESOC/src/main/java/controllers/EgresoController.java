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

    public Response guardarEgreso(Request request, Response response){

        System.out.println("ENTRE AL GUARDAR EGRESO");
        Usuario usuario = new Usuario();
        Egreso egreso = new Egreso();
        Compra compra = new Compra();
        Presupuesto presupuesto = new Presupuesto();
        Presupuesto presupuestoElegido = new Presupuesto();
        Item item = new Item();
        Producto producto = new Producto();


        if (request.queryParams("productos_id") != null) {
            int idProducto = Integer.valueOf(request.queryParams("productos_id"));
            Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
            producto = repoProducto.buscar(idProducto);
        }
        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 1");

        if(producto != null){
            if (request.queryParams("productos_cantidad") != null) {
                int cantidad = Integer.valueOf(request.queryParams("productos_cantidad"));
                item.setProducto(producto);
                item.setCantidad(cantidad);

                gestorDeEgresos.agregarItemAListaDeItemDeEgreso(item);
            }
        }


        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 2");
        System.out.println("USUARIO_ID =" + request.queryParams("usuario_id"));

        if (request.queryParams("usuario_id") != null) {
            int idUsuario = Integer.valueOf(request.queryParams("usuario_id"));
            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            usuario = repoUsuario.buscar(idUsuario);

            if(usuario != null){
                gestorDeEgresos.agregarUsuarioRevisorAListaDeUsuarioRevisoresDeEgreso(usuario);
            }
        }


        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 3");

        if (request.queryParams("presupuesto_id") != null) {
            int idPresupuesto = Integer.valueOf(request.queryParams("presupuesto_id"));
            Repositorio<Presupuesto> repoPresupuestos = FactoryRepositorio.get(Presupuesto.class);
            presupuesto = repoPresupuestos.buscar(idPresupuesto);

            if(presupuesto != null){
                gestorDeEgresos.agregarPresupuestoALaListaDePresupuestosDelEgreso(presupuesto);
            }
        }

        Documento documento = new Documento();
        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 4");

        if (request.queryParams("documento_id") != null) {
            int idDocumento = Integer.valueOf(request.queryParams("documento_id"));
            Repositorio<Documento> repoDocumentos = FactoryRepositorio.get(Documento.class);
            documento = repoDocumentos.buscar(idDocumento);

            if(documento != null){
                gestorDeEgresos.agregarDocumentoALaListaDeDocumentosDelEgreso(documento);
            }
        }
        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 5");

        compra.setItems(gestorDeEgresos.getItemsAAgregarAUnEgreso());
        compra.setUsuariosRevisores(gestorDeEgresos.getUsuariosRevisoresDeUnEgreso());
        compra.setPresupuestos(gestorDeEgresos.getPresupuestosDelEgreso());
        compra.setDocumentosComerciales(gestorDeEgresos.getDocumentosDelEgreso());

        if (request.queryParams("requierePresupuesto") != null) {
            int valorBool = Integer.valueOf(request.queryParams("requierePresupuesto"));
            if(valorBool == 1){
                compra.setRequierePresupuesto(true);
            }
            else if(valorBool == 2){
                compra .setRequierePresupuesto(false);
            }

        }

        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 6");

        if (request.queryParams("presupuesto_elegido_id") != null) {
            int idPresupuestoElegido = Integer.valueOf(request.queryParams("presupuesto_elegido_id"));
            Repositorio<Presupuesto> repoPresupuestos = FactoryRepositorio.get(Presupuesto.class);
            presupuestoElegido = repoPresupuestos.buscar(idPresupuestoElegido);

            if(presupuestoElegido != null){
                compra.setPresupuestoElegido(presupuestoElegido);
            }
        }
        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 7");

        if (request.queryParams("tipoDePago") != null) {
            MedioDePago medioDePago = new MedioDePago();
            medioDePago.setTipoDePago(request.queryParams("tipoDePago"));

           compra.setMedioDePago(medioDePago);
        }

        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 8");
        if (request.queryParams("entidad_id") != null) {
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

        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 9");

        if (request.queryParams("proveedor_id") != null) {
            Proveedor proveedor = new Proveedor();
            int idProveedor = Integer.valueOf(request.queryParams("proveedor_id"));
            Repositorio<Proveedor> repoProveedor = FactoryRepositorio.get(Proveedor.class);
            proveedor = repoProveedor.buscar(idProveedor);

            if(proveedor != null){
                compra.setProveedor(proveedor);
            }
        }

        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 10");



        System.out.println("ENTRE AL GUARDAR EGRESO - PARTE 11");
        System.out.println("USUARIO = " + usuario.getUsuario());
        System.out.println("PRESUPUESTO =" + presupuesto.getId());
        System.out.println("PRESUPUESTO ELEGIDO =" + presupuestoElegido.getId());
        System.out.println("DOCUMENTO =" + documento.getId());
/*
        egreso.setCompra(compra);
        egreso.setFechaDeOperacion(new Date());
        egreso.setValorTotal(compra.valorTotal());

*/

        egreso = compra.efectuarCompra();
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

            Repositorio<Ingreso> repoIngreso = FactoryRepositorio.get(Ingreso.class);
            List<Ingreso> ingresos = repoIngreso.buscarTodos();

            for(int i = 0; i < ingresos.size(); i++){
                criterioElegido.vincular(ingresos.get(i));
                repoIngreso.modificar(ingresos.get(i));
            }


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
