package Control;

import Negocio.Main;

import java.util.concurrent.TimeUnit;

public class Registrarse {

    public static void registrarUsuario(){ // la hicimos static
        System.out.println("Ingrese un Usuario :");
        String usuario = Main.pedirPorPantallaString();
        // verificarUsuario(usuario);

        String password = setRandomPassword(); //la hicimos static
        System.out.println("Su contraseña sugerida es: " + password + ", desea cambiarla? si, no");
        String opcion = Main.pedirPorPantallaString();

        if(opcion.equals("si")){
            System.out.println("Ingrese una Contraseña:");
            password = Main.pedirPorPantallaString();
            password = verificarPassword(password); //la hciimos static
        }

        seguridadClave(password);

        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + password);
    }

    public static String verificarPassword(String unaPassword) {
        unaPassword = chequearEspaciosSeguidos(unaPassword); //sacamos this pq es static y lo hicimos static
        while(!(longitudDePasswordPretendida(unaPassword))){//sacamos this pq es static y lo hicimos static
            System.out.println("Su contraseña no tiene entre 8 y 64 caracteres. Por favor ingrese otra: ");
            unaPassword = Main.pedirPorPantallaString();
            unaPassword = chequearEspaciosSeguidos(unaPassword);
        }
        return unaPassword;
    }

    private static boolean longitudDePasswordPretendida(String unaPassword){
        return unaPassword.length() >= 8 && unaPassword.length() <= 64;
    }

    private static String chequearEspaciosSeguidos(String unaPassword){
        int i;
        int contador = 0;
        String passwordFinal = "";
        for (i = 0; i < unaPassword.length(); i++){
            if(!(unaPassword.charAt(i) == ' ' && unaPassword.charAt((i+1)) == ' ')){
                passwordFinal = passwordFinal +unaPassword.charAt(i);
            }
            else{
                contador += 1;
            }
        }
        System.out.println("Se encontraron "+contador+" espacios seguidos y se los reemplazo por uno solo");
        return passwordFinal;
    }

    public static void seguridadClave(String clave){
        int seguridad = 0;
        if (clave.length()!=0){
            if (tienenNumeros(clave) && tieneLetras(clave)){ // los hicimos static
                seguridad += 30;
            }
            if (tieneMinusculas(clave) && tieneMayusculas(clave)){ // los hicimos static
                seguridad += 30;
            }
            if (clave.length() >= 4 && clave.length() <= 5){
                seguridad += 10;
            }else{
                if (clave.length() >= 6 && clave.length() <= 8){
                    seguridad += 30;
                }else{
                    if (clave.length() > 8){
                        seguridad += 40;
                    }
                }
            }
        }

        System.out.println("La seguridad de la contraseña es de: %"+seguridad);
    }

    private static boolean tienenNumeros(String texto){
        String numeros ="0123456789";
        for(int i=0; i<texto.length(); i++){
            if (numeros.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private static boolean tieneLetras(String texto){
        String letras="abcdefghyjklmnñopqrstuvwxyz";
        texto = texto.toLowerCase();
        for(int i=0; i<texto.length(); i++){
            if (letras.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }


    private static boolean tieneMinusculas(String texto){
        String letras="abcdefghyjklmnñopqrstuvwxyz";
        for(int i=0; i<texto.length(); i++){
            if (letras.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private static boolean tieneMayusculas(String texto){
        String letrasMayusculas="ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";
        for(int i=0; i<texto.length(); i++){
            if (letrasMayusculas.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private static String setRandomPassword(){

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }


}
