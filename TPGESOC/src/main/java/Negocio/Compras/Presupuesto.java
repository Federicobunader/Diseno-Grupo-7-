package Negocio.Compras;

import Negocio.Documento;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="presupuesto")
public class Presupuesto {

    @Id
    @GeneratedValue
    private int id;

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
