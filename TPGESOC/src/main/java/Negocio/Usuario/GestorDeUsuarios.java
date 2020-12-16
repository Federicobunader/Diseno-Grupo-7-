package Negocio.Usuario;

import InterfazDeUsuario.InterfazUsuarios;
import Negocio.Compras.*;
import Negocio.Compras.Criterios.Criterio;
import Negocio.Compras.Criterios.MenorValor;
import Negocio.Compras.Vinculacion.*;
import Negocio.Documento;
import Negocio.Entidad.Entidad;
import Negocio.Entidad.EntidadBase;
import Negocio.Main;
import Negocio.MedioDePago;
import Negocio.Proveedor;
import repositories.RepositorioDeUsuarios;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GestorDeUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();
    InterfazUsuarios interfazUsuarios = InterfazUsuarios.GetInstance();
    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();
    GestorDeIngresos gestorDeIngresos = GestorDeIngresos.GetInstance();

    private static GestorDeUsuarios instance = null;

    private GestorDeUsuarios() {
    }

    public static GestorDeUsuarios GetInstance() {
        if (instance == null)
            instance = new GestorDeUsuarios();
        return instance;
    }

    public void consolaUsuario(){
        interfazUsuarios.menuUsuario();
        int opcion = Main.pedirPorPantallaInt();
        GestorDeCriterios gestorDeCriterios = GestorDeCriterios.GetInstance();

        while (opcion != 11) {
            switch (opcion) {
                case 1:
                   // this.registrarUsuario();
                    break;
                case 2:
                  //  this.loguearse();
                    break;
                case 3:
                    Usuario unUsuario = this.elegirUsuario();

                case 4:
                    this.mostrarUsuarios();
                    break;
                case 5:
                    gestorDeCriterios.agregarCriterio("");
                    break;
                case 6:
                    gestorDeCriterios.agregarCategoria();
                    break;
                case 7:
                    gestorDeCriterios.mostrarCategorias();
                    break;
                case 8:
                    gestorDeIngresos.agregarIngreso();
                    break;
                case 9:
                    String passwordAdmin = interfazUsuarios.pedirString("Ingrese la Contraseña de administrador");

                    if(passwordAdmin.equals("GESOC")){

                        String criterio = interfazUsuarios.pedirString("Ingrese el criterio que desea utilizar para la vinculación: \n 1- Orden valor primero egreso \n 2- Orden valor primero ingreso \n 3- Por fecha \n 4- Mix");

                        CriterioDeVinculacion criterioElegido = new OrdenValorPrimeroIngreso();
                        switch (criterio){
                            case "1":
                                criterioElegido = new OrdenValorPrimeroEgreso();
                                break;
                            case "2":
                                criterioElegido = new OrdenValorPrimeroIngreso();
                                break;
                            case "3":
                                criterioElegido = new PorFecha();
                                break;
                            case "4":
                                criterioElegido = new Mix();
                                break;
                            default:
                                interfazUsuarios.mostrarError("No existe esa opcion");
                        }

                        if(criterio.equals("4")){
                            interfazUsuarios.mostrarInformacion("Coming soon");
                        }else {
                          //  criterioElegido.vincular();
                            interfazUsuarios.mostrarInformacion("Vinculación efectuada con éxito");
                        }

                    }else{
                        interfazUsuarios.mostrarError("Error: Contraseña incorrecta.");
                    }
                    break;
                case 10:

                    Proveedor proveedor1 = new Proveedor("Harry Styles",1);
                    Proveedor proveedor2 = new Proveedor("Gonzalo Miele",2);
                    Proveedor proveedor3 = new Proveedor("Justin Bieber",3);
                    Proveedor proveedor4 = new Proveedor("C.R.O.",4);

                    Producto producto1 = new Producto(5,proveedor1,"Silla");
                    Producto producto2 = new Producto(10,proveedor1,"Mesa");
                    Producto producto3 = new Producto(15,proveedor1,"Sillon");

                    ArrayList<CategoriaItem> categorias = new ArrayList<CategoriaItem>();

                    CategoriaItem categoria1 = new CategoriaItem("Roble","Muebles");
                    CategoriaItem categoria2 = new CategoriaItem("Pino","Muebles");

                    categorias.add(categoria1);
                    categorias.add(categoria2);

                    CriterioDeItem criterioDeItem = new CriterioDeItem("Muebles");

                    Item item1 = new Item(producto1,5, categorias);
                    Item item2 = new Item(producto2,15, categorias);
                    Item item3 = new Item(producto3,25, categorias);
                    Item item4 = new Item(producto1,150, categorias);
                    Item item5 = new Item(producto2,50, categorias);
                    Item item6 = new Item(producto3,100, categorias);
                    Item item7 = new Item(producto1,500, categorias);
                    Item item8 = new Item(producto2,200, categorias);

                    Documento documento = new Documento(1,"a");

                    ArrayList<Documento> documentosComerciales = new ArrayList<Documento>();
                    documentosComerciales.add(documento);

                    ArrayList<Item> items1 = new ArrayList<Item>();
                    items1.add(item1);
                    items1.add(item2);
                    items1.add(item3);

                    ArrayList<Item> items2 = new ArrayList<Item>();
                    items2.add(item4);
                    items2.add(item5);
                    items2.add(item6);

                    ArrayList<Item> items3 = new ArrayList<Item>();
                    items3.add(item7);
                    items3.add(item2);
                    items3.add(item8);

                    Presupuesto presupuesto1 = new Presupuesto(items1,documentosComerciales);
                    Presupuesto presupuesto2 = new Presupuesto(items2,documentosComerciales);
                    Presupuesto presupuesto3 = new Presupuesto(items3,documentosComerciales);

                    ArrayList<Presupuesto> presupuestos1 = new ArrayList<Presupuesto>();
                    presupuestos1.add(presupuesto1);
                    presupuestos1.add(presupuesto2);
                    presupuestos1.add(presupuesto3);

                    Usuario usuario1 = new Usuario();
                    usuario1.setUsuario("Federico di Napoli");
                    usuario1.setPassword("AguanteRiver01");

                    Usuario usuario2 = new Usuario();
                    usuario2.setUsuario("Alejandro Hassan");
                    usuario2.setPassword("Pablo Mendez02");
                    Usuario usuario3 = new Usuario();
                    usuario3.setUsuario("Juan Francisco Nadal");
                    usuario3.setPassword("AwanteLaUBA123");

                    ArrayList<Usuario> losUsuarios = new ArrayList<Usuario>();
                    losUsuarios.add(usuario1);
                    losUsuarios.add(usuario2);
                    losUsuarios.add(usuario3);

                    usuarios.add(usuario1);
                    usuarios.add(usuario2);
                    usuarios.add(usuario3);
                    //MedioDePago medioDePago = new MedioDePago(MedioDePago.tipoDePago.creditCard,1); //no se que onda esto

                    EntidadBase entidadbase1 = new EntidadBase("EntidadBase" ,"descripcion");
                    MenorValor menorValor = new MenorValor();

                    Compra compra1 = new Compra(items1, presupuestos1, null, documentosComerciales,  entidadbase1,  proveedor1,  true,menorValor ,  100);
                    compra1.seleccionarPresupuesto();
                    compra1.setUsuariosRevisores(losUsuarios);
                    compra1.efectuarCompra();

                    System.out.println(gestorDeEgresos.getComprasNoValidadas().size());
                    for (int i = 0; i < gestorDeEgresos.getComprasNoValidadas().size() ; i++) {
                        Compra compra = gestorDeEgresos.getComprasNoValidadas().get(i);
                        compra.validar(0,0,i);
                    }

                    for(int i = 0; i < usuarios.size(); i++){
                        System.out.println("Usuario " + usuarios.get(i).getUsuario() + ": ");
                        for(int j = 0; j < usuarios.get(i).getBandejaDeMensajes().size(); j++) {
                            System.out.println("Mensaje nro " + (j+1) + ": ");
                            System.out.println(usuarios.get(i).getBandejaDeMensajes().get(j).getContenido());
                        }
                    }
                    break;
                default:
                    interfazUsuarios.mostrarError("La opcion ingresada no es valida.");
            }
            interfazUsuarios.menuUsuario();

            opcion = Main.pedirPorPantallaInt();
        }
    }

    private void mostrarUsuarios(){
        interfazUsuarios.mostrarInformacion("Usuarios Existentes :");
        if (!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                interfazUsuarios.mostrarInformacion(" -----------------------------");
                interfazUsuarios.mostrarInformacion("Nombre de Usuario: " + usuarios.get(i).getUsuario());
                interfazUsuarios.mostrarInformacion("Password de Usuario: " + usuarios.get(i).getPassword());
                interfazUsuarios.mostrarInformacion(" -----------------------------");
            }
        }else{
           interfazUsuarios.mostrarError("No hay usuarios para mostrar.");
        }
    }

    public boolean nombreDeUsuarioVacio(String nombreDeUsuario){
        boolean esVacio = false;
         if(nombreDeUsuario.isEmpty()){
             interfazUsuarios.mostrarError("El nombre de usuario no puede estar vacio");
             esVacio = true;
         }
        return esVacio;
    }

    public boolean laListaDeUsuariosEstaVacia() {
        return usuarios.isEmpty();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean usuarioYaExiste(String nombreDeUsuario) {
        if (!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).elNombreCoincide(nombreDeUsuario)) {
                    interfazUsuarios.mostrarError("El usuario ya existe");
                    return true;
                }
            }
        }
        return false;
    }

    public Usuario buscarUsuario(String nombreDeUsuario) {
        if (!this.laListaDeUsuariosEstaVacia()) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).elNombreCoincide(nombreDeUsuario)) {
                    return usuarios.get(i);
                }
            }
        }
        interfazUsuarios.mostrarError("Usuario No Encontrado");
        return null;
    }

    public void loguearse(String nombreUsuario, String password_hasheada, RepositorioDeUsuarios repoUsuarios) {
        int contador = 0;

        if( ! repoUsuarios.existe(nombreUsuario,password_hasheada) && contador < 10) {

            Usuario usuario = repoUsuarios.buscarUsuario(nombreUsuario,password_hasheada);
            interfazUsuarios.mostrarError("Password invalida.");
            interfazUsuarios.mostrarAdvertencia("Intentos Restantes: " + (10 - contador));
            contador += 1;
            interfazUsuarios.mostrarAdvertencia("Por favor espere " + contador * 10 + " segundos.");
            repoUsuarios.buscar(usuario.getId());
            usuario.setIntentos(contador);
            repoUsuarios.modificar(usuario);

            try {
                TimeUnit.SECONDS.sleep(contador * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (contador == 10) {
            interfazUsuarios.mostrarAdvertencia("Has gastado todos los intentos. Vuelve a intentarlo mas tarde.");
        }
    }

    public void agregarUsuario(String usuario, String passwordHasheada) {
        Usuario usuario1 = new Usuario();
        usuario1.setUsuario(usuario);
        usuario1.setPassword(passwordHasheada);
        usuarios.add(usuario1);
    }

    public Usuario elegirUsuario() {
        String nombreDeUsuario = interfazUsuarios.pedirString("Ingrese su Usuario");
        while (this.buscarUsuario(nombreDeUsuario) == null) {
            nombreDeUsuario = interfazUsuarios.pedirString("Ingrese su Usuario nuevamente");
        }
        return this.buscarUsuario(nombreDeUsuario);
    }

    public void registrarUsuario(String usuario) {
        while (this.nombreDeUsuarioVacio(usuario) || this.usuarioYaExiste(usuario)) {
            usuario = interfazUsuarios.pedirString("Ingrese nuevamente un Usuario:");
        }
        String password = gestorDePasswords.setRandomPassword();
        String opcion = interfazUsuarios.pedirString("Su password sugerida es: " + password + ", desea cambiarla? si, no");

        if (opcion.equals("si")) {
            password = interfazUsuarios.pedirString("Ingrese una Password que cuente con al menos una minuscula, una mayuscula, un numero y/o un simbolo:");
            //password = gestorDePasswords.verificarPassword(password,usuario);
        }

        String passwordHasheada = gestorDePasswords.hashearPassword(password);
        interfazUsuarios.mostrarInformacion("La password hasheada es " + passwordHasheada);

        gestorDePasswords.seguridadClave(password);

       interfazUsuarios.mostrarInformacion("Usuario: " + usuario);
       interfazUsuarios.mostrarInformacion("Password: " + password);

        this.agregarUsuario(usuario, passwordHasheada);
    }
}
