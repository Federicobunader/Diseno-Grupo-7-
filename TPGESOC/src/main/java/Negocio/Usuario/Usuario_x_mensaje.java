
package Negocio.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario_x_mensaje")
public class Usuario_x_mensaje {

    @Column
    private int usuario_id;
    @Column
    private int mensaje_id;

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getMensaje_id() {
        return mensaje_id;
    }

    public void setMensaje_id(int mensaje_id) {
        this.mensaje_id = mensaje_id;
    }
}
