package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenValorPrimeroIngreso extends CriterioDeVinculacion {

    @Override
    public void vincular(List<Ingreso> ingresos, List<Egreso> egresos, Date fechaInicia, Date fechaFinal) {

        /*Orden-Valor-PrimeroIngreso: similar al anterior, pero se toma el primer ingreso y se asigna al
        primer egreso, luego el segundo ingreso con el primer egreso y así sucesivamente.*/

        ingresos = ordenarIngresosPorValor(ingresos);
        egresos = ordenarEgresosPorValor(egresos);

        for (int j = 0; j < egresos.size(); j++) {
            for (int i = 0; i < ingresos.size(); i++) {
                if (!egresos.get(j).isEstaVinculado() && ingresos.get(i).calcularMontoVinculable() >= egresos.get(j).getValorTotal() && egresos.get(j).estaEnElPeriodoAceptable(fechaInicia,fechaFinal)) {
                    ingresos.get(i).vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                    egresos.get(j).setEstaVinculado(true);
                    if (ingresos.get(i).calcularMontoVinculable() == 0) {
                        gestorDeIngresos.ingresoVinculado(ingresos.get(i));
                    }
                    break; //sacar si rompe xd
                }
            }
        }
    }
}