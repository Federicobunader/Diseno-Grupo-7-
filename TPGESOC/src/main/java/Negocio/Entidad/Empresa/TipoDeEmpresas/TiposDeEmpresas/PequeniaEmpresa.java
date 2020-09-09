package Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas;

import javax.persistence.*;

@Entity
@Table(name = "pequeniaEmpresa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeEmpresa")
public class PequeniaEmpresa extends TipoDeEmpresa {

    @Transient
    private static PequeniaEmpresa instance = null;
   // @Transient
   // String nombreTipoEmpresa = "Pequeña";

    private PequeniaEmpresa() {
    }

    public static PequeniaEmpresa GetInstance() {
        if (instance == null)
            instance = new PequeniaEmpresa();
        return instance;
    }

   // public String getNombreTipoEmpresa() {
      //  return nombreTipoEmpresa;
    //}
}
