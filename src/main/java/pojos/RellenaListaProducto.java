/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.ArrayList;

/**
 *
 * @author a022595832b
 */
public class RellenaListaProducto {

    public static ArrayList<Producto> AProducto() {
        ArrayList<Producto> exist = new ArrayList();
        
        Producto oProducto = new Producto();        
        oProducto.setNombre("Pepino");
        oProducto.setAmount(5);     
        exist.add(oProducto);
        
        Producto oProducto0 = new Producto(); 
        oProducto0.setNombre("Calabaza");
        oProducto0.setAmount(2);        
        exist.add(oProducto0);
        
        Producto oProducto1 = new Producto(); 
        oProducto1.setNombre("Zanahoria");
        oProducto1.setAmount(6);        
        exist.add(oProducto1);
        
        Producto oProducto2 = new Producto(); 
        oProducto2.setNombre("Cebolla");
        oProducto2.setAmount(8);        
        exist.add(oProducto2);
        
        Producto oProducto3 = new Producto(); 
        oProducto3.setNombre("Patata");
        oProducto3.setAmount(1);        
        exist.add(oProducto3);
        
        Producto oProducto4 = new Producto(); 
        oProducto4.setNombre("Calabacin");
        oProducto4.setAmount(3);        
        exist.add(oProducto4);
        
        Producto oProducto5 = new Producto(); 
        oProducto5.setNombre("Tomate");
        oProducto5.setAmount(12);        
        exist.add(oProducto5);
        
        Producto oProducto6 = new Producto(); 
        oProducto6.setNombre("Judia");
        oProducto6.setAmount(8);        
        exist.add(oProducto6);
        
        Producto oProducto7 = new Producto(); 
        oProducto7.setNombre("Puerro");
        oProducto7.setAmount(21);        
        exist.add(oProducto7);
        
        Producto oProducto8 = new Producto(); 
        oProducto8.setNombre("Pimiento");
        oProducto8.setAmount(10);        
        exist.add(oProducto8);
        
        return exist;
    }

}
