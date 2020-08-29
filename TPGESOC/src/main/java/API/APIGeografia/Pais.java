package API.APIGeografia;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="pais")
public class Pais {

    @Id
    @GeneratedValue
    public String id;
    @Column
    public String name;
    @Column
    public String currency_id;
   // public List<Provincia> states;


}
