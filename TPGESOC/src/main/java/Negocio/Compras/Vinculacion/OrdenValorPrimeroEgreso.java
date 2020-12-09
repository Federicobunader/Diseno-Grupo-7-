package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public class OrdenValorPrimeroEgreso extends CriterioDeVinculacion{

    @Override
    public void vincular(List<Ingreso> ingresos,List<Egreso> egresos) {
        ingresos = ordenarIngresosPorValor(ingresos);
        egresos = ordenarEgresosPorValor(egresos);

        for (int j = 0; j < egresos.size(); j++) {
            for (int i = 0; i < ingresos.size(); i++) {
                if (ingresos.get(i).calcularMontoVinculable() >= egresos.get(j).getValorTotal()/* && egresos.get(j).estaEnElPeriodoAceptable()*/) {
                    ingresos.get(i).vincularEgreso(egresos.get(j));
                    gestorDeEgresos.egresoVinculado(egresos.get(j));
                    if (ingresos.get(i).calcularMontoVinculable() == 0) {
                        gestorDeIngresos.ingresoVinculado(ingresos.get(i));
                    }
                    break; //sacar si rompe xd
                }
            }
        }
    }
}
