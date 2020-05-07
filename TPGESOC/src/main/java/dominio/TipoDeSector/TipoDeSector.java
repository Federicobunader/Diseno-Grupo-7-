package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public abstract class TipoDeSector {

    public abstract TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa);

    public abstract TipoDeEmpresa calcularTipoDeEmpresaEspecial(Empresa unaEmpresa); //ver que onda esto

}