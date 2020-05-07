package dominio.TiposDeEmpresas;

import dominio.GestorDePasswords;
import dominio.GestorDeUsuarios;

public class MedianaTramo1 extends TipoDeEmpresa {

    private static MedianaTramo1 instance = null;

    private MedianaTramo1() {
    }

    public static MedianaTramo1 GetInstance() {
        if (instance == null)
            instance = new MedianaTramo1();
        return instance;
    }
}
