package repositories.daos;

import BaseDeDatos.EntityManagerHelper;
import repositories.BusquedaCondicional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DAOMensaje<Mensaje> extends DAOHibernate {

    private Class<Mensaje> type;

    public Class<Mensaje> getType() {
        return type;
    }

    public DAOMensaje(Class type) {
        super(type);
    }

    //HAY QUE VER COMO FILTRAR LOS MENSAJES PARA CADA USUARIO -> HAY QUE USAR critera.where() o filtrar la lista despues de recuperarla de la BD

    public List<Mensaje> buscarMensajesPorCondicion(int idUsuario) {
        CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mensaje> critera = builder.createQuery(this.type);
        critera.from(type);
        List<Mensaje> mensajes = EntityManagerHelper.getEntityManager().createQuery(critera).getResultList();
        return mensajes;
    }

}
