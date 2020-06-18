package Negocio.Compras;

import Negocio.Compras.Criterios.MenorValor;
import Negocio.Documento;
import Negocio.Entidad.Entidad;
import Negocio.Entidad.EntidadBase;
import Negocio.MedioDePago;
import Negocio.Proveedor;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;

public class CompraTest {

    Compra compra1;
    Proveedor proveedor1, proveedor2, proveedor3,proveedor4;
    Producto producto1, producto2, producto3;
    Documento documento;
    Presupuesto presupuesto1, presupuesto2, presupuesto3;
    Usuario usuario1, usuario2, usuario3;
    Item item1,item2,item3,item4,item5,item6,item7,item8;
    ArrayList<Documento> documentosComerciales;
    ArrayList<Item> items1, items2, items3;
    ArrayList<Presupuesto> presupuestos1;
    ArrayList<Usuario> usuarios;
    EntidadBase entidadbase1;

    @Before public void setUp() {


        proveedor1 = new Proveedor("Harry Styles",1);
        proveedor2 = new Proveedor("Gonzalo Miele",2);
        proveedor3 = new Proveedor("Justin Bieber",3);
        proveedor4 = new Proveedor("C.R.O.",4);

        producto1 = new Producto(5,proveedor1,"Silla");
        producto2 = new Producto(10,proveedor2,"Mesa");
        producto3 = new Producto(15,proveedor3,"Sillon");

        ArrayList<CategoriaItem> categorias = new ArrayList<CategoriaItem>();

        CategoriaItem categoria1 = new CategoriaItem("Roble","Muebles");
        CategoriaItem categoria2 = new CategoriaItem("Pino","Muebles");

        categorias.add(categoria1);
        categorias.add(categoria2);

        CriterioDeItem criterioDeItem = new CriterioDeItem("Muebles");

        item1 = new Item(producto1,5, categorias);
        item2 = new Item(producto2,15, categorias);
        item3 = new Item(producto3,25, categorias);
        item4 = new Item(producto1,150, categorias);
        item5 = new Item(producto2,50, categorias);
        item6 = new Item(producto3,100, categorias);
        item7 = new Item(producto1,500, categorias);
        item8 = new Item(producto2,200, categorias);

        documento = new Documento(1,'a');

        documentosComerciales = new ArrayList<Documento>();
        documentosComerciales.add(documento);

        items1 = new ArrayList<Item>();
        items1.add(item1);
        items1.add(item2);
        items1.add(item3);

        items2 = new ArrayList<Item>();
        items2.add(item4);
        items2.add(item5);
        items2.add(item6);

        items3 = new ArrayList<Item>();
        items3.add(item7);
        items3.add(item2);
        items3.add(item8);

        presupuesto1 = new Presupuesto(items1,documentosComerciales);
        presupuesto2 = new Presupuesto(items2,documentosComerciales);
        presupuesto3 = new Presupuesto(items3,documentosComerciales);

        presupuestos1 = new ArrayList<Presupuesto>();
        presupuestos1.add(presupuesto1);
        presupuestos1.add(presupuesto2);
        presupuestos1.add(presupuesto3);

        usuario1 = new Usuario("Federico di Napoli", "AguanteRiver01");
        usuario2 = new Usuario("Alejandro Hassan","Pablo Mendez02");
        usuario3 = new Usuario("Juan Francisco Nadal", "AwanteLaUBA123");

        usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        //MedioDePago medioDePago = new MedioDePago(MedioDePago.tipoDePago.creditCard,1); //no se que onda esto

        entidadbase1 = new EntidadBase("EntidadBase" ,"descripcion");

        compra1 = new Compra(items1,presupuestos1, documento, entidadbase1,proveedor1, true, new MenorValor(), 1000);

    }
    @Test
    public void suscribirUsuario() {
        compra1.suscribirUsuario(usuario1);
        compra1.suscribirUsuario(usuario2);
        compra1.suscribirUsuario(usuario3);

        Assert.assertEquals(3, compra1.getUsuariosRevisores().size());
    }

    @Test
    public void verificarPresupuestoElegido() {
        compra1.seleccionarPresupuesto();

        Assert.assertEquals(presupuesto1,compra1.getPresupuestoElegido());
    }

    @Test
    public void verificarValidacion() {
        compra1.suscribirUsuario(usuario1);
        compra1.seleccionarPresupuesto();
        compra1.validar();

        Assert.assertEquals(3,usuario1.getBandejaDeMensajes().size());
    }
    @Test
    public void verificarEnUsuarioSuscriptoQueSeEligioElPresupuesto() {
        compra1.suscribirUsuario(usuario1);
        compra1.seleccionarPresupuesto();
        compra1.validar();

        Assert.assertEquals("Para la compra 1000 se eligio el presupuesto a partir del criterio",usuario1.getBandejaDeMensajes().get(2));
    }

    @Test
    public void verificarEleccionDePresupuestoConOtroPresupuesto() {
        ArrayList<CategoriaItem> categorias = new ArrayList<CategoriaItem>();

        CategoriaItem categoria1 = new CategoriaItem("Roble","Muebles");
        CategoriaItem categoria2 = new CategoriaItem("Pino","Muebles");

        categorias.add(categoria1);
        categorias.add(categoria2);

        CriterioDeItem criterioDeItem = new CriterioDeItem("Muebles");

        Item itemNuevo = new Item(producto3,5,categorias);
        ArrayList<Item> itemsNuevos = new ArrayList<Item>();
        itemsNuevos.add(item1);
        itemsNuevos.add(item2);
        itemsNuevos.add(itemNuevo);
        presupuesto3.setItems(itemsNuevos);
        compra1.seleccionarPresupuesto();

        Assert.assertEquals(presupuesto3, compra1.getPresupuestoElegido());
    }
}