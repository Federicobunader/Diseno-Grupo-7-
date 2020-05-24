package Negocio.Entidad;

import Negocio.Proveedor;

public abstract class Entidad {

	private Negocio.Usuario.Usuario Usuario;
	public Proveedor m_Proveedor;

	public Entidad(){

	}

	public void finalize() throws Throwable {

	}
}//end Entidad