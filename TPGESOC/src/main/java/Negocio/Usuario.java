package Negocio;


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

    public boolean laPasswordCoincide(String unaPassword){
        return password.equals(unaPassword);
    }

    public boolean elNombreCoincide(String nombreDeUsuario) { return usuario.equals(nombreDeUsuario);}


}



