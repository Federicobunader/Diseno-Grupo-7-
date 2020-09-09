package Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas;

import javax.persistence.*;

@Entity
@Table(name = "microEmpresa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeEmpresa")
public class MicroEmpresa extends TipoDeEmpresa {

    @Transient
    private static MicroEmpresa instance = null;

    //String nombreTipoEmpresa = "Micro";

    private MicroEmpresa() {
    }

    public static MicroEmpresa GetInstance() {
        if (instance == null)
            instance = new MicroEmpresa();
        return instance;
    }

   // public String getNombreTipoEmpresa() {
        //return nombreTipoEmpresa;
  //  }
}
