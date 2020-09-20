package Negocio.Entidad;

import Negocio.Entidad.Entidad;
import Negocio.Entidad.EntidadBase;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name="entidadJuridica")
public class EntidadJuridica extends Entidad {

	private ArrayList<EntidadBase> entidadBases = new ArrayList<EntidadBase>();

	@Column
	private String RazonSocial;
	@Column
	private String NombreFicticio;
	@Column
	private int CUIT;
	@Column
	private int CodigoDeInscripcion;

	public EntidadJuridica(){
	}

	public void setEntidadBases(ArrayList<EntidadBase> entidadBases) {
		this.entidadBases = entidadBases;
	}

	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}

	public void setNombreFicticio(String nombreFicticio) {
		NombreFicticio = nombreFicticio;
	}

	public void setCUIT(int CUIT) {
		this.CUIT = CUIT;
	}

	public void setCodigoDeInscripcion(int codigoDeInscripcion) {
		CodigoDeInscripcion = codigoDeInscripcion;
	}

}//end EntidadJuridica