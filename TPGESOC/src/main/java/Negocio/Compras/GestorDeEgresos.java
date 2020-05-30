package Negocio.Compras;

import java.util.ArrayList;

public class GestorDeEgresos {

    private static GestorDeEgresos instance = null;
    private ArrayList<Compra> compras = new ArrayList<Compra>();
    private ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

    private GestorDeEgresos() {
    }

    public static GestorDeEgresos GetInstance() {
        if (instance == null)
            instance = new GestorDeEgresos();
        return instance;
    }

    public void registrarUnaCompra(Compra unaCompra){
        compras.add(unaCompra);
    }

    public void registrarUnPresupuesto(Presupuesto unPresupuesto){
        presupuestos.add(unPresupuesto);
    }

    public Compra buscarCompraPorID(int unaCompraID){
        for(int i=0;i<compras.size();i++){
            if(compras.get(i).getIDCompra() == unaCompraID){
                return compras.get(i);
            }
        }
        return null;
    }
}
