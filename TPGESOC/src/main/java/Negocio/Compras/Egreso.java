package Negocio.Compras;

public class Egreso {
    //agregar al diagrama de clases

    private Compra unaCompra;

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public Egreso(Compra unaCompra) {
        this.unaCompra = unaCompra;
    }

    private void registrarCompra(){
        gestorDeEgresos.registrarUnaCompra(unaCompra);
        if(unaCompra.getPresupuestoElegido() != null) {
            this.registrarPresupuesto();
        }
    }

    private void registrarPresupuesto(){
        gestorDeEgresos.registrarUnPresupuesto(unaCompra.getPresupuestoElegido());
    }
}
