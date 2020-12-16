package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenValorPrimeroEgreso extends CriterioDeVinculacion{

    @Override
    public void vincular(List<Ingreso> ingresos, List<Egreso> egresos, Date fechaInicia, Date fechaFinal) {

        /*Orden-Valor-PrimeroEgreso: se ordenan los egresos por valor, se orden los ingresos; luego
        se asigna el egreso al primer ingreso, luego el segundo egreso al ingreso y así
        sucesivamente; luego de terminar con todos los egresos, se procesan para el segundo
        ingreso.*/

        ingresos = ordenarIngresosPorValor(ingresos);
        egresos = ordenarEgresosPorValor(egresos);
        ArrayList<Egreso> egresosVinculados = new ArrayList<Egreso>();

        for (int i = 0; i < ingresos.size(); i++) {
            for (int j = 0; j < egresos.size(); j++) {
                if (ingresos.get(i).calcularMontoVinculable() >= egresos.get(j).getValorTotal() && egresos.get(j).estaEnElPeriodoAceptable(fechaInicia,fechaFinal) && !egresosVinculados.contains(egresos.get(j))) {
                    ingresos.get(i).vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                    egresosVinculados.add(egresos.get(j));
                    if (ingresos.get(i).calcularMontoVinculable() == 0) {
                        gestorDeIngresos.ingresoVinculado(ingresos.get(i));
                        break; //sacar si rompe xd
                    }
                }
            }
        }
    }
}
