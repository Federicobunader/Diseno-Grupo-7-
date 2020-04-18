

public class Sistema {

    GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();

    private static Sistema instance = null;

    private Sistema() {
    }

    public static Sistema GetInstance() {
        if (instance == null)
            instance = new Sistema();
        return instance;
    }




    public void arrancar(){
        GestorDeUsuarios gestorDeUsuarios = GestorDeUsuarios.GetInstance();
        gestorDeUsuarios.consolaUsuario();
    }

}
