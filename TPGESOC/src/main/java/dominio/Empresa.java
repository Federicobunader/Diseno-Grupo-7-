package dominio;


//import com.sun.org.apache.bcel.internal.generic.SWITCH;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import dominio.TipoDeSector.*;
import dominio.TiposDeEmpresas.TipoDeEmpresa;




public class Empresa extends EntidadJuridica {

	private float promedioDeVentasAnuales;
	private int cantidadDePersonal;
	private String seleccionTipoDeSector;
	private TipoDeSector sector;
	private TipoDeEmpresa tipoDeEmpresa;


	public Empresa(float promedioDeVentasAnuales,int cantidadDePersonal, String seleccionTipoDeSector) {
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

	public int getCantidadDePersonal() {
		return cantidadDePersonal;
	}

	public void setCantidadDePersonal(int cantidadDePersonal) {
		this.cantidadDePersonal = cantidadDePersonal;
	}

	public float getPromedioDeVentasAnuales() {
		return promedioDeVentasAnuales;
	}

	public void setPromedioDeVentasAnuales(float promedioDeVentasAnuales) {
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

	private void seleccionarSector(){

		switch (seleccionTipoDeSector) {
			case "Agropecuaria":
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