package API.APIGeografia;

import javax.persistence.*;

@Entity
@Table(name="provincia")
public class Provincia {

    @Id
    @GeneratedValue
    private int idProvincia;
    @Column
    public String id;
    @Column
    public String name;

   // @ManyToOne
    //@JoinColumn(name="pais_idPais", referencedColumnName = "id")
   // public Pais country;

    @Column
    public String id_pais;

    public void setId_pais(String id_pais) {
        this.id_pais = id_pais;
    }
}
