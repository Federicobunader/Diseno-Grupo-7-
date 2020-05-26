package Negocio;

import Negocio.Entidad.Entidad;

import java.util.ArrayList;
import java.util.Calendar;

public class Compra {

	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	private Calendar fechaDeOperacion;
	private MedioDePago medioDePago;
	private Documento documentoComercial;
	private Entidad entidad;
	private Proveedor proveedor;
	public boolean requierePresupuesto;
	public Criterio criterioEleccionPresupuesto;
	int IDCompra;

	Validador validador = Validador.GetInstance();

	private void validar(){
		if(requierePresupuesto) {
			validador.validarPresupuestosTotales(this);
			validador.validarSiSeUtilizoPresupuesto(this);
		}
		validador.validarEleccionCorrecta(this);
	}

	public int getIDCompra() {
		return IDCompra;
	}

	public Criterio getCriterioEleccionPresupuesto() {
		return criterioEleccionPresupuesto;
	}

	public void elegirEstrategia(Criterio unCriterio){
		criterioEleccionPresupuesto = unCriterio;
	}

	public int cantidadPresupuestos(){
		return presupuestos.size();
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public ArrayList<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public int valorTotal(){
		return 0;
	}
}//end Compra