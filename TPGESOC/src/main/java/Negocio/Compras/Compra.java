package Negocio.Compras;

import Negocio.*;
import Negocio.Entidad.Entidad;
import Negocio.Usuario.Usuario;
import org.quartz.Job;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name ="compra")
public class Compra {

	@Id
	@GeneratedValue
	private int id;
	@OneToMany(mappedBy = "compra")
	private ArrayList<Item> items = new ArrayList<Item>();
	@OneToMany(mappedBy = "compra")
	private ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	@Transient
	private ArrayList<Usuario> usuariosRevisores = new ArrayList<Usuario>();
	@ManyToOne
	@JoinColumn(name="presupuesto_id", referencedColumnName = "id")
	private Presupuesto presupuestoElegido;
	//private MedioDePago medioDePago;
	@ManyToOne
	@JoinColumn(name="presupuesto_id", referencedColumnName = "id")
	private Documento documentoComercial;
	@Column
	private Entidad entidad;
	@Column
	private Proveedor proveedor;
	@Column
	private boolean requierePresupuesto;
	@Column
	private Criterio criterioEleccionPresupuesto;

	public Compra(ArrayList<Item> items, ArrayList<Presupuesto> presupuestos/*, MedioDePago medioDePago*/, Documento documentoComercial, Entidad entidad, Proveedor proveedor, boolean requierePresupuesto, Criterio criterioEleccionPresupuesto, int IDCompra) {
		this.items = items;
		this.presupuestos = presupuestos;
		//this.fechaDeOperacion = fechaDeOperacion;
		//this.medioDePago = medioDePago;
		this.documentoComercial = documentoComercial;
		this.entidad = entidad;
		this.proveedor = proveedor;
		this.requierePresupuesto = requierePresupuesto;
		this.criterioEleccionPresupuesto = criterioEleccionPresupuesto;
		this.id = IDCompra;
	}

	public void validar(){
		Validador validador = Validador.GetInstance();
		if(requierePresupuesto) {
			if(validador.tieneSuficientesPresupuestos(this.cantidadPresupuestos())){
				notificarUsuarios("La compra " + id + " tiene la cantidad de presupuestos requeridos.");
			} else {
				notificarUsuarios("La compra " + id + " no tiene la cantidad de presupuestos requeridos");
			}

			if(validador.seUtilizoPresupuesto(this)){
				notificarUsuarios("En la compra " + id + " se utilizo un presupuesto de los asignados.");
			}else{
				notificarUsuarios("En la compra " + id + " no se utilizo ningun presupuesto de los asignados.");
			}
		}

		if(validador.eleccionCorrecta(this)) {
			notificarUsuarios("Para la compra " + id + " no se eligio el presupuesto a partir del criterio");
		} else {
			notificarUsuarios("Para la compra " + id + " se eligio el presupuesto a partir del criterio");
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
		return id;
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
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		Egreso nuevoEgreso = new Egreso(this,date);

		GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
		gestorDeEgresos.getEgresos().add(nuevoEgreso);
		nuevoEgreso.agregarCompraYPresupues();

	}
}