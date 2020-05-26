package Negocio;

import Negocio.Entidad.Entidad;

import java.util.ArrayList;
import java.util.Calendar;

public class Presupuesto {

    Validador validador = Validador.GetInstance();


    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Documento> documentosComerciales = new ArrayList<Documento>();
    int IDCompraRealizada;

    public double valorTotal(){
        return items.stream().mapToInt(item->item.valorTotal()).sum();
    }

}
