package Negocio.Compras;

import Negocio.Usuario.Mensaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;

public class GestorDeEgresos {

    private static GestorDeEgresos instance = null;
    private ArrayList<Compra> comprasNoValidadas = new ArrayList<Compra>();
    private ArrayList<Compra> comprasValidadas = new ArrayList<Compra>();
    private ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

    private List<Egreso> egresos = new ArrayList<Egreso>();
    private ArrayList<Egreso> egresosVinculados = new ArrayList<Egreso>();

    private Date fechaAcceptableDesde;
    private Date fechaAcceptableHasta;


    public List<Egreso> ordenarPorValor(){
        Egreso auxiliar;

        List<Egreso> listaDuplicada = new ArrayList<Egreso>();
        listaDuplicada.addAll(egresos);

        for(int i = 2; i < listaDuplicada.size(); i++)
        {
            for(int j = 0;j < listaDuplicada.size()-1;j++)
            {
                if(listaDuplicada.get(j).getCompra().getMonto() > listaDuplicada.get(j+1).getCompra().getMonto())
                {
                    auxiliar = listaDuplicada.get(j);
                    listaDuplicada.set(j,listaDuplicada.get(j+1));
                    listaDuplicada.set(j+1,auxiliar);
                }
            }
        }
        return listaDuplicada;
    }

    public List<Egreso> ordenarPorFecha(){
        Egreso auxiliar;

        List<Egreso> listaDuplicada = new ArrayList<Egreso>();
        listaDuplicada.addAll(egresos);

        for(int i = 2; i < listaDuplicada.size(); i++)
        {
            for(int j = 0;j < listaDuplicada.size()-1;j++)
            {
                if(listaDuplicada.get(j).getFechaDeOperacion().after(listaDuplicada.get(j+1).getFechaDeOperacion()))
                {
                    auxiliar = listaDuplicada.get(j);
                    listaDuplicada.set(j,listaDuplicada.get(j+1));
                    listaDuplicada.set(j+1,auxiliar);
                }
            }
        }
        return listaDuplicada;
    }

    public Date getFechaAcceptableDesde() {
        return fechaAcceptableDesde;
    }

    public Date getFechaAcceptableHasta() {
        return fechaAcceptableHasta;
    }

    private GestorDeEgresos() {
    }

    public List<Egreso> getEgresos() {
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

    public void registrarCompra(Compra unaCompra){
        comprasNoValidadas.add(unaCompra);
        if(unaCompra.tienePresupuesto()) {
            this.registrarUnPresupuesto(unaCompra.getPresupuestoElegido());
        }
    }

    public void agregarCompraValidada(Compra unaCompra){
        comprasValidadas.add(unaCompra);
    }

    public List<Egreso> filtrarSegunMontoMenorOIgualA(double monto){
        return egresos.stream().filter(egreso -> egreso.getCompra().getMonto() <= monto).collect(Collectors.toList());
    }

    public void egresoVinculado(Egreso egreso) {
        egresos.remove(egreso);
        egresosVinculados.add(egreso);
    }
}
