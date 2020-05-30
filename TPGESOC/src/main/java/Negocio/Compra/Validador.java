package Negocio.Compra;


public class Validador {

    Validador validador = Validador.GetInstance();
    String mensaje;


    int cantidadDePresupuestosRequeridos;

    private static Validador instance = null;

    public static Validador GetInstance() {
        if (instance == null)
            instance = new Validador();
        return instance;
    }

    public String tieneSuficientesPresupuestos(int cantidadDePresupuestos, int compraID){

        if(cantidadDePresupuestos != cantidadDePresupuestosRequeridos) {
           return "La compra " + compraID + " no tiene la cantidad de presupuestos requeridos.";
        }else{
           return "La compra " + compraID + " tiene la cantidad de presupuestos requeridos";
        }
    }

    public String seUtilizoPresupuesto(Compra unaCompra){
        if(unaCompra.getPresupuestos().stream().anyMatch(presupuesto -> presupuesto.valorTotal() == unaCompra.valorTotal())){
           return "En la compra " + unaCompra.getIDCompra() + " se utilizo un presupuesto de los asignados.";
        }
        return "En la compra " + unaCompra.getIDCompra() + " no se utilizo ningun presupuesto de los asignados.";
    }

    public String eleccionCorrecta(Compra unaCompra){

        Criterio unCriterio = unaCompra.criterioEleccionPresupuesto;

        if(unaCompra.valorTotal() != unCriterio.elegirPresupuesto(unaCompra.getPresupuestos()).valorTotal()){
            return "Para la compra " + unaCompra.getIDCompra() + " no se eligio el presupuesto a partir del criterio " + unCriterio.getNombreCriterio();
        }
        return "Para la compra " + unaCompra.getIDCompra() + " se eligio el presupuesto a partir del criterio " + unCriterio.getNombreCriterio();
    }

}
