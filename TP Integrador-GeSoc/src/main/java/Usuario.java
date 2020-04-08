import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class Usuario {

    private String usuario;
    private String password;
    //public Entidad m_Entidad;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public void cambiarPassword() {
        System.out.println("Ingrese una NUEVA Contraseña:");
        String nuevaPassword = Main.pedirPorPantallaString();

        if(this.laNuevaPasswordEstaOK(nuevaPassword)){
            System.out.println("La Clave esta OK");
        }
        else{
            System.out.println("MALA CLAVE");
        }

    }

    private boolean laNuevaPasswordEstaOK(String nuevaPassword){
        return  Main.verificarPassword(nuevaPassword) &&
                this.noTiene2LetrasSeguidasIguales(nuevaPassword)&&
                this.noTiene3CaracteresConsecutivos(nuevaPassword);

    }

    private boolean noTiene2LetrasSeguidasIguales(String nuevaPassword){
        int i=0;

        while(i < nuevaPassword.length()-1){
            if(!this.las2LetrasSonDistintas(nuevaPassword.charAt(i),nuevaPassword.charAt(i+1))){
                System.out.println("La Contraseña no puede tener 2 letras iguales seguidas");
                return false;
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

            if(this.esUnCaracterSucesivo(nuevaPassword.codePointAt(i),nuevaPassword.codePointAt((i+1)))){
                if(this.esUnCaracterSucesivo(nuevaPassword.codePointAt((i+1)),nuevaPassword.codePointAt((i+2)))){
                    System.out.println("La Contraseña no puede tener mas de 2 caracteres consecutivos");
                    return false;
                }
            }

            i++;
        }
        return true;
    }

    private boolean esUnCaracterSucesivo(Integer unValor, Integer otroValor){

        return (unValor == otroValor + 1) || (unValor == otroValor -1);
    }

    public void loguearse() throws InterruptedException {
        int contador = 0;
        while(!(Main.verificarPassword(password)) && contador < 100){
            System.out.println("Contraseña invalida. Por favor espere.");
            System.out.println("Intentos Restantes : " + (100-contador));
            contador += 1;
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Ingrese otra contraseña:");
            password = Main.pedirPorPantallaString();
        };
        if(contador == 100){
            System.out.println("Has gastado todos los intentos. Vuelve a intentarlo mas tarde.");
        }
        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + password);
    }
    
    private static void seguridadClave(String clave){
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
        //return seguridad;
        System.out.println("La seguridad de la contraseña es de: "+seguridad);
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
}




