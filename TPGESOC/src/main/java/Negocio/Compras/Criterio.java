package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import java.util.ArrayList;
import java.util.List;

// no relacional

public abstract class Criterio{

    public abstract Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos);

}
