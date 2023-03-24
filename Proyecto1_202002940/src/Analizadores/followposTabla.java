
package Analizadores;

import java.util.ArrayList;

public class followposTabla {
    private String simbolo;
    // N solo
    private int hoja;
    private ArrayList<Integer> siguientes = new ArrayList<>();

    // Constructor
    public followposTabla(String simbolo, int hoja) {
        this.simbolo = simbolo;
        this.hoja = hoja;
    }

    //Getters-Setters------------------------------------------
    public ArrayList<Integer> getSiguientes() {
        return siguientes;
    }
    
    public String getSimbolo() {
        return simbolo;
    }

    public int getHoja() {
        return hoja;
    }
    
    
}

