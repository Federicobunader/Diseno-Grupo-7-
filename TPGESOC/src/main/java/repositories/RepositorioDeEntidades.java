package repositories;

import Negocio.Entidad.Entidad;
import Negocio.Usuario.Usuario;
import repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioDeEntidades extends Repositorio<Entidad> {

    public RepositorioDeEntidades(DAO<Entidad> dao) {
        super(dao);
    }


    public Boolean existe(int idEntidad){
        return buscarEntidad(idEntidad) != null;
    }

    public Entidad buscarEntidad(int idEntidad){
        return this.dao.buscar(idEntidad);
    }

}