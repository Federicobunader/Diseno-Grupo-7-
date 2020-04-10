
import java.util.concurrent.TimeUnit;



public class Usuario {

    private String usuario;
    private String password;

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
        Registrarse.verificarPassword(nuevaPassword); //lo hicimos static y no tendria q ser asi ?

        while(!(this.laNuevaPasswordEstaOK(nuevaPassword))){
            System.out.println("La clave no es aceptada porque es debil, ingrese otra:");
            nuevaPassword = Main.pedirPorPantallaString();
        }
        this.password = nuevaPassword;
        Registrarse.seguridadClave(nuevaPassword); //lo hicimos static y no tendria q ser asi ?
    }

    private boolean laNuevaPasswordEstaOK(String nuevaPassword){
        return  this.noTiene3LetrasSeguidasIguales(nuevaPassword)&&
                this.noTiene3CaracteresConsecutivos(nuevaPassword);
    }

    private boolean noTiene3LetrasSeguidasIguales(String nuevaPassword){
        int i=0;

        while(i < nuevaPassword.length()-1){
            if(!this.las2LetrasSonDistintas(nuevaPassword.charAt(i),nuevaPassword.charAt(i+1))){
                if(!this.las2LetrasSonDistintas(nuevaPassword.charAt((i+1)),nuevaPassword.charAt(i+2))) {
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

    public void loguearse(String unaPassword) throws InterruptedException {
        int contador = 0;
        while(!(this.password.equals(unaPassword)) && contador < 10){
            System.out.println("Contraseña invalida. Por favor espere.");
            System.out.println("Intentos Restantes: " + (10-contador));
            contador += 1;
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Ingrese otra contraseña:");
            password = Main.pedirPorPantallaString();
        };
        if(contador == 10){
            System.out.println("Has gastado todos los intentos. Vuelve a intentarlo mas tarde.");
        } else {
            System.out.println("Usuario: " + usuario);
            System.out.println("Contraseña: " + password);
        }
    }
}



