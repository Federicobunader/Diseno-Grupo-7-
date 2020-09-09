package API.APIGeografia;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="ciudad")
public class Ciudad{

    @Id
    @GeneratedValue
    private int idCiudad;
    @Column
    public String id;
    @Column
    public String name;
}
