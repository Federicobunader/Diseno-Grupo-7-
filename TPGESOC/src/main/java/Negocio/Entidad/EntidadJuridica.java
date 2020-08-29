package Negocio.Entidad;

import Negocio.Entidad.Entidad;
import Negocio.Entidad.EntidadBase;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name="entidadJuridica")
public class EntidadJuridica extends Entidad {

	@Id
	@GeneratedValue
	private int id;

	private ArrayList<EntidadBase> entidadBases = new ArrayList<EntidadBase>();

	@Column
	private String RazonSocial;
	@Column
	private String NombreFicticio;
	@Column
	private int CUIT;
	@Column
	private String DireccionPostal;
	@Column
	private int CodigoDeInscripcion;

	public EntidadJuridica(){

	}

	public void finalize() throws Throwable {

	}
}//end EntidadJuridica