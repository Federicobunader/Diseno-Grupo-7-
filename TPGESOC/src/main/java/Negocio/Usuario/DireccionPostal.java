package Negocio.Usuario;

import API.APIGeografia.Ciudad;
import API.APIGeografia.Pais;
import API.APIGeografia.Provincia;

import javax.persistence.*;

@Entity
@Table(name="direccionPostal")
public class DireccionPostal {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String calle;
    @Column
    private int altura;
    @Column
    private int piso;
    @Column
    private char departamento;
    @Column
    private Pais pais;
    private Provincia provincia;
    private Ciudad ciudad;
}
