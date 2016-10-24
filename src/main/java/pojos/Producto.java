/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.google.gson.annotations.Expose;

/**
 *
 * @author a022595832b
 */
public class Producto {

    @Expose
    private String Nombre;
    @Expose
    private int Amount;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }
    
    
}
