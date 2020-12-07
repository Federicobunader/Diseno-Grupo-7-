package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public class OrdenValorPrimeroIngreso extends CriterioDeVinculacion {

    @Override
    public void vincular(Ingreso ingreso,List<Egreso> egresos) {

        for(int j = 0; j < egresos.size(); j++){

            System.out.println("PRESUPUESTO =" + egresos.get(j).getCompra().getPresupuestoElegido());
            if(egresos.get(j).getCompra().getPresupuestoElegido()!= null ) {

                System.out.println("MONTO INGRESO VINCULABLE = "+ ingreso.montoVinculable());
                System.out.println("MONTO EGRESO = "+ egresos.get(j).getCompra().getMonto());
               // if (ingreso.montoVinculable() >= egresos.get(j).getCompra().getMonto() /*&& egresos.get(j).estaEnElPeriodoAceptable()*/) {
                    System.out.println("ENTRE A LA PARTE DE VINCULACIONN");
                    ingreso.vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                    if (ingreso.montoVinculable() == 0) {
                        gestorDeIngresos.ingresoVinculado(ingreso);
                    }
                    break;
              //  }
            }

        }
    }
}
