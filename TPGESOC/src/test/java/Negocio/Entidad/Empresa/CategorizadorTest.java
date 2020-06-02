package Negocio.Entidad.Empresa;

import Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas.MedianaTramo1;
import Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas.MedianaTramo2;
import Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas.MicroEmpresa;
import Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas.PequeniaEmpresa;
import org.junit.Assert;
import org.junit.Test;

import javax.print.attribute.standard.Media;

import static org.junit.Assert.*;

public class CategorizadorTest {

    @Test
    public void calcularTipoDeEmpresa() {
        Empresa unaEmpresa = new Empresa(10,10,"Agropecuario");
        Assert.assertEquals(unaEmpresa.getTipoDeEmpresa(), PequeniaEmpresa.GetInstance());
    }

    @Test
    public void calcularTipoDeEmpresa1() {
        Empresa unaEmpresa = new Empresa(1503,200,"Comercio");
        Assert.assertEquals(unaEmpresa.getTipoDeEmpresa(), MedianaTramo2.GetInstance());
    }
    @Test
    public void calcularTipoDeEmpresa2() {
        Empresa unaEmpresa = new Empresa(100,50,"Construccion");
        Assert.assertEquals(unaEmpresa.getTipoDeEmpresa(), MedianaTramo1.GetInstance());
    }
    @Test
    public void calcularTipoDeEmpresa3() {
        Empresa unaEmpresa = new Empresa(51,10,"Servicios");
        Assert.assertEquals(unaEmpresa.getTipoDeEmpresa(), MedianaTramo1.GetInstance());
    }
    @Test
    public void calcularTipoDeEmpresa4() {
        Empresa unaEmpresa = new Empresa(14,9,"IndustriaYMinera");
        Assert.assertEquals(unaEmpresa.getTipoDeEmpresa(), MicroEmpresa.GetInstance());
    }
}