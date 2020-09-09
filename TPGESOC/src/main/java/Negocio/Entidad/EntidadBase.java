package Negocio.Entidad;

import Negocio.Entidad.Entidad;
import Negocio.Proveedor;
import Negocio.Usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name="entidadBase")
public class EntidadBase extends Entidad {

	@Column
	private String Nombre;

	@Column
	private String Descripcion;


	public EntidadBase(String nombre, String descripcion) {
		Nombre = nombre;
		Descripcion = descripcion;
	}


}//end EntidadBase