package Negocio.Compras;

import InterfazDeUsuario.InterfazUsuarios;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorDeIngresos {
    private static GestorDeIngresos instance = null;
    private List<Ingreso> ingresos = new ArrayList<Ingreso>();
    private List<Ingreso> ingresosNoVinculados = new ArrayList<Ingreso>();
    InterfazUsuarios interfaz = InterfazUsuarios.GetInstance();
    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public static GestorDeIngresos GetInstance() {
        if (instance == null)
            instance = new GestorDeIngresos();
        return instance;
    }

    public List<Ingreso> getIngresosNoVinculados() {
        return ingresosNoVinculados;
    }

    public List<Ingreso> ordenarPorValor(){
        Ingreso auxiliar;

        List<Ingreso> listaDuplicada = new ArrayList<Ingreso>();;
        listaDuplicada.addAll(ingresosNoVinculados);

        for(int i = 2; i < listaDuplicada.size(); i++)
        {
            for(int j = 0;j < listaDuplicada.size()-1;j++)
            {
                if(listaDuplicada.get(j).getMontoTotal() > listaDuplicada.get(j+1).getMontoTotal())
                {
                    auxiliar = listaDuplicada.get(j);
                    listaDuplicada.set(j,listaDuplicada.get(j+1));
                    listaDuplicada.set(j+1,auxiliar);
                }
            }
        }
        return listaDuplicada;
    }


    public void agregarIngreso() {
        String descripcionDelIngreso = interfaz.pedirString("Ingrese la descripción del ingreso: ");
        double montoTotal = interfaz.pedirDouble("Ingrese el monto total del ingreso: ");

        Ingreso nuevoIngreso = new Ingreso(descripcionDelIngreso, montoTotal);

        /*
            int listaSize = gestorDeEgresos.filtrarSegunMontoMenorOIgualA(montoTotal).size();
            interfaz.mostrarInformacion("Puede asociar el ingreso a los siguientes egresos: ");
            int contador = 1;
            for (int i = 0; i < listaSize; i++) {
                if(gestorDeEgresos.filtrarSegunMontoMenorOIgualA(montoTotal).get(i).estaEnElPeriodoAceptale()) {
                    interfaz.mostrarInformacion(contador + "- " + gestorDeEgresos.filtrarSegunMontoMenorOIgualA(montoTotal).get(i).getCompra().getIDCompra());
                    contador++;
                }
            }

            int egresoElegido = interfaz.pedirInt("Ingrese el ID de egreso a asociar: ");

            for (int i = 0; i < listaSize; i++) {
                if (gestorDeEgresos.getEgresos().get(i).getCompra().getIDCompra() == egresoElegido) {
                    nuevoIngreso.asociarseAEgreso(gestorDeEgresos.getEgresos().get(i));
                }
            }

         */
    }

    public void ingresoVinculado(Ingreso ingreso) {
        ingresosNoVinculados.remove(ingreso);
        ingresos.add(ingreso);
    }
}
