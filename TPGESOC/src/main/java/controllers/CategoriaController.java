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

    public ModelAndView mostrarProductosDeUnaCategoria(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        List<CategoriaItem> categorias = this.repo.buscarTodos();
        parametros.put("categorias", categorias);

        CategoriaItem categoria = this.repo.buscar(Integer.valueOf(request.queryParams("unaCategoria")));

        parametros.put("productos", categoria.getProductos());

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

    public ModelAndView mostrarTodosLosProductosDeUnaCategoria(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
/*
        if (request.queryParams("categoria") != null) {
            int idCategoria = Integer.valueOf(request.queryParams("categoria"));
            CategoriaItem categoriaABuscar = this.repo.buscar(idCategoria);

            List<Egreso> egresosDeLaCategoria = categoriaABuscar.getEgresos();

            parametros.put("egresosDeLaCategoria", egresosDeLaCategoria);
        }
*/

        return new ModelAndView(parametros, "GESOC_VisualizarIngEgPorCategoria.hbs");
    }

    public Response asociarProductoACategoria(Request request, Response response){

        if (request.queryParams("categoria_id") != null && request.queryParams("producto_id") != null ) {
            int idCategoria = Integer.valueOf(request.queryParams("categoria_id"));
            int idProducto = Integer.valueOf(request.queryParams("producto_id"));
            CategoriaItem categoriaABuscar = this.repo.buscar(idCategoria);

            Repositorio<Producto> repoProducto = FactoryRepositorio.get(Producto.class);
            Producto producto = repoProducto.buscar(idProducto);

            categoriaABuscar.asociarProductoACategoria(producto);

            //this.repo.modificar(categoriaABuscar);
        }

        response.redirect("/menu_logueado");
        return null;
    }

}
