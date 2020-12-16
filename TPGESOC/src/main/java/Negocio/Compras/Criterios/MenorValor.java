package Negocio.Compras.Criterios;

import Negocio.Compras.Presupuesto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenorValor extends Criterio {


    public MenorValor() {
    }

    public Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos) {
        Presupuesto aux;
        for(int i = 0;i < presupuestos.size()-1;i++){
            for(int j = 0;j < presupuestos.size()-i-1;j++){
                if(presupuestos.get(j+1).getValorTotal() <  presupuestos.get(j).getValorTotal()){
                    aux = presupuestos.get(j+1);
                    presupuestos.set(j+1,presupuestos.get(j));
                    presupuestos.set(j,aux);
                }
            }
        }
        if(!presupuestos.isEmpty()){
            return presupuestos.get(0);
        }else{
            return null;
        }

    }

    /*
    private Presupuesto busquedaPresupuestoMasBarato(List<Presupuesto> presupuestos) {
        Presupuesto aux;
        for(int i = 0;i < presupuestos.size()-1;i++){
            for(int j = 0;j < presupuestos.size()-i-1;j++){
                if(presupuestos.get(j+1).getValorTotal() <  presupuestos.get(j).getValorTotal()){
                    aux = presupuestos.get(j+1);
                    presupuestos.set(j+1,presupuestos.get(j));
                    presupuestos.set(j,aux);
                }
            }
        }
        return presupuestos.get(0);
    }
*/
    private double menorValor(List<Presupuesto> presupuestos) {
        return this.valoresTotales(presupuestos).stream().min(Comparator.naturalOrder()).get();
    }

    private List<Double> valoresTotales(List<Presupuesto> presupuestos){
        return presupuestos.stream().map(presupuesto -> presupuesto.valorTotal()).collect(Collectors.toList());
    }
}
