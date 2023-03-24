/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class Nodo_binario {
    // Primeros
    private ArrayList<Integer> primeros = new ArrayList<>();
    // Ultimos
    private ArrayList<Integer> ultimos = new ArrayList<>();
    
    public String dato;
    private int identificador;
    // Nodos individuales
    public Nodo_binario hijo_izq;
    public Nodo_binario hijo_der;
    private boolean anulable;
    // En caso de no tener hijos
    private boolean hoja = false;
    

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

    public int getIdentificador() {
        return identificador;
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

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }
    
  
        
    }
    
    
    

    
    
    
    
    
    
    


