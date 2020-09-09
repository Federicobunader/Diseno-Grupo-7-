package API.APIGeografia;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="pais")
public class Pais {

    @Id
    @GeneratedValue
    private int idPais;

    @Column
    public String id;
    @Column
    public String name;
    @Column
    public String currency_id;
   // public List<Provincia> states;


}
