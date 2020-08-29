package API.APIGeografia;

import javax.persistence.*;

@Entity
@Table(name="ciudad")
public class Ciudad {

    @Id
    @GeneratedValue
    public String id;
    @Column
    public String name;
}
