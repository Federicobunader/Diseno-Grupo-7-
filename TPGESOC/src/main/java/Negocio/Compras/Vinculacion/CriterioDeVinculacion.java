package Negocio.Compras.Vinculacion;

import Negocio.Compras.Egreso;
import Negocio.Compras.GestorDeEgresos;
import Negocio.Compras.GestorDeIngresos;
import Negocio.Compras.Ingreso;

import java.util.List;

public abstract class CriterioDeVinculacion {

    public abstract void vincular(Ingreso unIngreso);

    protected GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
    protected GestorDeIngresos gestorDeIngresos = GestorDeIngresos.GetInstance();

    protected List<Egreso> egresos;
    protected List<Ingreso> ingresos;
}
