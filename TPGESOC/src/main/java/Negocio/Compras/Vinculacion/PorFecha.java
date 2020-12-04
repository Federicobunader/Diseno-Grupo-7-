package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public class PorFecha extends CriterioDeVinculacion {

    @Override
    public void vincular(Ingreso ingreso) {
        egresos.addAll(gestorDeEgresos.ordenarPorFecha());
        ingresos.addAll(gestorDeIngresos.getIngresos());

            for(int j = 0; j < egresos.size(); j++) {
                if (ingreso.montoVinculable() >= egresos.get(j).getCompra().getMonto() && egresos.get(j).estaEnElPeriodoAceptable()) {
                    ingreso.vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                }
            }
            if(ingreso.montoVinculable() == 0) {
                gestorDeIngresos.ingresoVinculado(ingreso);
            }

    }

}
