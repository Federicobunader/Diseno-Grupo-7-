package dominio;


import dominio.TipoDeSector.TipoDeSector;
import dominio.TiposDeEmpresas.TipoDeEmpresa;

public class Empresa extends EntidadJuridica {

	private TipoDeEmpresa tipoDeEmpresa;
	private int cantidadDePersonal;
	private TipoDeSector sector;
	private String actividad;
	private float promedioDeVentasAnuales;

	//faltaria un metodo para calcular el tipo de sector en base al tipo de actividad q ingresa

	public void ActualizarTipoDeEmpresa(){
		if(actividad.equals("Agencia de viaje") || actividad.equals("Comisionista")){
			tipoDeEmpresa = sector.calcularTipoDeEmpresaEspecial(this);
		}
		else{
			tipoDeEmpresa = sector.calcularTipoDeEmpresa(this);
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

}//end Empresa