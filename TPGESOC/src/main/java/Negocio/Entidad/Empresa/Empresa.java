package Negocio.Entidad.Empresa;


//import com.sun.org.apache.bcel.internal.generic.SWITCH;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector.*;
import Negocio.Entidad.EntidadJuridica;
import Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector.TipoDeSector;
import Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas.TipoDeEmpresa;

import javax.persistence.*;


public class Empresa extends EntidadJuridica {

	@Column
	private double promedioDeVentasAnuales;
	@Column
	private int cantidadDePersonal;
	@Column
	private String seleccionTipoDeSector;

	@ManyToOne
	@JoinColumn(name="tipoDeSector_id", referencedColumnName = "id")
	private TipoDeSector sector;

	@ManyToOne
	@JoinColumn(name="tipoDeEmpresa_id", referencedColumnName = "id")
	private TipoDeEmpresa tipoDeEmpresa;

	@Transient
	Categorizador categorizador = Categorizador.GetInstance();


	public Empresa(double promedioDeVentasAnuales,int cantidadDePersonal, String seleccionTipoDeSector) {
		this.promedioDeVentasAnuales = promedioDeVentasAnuales;
		this.cantidadDePersonal = cantidadDePersonal;
		this.seleccionTipoDeSector =seleccionTipoDeSector;
		this.seleccionarSector();
		this.actualizarTipoDeEmpresa();
}

	public Empresa() {
	}

	public void setSector(TipoDeSector sector) {
		this.sector = sector;
	}

	public void setTipoDeEmpresa(TipoDeEmpresa tipoDeEmpresa) {
		this.tipoDeEmpresa = tipoDeEmpresa;
	}

	public void setCategorizador(Categorizador categorizador) {
		this.categorizador = categorizador;
	}

	public int getCantidadDePersonal() {
		return cantidadDePersonal;
	}

	public void setCantidadDePersonal(int cantidadDePersonal) {
		this.cantidadDePersonal = cantidadDePersonal;
	}

	public double getPromedioDeVentasAnuales() {
		return promedioDeVentasAnuales;
	}

	public void setPromedioDeVentasAnuales(double promedioDeVentasAnuales) {
		this.promedioDeVentasAnuales = promedioDeVentasAnuales;
	}

	public String getSeleccionTipoDeSector() {
		return seleccionTipoDeSector;
	}

	public TipoDeSector getSector() {
		return sector;
	}

	public TipoDeEmpresa getTipoDeEmpresa() {
		return tipoDeEmpresa;
	}

	public void setSeleccionTipoDeSector(String seleccionTipoDeSector) {
		this.seleccionTipoDeSector = seleccionTipoDeSector;
	}
	private void actualizarTipoDeEmpresa(){
		tipoDeEmpresa = categorizador.calcularTipoDeEmpresa(sector,promedioDeVentasAnuales,cantidadDePersonal);
	}

	private void seleccionarSector(){

		switch (seleccionTipoDeSector) {
			case "Agropecuario":
				sector = Agropecuaria.GetInstance();
				break;
			case "Comercio":
				sector = Comercio.GetInstance();
				break;
			case "Construccion":
				sector = Construccion.GetInstance();
				break;
			case "IndustriaYMinera":
				sector = IndustriaYMinera.GetInstance();
				break;
			case "Servicios":
				sector = Servicios.GetInstance();
				break;
			default:
				throw new IllegalArgumentException("No se encuentra el sector ingresado");
		}

	}

	private boolean quedaEnNegativo(double unaVariable,double unValor){
		return unaVariable - unValor < 0;
	}


	public void contratarPersonal(int unaCantidad){
		cantidadDePersonal += unaCantidad;
		this.actualizarTipoDeEmpresa();
	}
	public void despedirPersonal(int unaCantidad){

			if(this.quedaEnNegativo(cantidadDePersonal,unaCantidad)) {
				throw new IllegalArgumentException("No puede despedir esa cantidad de Personas");
			}
			else{
				cantidadDePersonal -= unaCantidad;
				this.actualizarTipoDeEmpresa();
			}
	}
	public void aumentarVentas(double unaCantidad){
		promedioDeVentasAnuales += unaCantidad;
		this.actualizarTipoDeEmpresa();
	}
	public void disminuirVentas(double unaCantidad){

		if(this.quedaEnNegativo(cantidadDePersonal,unaCantidad)) {
			throw new IllegalArgumentException("No puede despedir esa cantidad de Personas");
		}
		else{
			promedioDeVentasAnuales -= unaCantidad;
			this.actualizarTipoDeEmpresa();
		}
	}

}//end Empresa