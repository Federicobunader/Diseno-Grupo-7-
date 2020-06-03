package Negocio.Compras;


public class Validador {

    int cantidadDePresupuestosRequeridos = 3;

    private static Validador instance = null;

    public static Validador GetInstance() {
        if (instance == null)
            instance = new Validador();
        return instance;
    }

    public boolean tieneSuficientesPresupuestos(int cantidadDePresupuestos){

        return cantidadDePresupuestos == cantidadDePresupuestosRequeridos;
    }

    public boolean seUtilizoPresupuesto(Compra unaCompra){
         return unaCompra.getPresupuestos().stream().anyMatch(presupuesto -> presupuesto.valorTotal() == unaCompra.valorTotal());
    }

    public boolean eleccionCorrecta(Compra unaCompra) {

        Criterio unCriterio = unaCompra.criterioEleccionPresupuesto;

        return unaCompra.valorTotal() != unCriterio.elegirPresupuesto(unaCompra.getPresupuestos()).valorTotal();
    }
}
