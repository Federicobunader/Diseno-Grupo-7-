package Negocio.Compras;


import Negocio.Compras.Criterios.Criterio;

public class Validador {

    private static Validador instance = null;

    public static Validador GetInstance() {
        if (instance == null)
            instance = new Validador();
        return instance;
    }

    public boolean tieneSuficientesPresupuestos(int cantidadDePresupuestos, int cantidadDePresupuestosRequeridos){

        return cantidadDePresupuestos == cantidadDePresupuestosRequeridos;
    }

    public boolean seUtilizoPresupuesto(Compra unaCompra){
         return unaCompra.getPresupuestos().stream().anyMatch(presupuesto -> presupuesto.valorTotal() == unaCompra.valorTotal());
    }

    public boolean eleccionCorrecta(Compra unaCompra) {

        Criterio unCriterio = unaCompra.getCriterioEleccionPresupuesto();

        return unaCompra.valorTotal() != unCriterio.elegirPresupuesto(unaCompra.getPresupuestos()).valorTotal();
    }

    public boolean requierePresupuesto(double montoCompra, double montoDefinido){
        return montoCompra > montoDefinido;
    }

}
