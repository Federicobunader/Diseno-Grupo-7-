package Negocio.Compras;

import Negocio.Documento;
import Negocio.Entidad.Empresa.Empresa;
import Negocio.Entidad.EntidadBase;
import Negocio.Usuario.Mensaje;
import Negocio.Usuario.Usuario;

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
    private List<Egreso> egresosVinculados = new ArrayList<Egreso>();

    private List<Item> itemsAAgregarAUnEgreso = new ArrayList<>();
    private List<Usuario> usuariosRevisoresDeUnEgreso = new ArrayList<>();
    private List<Presupuesto> presupuestosDelEgreso = new ArrayList<>();
    private List<Documento> documentosDelEgreso = new ArrayList<>();
    private List<Empresa> empresasDelEgreso = new ArrayList<>();
    private List<EntidadBase> entidadBasesDelEgreso = new ArrayList<>();

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

    public static GestorDeEgresos getInstance() {
        return instance;
    }

    public void reiniciarDatosEgreso(){
        itemsAAgregarAUnEgreso.clear();
        usuariosRevisoresDeUnEgreso.clear();
        presupuestosDelEgreso.clear();
        documentosDelEgreso.clear();
        empresasDelEgreso.clear();
        entidadBasesDelEgreso.clear();
    }

    public void agregarItemAListaDeItemDeEgreso(Item item){
        itemsAAgregarAUnEgreso.add(item);
    }

    public void agregarUsuarioRevisorAListaDeUsuarioRevisoresDeEgreso(Usuario usuario){
        usuariosRevisoresDeUnEgreso.add(usuario);
    }

    public void agregarPresupuestoALaListaDePresupuestosDelEgreso (Presupuesto presupuesto){
        presupuestosDelEgreso.add(presupuesto);
    }

    public void agregarDocumentoALaListaDeDocumentosDelEgreso (Documento documento){
        documentosDelEgreso.add(documento);
    }

    public void guardarEntidadesProvisoriamente(List<Empresa> empresas ,List<EntidadBase> entidadBases){
        empresasDelEgreso.addAll(empresas);
        entidadBasesDelEgreso.addAll(entidadBases);
    }

    public List<Item> getItemsAAgregarAUnEgreso() {
        return itemsAAgregarAUnEgreso;
    }

    public List<Usuario> getUsuariosRevisoresDeUnEgreso() {
        return usuariosRevisoresDeUnEgreso;
    }

    public List<Presupuesto> getPresupuestosDelEgreso() {
        return presupuestosDelEgreso;
    }

    public ArrayList<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public List<Egreso> getEgresosVinculados() {
        return egresosVinculados;
    }

    public List<Documento> getDocumentosDelEgreso() {
        return documentosDelEgreso;
    }

    public List<Empresa> getEmpresasDelEgreso() {
        return empresasDelEgreso;
    }

    public List<EntidadBase> getEntidadBasesDelEgreso() {
        return entidadBasesDelEgreso;
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

    public void setEgresosVinculados(List<Egreso> egresosVinculados) {
        this.egresosVinculados = egresosVinculados;
    }
}
