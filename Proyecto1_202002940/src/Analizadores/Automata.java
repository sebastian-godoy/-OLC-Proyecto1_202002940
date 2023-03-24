/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Sebas
 */
public class Automata {
    public Nodo_binario arbol_de_expresion;
    private int num_nodo = 1;
    private ArrayList<Integer> estados_aceptacion = new ArrayList<>();
    //private Map<String, Set<String>> transiciones;
    //private Map<String, Set<String>> siguientes;
    //private String estadoInicial;
    private Set<String> estadosFinales;
    private Set<String> alfabeto;
    private final ArrayList<followposTabla> siguientes = new ArrayList<>();
    private final ArrayList<String> terminales = new ArrayList<>(); 
    private final ArrayList<ArrayList> transiciones = new ArrayList<>();
    
    
    

    

    public Automata(Nodo_binario arbol_de_expresion) {
        this.arbol_de_expresion = arbol_de_expresion;
        
        Nodo_binario raiz = new Nodo_binario(".");
        Nodo_binario aceptacion = new Nodo_binario("#");
        aceptacion.setHoja(true);
        aceptacion.setAnulable(false);
        raiz.setHijo_derecho(aceptacion);
        raiz.setHijo_izquierdo(arbol_de_expresion);
        this.arbol_de_expresion=raiz;
        asignar_numeros(this.arbol_de_expresion);
        num_nodo = 0;
        metodo_arbol(this.arbol_de_expresion);
        System.out.println("digraph{\n" + graficar_arbol(this.arbol_de_expresion,num_nodo) + "\n}");
        
        //transiciones = new HashMap<String, Set<String>>();
        //siguientes = new HashMap<String, Set<String>>();
        //estadoInicial = "";
        //estadosFinales = new HashSet<String>();
        //alfabeto = new HashSet<String>();
        
        calcular_transiciones();
        System.out.println("digraph{\n"+graficar_tabla_siguientes()+"\n}");
        
        
        
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
            //s+= "digraph{\n";
            s+= "n_" + num_nodo + "[label=<";
            s+= "<TABLE border=\"1\" cellspacing=\"2\" cellpading=\"10\" >\n"
                    +"<TR>\n"
                    // aca poner un if para ver si es falso o true
                    +"<TD colspan=\"3\">"+nodo.isAnulable()+"</TD>\n"
                    +"</TR>\n"
                    +"<TR>\n"
                    +"<TD>"+nodo.getPrimeros()+"</TD>\n"
                    +"<TD>"+nodo.getDato()+"</TD>\n"
                    +"<TD>"+nodo.getUltimos()+"</TD>\n"
                    +"</TR>\n"
                    +"<TR>\n"
                    +"<TD colspan=\"3\">"+nodo.getIdentificador()+"</TD>\n" 
                    +"</TR>\n"
                    +"</TABLE>>];\n";
            //System.out.println(s);
      
        } else {
            s+= "n_" + num_nodo + "[label=<";
            s+= "<TABLE border=\"1\" cellspacing=\"2\" cellpading=\"10\" >\n"
                    +"<TR>\n"
                    +"<TD colspan=\"3\">"+nodo.isAnulable()+"</TD>\n"
                    +"</TR>\n"
                    +"<TR>\n"
                    +"<TD>"+nodo.getPrimeros()+"</TD>\n"
                    +"<TD>"+nodo.getDato()+"</TD>\n"
                    +"<TD>"+nodo.getUltimos()+"</TD>\n"
                    +"</TR>\n"
                    +"<TR>\n"
                    +"<TD colspan=\"3\">"+nodo.getIdentificador()+"</TD>\n" 
                    +"</TR>\n"
                    +"</TABLE>>];\n";
            //System.out.println(s);
            
        }
        if (padre != 0) {
            s+= "n_" + padre + "-> n_" + actual + ";\n";
        }
        s+=graficar_arbol(nodo.getHijo_izquierdo(),actual);
        s+=graficar_arbol(nodo.getHijo_derecho(),actual);
        //System.out.println(s);
        return s;
    }
    
   
    
    public void metodo_arbol(Nodo_binario actual) {
        if (actual == null) {
            return;
        }
        
        if (actual.isHoja()) {
            // Agregar ids propios
            actual.getPrimeros().add(actual.getIdentificador());
            // El ultimo es el num de hoja
            actual.getUltimos().add(actual.getIdentificador());
            return;
        }
        
        metodo_arbol(actual.getHijo_izquierdo());
        metodo_arbol(actual.getHijo_derecho());
        
        if(actual.getDato().equals("*")) {
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
            // Ultimos del primer hijo
            actual.getUltimos().addAll(actual.getHijo_derecho().getPrimeros());
            for (int i = 0; i < actual.getHijo_derecho().getPrimeros().size(); i++) {
                for (int j = 0; j < actual.getHijo_derecho().getUltimos().size(); j++) {
                    siguientes.get(actual.getHijo_derecho().getPrimeros().get(i)-1).getSiguientes().add(actual.getHijo_derecho().getUltimos().get(j));
                }
                
            }
            
        }
        else if(actual.getDato().equals("+")) {
            actual.setAnulable(actual.getHijo_derecho().isAnulable());
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
            for (int i = 0; i < actual.getHijo_derecho().getPrimeros().size(); i++) {
                for (int j = 0; j < actual.getHijo_derecho().getUltimos().size(); j++) {
                    siguientes.get(actual.getHijo_derecho().getPrimeros().get(i)-1).getSiguientes().add(actual.getHijo_derecho().getUltimos().get(j));
                }
                
            }
        }
        else if(actual.getDato().equals("?")) {
            // Si es anulable
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_derecho().getPrimeros());
            //siguientes.add(new followposTabla(actual.getDato(), actual.getIdentificador()));
        }
        else if(actual.getDato().equals("|")) {
           // Or
            actual.setAnulable(actual.getHijo_izquierdo().isAnulable() || actual.getHijo_derecho().isAnulable());
            actual.getPrimeros().addAll(actual.getHijo_izquierdo().getPrimeros());
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_izquierdo().getUltimos());
            actual.getUltimos().addAll(actual.getHijo_derecho().getUltimos());
            //siguientes.add(new followposTabla(actual.getDato(), actual.getIdentificador()));
        }
        else if(actual.getDato().equals(".")) {
            // Concatenacion
            actual.setAnulable(actual.getHijo_izquierdo().isAnulable() && actual.getHijo_derecho().isAnulable());
            if(actual.getHijo_izquierdo().isAnulable()) {
                actual.getPrimeros().addAll(actual.getHijo_izquierdo().getPrimeros());
                actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
                
                
            }
            else{
                actual.getPrimeros().addAll(actual.getHijo_izquierdo().getPrimeros());
                
            }
            if(actual.getHijo_izquierdo().isAnulable()) {
                actual.getUltimos().addAll(actual.getHijo_izquierdo().getUltimos());
                actual.getUltimos().addAll(actual.getHijo_derecho().getUltimos());
                
            }
            else{
                actual.getPrimeros().addAll(actual.getHijo_izquierdo().getPrimeros());
                
            }
           for (int i = 0; i < actual.getHijo_izquierdo().getUltimos().size(); i++) {
                for (int j = 0; j < actual.getHijo_derecho().getPrimeros().size(); j++) {
                    siguientes.get(actual.getHijo_izquierdo().getUltimos().get(i)-1).getSiguientes().add(actual.getHijo_derecho().getPrimeros().get(j));
                }
                
            }
        }
        
    }
    
    
    public void asignar_numeros(Nodo_binario actual) {
        if (actual == null) {
            return;
        }
        if(actual.isHoja()) {
            System.out.println("Mensaje de prueba!!!!!!");
            actual.setIdentificador(num_nodo);
            num_nodo++;
            siguientes.add(new followposTabla(actual.getDato(), actual.getIdentificador()));
            if (!terminales.contains(actual.getDato()) && !actual.getDato().equals("#")) {
                terminales.add(actual.getDato());
            return;
        }
        
        }
        asignar_numeros(actual.getHijo_izquierdo());
        asignar_numeros(actual.getHijo_derecho());
    
    }

    public Nodo_binario getArbol_de_expresion() {
        return arbol_de_expresion;
    }

    public void setArbol_de_expresion(Nodo_binario arbol_de_expresion) {
        this.arbol_de_expresion = arbol_de_expresion;
    }
    
    public void calcular_transiciones() {
        int indice = 0;
        transiciones.add(new ArrayList<>());
        ArrayList fila = transiciones.get(0);
        fila.add(this.arbol_de_expresion.getPrimeros());
        while (indice < transiciones.size()) {
            //llenar con espacios vacios las columnas de la fila
            fila = transiciones.get(indice);
            for (String s : terminales) {
                fila.add(new ArrayList<>());
            }
            /* Agregegar trans
            AL ----------
            */
            for (int siguiente : (ArrayList<Integer>) fila.get(0)) {
                String simbolo = siguientes.get(siguiente-1).getSimbolo();
                if (simbolo.equals("#")) {
                    continue;
                }
                int columna = terminales.indexOf(simbolo) + 1;
                ArrayList<Integer> col_terminal = (ArrayList<Integer>) fila.get(columna);
                for (int i : siguientes.get(siguiente).getSiguientes()) {
                    if (!col_terminal.contains(i)) {
                        col_terminal.add(i);
                    }
                }
                Collections.sort(col_terminal);
            }
            //Crear estados
            boolean encontrado;
            for (int i = 1; i < fila.size(); i++) {
                encontrado = false;
                ArrayList<Integer> estado = (ArrayList<Integer>) fila.get(i);
                //ver si el estado creado por el terminal existe
                for (ArrayList<ArrayList> filas : transiciones) {
                    if (filas.get(0).equals(estado)) {
                        encontrado = true;
                        break;
                    }
                }
                //si no existe hacer un nuevo estado con los siguientes 
                if (!encontrado && !estado.isEmpty()) {
                    ArrayList<ArrayList> nueva_fila = new ArrayList<>();
                    nueva_fila.add(estado);
                    transiciones.add(nueva_fila);
                }
            }
            //bajar una fila
            indice++;
        }
    }
    
    public String graficar_tabla_siguientes() {
        String c = "label=<";
        c += "<TABLE border=\"1\" cellspacing=\"2\" cellpading=\"10\" >\n"
                + "<TR>\n"
                + "<TD>Symbol</TD>\n"
                + "<TD>Leaf</TD>\n"
                + "<TD>followPos</TD>\n"
                + "</TR>\n";
        // Iterar cada siguiente
        for (followposTabla pos : siguientes) {
            c+= "<TR>\n";
            c+="<TD>" + pos.getSimbolo() + "</TD>\n"
            +"<TD>" + pos.getHoja() + "</TD>\n"
            +"<TD>" + pos.getSiguientes() + "</TD>\n"
            +"</TR>\n";
        }
        c+=" </TABLE>>";
        return c;      
    }
    
    
    
}
    
   


