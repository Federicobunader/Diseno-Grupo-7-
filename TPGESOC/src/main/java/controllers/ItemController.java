package controllers;

import Negocio.Compras.Item;
import Negocio.Compras.Producto;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;

public class ItemController {

    private Repositorio<Item> repo;

    public Repositorio<Item> getRepo() {
        return repo;
    }

    public ItemController() {
        this.repo = FactoryRepositorio.get(Item.class);
    }

    public void asignarAtributosA(Item item, Request request) {

        if (request.queryParams("id") != null) {
            ProductoController productoController = new ProductoController();
            Producto producto = productoController.getRepo().buscar(Integer.valueOf(request.params("id")));

            item.setProducto(producto);
        }

        if (request.queryParams("cantidad") != null) {
            item.setCantidad(Integer.valueOf(request.params("cantidad")));
        }

    }
}
