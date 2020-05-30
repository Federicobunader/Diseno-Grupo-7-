package Negocio.Usuario;


import Negocio.Compra.Compra;
import Negocio.Compra.GestorDeEgresos;

import java.util.ArrayList;

public class Usuario {

    private String usuario;
    private String password;
    private ArrayList<String> bandejaDeMensajes = new ArrayList<String>();


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

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public boolean laPasswordCoincide(String unaPassword){
        return password.equals(unaPassword);
    }

    public boolean elNombreCoincide(String nombreDeUsuario) { return usuario.equals(nombreDeUsuario);}


    public void serNotificado(String mensaje) {
        bandejaDeMensajes.add(mensaje);
    }

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public void darseDeAltaComoRevisor(int compraID){
        Compra unaCompra = gestorDeEgresos.buscarCompraPorID(compraID);
        unaCompra.suscribirUsuario(this);
    }
}



