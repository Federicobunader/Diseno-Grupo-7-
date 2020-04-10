//package Negocio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        menu();
        int opcion=pedirPorPantallaInt();

        while(opcion!=5)
        {
            if(opcion==1)
            {
                Registrarse.registrarUsuario();
            }
            else if(opcion==2)
            {
                Usuario usuario = new Usuario("pepe","juan");
                usuario.cambiarPassword();
            }
            else if(opcion==3)
            {


            }
            else if(opcion==4) //llamo a la funcion de cargar en bd y se lo asigno al vector
            {


            }

            menu();

            opcion =pedirPorPantallaInt();
        }

    }

    public static void menu() //Menu
    {
        System.out.println("Ingrese una opcion:");
        System.out.println("");
        System.out.println("1- REGISTRAR USUARIO");
        System.out.println("2- CAMBIAR CONTRASEÑA");
        System.out.println("3- GUARDAR");
        System.out.println("4- CARGAR");
        System.out.println("5- SALIR");
        System.out.println("");
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

