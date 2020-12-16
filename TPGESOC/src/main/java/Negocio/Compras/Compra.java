package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;
import Negocio.*;
import Negocio.Compras.Criterios.Criterio;
import Negocio.Compras.Criterios.MenorValor;
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

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="compra_id", referencedColumnName = "id")
	private List<Item> items = new ArrayList<>();

	@OneToMany
	@JoinColumn(name="compra_id", referencedColumnName = "id")
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

	@OneToMany
	@JoinColumn(name="compra_id", referencedColumnName = "id")
	private List<Usuario> usuariosRevisores = new ArrayList<Usuario>();

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="presupuesto_elegido_id", referencedColumnName = "id")
	private Presupuesto presupuestoElegido;

	@ManyToOne
	@JoinColumn(name="mediodepago_id", referencedColumnName = "id")
	private MedioDePago medioDePago;

	@OneToMany
	@JoinColumn(name = "compra_id", referencedColumnName = "id")
	private List<Documento> documentosComerciales = new ArrayList<Documento>();

	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "entidad_id", referencedColumnName = "id")
	private Entidad entidad;

	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "proveedor_id", referencedColumnName = "id")
	private Proveedor proveedor;

	@Column
	private boolean requierePresupuesto;

	@Transient
	private Criterio criterioEleccionPresupuesto;

	@Transient
	private boolean perteneceAProyecto = false;


	public Compra(ArrayList<Item> items, ArrayList<Presupuesto> presupuestos, MedioDePago medioDePago, List<Documento> documentosComerciales, Entidad entidad, Proveedor proveedor, boolean requierePresupuesto, Criterio criterioEleccionPresupuesto, int IDCompra) {
		this.items = items;
		this.presupuestos = presupuestos;
		this.medioDePago = medioDePago;
		this.documentosComerciales = documentosComerciales;
		this.entidad = entidad;
		this.proveedor = proveedor;
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

	public boolean isRequierePresupuesto() {
		return requierePresupuesto;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public boolean isPerteneceAProyecto() {
		return perteneceAProyecto;
	}

	public void setRequierePresupuesto(boolean requiere) {
		this.requierePresupuesto = requiere;
	}

	public void setCriterioEleccionPresupuesto(Criterio criterioEleccionPresupuesto) {
		this.criterioEleccionPresupuesto = criterioEleccionPresupuesto;
	}

	public void setPerteneceAProyecto(boolean perteneceAProyecto) {
		this.perteneceAProyecto = perteneceAProyecto;
	}

	public void validar(double montoDefinido, int cantidadPresupuestosExigibles,int idCompra){
		//boolean estaOK = true;
		Validador validador = Validador.GetInstance();
		GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
		MenorValor menorValor = new MenorValor();
		//presupuestoElegido = menorValor.elegirPresupuesto(gestorDeEgresos.getPresupuestos());

		this.setCriterioEleccionPresupuesto(menorValor);
		//presupuestoElegido = presupuestos.get(0);		ESTÁ ROMPIENDO ACA PORQUE NO ENCUENTRA LA LISTA DE PRESUPUESTOS CUANDO CARGAMOS EGRESO
		requierePresupuesto = validador.requierePresupuesto(this.valorTotal(), montoDefinido);
		if(requierePresupuesto) {
			if(validador.tieneSuficientesPresupuestos(this.cantidadPresupuestos(), cantidadPresupuestosExigibles)){
				notificarUsuarios("La compra " + idCompra + " tiene la cantidad de presupuestos requeridos.");
			} else {
				notificarUsuarios("La compra " + idCompra + " no tiene la cantidad de presupuestos requeridos");
				//estaOK = false;
			}

			if(validador.seUtilizoPresupuesto(this)){
				notificarUsuarios("En la compra " + idCompra + " se utilizo un presupuesto de los asignados.");
			}else{
				notificarUsuarios("En la compra " + idCompra + " no se utilizo ningun presupuesto de los asignados.");
				//estaOK = false;
			}
		}

		if(!validador.eleccionCorrecta(this)) {
			notificarUsuarios("Para la compra " + idCompra + " no se eligio el presupuesto a partir del criterio");
		} else {
			notificarUsuarios("Para la compra " + idCompra + " se eligio el presupuesto a partir del criterio");
			//estaOK = false;
		}
	}

	public List<Usuario> getUsuariosRevisores() {
		return usuariosRevisores;
	}

	public void suscribirUsuario(Usuario unUsuario){
		usuariosRevisores.add(unUsuario);
	}

	private void notificarUsuarios(String mensaje){
		//usuariosRevisores.forEach(usuario -> usuario.serNotificado(mensaje));
		for (int i = 0; i < usuariosRevisores.size() ; i++) {
			usuariosRevisores.get(i).serNotificado(mensaje);
		}
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
		return presupuestoElegido.getValorTotal();
	}

	public Egreso efectuarCompra(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		Egreso nuevoEgreso = new Egreso(this,date,this.valorTotal());


		GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
		gestorDeEgresos.getEgresos().add(nuevoEgreso);
		nuevoEgreso.agregarCompraYPresupuesto();
		//this.validar(0,0);

		return nuevoEgreso;
	}

	public double getMonto(){
		return presupuestoElegido.valorTotal();
	}

	public double valorTotalSinPresupuesto(){
		double valorTotal = 0;

		for (int i = 0; i < items.size(); i++){
			valorTotal += items.get(i).valorTotal();
		}

		return valorTotal;
	}
}