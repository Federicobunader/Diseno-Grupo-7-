package Negocio.Entidad;

import BaseDeDatos.EntidadPersistente;
import Negocio.Proveedor;
import Negocio.Usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name="entidad")
public abstract class Entidad extends EntidadPersistente {

	@OneToOne
	@JoinColumn(name="usuario_id", referencedColumnName = "id")
	private Usuario usuario;



	public Entidad(){
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Entidad(Negocio.Usuario.Usuario unUsuario) {
		usuario = unUsuario;
	}
}//end Entidad