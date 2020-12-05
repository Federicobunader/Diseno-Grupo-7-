package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;
import Negocio.Documento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="presupuesto")
public class Presupuesto  extends EntidadPersistente {

    @OneToMany
    @JoinColumn(name = "presupuesto_id", referencedColumnName = "id")
    private List<Item> items = new ArrayList<Item>();

    @OneToMany
    @JoinColumn(name = "presupuesto_id", referencedColumnName = "id")
    private List<Documento> documentosComerciales = new ArrayList<Documento>();

    @Column
    public double valorTotal;


    public Presupuesto( List<Item> items, List<Documento> documentosComerciales) {
        this.items = items;
        this.documentosComerciales = documentosComerciales;
        valorTotal = this.valorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Item> getItems() {
        return items;
    }

    public Presupuesto() {
    }

    public void setDocumentosComerciales(List<Documento> documentosComerciales) {
        this.documentosComerciales = documentosComerciales;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double valorTotal(){
        return items.stream().mapToInt(item->item.valorTotal()).sum();
    }



}
