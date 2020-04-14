import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class GestorDeUsuarios {



    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    private static GestorDeUsuarios instance = null;
    private GestorDeUsuarios() {}
    public static GestorDeUsuarios GetInstance()
    {
        if (instance == null)
            instance = new GestorDeUsuarios();
        return instance;
    }





    public void consolaUsuario(){
        this.menuUsuario();
        int opcion = Main.pedirPorPantallaInt();

        while(opcion!= '5')
        {
            switch(opcion){
                case 1:
                    this.registrarUsuario();
                    break;
                case 2:
                    this.loguearse();
                    break;
                case 3:
                    Usuario unUsuario = this.elegirUsuario();
                    if(unUsuario != null) {
                        this.ingresarNuevaPassword(unUsuario);
                        }
                    break;
                case 4:
                    this.mostrarUsuarios();
                    break;
                default:
                    System.out.println("Sos un forro. Ingresa un numero posta ");
            }
            this.menuUsuario();

            opcion = Main.pedirPorPantallaInt();
        }
    }

    private void menuUsuario() //Menu
    {
        System.out.println("Ingrese una opcion:");
        System.out.println(" ");
        System.out.println("1- REGISTRAR USUARIO");
        System.out.println("2- INICIAR SESION");
        System.out.println("3- CAMBIAR CONTRASEÑA");
        System.out.println("4- MOSTRAR USUARIOS");
        System.out.println("5- HASHEAR PASSWORD");
        System.out.println("6- SALIR");
        System.out.println(" ");
    }

    private void registrarUsuario(){
        System.out.println("Ingrese un Usuario :");
        String usuario = Main.pedirPorPantallaString();
        while(this.usuarioYaExiste(usuario)) {
            System.out.println("Ya existe un usuario con ese nombre, ingrese uno nuevo: ");
            usuario = Main.pedirPorPantallaString();
        }

        String password = setRandomPassword();
        System.out.println("Su contraseña sugerida es: " + password + ", desea cambiarla? si, no");
        String opcion = Main.pedirPorPantallaString();

        if(opcion.equals("si")){
            System.out.println("Ingrese una Contraseña:");
            password = Main.pedirPorPantallaString();
            password = verificarPassword(password);
        }

        byte[] salt = new byte[0];
        salt = getSalt();
        String passwordHasheada = hashearPassword(password, salt);
        System.out.println("La contraseña hasheada es " + passwordHasheada);

        seguridadClave(password);

        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + password);

        usuarios.add(new Usuario(usuario,passwordHasheada));
    }

    private boolean laListaDeUsuariosEstaVacia(){
        return usuarios.isEmpty();
    }

    private boolean usuarioYaExiste(String nombreDeUsuario){
        if(!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).elNombreCoincide(nombreDeUsuario)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Usuario elegirUsuario(){
        this.mostrarUsuarios(); //Lo pongo para probar, no tiene sentido mostrarle los usuarios al mismo usuario.
        System.out.println("Ingrese su Usuario");
        String nombreDeUsuario = Main.pedirPorPantallaString();
       while(this.buscarUsuario(nombreDeUsuario) == null){

           System.out.println("Ingrese devuelta su Usuario");
           nombreDeUsuario = Main.pedirPorPantallaString();

        }
        return this.buscarUsuario(nombreDeUsuario);
    }

    private Usuario buscarUsuario(String nombreDeUsuario){
        if(!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size() ; i++) {
                if (usuarios.get(i).elNombreCoincide(nombreDeUsuario)) {
                    return usuarios.get(i);
                }
            }
        }
        System.out.println("Usuario No Encontrado");
        return null;
    }

    private void mostrarUsuarios(){
        System.out.println("Usuarios Existentes :");
        if(!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(" -----------------------------");
                System.out.println("Nombre de Usuario: " + usuarios.get(i).getUsuario());
                System.out.println("Contraseña de Usuario: " + usuarios.get(i).getPassword());
                System.out.println(" -----------------------------");
            }
        }
    }
    private String verificarPassword(String unaPassword) {
        unaPassword = this.chequearEspaciosSeguidos(unaPassword);
        while(this.laPasswordTieneMalTamanio(unaPassword) || this.laPasswordEsMala(unaPassword)){
            System.out.println("Por favor ingrese otra: ");
            unaPassword = Main.pedirPorPantallaString();
            unaPassword = this.chequearEspaciosSeguidos(unaPassword);
        }
        return unaPassword;
    }

    private boolean laPasswordTieneMalTamanio(String unaPassword){
        boolean malTamanio = false;
        if(unaPassword.length() < 8 || unaPassword.length() > 64){
            System.out.println("Su contraseña no tiene entre 8 y 64 caracteres.");
            malTamanio = true;
        }
        return malTamanio;
    }

    private String chequearEspaciosSeguidos(String unaPassword){
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
        if(contador>0) {
            System.out.println("Se encontraron espacios consecutivos y se los reemplazo por uno solo");
        }
        return passwordFinal;
    }



    private void seguridadClave(String clave){
        int seguridad = 0;
        if (clave.length()!=0){
            if (tienenNumeros(clave) && tieneLetras(clave)){
                seguridad += 30;
            }
            if (tieneMinusculas(clave) && tieneMayusculas(clave)){
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

    private boolean tienenNumeros(String texto){
        String numeros ="0123456789";
        for(int i=0; i<texto.length(); i++){
            if (numeros.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private boolean tieneLetras(String texto){
        String letras="abcdefghyjklmnñopqrstuvwxyz";
        texto = texto.toLowerCase();
        for(int i=0; i<texto.length(); i++){
            if (letras.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private boolean laPasswordEsMala(String palabra) {
        int lineasTotales = 0;
        boolean esMala = false;
        File archivo = new File("claves.txt");
        try {
            // SI EXISTE EL ARCHIVO
            if(archivo.exists()) {
                // ABRE LECTURA DEL ARCHIVO
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo));
                // LINEA LEIDA
                String lineaLeida;
                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                while((lineaLeida = leerArchivo.readLine()) != null) {
                    lineasTotales = lineasTotales + 1;

                    String[] palabras = lineaLeida.split(" "); // si lee espacio en blanco detecta q es nueva palabra
                    for(int i = 0 ; i < palabras.length ; i++) {
                        if(palabras[i].equals(palabra)) {
                            System.out.println("Su contraseña está en el puesto número " + lineasTotales +
                                    " de contraseñas malas.");
                            esMala = true;
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return esMala;
    }

    private boolean tieneMinusculas(String texto){
        String letras="abcdefghyjklmnñopqrstuvwxyz";
        for(int i=0; i<texto.length(); i++){
            if (letras.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private boolean tieneMayusculas(String texto){
        String letrasMayusculas="ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";
        for(int i=0; i<texto.length(); i++){
            if (letrasMayusculas.indexOf(texto.charAt(i),0)!=-1){
                return true;
            }
        }
        return false;
    }

    private String setRandomPassword(){

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz" + "°@#$%&/=";
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public void ingresarNuevaPassword(Usuario usuario) {
        System.out.println("Ingrese una NUEVA Contraseña:");
        String nuevaPassword = Main.pedirPorPantallaString();
        this.verificarPassword(nuevaPassword);

        while(!(this.laNuevaPasswordEstaOK(nuevaPassword))){
            System.out.println("La clave no es aceptada porque es debil, ingrese otra:");
            nuevaPassword = Main.pedirPorPantallaString();
        }

        this.seguridadClave(nuevaPassword);
        usuario.setPassword(nuevaPassword); //Creo que esto esta mal, porque rompe el encapsulamiento

    }

    private boolean laNuevaPasswordEstaOK(String nuevaPassword){
        return  this.noTiene3LetrasSeguidasIguales(nuevaPassword)&&
                this.noTiene3CaracteresConsecutivos(nuevaPassword);
    }

    private boolean noTiene3LetrasSeguidasIguales(String nuevaPassword){
        int i=0;
        String nuevaPasswordSinEspacios = this.chequearEspaciosSeguidos(nuevaPassword);

        while(i < nuevaPasswordSinEspacios.length()-1){
            if(!this.las2LetrasSonDistintas(nuevaPasswordSinEspacios.charAt(i),nuevaPasswordSinEspacios.charAt(i+1))){
                if(!this.las2LetrasSonDistintas(nuevaPasswordSinEspacios.charAt((i+1)),nuevaPasswordSinEspacios.charAt(i+2))) {
                    System.out.println("La Contraseña no puede tener 3 letras iguales seguidas");
                    return false;
                }
            }
            i++;
        }
        return  true;
    }

    private boolean las2LetrasSonDistintas(char unaLetra,char otraLetra){
        return unaLetra != otraLetra;
    }


    private boolean noTiene3CaracteresConsecutivos(String nuevaPassword){
        int i=0;

        while(i<nuevaPassword.length()-2){

            if(this.esUnCaracterSucesivoParaAdelante(nuevaPassword.codePointAt(i),nuevaPassword.codePointAt(i+1),nuevaPassword.codePointAt(i+2)) ||
                    this.esUnCaracterSucesivoParaAtras(nuevaPassword.codePointAt(i),nuevaPassword.codePointAt(i+1),nuevaPassword.codePointAt(i+2))){
                System.out.println("La Contraseña no puede tener mas de 2 caracteres consecutivos");
                return false;
            }

            i++;
        }
        return true;
    }

    private boolean esUnCaracterSucesivoParaAdelante(int valor, int otroValor, int unValor){

        return valor == otroValor -1 && valor == unValor -2;
    }

    private boolean esUnCaracterSucesivoParaAtras(int valor, int otroValor, int unValor){
        return valor == otroValor + 1 && valor == unValor +2;
    }

    private void loguearse() {
        int contador = 0;
        byte[] salt = new byte[0];
        Usuario unUsuario = this.elegirUsuario();
        System.out.println("Ingrese la contraseña:");
        String unaPassword = Main.pedirPorPantallaString();
        unaPassword = hashearPassword(unaPassword,salt);
        while(!(unUsuario.laPasswordCoincide(unaPassword)) && contador < 10){
            System.out.println("Contraseña invalida. Por favor espere.");
            System.out.println("Intentos Restantes: " + (10-contador));
            contador += 1;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ingrese otra contraseña:");
            unaPassword = Main.pedirPorPantallaString();
        };
        if(contador == 10){
            System.out.println("Has gastado todos los intentos. Vuelve a intentarlo mas tarde.");
        } else {
            System.out.println("SE HA LOGUEADO CORRECTAMENTE");
            System.out.println("Usuario: " + unUsuario.getUsuario());
            System.out.println("Contraseña: " + unUsuario.getPassword());
        }
    }

    private static String hashearPassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }


}
