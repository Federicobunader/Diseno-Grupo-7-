package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;
import Negocio.*;
import Negocio.Compras.Criterios.Criterio;
import Negocio.Entidad.Entidad;
import Negocio.Usuario.Usuario;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="compra")
public class Compra extends EntidadPersistente {

	@OneToMany
	@JoinColumn(name="id", referencedColumnName = "id")
	private List<Item> items = new ArrayList<>();

	@OneToMany
	@JoinColumn(name="id", referencedColumnName = "id")
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

	@OneToMany
	@JoinColumn(name="usuario", referencedColumnName = "id")
	private List<Usuario> usuariosRevisores = new ArrayList<Usuario>();

	@OneToOne
	@JoinColumn(name="presupuesto_id", referencedColumnName = "id")
	private Presupuesto presupuestoElegido;

	@ManyToOne
	@JoinColumn(name="medioDePago_id", referencedColumnName = "id")
	private MedioDePago medioDePago;

	@OneToMany
	@JoinColumn(name = "id", referencedColumnName = "id")
	private List<Documento> documentosComerciales = new ArrayList<Documento>();

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "entidad_id", referencedColumnName = "id")
	private Entidad entidad;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "proveedor_id", referencedColumnName = "id")
	private Proveedor proveedor;

	@Column
	private boolean requierePresupuesto;

	@Transient
	private Criterio criterioEleccionPresupuesto;

	public Compra(ArrayList<Item> items, ArrayList<Presupuesto> presupuestos, MedioDePago medioDePago, List<Documento> documentosComerciales, Entidad entidad, Proveedor proveedor, boolean requierePresupuesto, Criterio criterioEleccionPresupuesto, int IDCompra) {
		this.items = items;
		this.presupuestos = presupuestos;
		this.medioDePago = medioDePago;
		this.documentosComerciales = documentosComerciales;
		this.entidad = entidad;
		this.proveedor = proveedor;
		this.requierePresupuesto = requierePresupuesto;
		this.criterioEleccionPresupuesto = criterioEleccionPresupuesto;
	}

	public Compra() {
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public void setUsuariosRevisores(List<Usuario> usuariosRevisores) {
		this.usuariosRevisores = usuariosRevisores;
	}

	public void setPresupuestoElegido(Presupuesto presupuestoElegido) {
		this.presupuestoElegido = presupuestoElegido;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public void setDocumentosComerciales(List<Documento> documentosComerciales) {
		this.documentosComerciales = documentosComerciales;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setRequierePresupuesto(boolean requiere) {
		this.requierePresupuesto = requiere;
	}

	public void setCriterioEleccionPresupuesto(Criterio criterioEleccionPresupuesto) {
		this.criterioEleccionPresupuesto = criterioEleccionPresupuesto;
	}

	public void validar(){
		Validador validador = Validador.GetInstance();
		this.setRequierePresupuesto(validador.requierePresupuesto(this.getMonto()));
		if(requierePresupuesto) {
			if(validador.tieneSuficientesPresupuestos(this.cantidadPresupuestos())){
				notificarUsuarios("La compra " + getId() + " tiene la cantidad de presupuestos requeridos.");
			} else {
				notificarUsuarios("La compra " + getId() + " no tiene la cantidad de presupuestos requeridos");
			}

			if(validador.seUtilizoPresupuesto(this)){
				notificarUsuarios("En la compra " + getId() + " se utilizo un presupuesto de los asignados.");
			}else{
				notificarUsuarios("En la compra " + getId() + " no se utilizo ningun presupuesto de los asignados.");
			}
		}

		if(validador.eleccionCorrecta(this)) {
			notificarUsuarios("Para la compra " + getId() + " no se eligio el presupuesto a partir del criterio");
		} else {
			notificarUsuarios("Para la compra " + getId() + " se eligio el presupuesto a partir del criterio");
		}
	}

	public List<Usuario> getUsuariosRevisores() {
		return usuariosRevisores;
	}

	public void suscribirUsuario(Usuario unUsuario){
		usuariosRevisores.add(unUsuario);
	}

	private void notificarUsuarios(String mensaje){
		usuariosRevisores.forEach(usuario -> usuario.serNotificado(mensaje));
	}
	public int getIDCompra() {
		return getId();
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

	public void setCriterio(Criterio unCriterio){
		criterioEleccionPresupuesto = unCriterio;
	}

	public int cantidadPresupuestos(){
		return presupuestos.size();
	}

	public List<Item> getItems() {
		return items;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public double valorTotal(){
		return presupuestoElegido.valorTotal();
	}

	private void efectuarCompra(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		Egreso nuevoEgreso = new Egreso(this,date);

		GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
		gestorDeEgresos.getEgresos().add(nuevoEgreso);
		nuevoEgreso.agregarCompraYPresupuesto();

	}

	public double getMonto(){
		return presupuestoElegido.valorTotal();
	}
}