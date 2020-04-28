package dominio;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GestorDeUsuarios {


    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();

    private static GestorDeUsuarios instance = null;

    private GestorDeUsuarios() {
    }

    public static GestorDeUsuarios GetInstance() {
        if (instance == null)
            instance = new GestorDeUsuarios();
        return instance;
    }


    public void consolaUsuario() {
        this.menuUsuario();
        int opcion = Main.pedirPorPantallaInt();

        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    this.registrarUsuario();
                    break;
                case 2:
                    this.loguearse();
                    break;
                case 3:
                    Usuario unUsuario = this.elegirUsuario();
                    if (unUsuario != null) {
                        gestorDePasswords.ingresarNuevaPassword(unUsuario);
                    }
                    break;
                case 4:
                    this.mostrarUsuarios();
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida.");
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
        System.out.println("3- CAMBIAR PASSWORD");
        System.out.println("4- MOSTRAR USUARIOS");
        System.out.println("5- SALIR");
        System.out.println(" ");
    }

    private void registrarUsuario() {
        System.out.println("Ingrese un Usuario :");
        String usuario = Main.pedirPorPantallaString();
        while (this.nombreDeUsuarioVacio(usuario) || this.usuarioYaExiste(usuario)) {
            System.out.println("Ingrese uno nuevo: ");
            usuario = Main.pedirPorPantallaString();
        }

        String password = gestorDePasswords.setRandomPassword();
        System.out.println("Su password sugerida es: " + password + ", desea cambiarla? si, no");
        String opcion = Main.pedirPorPantallaString();

        if (opcion.equals("si")) {
            System.out.println("Ingrese una Password que cuente con al menos una minuscula, una mayuscula, un numero y/o un simbolo:");
            password = Main.pedirPorPantallaString();
            password = gestorDePasswords.verificarPassword(password,usuario);
        }

        String passwordHasheada = gestorDePasswords.hashearPassword(password);
        System.out.println("La password hasheada es " + passwordHasheada);

        gestorDePasswords.seguridadClave(password);

        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);

        usuarios.add(new Usuario(usuario, passwordHasheada));
    }

    private boolean nombreDeUsuarioVacio(String nombreDeUsuario){
        boolean esVacio = false;
         if(nombreDeUsuario.isEmpty()){
             System.out.println("El nombre de usuario no puede estar vacio");
             esVacio = true;
         }
        return esVacio;
    }

    private boolean laListaDeUsuariosEstaVacia() {
        return usuarios.isEmpty();
    }

    private boolean usuarioYaExiste(String nombreDeUsuario) {
        if (!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).elNombreCoincide(nombreDeUsuario)) {
                    System.out.println("El usuario ya existe");
                    return true;
                }
            }
        }
        return false;
    }

    private Usuario elegirUsuario() {
        this.mostrarUsuarios(); //Lo pongo para probar, no tiene sentido mostrarle los usuarios al mismo usuario.
        System.out.println("Ingrese su Usuario");
        String nombreDeUsuario = Main.pedirPorPantallaString();

        while (this.buscarUsuario(nombreDeUsuario) == null) {

            System.out.println("Ingrese su Usuario nuevamente");
            nombreDeUsuario = Main.pedirPorPantallaString();

        }
        return this.buscarUsuario(nombreDeUsuario);
    }

    private Usuario buscarUsuario(String nombreDeUsuario) {
        if (!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).elNombreCoincide(nombreDeUsuario)) {
                    return usuarios.get(i);
                }
            }
        }
        System.out.println("Usuario No Encontrado");
        return null;
    }

    private void mostrarUsuarios() {
        System.out.println("Usuarios Existentes :");
        if (!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(" -----------------------------");
                System.out.println("Nombre de Usuario: " + usuarios.get(i).getUsuario());
                System.out.println("Password de Usuario: " + usuarios.get(i).getPassword());
                System.out.println(" -----------------------------");
            }
        }else{
            System.out.println("No hay usuarios para mostrar.");
        }
    }

    private void loguearse() {
        int contador = 0;

        Usuario unUsuario = this.elegirUsuario();
        System.out.println("Ingrese la password:");

        String unaPassword = Main.pedirPorPantallaString();
        unaPassword = gestorDePasswords.hashearPassword(unaPassword);

        while (!(unUsuario.laPasswordCoincide(unaPassword)) && contador < 10) {
            System.out.println("Password invalida.");
            System.out.println("Intentos Restantes: " + (10 - contador));
            contador += 1;
            System.out.println("Por favor espere " + contador * 10 + " segundos.");
            try {
                TimeUnit.SECONDS.sleep(contador * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ingrese otra password:");
            unaPassword = gestorDePasswords.hashearPassword(Main.pedirPorPantallaString());
        }

        if (contador == 10) {
            System.out.println("Has gastado todos los intentos. Vuelve a intentarlo mas tarde.");
        } else {
            System.out.println("SE HA LOGUEADO CORRECTAMENTE");
            System.out.println("Usuario: " + unUsuario.getUsuario());
            System.out.println("Password: " + unUsuario.getPassword());
        }
    }
}

