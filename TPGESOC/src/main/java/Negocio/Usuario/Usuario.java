package Negocio.Usuario;


import InterfazDeUsuario.InterfazUsuarios;
import Negocio.Compras.Compra;
import Negocio.Compras.GestorDeEgresos;

import java.util.ArrayList;

public class Usuario {

    private String usuario;
    private String password;
    private ArrayList<String> bandejaDeMensajes = new ArrayList<String>();
    private InterfazUsuarios interfazUsuarios = InterfazUsuarios.GetInstance();


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
        interfazUsuarios.mostrarInformacion(mensaje);
    }

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public void darseDeAltaComoRevisor(int compraID){
        Compra unaCompra = gestorDeEgresos.buscarCompraPorID(compraID);
        unaCompra.suscribirUsuario(this);
    }
}



