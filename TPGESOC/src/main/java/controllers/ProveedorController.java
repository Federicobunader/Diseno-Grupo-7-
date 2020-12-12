package controllers;

import Negocio.Compras.Presupuesto;
import Negocio.Compras.Producto;
import Negocio.Documento;
import Negocio.Entidad.Empresa.Empresa;
import Negocio.Entidad.EntidadBase;
import Negocio.Proveedor;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProveedorController {

    private Repositorio<Proveedor> repo;

    public Repositorio<Proveedor> getRepo() {
        return repo;
    }

    public ProveedorController() {
        this.repo = FactoryRepositorio.get(Proveedor.class);
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Proveedor> productos = this.repo.buscarTodos();
        parametros.put("productos", productos);

        return new ModelAndView(parametros, "GESOC_CargarProductos.hbs");
    }

    public ModelAndView mostrarProductosDelProveedor(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();

        Proveedor proveedor = this.repo.buscar(Integer.valueOf(request.queryParams("id")));

        System.out.println("NOMBRE PROVEEDOR =" + proveedor.getNombre());
        System.out.println("CANTIDAD PRODUCTOS PROVEEDOR =" + proveedor.getProductos().size());
        parametros.put("productosProveedor",proveedor.getProductos());

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

        Repositorio<Proveedor> repoProveedor = FactoryRepositorio.get(Proveedor.class);
        List<Proveedor> proveedores = repoProveedor.buscarTodos();
        parametros.put("proveedores", proveedores);

        return new ModelAndView(parametros, "GESOC_CargaEgresos.hbs");
    }
}
