package repositories;

import BaseDeDatos.EntityManagerHelper;
import Bitacora.GuardadorDeLog;
import repositories.daos.DAO;
import repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class Repositorio<T> {
    protected DAO<T> dao;
    GuardadorDeLog guardadorDeLog = GuardadorDeLog.GetInstance();

    public Repositorio(DAO<T> dao) {
        this.dao = dao;
    }

    public void setDao(DAO<T> dao) {
        this.dao = dao;
    }

    public void agregar(Object unObjeto){

        this.dao.agregar(unObjeto);
        this.guardadorDeLog.GuardarEnBitacora(unObjeto, "ALTA");
    }

    public void modificar(Object unObjeto){
        this.dao.modificar(unObjeto);
        this.guardadorDeLog.GuardarEnBitacora(unObjeto, "MODIFICACIÓN");
    }

    public void eliminar(Object unObjeto){
        this.dao.eliminar(unObjeto);
        this.guardadorDeLog.GuardarEnBitacora(unObjeto, "BAJA");
    }

    public List<T> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public List<T> buscarTodosPorQuery(String query){
        return this.dao.buscarTodosPorQuery(query);
    }

    public T buscar(int id){
        return this.dao.buscar(id);
    }

    public CriteriaBuilder criteriaBuilder(){
        return EntityManagerHelper.getEntityManager().getCriteriaBuilder();
    }
}