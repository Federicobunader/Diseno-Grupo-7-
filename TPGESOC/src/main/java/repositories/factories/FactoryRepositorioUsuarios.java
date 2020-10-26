package repositories.factories;

import Negocio.Usuario.Usuario;
import config.Config;
import repositories.RepositorioDeUsuarios;
import repositories.daos.DAO;
import repositories.daos.DAOHibernate;
import repositories.daos.DAOMemoria;
import repositories.testMemoData.Data;

public class FactoryRepositorioUsuarios {
    private static RepositorioDeUsuarios repo;

    static {
        repo = null;
    }

    public static RepositorioDeUsuarios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
                repo = new RepositorioDeUsuarios(dao);
            }
            else{
                repo = new RepositorioDeUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
            }
        }
        return repo;
    }
}