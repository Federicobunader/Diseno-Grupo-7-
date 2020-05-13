package dominio;


import com.sun.org.apache.bcel.internal.generic.SWITCH;
import dominio.TipoDeSector.*;
import dominio.TiposDeEmpresas.TipoDeEmpresa;

public class Empresa extends EntidadJuridica {

	private char actividad;
	private float promedioDeVentasAnuales;
	private int cantidadDePersonal;
	private TipoDeSector sector;
	private TipoDeEmpresa tipoDeEmpresa;

	public Empresa(char actividad,float promedioDeVentasAnuales,int cantidadDePersonal) {
		this.actividad = actividad;
		this.promedioDeVentasAnuales = promedioDeVentasAnuales;
		this.cantidadDePersonal = cantidadDePersonal;
		this.definirSector();
		this.ActualizarTipoDeEmpresa();
	}

	public void ActualizarTipoDeEmpresa(){
		tipoDeEmpresa = sector.calcularTipoDeEmpresa(this,0,0,0,0,0,0);
		System.out.println(sector.getNombreSector());
		System.out.println(tipoDeEmpresa.getNombreTipoEmpresa());
	}

	public void setTipoDeEmpresa(TipoDeEmpresa tipoDeEmpresa) {
		this.tipoDeEmpresa = tipoDeEmpresa;
	}

	public int getCantidadDePersonal() {
		return cantidadDePersonal;
	}

	public float getPromedioDeVentasAnuales(){

		return promedioDeVentasAnuales;
	}

	public void definirSector(){
		switch (actividad){
			case 'A': //agropecuario
				Agropecuaria agropecuaria = Agropecuaria.GetInstance();
				sector = agropecuaria;
				break;
			case 'B': //industriayminera
				IndustriaYMinera industriaYMinera = IndustriaYMinera.GetInstance();
				sector = industriaYMinera;
				break;
			case 'C': //comercio
				Comercio comercio = Comercio.GetInstance();
				sector = comercio;
				break;
			case 'D': //servicios
				Servicios servicios = Servicios.GetInstance();
				sector = servicios;
				break;
			case 'E': //construccion
				Construccion construccion = Construccion.GetInstance();
				sector = construccion;
				break;
		}
	}

}//end Empresa