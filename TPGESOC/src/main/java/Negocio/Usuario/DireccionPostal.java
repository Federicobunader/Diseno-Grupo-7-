package Negocio.Usuario;

import API.APIGeografia.Ciudad;
import API.APIGeografia.Pais;
import API.APIGeografia.Provincia;
import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="direccionPostal")
public class DireccionPostal  extends EntidadPersistente {

    @Column
    private String calle;
    @Column
    private int altura;
    @Column
    private int piso;
    @Column
    private char departamento;
    @Column
    private String pais;
    @Column
    private String provincia;
    @Column
    private String ciudad;

    public DireccionPostal() {
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public void setDepartamento(char departamento) {
        this.departamento = departamento;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
