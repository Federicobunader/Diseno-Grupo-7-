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

        return cantidadDePresupuestos >= cantidadDePresupuestosRequeridos;
    }

    public boolean seUtilizoPresupuesto(Compra unaCompra){

        for(int i = 0; i< unaCompra.getPresupuestos().size(); i++){
            if(unaCompra.getPresupuestos().get(i).getValorTotal() == unaCompra.valorTotal()){
                return true;
            }
        }
        return false;
    }

    public boolean eleccionCorrecta(Compra unaCompra) {

        Criterio unCriterio = unaCompra.getCriterioEleccionPresupuesto();

        return unaCompra.getPresupuestoElegido().getValorTotal() == unCriterio.elegirPresupuesto(unaCompra.getPresupuestos()).getValorTotal();
    }

    public boolean requierePresupuesto(double montoCompra, double montoDefinido){
        return montoCompra > montoDefinido;
    }

}
