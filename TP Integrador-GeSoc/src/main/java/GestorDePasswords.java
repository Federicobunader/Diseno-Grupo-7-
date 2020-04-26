import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GestorDePasswords {

    private static GestorDePasswords instance = null;

    private GestorDePasswords() {
    }

    public static GestorDePasswords GetInstance() {
        if (instance == null)
            instance = new GestorDePasswords();
        return instance;
    }

    public String verificarPassword(String unaPassword,String unNombreDeUsuario) {
        unaPassword = this.chequearEspaciosSeguidos(unaPassword);
        while (this.laPasswordTieneMalTamanio(unaPassword) || this.laPasswordEsMala(unaPassword) || this.laPasswordEsElNombreDeUsuario(unaPassword,unNombreDeUsuario)
                 || this.seguridadClave(unaPassword) >= 80) {
            System.out.println("Por favor ingrese otra: ");
            unaPassword = Main.pedirPorPantallaString();
            unaPassword = this.chequearEspaciosSeguidos(unaPassword);
        }
        return unaPassword;
    }

    private boolean laPasswordEsElNombreDeUsuario(String unaPassword,String unNombreDeUsuario){
        if(unaPassword.equals(unNombreDeUsuario)){
            System.out.println("La Contraseña no puede coincidir con el nombre de usuario");
            return true;
        } else {
            return false;
        }
    }

    private boolean laPasswordTieneMalTamanio(String unaPassword) {
        boolean malTamanio = false;
        if (unaPassword.length() < 8 || unaPassword.length() > 64) {
            System.out.println("Su contraseña no tiene entre 8 y 64 caracteres.");
            malTamanio = true;
        }
        return malTamanio;
    }

    private String chequearEspaciosSeguidos(String unaPassword) {
        int i;
        int contador = 0;
        String passwordFinal = "";
        for (i = 0; i < unaPassword.length(); i++) {
            if (!(unaPassword.charAt(i) == ' ' && unaPassword.charAt((i + 1)) == ' ')) {
                passwordFinal = passwordFinal + unaPassword.charAt(i);
            } else {
                contador += 1;
            }
        }
        if (contador > 0) {
            System.out.println("Se encontraron espacios consecutivos y se los reemplazo por uno solo");
        }
        return passwordFinal;
    }

    public int seguridadClave(String clave) {
        int seguridad = 0;
        if (clave.length() != 0) {
            if (tieneMinusculas(clave)) {
                seguridad += 20;
            }
            if (tieneMayusculas(clave)){
                seguridad += 20;
            }
            if (tienenNumeros(clave)){
                seguridad += 20;
            }
            if(tieneSimbolos(clave)){
                seguridad += 20;
            }
            if(clave.length() > 15){
                seguridad += 20;
            }
        }

        System.out.println("La seguridad de la contraseña es de: " + seguridad + "%");
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
        File archivo = new File("10000_PeoresContrasenias.txt");
        try {
            // SI EXISTE EL ARCHIVO
            if (archivo.exists()) {
                // ABRE LECTURA DEL ARCHIVO
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo));
                // LINEA LEIDA
                String lineaLeida;
                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                while ((lineaLeida = leerArchivo.readLine()) != null) {
                    lineasTotales = lineasTotales + 1;

                    String[] palabras = lineaLeida.split(" "); // si lee espacio en blanco detecta q es nueva palabra
                    for (int i = 0; i < palabras.length; i++) {
                        if (palabras[i].equals(palabra)) {
                            System.out.println("Su contraseña está en el puesto número " + lineasTotales +
                                    " de contraseñas malas.");
                            esMala = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return esMala;
    }

    public void ingresarNuevaPassword(Usuario usuario) {
        System.out.println("Ingrese una NUEVA Contraseña:");
        String nuevaPassword = Main.pedirPorPantallaString();
        this.verificarPassword(nuevaPassword,usuario.getUsuario());

        while (!(this.laNuevaPasswordEstaOK(nuevaPassword))) {
            System.out.println("La contraseña no es aceptada porque es debil, ingrese otra:");
            nuevaPassword = Main.pedirPorPantallaString();
        }
        String passwordHasheada = this.hashearPassword(nuevaPassword);
        System.out.println("La contraseña hasheada es " + passwordHasheada);

        this.seguridadClave(nuevaPassword);
        usuario.setPassword(passwordHasheada); //Creo que esto esta mal, porque rompe el encapsulamiento

    }

    private boolean laNuevaPasswordEstaOK(String nuevaPassword) {
        return this.noTiene3LetrasSeguidasIguales(nuevaPassword) &&
                this.noTiene3CaracteresConsecutivos(nuevaPassword);
    }

    private boolean noTiene3LetrasSeguidasIguales(String nuevaPassword) {
        int i = 0;
        String nuevaPasswordSinEspacios = this.chequearEspaciosSeguidos(nuevaPassword);

        while (i < nuevaPasswordSinEspacios.length() - 1) {
            if (!this.las2LetrasSonDistintas(nuevaPasswordSinEspacios.charAt(i), nuevaPasswordSinEspacios.charAt(i + 1))) {
                if (!this.las2LetrasSonDistintas(nuevaPasswordSinEspacios.charAt((i + 1)), nuevaPasswordSinEspacios.charAt(i + 2))) {
                    System.out.println("La Contraseña no puede tener 3 letras iguales seguidas");
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    private boolean las2LetrasSonDistintas(char unaLetra, char otraLetra) {
        return unaLetra != otraLetra;
    }

    private boolean noTiene3CaracteresConsecutivos(String nuevaPassword) {
        int i = 0;

        while (i < nuevaPassword.length() - 2) {

            if (this.esUnCaracterSucesivoParaAdelante(nuevaPassword.codePointAt(i), nuevaPassword.codePointAt(i + 1), nuevaPassword.codePointAt(i + 2)) ||
                    this.esUnCaracterSucesivoParaAtras(nuevaPassword.codePointAt(i), nuevaPassword.codePointAt(i + 1), nuevaPassword.codePointAt(i + 2))) {
                System.out.println("La Contraseña no puede tener mas de 2 caracteres consecutivos");
                return false;
            }

            i++;
        }
        return true;
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
