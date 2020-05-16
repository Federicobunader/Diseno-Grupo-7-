package dominio;


import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import dominio.TipoDeSector.*;
import dominio.TiposDeEmpresas.TipoDeEmpresa;

public class Empresa extends EntidadJuridica {

	private char actividad;
	private float promedioDeVentasAnuales;
	private int cantidadDePersonal;
	private TipoDeSector sector;
	private TipoDeEmpresa tipoDeEmpresa;


	public Empresa(char actividad,float promedioDeVentasAnuales,int cantidadDePersonal, TipoDeSector sector) {
		this.actividad = actividad;
		this.promedioDeVentasAnuales = promedioDeVentasAnuales;
		this.cantidadDePersonal = cantidadDePersonal;
		this.sector = sector;
		this.actualizarTipoDeEmpresa();
	}

	private void actualizarTipoDeEmpresa(){
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

	private boolean quedaEnNegativo(float unaVariable,float unValor){
		return unaVariable - unValor < 0;
	}

	public void contratarPersonal(int unaCantidad){
		cantidadDePersonal += unaCantidad;
		this.actualizarTipoDeEmpresa();
	}
	public void despedirPersonal(int unaCantidad){

			if(this.quedaEnNegativo(cantidadDePersonal,unaCantidad)) {
				System.out.print("No puede despedir esa cantidad de Personas");
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
			System.out.print("No puede despedir esa cantidad de Personas");
		}
		else{
			promedioDeVentasAnuales -= unaCantidad;
			this.actualizarTipoDeEmpresa();
		}
	}

}//end Empresa