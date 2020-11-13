package repositories;

import Negocio.Usuario.Usuario_x_mensaje;
import repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class RepositorioMensajesPorUsuario extends  Repositorio <Usuario_x_mensaje> {

    public RepositorioMensajesPorUsuario(DAO<Usuario_x_mensaje> dao) {
        super(dao);
    }

    public List<Usuario_x_mensaje> buscarMensajes(int idUsuario){
        return this.dao.buscarTodos(condicionIdUsuario(idUsuario));
    }

    private BusquedaCondicional condicionIdUsuario(int idUsuario){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario_x_mensaje> mensajesPorUsuarioQuery = criteriaBuilder.createQuery(Usuario_x_mensaje.class);

        Root<Usuario_x_mensaje> condicionRaiz = mensajesPorUsuarioQuery.from(Usuario_x_mensaje.class);

        Predicate condicionIdUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario_id"), idUsuario);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionIdUsuario);

        mensajesPorUsuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, mensajesPorUsuarioQuery);
    }
}
