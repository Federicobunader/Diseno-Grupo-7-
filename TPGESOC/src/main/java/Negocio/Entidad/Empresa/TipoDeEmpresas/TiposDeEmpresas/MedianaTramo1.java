package Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas;

import javax.persistence.*;

@Entity
@Table(name = "medianaTramo1")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeEmpresa")
public class MedianaTramo1 extends TipoDeEmpresa {

    @Transient
    private static MedianaTramo1 instance = null;

   // @Transient
   // String nombreTipoEmpresa = "Mediana Tramo 1";

    private MedianaTramo1() {
    }

    public static MedianaTramo1 GetInstance() {
        if (instance == null)
            instance = new MedianaTramo1();
        return instance;
    }

    //public String getNombreTipoEmpresa() {
     //   return nombreTipoEmpresa;
    //}
}
