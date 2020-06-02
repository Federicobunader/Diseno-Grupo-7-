package Negocio.Entidad.Empresa;

import org.junit.Assert;

import static org.junit.Assert.*;

public class EmpresaTest {

    @org.junit.Before
    public void setUp() throws Exception {
        Empresa unaEmpresa = new Empresa(10,10,"Agropecuaria");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void contratarPersonal() {
        Empresa unaEmpresa = new Empresa(10,10,"Agropecuaria");

        unaEmpresa.contratarPersonal(10);
        Assert.assertEquals(unaEmpresa.getCantidadDePersonal(),20);
    }

    @org.junit.Test
    public void despedirPersonal() {
    }

    @org.junit.Test
    public void aumentarVentas() {
    }

    @org.junit.Test
    public void disminuirVentas() {
    }
}