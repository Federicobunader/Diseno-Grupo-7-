package Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tipoDeEmpresa")
@DiscriminatorColumn(name="nombreTipoDeSector")
public abstract class TipoDeSector extends EntidadPersistente {

    public abstract double getTopeVentasMedianaTramo2();

    public abstract int getTopePersonalMedianaTramo2();

    public abstract double getTopeVentasMedianaTramo1();

    public abstract int getTopePersonalMedianaTramo1();

    public abstract double getTopeVentasPequenia();

    public abstract int getTopePersonalPequenia();
}
