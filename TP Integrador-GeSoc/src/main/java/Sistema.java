

public class Sistema {

    GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();
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
