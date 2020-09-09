package BaseDeDatos;

import Negocio.Usuario.Usuario;

import java.util.List;

public class UsuarioModel extends Model{

    private static UsuarioModel instance = null;

    public static UsuarioModel GetInstance() {
        if (instance == null)
            instance = new UsuarioModel();
        return instance;
    }

    @Override
    public List <Usuario> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from usuario").getResultList();
    }

    @Override
    public Usuario buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Usuario.class, id);
    }

    @Override
    public <T> T buscar(String email) {
        return null;
    }
}
