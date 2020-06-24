package Negocio.Compras;

import InterfazDeUsuario.InterfazUsuarios;

import java.util.ArrayList;

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

    public void agregarIngreso(){
        String descripcionDelIngreso = interfaz.pedirString("Ingrese la descripción del ingreso: ");
        double montoTotal = interfaz.pedirDouble("Ingrese el monto total del ingreso: ");

        Ingreso nuevoIngreso = new Ingreso(descripcionDelIngreso,montoTotal);

        interfaz.mostrarInformacion("¿Desea asociar el ingreso a un egreso?");
        interfaz.mostrarInformacion("1- Si");
        String opcion = interfaz.pedirString("2- No");

        if(opcion.equals("1")){
            int listaSize = gestorDeEgresos.getEgresos().size();
            interfaz.mostrarInformacion("Puede asociar el ingreso a los siguientes egresos: ");
            for(int i = 0; i<listaSize; i++){
                interfaz.mostrarInformacion((i+1) + "- " + gestorDeEgresos.getEgresos().get(i).getCompra().getIDCompra());
            }

            int egresoElegido = interfaz.pedirInt("Ingrese el ID de egreso a asociar: ");

            for(int i = 0; i<listaSize; i++) {
                if(gestorDeEgresos.getEgresos().get(i).getCompra().getIDCompra()  == egresoElegido){
                    nuevoIngreso.asociarseAEgreso(gestorDeEgresos.getEgresos().get(i));
                }
            }
        }
    }

}
