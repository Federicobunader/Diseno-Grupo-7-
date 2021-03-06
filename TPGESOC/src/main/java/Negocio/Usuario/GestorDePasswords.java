package Negocio.Usuario;

import InterfazDeUsuario.InterfazPassword;
import Negocio.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GestorDePasswords {

    private static GestorDePasswords instance = null;
    private InterfazPassword interfazPassword = InterfazPassword.GetInstance();

    private GestorDePasswords() {
    }

    public static GestorDePasswords GetInstance() {
        if (instance == null)
            instance = new GestorDePasswords();
        return instance;
    }

    public boolean laPasswordNoCumpleLosRequisitos(String unaPassword,String unNombreDeUsuario) {
        return (this.laPasswordTieneMalTamanio(unaPassword)|| this.laPasswordEsMala(unaPassword) || this.laPasswordEsElNombreDeUsuario(unaPassword,unNombreDeUsuario)
                 || this.passwordNoCumpleRequisitos(unaPassword));
            //interfazPassword.mostrarError("Por favor ingrese otra:");
            //unaPassword = Main.pedirPorPantallaString();
            //unaPassword = this.chequearEspaciosSeguidos(unaPassword);
    }

    private boolean laPasswordEsElNombreDeUsuario(String unaPassword,String unNombreDeUsuario){
        if(unaPassword.equals(unNombreDeUsuario)){
            interfazPassword.mostrarError("La Contraseña no puede coincidir con el nombre de usuario");
            return true;
        }
        return false;
    }

    private boolean laPasswordTieneMalTamanio(String unaPassword) {
        if (unaPassword.length() < 8 || unaPassword.length() > 64) {
            interfazPassword.mostrarError("Su contraseña no tiene entre 8 y 64 caracteres.");
            return true;
        }
        return false;
    }
    private boolean passwordNoCumpleRequisitos(String password){
        boolean noCumpleRequisitos = false;
        if(!this.tieneMayusculas(password)){
            interfazPassword.mostrarError("La contrasenia debe tener al menos una mayuscula.");
            noCumpleRequisitos = true;
        }
        if(!this.tieneMinusculas(password)){
            interfazPassword.mostrarError("La contrasenia debe tener al menos una minuscula.");
            noCumpleRequisitos = true;
        }
        if(!this.tienenNumeros(password) && !this.tieneSimbolos(password)){
            interfazPassword.mostrarError("La contrasenia debe tener al menos un numero y/o un simbolo.");
            noCumpleRequisitos = true;
        }
        return noCumpleRequisitos;
    }
    public String chequearEspaciosSeguidos(String unaPassword) {
        int i;
        int contador = 0;
        String passwordFinal = "";
        for (i = 0; i < unaPassword.length(); i++) {
            if (i+1 == unaPassword.length()||!(unaPassword.charAt(i) == ' ' && unaPassword.charAt((i + 1)) == ' ')) {
                passwordFinal = passwordFinal + unaPassword.charAt(i);
            } else {
                contador += 1;
            }
        }
        if (contador > 0) {
            interfazPassword.mostrarAdvertencia("Se encontraron espacios consecutivos y se los reemplazo por uno solo.");
        }
        return passwordFinal;
    }

    public int seguridadClave(String clave) {
        int seguridad = 0;
        if (clave.length() != 0) {
            if (tieneMinusculas(clave)) {
                seguridad += 25;
            }
            if (tieneMayusculas(clave)){
                seguridad += 25;
            }
            if (tienenNumeros(clave)){
                seguridad += 15;
            }
            if(tieneSimbolos(clave)){
                seguridad += 15;
            }
            if(clave.length() > 15){
                seguridad += 20;
            }
        }
        interfazPassword.mostrarInformacion("La seguridad de la contraseña es de: " + seguridad + "%");
        return seguridad;
    }

    private boolean tienenNumeros(String texto) {
        String numeros = "0123456789";
        for (int i = 0; i < texto.length(); i++) {
            if (numeros.indexOf(texto.charAt(i), 0) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean tieneMinusculas(String texto) {
        String letras = "abcdefghyjklmnñopqrstuvwxyz";
        for (int i = 0; i < texto.length(); i++) {
            if (letras.indexOf(texto.charAt(i), 0) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean tieneSimbolos(String texto) {
        String letras = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        for (int i = 0; i < texto.length(); i++) {
            if (letras.indexOf(texto.charAt(i), 0) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean tieneMayusculas(String texto) {
        String letrasMayusculas = "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";
        for (int i = 0; i < texto.length(); i++) {
            if (letrasMayusculas.indexOf(texto.charAt(i), 0) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean laPasswordEsMala(String palabra) {
        int lineasTotales = 0;
        boolean esMala = false;
        File archivo = new File("src/main/java/10000_PeoresContrasenias.txt");
        try {
            if (archivo.exists()) {
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo));
                String lineaLeida;

                while ((lineaLeida = leerArchivo.readLine()) != null) {
                    lineasTotales = lineasTotales + 1;

                    String[] palabras = lineaLeida.split(" ");
                    for (int i = 0; i < palabras.length; i++) {
                        if (palabras[i].equals(palabra)) {
                            interfazPassword.mostrarInformacion("Su contraseña está en el puesto número " + lineasTotales + " de contraseñas malas.");
                            esMala = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            interfazPassword.mostrarError(e.getMessage());
        }
        return esMala;
    }

/*
    public void ingresarNuevaPassword(Usuario usuario) {


        while (this.laNuevaPasswordNoEstaOK(nuevaPassword) || this.laNuevaPasswordEsIgualALaAnterior(nuevaPassword,usuario)) {
            nuevaPassword = this.verificarPassword(interfazPassword.pedirString("Por favor ingrese otra contraseña:"),usuario.getUsuario());
        }
        String passwordHasheada = this.hashearPassword(nuevaPassword);
        interfazPassword.mostrarInformacion("La contraseña hasheada es " + passwordHasheada);
        interfazPassword.mostrarInformacion("La contraseña Final es " + nuevaPassword);

        this.seguridadClave(nuevaPassword);
        usuario.setPassword(passwordHasheada);
    }
*/
    private boolean laNuevaPasswordNoEstaOK(String nuevaPassword) {
        return this.tiene3LetrasSeguidasIguales(nuevaPassword) ||
                this.tiene3CaracteresConsecutivos(nuevaPassword);
    }

    private boolean tiene3LetrasSeguidasIguales(String nuevaPassword) {
        int i = 0;
        String nuevaPasswordSinEspacios = this.chequearEspaciosSeguidos(nuevaPassword);

        while (i < nuevaPasswordSinEspacios.length() - 1) {
            if (!this.las2LetrasSonDistintas(nuevaPasswordSinEspacios.charAt(i), nuevaPasswordSinEspacios.charAt(i + 1))) {
                if (!this.las2LetrasSonDistintas(nuevaPasswordSinEspacios.charAt((i + 1)), nuevaPasswordSinEspacios.charAt(i + 2))) {
                    interfazPassword.mostrarError("La Contraseña no puede tener 3 letras iguales seguidas");
                    return true;
                }
            }
            i++;
        }
        return false;
    }
    private boolean laNuevaPasswordEsIgualALaAnterior(String nuevaPassword,Usuario usuario){
        String passwordHasheada = this.hashearPassword(nuevaPassword);
        if( usuario.laPasswordCoincide(passwordHasheada)){
            interfazPassword.mostrarAdvertencia("La contrasenia ingresada es igual a la actual.");
            return true;
        }
        return false;
    }
    private boolean las2LetrasSonDistintas(char unaLetra, char otraLetra) {
        return unaLetra != otraLetra;
    }

    private boolean tiene3CaracteresConsecutivos(String nuevaPassword) {
        int i = 0;

        while (i < nuevaPassword.length() - 2) {

            if (this.esUnCaracterSucesivoParaAdelante(nuevaPassword.codePointAt(i), nuevaPassword.codePointAt(i + 1), nuevaPassword.codePointAt(i + 2)) ||
                    this.esUnCaracterSucesivoParaAtras(nuevaPassword.codePointAt(i), nuevaPassword.codePointAt(i + 1), nuevaPassword.codePointAt(i + 2))) {
                interfazPassword.mostrarError("La Contraseña no puede tener mas de 2 caracteres consecutivos");
                return true;
            }
            i++;
        }
        return false;
    }

    private boolean esUnCaracterSucesivoParaAdelante(int valor, int otroValor, int unValor) {
        return valor == otroValor - 1 && valor == unValor - 2;
    }

    private boolean esUnCaracterSucesivoParaAtras(int valor, int otroValor, int unValor) {
        return valor == otroValor + 1 && valor == unValor + 2;
    }

    public String setRandomPassword() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz" + "°@#$%&/=";
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public String hashearPassword(String input) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);

        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }
}
