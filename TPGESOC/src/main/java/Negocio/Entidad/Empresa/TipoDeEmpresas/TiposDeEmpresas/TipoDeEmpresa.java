package Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tipoDeEmpresa")
@DiscriminatorColumn(name="nombreTipoEmpresa")
public abstract class TipoDeEmpresa extends EntidadPersistente {

   // @Column
   // String nombreTipoEmpresa;

    //public String getNombreTipoEmpresa() {
     //   return nombreTipoEmpresa;
   // }
}
