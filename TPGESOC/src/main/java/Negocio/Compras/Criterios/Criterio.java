package Negocio.Compras.Criterios;

import BaseDeDatos.EntidadPersistente;
import Negocio.Compras.Presupuesto;

import java.util.ArrayList;
import java.util.List;

// no relacional

public abstract class Criterio{

    public abstract Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos);

}
