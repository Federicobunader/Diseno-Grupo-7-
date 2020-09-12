package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public class OrdenValorPrimeroIngreso extends CriterioDeVinculacion {

    @Override
    public void vincular(Ingreso unIngreso) {
        egresos.addAll(gestorDeEgresos.getEgresos());
        ingresos.addAll(gestorDeIngresos.ordenarPorValor());

        for(int j = 0; j < egresos.size(); j++){

            for(int i = 0; i < ingresos.size(); i++) {
                if (ingresos.get(i).montoVinculable() >= egresos.get(j).getCompra().getMonto() && egresos.get(j).estaEnElPeriodoAceptale()) {
                    ingresos.get(i).vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                    if(ingresos.get(i).montoVinculable() == 0){
                        gestorDeIngresos.ingresoVinculado(ingresos.get(i));
                    }
                    break;
                }
            }
        }
    }
}
