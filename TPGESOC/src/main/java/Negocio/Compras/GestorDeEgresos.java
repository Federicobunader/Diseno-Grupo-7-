package Negocio.Compras;

import java.util.ArrayList;

public class GestorDeEgresos {

    private static GestorDeEgresos instance = null;
    private ArrayList<Compra> comprasNoValidadas = new ArrayList<Compra>();
    private ArrayList<Compra> comprasValidadas = new ArrayList<Compra>();
    private ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
    private ArrayList<Egreso> egresos = new ArrayList<Egreso>();

    private GestorDeEgresos() {
    }

    public ArrayList<Egreso> getEgresos() {
        return egresos;
    }

    public static GestorDeEgresos GetInstance() {
        if (instance == null)
            instance = new GestorDeEgresos();
        return instance;
    }

    public ArrayList<Compra> getComprasValidadas() { return comprasValidadas;     }

    public ArrayList<Compra> getComprasNoValidadas() {
        return comprasNoValidadas;
    }

    public void registrarUnPresupuesto(Presupuesto unPresupuesto){
        presupuestos.add(unPresupuesto);
    }

    public void vaciarListaDeNoValidadas(){
        comprasNoValidadas.clear();
    }

    public Compra buscarCompraPorID(int unaCompraID){
        for(int i=0;i<comprasNoValidadas.size();i++){
            if(comprasNoValidadas.get(i).getIDCompra() == unaCompraID){
                return comprasNoValidadas.get(i);
            }
        }
        return null;
    }

    private void registrarCompra(Compra unaCompra){
        comprasNoValidadas.add(unaCompra);
        if(unaCompra.tienePresupuesto()) {
            this.registrarUnPresupuesto(unaCompra.getPresupuestoElegido());
        }
    }

    public void agregarCompraValidada(Compra unaCompra){
        comprasValidadas.add(unaCompra);
    }

}
