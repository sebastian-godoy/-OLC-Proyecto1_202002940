/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author Sebas
 */
public class Nodo_binario {
    public String dato;
    public Nodo_binario hijo_izq;
    public Nodo_binario hijo_der;
    private boolean hoja = false;
    private int identificador;

    public Nodo_binario(String dato) {
        this.dato = dato;
    } 
    
    public Nodo_binario getHijo_izquierdo() {
        return hijo_izq;
    }
    
    public void setHijo_izquierdo(Nodo_binario hijo_izq) {
        this.hijo_izq = hijo_izq;
    }
    
    public Nodo_binario getHijo_derecho() {
        return hijo_der;
    }
    
    public void setHijo_derecho(Nodo_binario hijo_der) {
        this.hijo_der = hijo_der;
    }
    
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }
    
    

}
