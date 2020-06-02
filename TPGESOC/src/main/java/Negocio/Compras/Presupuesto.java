package Negocio.Compras;

import Negocio.Documento;

import java.util.ArrayList;

public class Presupuesto {

    Validador validador = Validador.GetInstance();


    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Documento> documentosComerciales = new ArrayList<Documento>();


    public Presupuesto( ArrayList<Item> items, ArrayList<Documento> documentosComerciales) {
        this.items = items;
        this.documentosComerciales = documentosComerciales;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double valorTotal(){
        return items.stream().mapToInt(item->item.valorTotal()).sum();
    }

}
