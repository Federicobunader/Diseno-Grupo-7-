package repositories.factories;

import Negocio.Entidad.Entidad;
import Negocio.Usuario.Usuario;
import config.Config;
import repositories.RepositorioDeEntidades;
import repositories.RepositorioDeUsuarios;
import repositories.daos.DAO;
import repositories.daos.DAOHibernate;
import repositories.daos.DAOMemoria;
import repositories.testMemoData.Data;

public class FactoryRepositorioEntidad {
    private static RepositorioDeEntidades repo;

    static {
        repo = null;
    }

    public static RepositorioDeEntidades get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Entidad> dao = new DAOHibernate<>(Entidad.class);
                repo = new RepositorioDeEntidades(dao);
            }
            else{
                repo = new RepositorioDeEntidades(new DAOMemoria<>(Data.getData(Entidad.class)));
            }
        }
        return repo;
    }
}