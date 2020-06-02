package Negocio.Entidad;

import Negocio.Entidad.Entidad;
import Negocio.Proveedor;
import Negocio.Usuario.Usuario;

public class EntidadBase extends Entidad {

	private String Nombre;
	private String Descripcion;


	public EntidadBase(String nombre, String descripcion) {
		Nombre = nombre;
		Descripcion = descripcion;
	}


}//end EntidadBase