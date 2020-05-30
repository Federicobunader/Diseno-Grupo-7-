package Negocio.Compra;

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
			notificarUsuarios(validador.tieneSuficientesPresupuestos(this.cantidadPresupuestos(),IDCompra));
			notificarUsuarios(validador.seUtilizoPresupuesto(this));
		}
		notificarUsuarios(validador.eleccionCorrecta(this));
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

	private void seleccionarPresupuesto(){
		presupuestoElegido = criterioEleccionPresupuesto.elegirPresupuesto(presupuestos);
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

	public int valorTotal(){
		return 0;
	}

	private void efectuarCompra(){
		new Egreso(this);
	}
}//end Compra