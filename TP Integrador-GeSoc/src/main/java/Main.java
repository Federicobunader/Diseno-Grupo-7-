import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        //registrarUsuario();

        Usuario usuario = new Usuario("pepe","juan");
        //usuario.cambiarPassword();
        //usuario.loguearse();

    }

    public static void registrarUsuario(){
        System.out.println("Ingrese un Usuario :");
        String usuario = pedirPorPantallaString();
        verificarUsuario(usuario);
        System.out.println("Ingrese una Contraseña:");
        String password = pedirPorPantallaString();
        verificarPassword(password);

        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + password);
    }

    public static boolean verificarUsuario(String unUsuario) {
        return true;
    }

    public static boolean verificarPassword (String unaPassword) {

        return true;
    }
    public static String pedirPorPantallaString() {
        String res = "";
        try {
            BufferedReader entrada =
                    new BufferedReader(new InputStreamReader(System.in));
            res = entrada.readLine();
        }
        catch (IOException e) {}

        return res;
    }

    public static int pedirPorPantallaInt() {
        String dato = pedirPorPantallaString();

        int res = Integer.parseInt(dato);
        return res;
    }
}

