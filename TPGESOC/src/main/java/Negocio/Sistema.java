package Negocio;

import InterfazDeUsuario.InterfazPassword;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.GestorDeUsuarios;
import InterfazDeUsuario.InterfazUsuarios;

public class Sistema {

    GestorDeUsuarios gestorDeUsuarios = GestorDeUsuarios.GetInstance();



    private static Sistema instance = null;

    private Sistema() {
    }

    public static Sistema GetInstance() {
        if (instance == null)
            instance = new Sistema();
        return instance;
    }

    public void arrancar(){
        gestorDeUsuarios.consolaUsuario();
    }

}
