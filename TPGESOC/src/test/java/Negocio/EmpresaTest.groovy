package Negocio

class EmpresaTest{
    void setUp() {
        Empresa unaEmpresaAgropecuaria = new Empresa(10,10,"Agropecuaria");
    }

    void tearDown() {
    }

    void testContratarPersonal_5Empleados_pasaDeMicroAPequenia_() {
        Empresa unaEmpresaAgropecuaria = new Empresa(1,1,"Agropecuaria");
        unaEmpresaAgropecuaria.contratarPersonal(5);
        assertEquals("Se actualiza exitosamente",pequeniaEmpresa,unaEmpresaAgropecuaria.getSector());
    }

    void testDespedirPersonal() {
    }

    void testAumentarVentas() {
    }

    void testDisminuirVentas() {
    }
}
