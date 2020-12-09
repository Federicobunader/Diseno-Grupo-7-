package Bitacora;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="bitacora")
public class Bitacora extends EntidadPersistente {

   @Transient
    private static Bitacora instance = null;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "bitacora_id", referencedColumnName = "id")
    private List<Operacion> operaciones = new ArrayList<>();

    public static Bitacora GetInstance() {
        if (instance == null)
            instance = new Bitacora();
        return instance;
    }

    public void add(Operacion operacion){
        operaciones.add(operacion);
    }

    public List<Operacion> getOperaciones() {
        return operaciones;
    }

    public void agregarOperacion (Operacion operacion){
        operaciones.add(operacion);
    }

    public void setOperaciones(List<Operacion> operaciones) {
        this.operaciones = operaciones;
    }
}
