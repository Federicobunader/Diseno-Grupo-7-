package Negocio.Entidad;

import Negocio.Proveedor;
import Negocio.Usuario.Usuario;

public abstract class Entidad {

	private Negocio.Usuario.Usuario usuario;
	private Proveedor proveedor;

	public Entidad(){

	}

	public Entidad(Negocio.Usuario.Usuario unUsuario, Proveedor unProveedor) {
		usuario = unUsuario;
		proveedor = unProveedor;
	}
}//end Entidad