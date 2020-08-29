package API.APIGeografia;

import javax.persistence.*;

@Entity
@Table(name="provincia")
public class Provincia {

    @Id
    @GeneratedValue
    public String id;
    @Column
    public String name;
}
