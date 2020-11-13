
package repositories.factories;

import Negocio.Usuario.Usuario_x_mensaje;
import config.Config;
import repositories.RepositorioMensajesPorUsuario;
import repositories.daos.DAO;
import repositories.daos.DAOHibernate;
import repositories.daos.DAOMemoria;
import repositories.testMemoData.Data;

public class FactoryRepositorioMensajesPorUsuario {
    private static RepositorioMensajesPorUsuario repo;

    static {
        repo = null;
    }

    public static RepositorioMensajesPorUsuario get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Usuario_x_mensaje> dao = new DAOHibernate<>(Usuario_x_mensaje.class);
                repo = new RepositorioMensajesPorUsuario(dao);
            }
            else{
                repo = new RepositorioMensajesPorUsuario(new DAOMemoria<>(Data.getData(Usuario_x_mensaje.class)));
            }
        }
        return repo;
    }
}
