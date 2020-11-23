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
	protected String RazonSocial;
	@Column
	protected String NombreFicticio;
	@Column
	protected long CUIT;
	@Column
	protected long CodigoDeInscripcion;

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

	public void setCUIT(long CUIT) {
		this.CUIT = CUIT;
	}

	public void setCodigoDeInscripcion(long codigoDeInscripcion) {
		CodigoDeInscripcion = codigoDeInscripcion;
	}

	public ArrayList<EntidadBase> getEntidadBases() {
		return entidadBases;
	}

	public String getRazonSocial() {
		return RazonSocial;
	}

	public String getNombreFicticio() {
		return NombreFicticio;
	}

	public long getCUIT() {
		return CUIT;
	}

	public long getCodigoDeInscripcion() {
		return CodigoDeInscripcion;
	}


}//end EntidadJuridica