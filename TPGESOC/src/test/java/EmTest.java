import API.APIGeografia.Ciudad;
import API.APIGeografia.Pais;
import API.APIGeografia.Provincia;
import BaseDeDatos.UsuarioModel;
import Negocio.Usuario.DireccionPostal;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.Usuario;
import converters.LocalDateAttributeConverter;
import BaseDeDatos.EntityManagerHelper;
import org.junit.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.undo.AbstractUndoableEdit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmTest {

    @Test
    public void persistirUsuario() {
        Usuario usuario = new Usuario();

        DireccionPostal direccion = new DireccionPostal();
        direccion.setAltura(100);
        direccion.setCalle("Gonzalo Miele");
        direccion.setCiudad("CABA");
        direccion.setDepartamento('A');
        direccion.setPais("Pais");
        direccion.setProvincia("Buenos Aires");
        direccion.setPiso(10);

        usuario.setUsuario("Gonza");
        String password = GestorDePasswords.GetInstance().hashearPassword("Miele");
        usuario.setPassword(password);
        usuario.setDireccionPostal(direccion);
        usuario.setHabilitado(true);
        usuario.setIntentos(5);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();

    }

    @Test
    public void select(){
        Usuario usuario = (Usuario) EntityManagerHelper.createQuery("from Usuario where id = '12'").getSingleResult();
        System.out.println("ID = "+ usuario.getId() + " ,Nombre = " + usuario.getUsuario());
        //Assert.assertEquals("Eze", eze.getNombre());
    }
}