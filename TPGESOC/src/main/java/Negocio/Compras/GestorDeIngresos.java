package Negocio.Compras;

import InterfazDeUsuario.InterfazUsuarios;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class GestorDeIngresos {
    private static GestorDeIngresos instance = null;
    private ArrayList<Ingreso> ingresos = new ArrayList<Ingreso>();
    InterfazUsuarios interfaz = InterfazUsuarios.GetInstance();
    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public static GestorDeIngresos GetInstance() {
        if (instance == null)
            instance = new GestorDeIngresos();
        return instance;
    }

    public void agregarIngreso() {
        String descripcionDelIngreso = interfaz.pedirString("Ingrese la descripción del ingreso: ");
        double montoTotal = interfaz.pedirDouble("Ingrese el monto total del ingreso: ");

        Ingreso nuevoIngreso = new Ingreso(descripcionDelIngreso, montoTotal);

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
    }
}
