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
    private int num_nodo = 1;

    public Automata(Nodo_binario arbol_de_expresion) {
        this.arbol_de_expresion = arbol_de_expresion;
        Nodo_binario raiz = new Nodo_binario(".");
        Nodo_binario aceptacion = new Nodo_binario("#");
        aceptacion.setHoja(true);
        aceptacion.setAnulable(false);
        raiz.setHijo_derecho(aceptacion);
        raiz.setHijo_izquierdo(arbol_de_expresion);
        asignar_numeros(this.arbol_de_expresion);
        num_nodo = 0;
        metodo_arbol(this.arbol_de_expresion);
        System.out.println("digraph{\n" + graficar_arbol(this.arbol_de_expresion,num_nodo) + "\n}");
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
    
    public void graficar_tabla() {
        String t = "digraph Tabla{\n";
        t+="TITULO [shape=none label=\"Tabla\" fontsize=25];\n";
        t+="Tabla [label=\n";
        t+="{<f0> Simbolo|texto|";
        t+="{<f0> Hoja|";
        t+="{<f0> Siguientes|";
        
        
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
        }
        else if(actual.getDato().equals("+")) {
            actual.setAnulable(actual.getHijo_derecho().isAnulable());
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
        }
        else if(actual.getDato().equals("?")) {
            // Si es anulable
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_derecho().getPrimeros());
        }
        else if(actual.getDato().equals("|")) {
           // Or
            actual.setAnulable(actual.getHijo_izquierdo().isAnulable() || actual.getHijo_derecho().isAnulable());
            actual.getPrimeros().addAll(actual.getHijo_izquierdo().getPrimeros());
            actual.getPrimeros().addAll(actual.getHijo_derecho().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_izquierdo().getUltimos());
            actual.getUltimos().addAll(actual.getHijo_derecho().getUltimos());
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
        }
        
    }
    
    
    public void asignar_numeros(Nodo_binario actual) {
        if (actual == null) {
            return;
        }
        if(actual.isHoja()) {
            actual.setIdentificador(num_nodo);
            num_nodo++;
            return ;
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
    
    
    
    
    
}
