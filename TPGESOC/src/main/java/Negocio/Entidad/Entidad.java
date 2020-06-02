package Negocio.Entidad;

import Negocio.Proveedor;
import Negocio.Usuario.Usuario;

public abstract class Entidad {

	private Negocio.Usuario.Usuario Usuario;
	public Proveedor m_Proveedor;

	public Entidad(){

	}

	public Entidad(Negocio.Usuario.Usuario usuario, Proveedor m_Proveedor) {
		Usuario = usuario;
		this.m_Proveedor = m_Proveedor;
	}
}//end Entidad