package controllers;

import Negocio.Compras.CategoriaItem;
import Negocio.Compras.Egreso;
import Negocio.Compras.Producto;
import Negocio.Compras.producto_x_categoria;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaController {

    private Repositorio<CategoriaItem> repo;

    public Repositorio<CategoriaItem> getRepo() {
        return repo;
    }

    public CategoriaController() {
        this.repo = FactoryRepositorio.get(CategoriaItem.class);
    }

    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_VisualizarIngEgPorCategoria.hbs");
    }

    private Map<String, Object> mostrarCategoriasparametros(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<CategoriaItem> categorias = this.repo.buscarTodos();
        parametros.put("categorias", categorias);

        return parametros;
    }

    private Map<String, Object> mostrarProductosparametros(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
        List<Producto> productos = repoProducto.buscarTodos();
        parametros.put("productos", productos);

        return parametros;
    }

    private Map<String, Object> mostrarProductosYCategoriasParametros(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        List<CategoriaItem> categorias = this.repo.buscarTodos();
        parametros.put("categorias", categorias);


        Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
        List<Producto> productos = repoProducto.buscarTodos();
        parametros.put("productos", productos);

        return parametros;
    }

    public ModelAndView mostrarTodosParaVisualizar(Request request, Response response) {
        Map<String, Object> parametros = this.mostrarProductosYCategoriasParametros(request,response);
        /*
        List<Egreso> egresosTotales = new ArrayList<>();

        for(int i=0; i< categorias.size();i++){
            egresosTotales.addAll(categorias.get(i).getEgresos());
        }

        parametros.put("egresosTotales",egresosTotales);
*/
        return new ModelAndView(parametros, "GESOC_VisualizarIngEgPorCategoria.hbs");
    }

    public ModelAndView mostrarProductosDeLasCategoria(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        CategoriaItem categoria = this.repo.buscar(Integer.valueOf(request.queryParams("categoria_id")));

        Repositorio<producto_x_categoria> repoProductoXCategoria = FactoryRepositorio.get(producto_x_categoria.class);
        List<producto_x_categoria> productosPorCategoria = repoProductoXCategoria.buscarTodos();

        Repositorio<Producto> repoProductos = FactoryRepositorio.get(Producto.class);
        List<Producto> productos = repoProductos.buscarTodos();

        List <Producto> ProductosDeLaCategoria = new ArrayList<>();

        for(int i = 0 ; i< productos.size(); i++){
            for(int j = 0 ; j < productosPorCategoria.size();j++){
                if(productos.get(i).getId() == productosPorCategoria.get(j).getProducto_id() && productosPorCategoria.get(j).getCategoria_id() == categoria.getId()){
                    ProductosDeLaCategoria.add(productos.get(i));
                }
            }
        }

        parametros.put("productos", ProductosDeLaCategoria);

        return new ModelAndView(parametros, "GESOC_VisualizarIngEgPorCategoria.hbs");
    }
/*
    public ModelAndView mostrarProductosDeUnaCategoria(Request request, Response response) {
        Map<String, Object> parametros = this.mostrarProductosYCategoriasParametros(request,response);

        List<Egreso> egresosTotales = new ArrayList<>();

        for(int i=0; i< categorias.size();i++){
            egresosTotales.addAll(categorias.get(i).getEgresos());
        }

        parametros.put("egresosTotales",egresosTotales);

        return new ModelAndView(parametros, "GESOC_VisualizarIngEgPorCategoria.hbs");
    }
*/
    public ModelAndView mostrarTodosParaAsociar(Request request, Response response) {
        Map<String, Object> parametros = this.mostrarProductosYCategoriasParametros(request,response);
        return new ModelAndView(parametros, "GESOC_AsociarItemACategoria.hbs");
    }

    public Response asociarProductoACategoria(Request request, Response response){

        if (request.queryParams("categoria_id") != null && request.queryParams("producto_id") != null ) {
            int idCategoria = Integer.valueOf(request.queryParams("categoria_id"));
            int idProducto = Integer.valueOf(request.queryParams("producto_id"));

            producto_x_categoria prod_x_cat = new producto_x_categoria();

            Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
            Producto producto = repoProducto.buscar(idProducto);

            prod_x_cat.setProducto_id(idProducto);
            prod_x_cat.setCategoria_id(idCategoria);

            this.repo.agregar(prod_x_cat);
        }

        response.redirect("/menu_logueado");
        return null;
    }

}
