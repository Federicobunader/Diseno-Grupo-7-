package Negocio.Entidad;

import Negocio.Entidad.Entidad;
import Negocio.Proveedor;
import Negocio.Usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name="entidadBase")
public class EntidadBase extends Entidad {

	@Column
	private String NombreFicticio;

	@Column
	private String EntidadJuridica;

	@Column
	private String Descripcion;


	public EntidadBase(String nombreFicticio, String descripcion) {
		NombreFicticio = nombreFicticio;
		Descripcion = descripcion;
	}

	public EntidadBase() {
	}

	public void setNombre(String nombre) {
		NombreFicticio = nombre;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public void setEntidadJuridica(String entidadJuridica) {
		EntidadJuridica = entidadJuridica;
	}
}//end EntidadBase