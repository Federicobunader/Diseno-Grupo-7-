package Negocio.Compras;

import Negocio.*;
import Negocio.Entidad.Entidad;
import Negocio.Usuario.Usuario;

import java.util.ArrayList;
import java.util.Calendar;

public class Compra {

	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	private ArrayList<Usuario> usuariosRevisores = new ArrayList<Usuario>();
	private Presupuesto presupuestoElegido;
	//private Calendar fechaDeOperacion;
	//private MedioDePago medioDePago;
	private Documento documentoComercial;
	private Entidad entidad;
	private Proveedor proveedor;
	public boolean requierePresupuesto;
	public Criterio criterioEleccionPresupuesto;
	private int IDCompra;

	public Compra(ArrayList<Item> items, ArrayList<Presupuesto> presupuestos/*, Calendar fechaDeOperacion, MedioDePago medioDePago*/, Documento documentoComercial, Entidad entidad, Proveedor proveedor, boolean requierePresupuesto, Criterio criterioEleccionPresupuesto, int IDCompra) {
		this.items = items;
		this.presupuestos = presupuestos;
		//this.fechaDeOperacion = fechaDeOperacion;
		//this.medioDePago = medioDePago;
		this.documentoComercial = documentoComercial;
		this.entidad = entidad;
		this.proveedor = proveedor;
		this.requierePresupuesto = requierePresupuesto;
		this.criterioEleccionPresupuesto = criterioEleccionPresupuesto;
		this.IDCompra = IDCompra;
	}

	Validador validador = Validador.GetInstance();

	public void validar(){
		if(requierePresupuesto) {
			if(validador.tieneSuficientesPresupuestos(this.cantidadPresupuestos())){
				notificarUsuarios("La compra " + IDCompra + " tiene la cantidad de presupuestos requeridos.");
			} else {
				notificarUsuarios("La compra " + IDCompra + " no tiene la cantidad de presupuestos requeridos");
			}

			if(validador.seUtilizoPresupuesto(this)){
				notificarUsuarios("En la compra " + IDCompra + " se utilizo un presupuesto de los asignados.");
			}else{
				notificarUsuarios("En la compra " + IDCompra + " no se utilizo ningun presupuesto de los asignados.");
			}
		}

		if(validador.eleccionCorrecta(this)) {
			notificarUsuarios("Para la compra " + IDCompra + " no se eligio el presupuesto a partir del criterio");
		} else {
			notificarUsuarios("Para la compra " + IDCompra + " se eligio el presupuesto a partir del criterio");
		}
	}

	public ArrayList<Usuario> getUsuariosRevisores() {
		return usuariosRevisores;
	}

	public void suscribirUsuario(Usuario unUsuario){
		usuariosRevisores.add(unUsuario);
	}

	private void notificarUsuarios(String mensaje){
		usuariosRevisores.forEach(usuario -> usuario.serNotificado(mensaje));
	}
	public int getIDCompra() {
		return IDCompra;
	}

	public void seleccionarPresupuesto(){
		presupuestoElegido = criterioEleccionPresupuesto.elegirPresupuesto(presupuestos);
	}

	public boolean tienePresupuesto(){
		return presupuestoElegido != null;
	}

	public Presupuesto getPresupuestoElegido() {
		return presupuestoElegido;
	}

	public Criterio getCriterioEleccionPresupuesto() {
		return criterioEleccionPresupuesto;
	}

	public void elegirCriterio(Criterio unCriterio){
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

	public double valorTotal(){
		return presupuestoElegido.valorTotal();
	}

	private void efectuarCompra(){
		new Egreso(this);
	}
}//end Compras