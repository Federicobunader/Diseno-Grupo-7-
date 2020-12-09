package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.*;

public abstract class CriterioDeVinculacion {

    public abstract void vincular(List<Ingreso> ingresos, List<Egreso> egresos, Date fechaInicia, Date fechaFinal);

    protected GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
    protected GestorDeIngresos gestorDeIngresos = GestorDeIngresos.GetInstance();

    //protected List<Egreso> egresos;
    protected List<Ingreso> ingresos;

    public List<Egreso> ordenarEgresosPorValor(List<Egreso> egresos) {
        Egreso aux;
        for(int i = 0;i < egresos.size()-1;i++){
            for(int j = 0;j < egresos.size()-i-1;j++){
                if(egresos.get(j+1).getValorTotal() < egresos.get(j).getValorTotal()){
                    aux = egresos.get(j+1);
                    egresos.set(j+1,egresos.get(j));
                    egresos.set(j,aux);
                }
            }
        }
        return egresos;
    }

    public List<Ingreso> ordenarIngresosPorValor(List<Ingreso> ingresos) {
        Ingreso aux;
        for(int i = 0;i < ingresos.size()-1;i++){
            for(int j = 0;j < ingresos.size()-i-1;j++){
                if(ingresos.get(j+1).getMontoTotal() <  ingresos.get(j).getMontoTotal()){
                    aux = ingresos.get(j+1);
                    ingresos.set(j+1,ingresos.get(j));
                    ingresos.set(j,aux);
                }
            }
        }
        return ingresos;
    }

}
