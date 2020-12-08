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

            if(egresos.get(j).getCompra().getPresupuestoElegido()!= null ) {

                if (ingreso.calcularMontoVinculable() >= egresos.get(j).getValorTotal() /*&& egresos.get(j).estaEnElPeriodoAceptable()*/) {
                    ingreso.vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                    if (ingreso.calcularMontoVinculable() == 0) {
                        gestorDeIngresos.ingresoVinculado(ingreso);
                    }

                }
            }

        }
    }
}
