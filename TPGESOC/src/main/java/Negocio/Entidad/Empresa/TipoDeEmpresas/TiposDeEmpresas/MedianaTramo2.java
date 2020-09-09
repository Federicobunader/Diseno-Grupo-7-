package Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas;

import javax.persistence.*;

@Entity
@Table(name = "medianaTramo2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeEmpresa")
public class MedianaTramo2 extends TipoDeEmpresa {

    @Transient
    private static MedianaTramo2 instance = null;

    //@Transient
    //String nombreTipoEmpresa = "Mediana Tramo 2";

    private MedianaTramo2() {
    }

    public static MedianaTramo2 GetInstance() {
        if (instance == null)
            instance = new MedianaTramo2();
        return instance;
    }

    //public String getNombreTipoEmpresa() {
        //return nombreTipoEmpresa;
   // }
}
