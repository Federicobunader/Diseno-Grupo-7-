package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public class PorFecha extends CriterioDeVinculacion {

    @Override
    public void vincular(Ingreso unIngreso) {
        egresos.addAll(gestorDeEgresos.ordenarPorFecha());
        ingresos.addAll(gestorDeIngresos.getIngresos());

        for(int i = 0; i < ingresos.size(); i++){

            for(int j = 0; j < egresos.size(); j++) {
                if (ingresos.get(i).montoVinculable() >= egresos.get(j).getCompra().getMonto() && egresos.get(i).estaEnElPeriodoAceptale()) {
                    ingresos.get(i).vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                }
            }
            if(ingresos.get(i).montoVinculable() == 0) {
                gestorDeIngresos.ingresoVinculado(ingresos.get(i));
            }
        }
    }

}
