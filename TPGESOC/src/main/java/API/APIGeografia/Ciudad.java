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

    @Column
    public String idProvincia;

    @Column
    public String idPais;

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }


    // @ManyToOne
   // @JoinColumn(name="provincia_idProvincia", referencedColumnName = "id")
   // public Provincia state;

    //@ManyToOne
   // @JoinColumn(name="pais_idPais", referencedColumnName = "id")
   // public Pais country;

}
