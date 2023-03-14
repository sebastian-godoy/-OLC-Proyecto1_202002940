/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author Sebas
 */
public class Automata {
    public Nodo_binario arbol_de_expresion;
    private int num_nodo = 0;

    public Automata(Nodo_binario arbol_de_expresion) {
        this.arbol_de_expresion = arbol_de_expresion;
        System.out.println(graficar_arbol(arbol_de_expresion,num_nodo));
    }
    
    public String crear_arbol(Nodo_binario actual) {
        String graphviz = "";
        if(actual == null) {
            num_nodo -= -1;
            return graphviz;
        }
        return "";
    }
    
    public String graficar_arbol(Nodo_binario nodo, int padre) {
        String s = "";
        num_nodo += 1;
        
        int actual = num_nodo;
        if (nodo ==  null) {
            num_nodo -=1;
            return s;
        }
        
        if (nodo.isHoja()) {
            s+= "n_" + num_nodo + "[label=\'" + nodo.dato + "\']" + ";";
            
        } else {
            s+= "n_" + num_nodo + "[label=\'" + nodo.dato + "\']" + ";";
            
        }
        if (padre != 0) {
            s+= "n_" + padre + "-> n_" + actual + ";";
        }
        s+=graficar_arbol(nodo.getHijo_izquierdo(),actual);
        s+=graficar_arbol(nodo.getHijo_derecho(),actual);
        return s;
    }
    
    public void graficar_tabla() {
        String t = "digraph Tabla{\n";
        t+="TITULO [shape=none label=\"Tabla\" fontsize=25];\n";
        t+="Tabla [label=\n";
        t+="{<f0> Simbolo|texto|";
        t+="{<f0> Hoja|";
        t+="{<f0> Siguientes|";
        
        
    }
    
    
    public void asignar_numeros(Nodo_binario actual) {
        if(actual.isHoja()) {
            actual.setIdentificador(num_nodo);
            num_nodo++;
            //return ;
        }
        //asignar_numeros(actual.getHijo_izquierdo())
    }
    
    
    
}
