package Negocio;//package Negocio;


import Negocio.Compras.Compra;
import Negocio.Compras.Item;
import Negocio.Compras.Producto;
import Negocio.Entidad.Empresa.Empresa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        Empresa unaEmpresa = new Empresa(10,10,"Construccion");

       // Proveedor provedor

        //Producto Producto1 = new Producto()

        //Item item1 = new Item();

       // Compra unaCompra = new Compra();
       // unaEmpresa.contratarPersonal(100);
       // unaEmpresa.despedirPersonal(500);

        Sistema sistema = Sistema.GetInstance();
        sistema.arrancar();
    }

    public static String pedirPorPantallaString() {
        String res = "";
        try {
            BufferedReader entrada =
                    new BufferedReader(new InputStreamReader(System.in));
            res = entrada.readLine();
        }
        catch (IOException e) {}

        return res;
    }

    public static int pedirPorPantallaInt() {
        String dato = pedirPorPantallaString();

        int res = Integer.parseInt(dato);
        return res;
    }
}

