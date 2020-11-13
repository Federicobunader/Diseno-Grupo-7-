package controllers;

import Negocio.Compras.Compra;
import Negocio.Compras.Item;
import Negocio.Compras.Presupuesto;
import Negocio.MedioDePago;
import Negocio.Proveedor;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;

import java.util.ArrayList;
import java.util.List;

public class CompraController {

    private Repositorio<Compra> repo;

    public Repositorio<Compra> getRepo() {
        return repo;
    }

    public CompraController() {
        this.repo = FactoryRepositorio.get(Compra.class);
    }

    public void asignarAtributosA(Compra compra, Request request) {

        if (request.queryParams("productos_id") != null) {

//           List <Item> itemsCompra = new List<>();
//           Item[] itemsCompra = Item.values();
//           ItemController itemController = new ItemController();
//           Item item = itemController.getRepo().buscar(Integer.valueOf(request.params("productos_id")));

            //compra.setItems([request.queryParams("items")]);
        }

        if (request.queryParams("presupuestos") != null) {
            //PresupuestoController itemController = new PresupuestoController();
            //Item item = itemController.getRepo().buscar(Integer.valueOf(request.params("id")));

            //compra.setPresupuestos([request.queryParams("presupuestos")]);
        }

        if (request.queryParams("usuariosRevisores") != null) {
            //ItemController itemController = new ItemController();
            //Item item = itemController.getRepo().buscar(Integer.valueOf(request.params("id")));

           // compra.setUsuariosRevisores([request.queryParams("usuariosRevisores")]);
        }

        if (request.queryParams("presupuestoElegido") != null) {
            PresupuestoController presupuestoController = new PresupuestoController();
            Presupuesto presupuesto = presupuestoController.getRepo().buscar(Integer.valueOf(request.params("presupuestoElegido")));

            compra.setPresupuestoElegido(presupuesto);
        }

        if (request.queryParams("medioDePago") != null) {
            String nombre;
            switch (Integer.valueOf(request.params("medioDePago"))) {
                case 1:
                    nombre = "creditCard";
                    break;
                case 2:
                    nombre = "debitCard";
                    break;
                case 3:
                    nombre = "ticket";
                    break;
                case 4:
                    nombre = "atm";
                    break;
                default:
                    nombre = "accountMoney";
            }
            MedioDePago medio = new MedioDePago(nombre);
            compra.setMedioDePago(medio);
        }

        if (request.queryParams("documentosComerciales") != null) {
            //ItemController itemController = new ItemController();
            //Item item = itemController.getRepo().buscar(Integer.valueOf(request.params("id")));

           // compra.setDocumentosComerciales([request.queryParams("documentosComerciales")]);
        }

        if(request.queryParams("entidad") != null){
            PresupuestoController presupuestoController = new PresupuestoController();
            Presupuesto presupuesto = presupuestoController.getRepo().buscar(Integer.valueOf(request.params("presupuestoElegido")));
        }

        if (request.queryParams("proveedor") != null) {
            ProveedorController proveedorController = new ProveedorController();
            Proveedor proveedor = proveedorController.getRepo().buscar(Integer.valueOf(request.params("proveedor")));

            compra.setProveedor(proveedor);
        }

       /* if (request.queryParams("requierePresupuesto") != null) {
            if(request.params("proveedor").equals("Si")) compra.setRequierePresupuesto(true);
            else compra.setRequierePresupuesto(false);

        }
        */
    }
}