package Negocio.Compras;


import Negocio.Compras.Criterios.Criterio;

public class Validador {

    int cantidadDePresupuestosRequeridos;
    double montoDefinido;

    private static Validador instance = null;

    public static Validador GetInstance() {
        if (instance == null)
            instance = new Validador();
        return instance;
    }

    public void setCantidadDePresupuestosRequeridos(int cantidadDePresupuestosRequeridos) {
        this.cantidadDePresupuestosRequeridos = cantidadDePresupuestosRequeridos;
    }

    public void setMontoDefinido(double montoDefinido) {
        this.montoDefinido = montoDefinido;
    }

    public double getMontoDefinido() {
        return montoDefinido;
    }

    public boolean tieneSuficientesPresupuestos(int cantidadDePresupuestos){

        return cantidadDePresupuestos == cantidadDePresupuestosRequeridos;
    }

    public boolean seUtilizoPresupuesto(Compra unaCompra){
         return unaCompra.getPresupuestos().stream().anyMatch(presupuesto -> presupuesto.valorTotal() == unaCompra.valorTotal());
    }

    public boolean eleccionCorrecta(Compra unaCompra) {

        Criterio unCriterio = unaCompra.getCriterioEleccionPresupuesto();

        return unaCompra.valorTotal() != unCriterio.elegirPresupuesto(unaCompra.getPresupuestos()).valorTotal();
    }

    public boolean requierePresupuesto(double monto){
        return monto > montoDefinido;
    }

}
