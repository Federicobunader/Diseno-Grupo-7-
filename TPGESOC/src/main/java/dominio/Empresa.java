package dominio;


import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import dominio.TipoDeSector.*;
import dominio.TiposDeEmpresas.TipoDeEmpresa;



public class Empresa extends EntidadJuridica {


	private char actividad;
	private float promedioDeVentasAnuales;
	private int cantidadDePersonal;
	private String seleccionTipoDeSector;
	private TipoDeSector sector;
	private TipoDeEmpresa tipoDeEmpresa;


	public Empresa(char actividad,float promedioDeVentasAnuales,int cantidadDePersonal, String seleccionTipoDeSector) {
		this.actividad = actividad;
		this.promedioDeVentasAnuales = promedioDeVentasAnuales;
		this.cantidadDePersonal = cantidadDePersonal;
		this.seleccionTipoDeSector =seleccionTipoDeSector;
		this.seleccionarSector();
		this.actualizarTipoDeEmpresa();
	}

	private void actualizarTipoDeEmpresa(){
		tipoDeEmpresa = sector.calcularTipoDeEmpresa(this,0,0,0,0,0,0);
		System.out.println(sector.getNombreSector());
		System.out.println(tipoDeEmpresa.getNombreTipoEmpresa());
	}

	private void seleccionarSector(){

			if(seleccionTipoDeSector.equals("Agropecuaria")){
				sector = Agropecuaria.GetInstance();
			}
			else if(seleccionTipoDeSector.equals("Comercio")){
			sector = Comercio.GetInstance();
			}
			else if(seleccionTipoDeSector.equals("Construccion")){
				sector = Construccion.GetInstance();
			}
			else if(seleccionTipoDeSector.equals("IndustriaYMinera"))
				sector = IndustriaYMinera.GetInstance();
			else if(seleccionTipoDeSector.equals("Servicios")) {
				sector = Servicios.GetInstance();
			}
			else {
				System.out.println("Sector Inexistente");
			}

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

	private boolean quedaEnNegativo(float unaVariable,float unValor){
		return unaVariable - unValor < 0;
	}

	public void contratarPersonal(int unaCantidad){
		cantidadDePersonal += unaCantidad;
		this.actualizarTipoDeEmpresa();
	}
	public void despedirPersonal(int unaCantidad){

			if(this.quedaEnNegativo(cantidadDePersonal,unaCantidad)) {
				System.out.println("No puede despedir esa cantidad de Personas");
			}
			else{
				cantidadDePersonal -= unaCantidad;
				this.actualizarTipoDeEmpresa();
			}
	}
	public void aumentarVentas(int unaCantidad){
		promedioDeVentasAnuales += unaCantidad;
		this.actualizarTipoDeEmpresa();
	}
	public void disminuirVentas(int unaCantidad){

		if(this.quedaEnNegativo(cantidadDePersonal,unaCantidad)) {
			System.out.println("No puede despedir esa cantidad de Personas");
		}
		else{
			promedioDeVentasAnuales -= unaCantidad;
			this.actualizarTipoDeEmpresa();
		}
	}

}//end Empresa