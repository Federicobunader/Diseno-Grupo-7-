package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public class PorFecha extends CriterioDeVinculacion {

    @Override
    public void vincular(List<Ingreso> ingresos,List<Egreso> egresos) {
        ingresos = ordenarIngresosPorValor(ingresos);
        egresos = ordenarEgresosPorFecha(egresos);

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

    private List<Egreso> ordenarEgresosPorFecha(List<Egreso> egresos) {
        Egreso aux;
        for(int i = 0;i < egresos.size()-1;i++){
            for(int j = 0;j < egresos.size()-i-1;j++){
                if(egresos.get(j+1).getFechaDeOperacion().before(egresos.get(j).getFechaDeOperacion())){
                    aux = egresos.get(j+1);
                    egresos.set(j+1,egresos.get(j));
                    egresos.set(j,aux);
                }
            }
        }
        return egresos;
    }
}
