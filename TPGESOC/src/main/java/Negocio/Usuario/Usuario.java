package Negocio.Usuario;


import BaseDeDatos.EntidadPersistente;
import InterfazDeUsuario.InterfazUsuarios;
import Negocio.Compras.Compra;
import Negocio.Compras.GestorDeEgresos;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name="usuario")
public class Usuario extends EntidadPersistente {

    @Column
    private String usuario;

    @Column
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "direccionPostal_id", referencedColumnName = "id")
    private DireccionPostal direccionPostal;

    @Transient
    private ArrayList<String> bandejaDeMensajes = new ArrayList<String>();

    @Transient
    private InterfazUsuarios interfazUsuarios = InterfazUsuarios.GetInstance();

    @Column
    private boolean habilitado;

    @Column
    private int intentos;

    public void setDireccionPostal(DireccionPostal direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public void setBandejaDeMensajes(ArrayList<String> bandejaDeMensajes) {
        this.bandejaDeMensajes = bandejaDeMensajes;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public Usuario() {

    }

    public boolean laPasswordCoincide(String unaPassword){
        return password.equals(unaPassword);
    }

    public boolean elNombreCoincide(String nombreDeUsuario) { return usuario.equals(nombreDeUsuario);}


    public void serNotificado(String mensaje) {
        bandejaDeMensajes.add(mensaje);
        interfazUsuarios.mostrarInformacion(mensaje);
    }

    @Transient
    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public void darseDeAltaComoRevisor(int compraID){
        Compra unaCompra = gestorDeEgresos.buscarCompraPorID(compraID);
        unaCompra.suscribirUsuario(this);
    }
}



