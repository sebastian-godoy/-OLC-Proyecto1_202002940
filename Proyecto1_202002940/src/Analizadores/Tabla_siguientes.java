/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Tabla_siguientes {
    private Map<Integer, Set<Integer>> tabla;

    public Tabla_siguientes() {
        tabla = new HashMap<>();
    }

    public void agregarSiguiente(int i, int j) {
        if (!tabla.containsKey(i)) {
            tabla.put(i, new HashSet<>());
        }
        tabla.get(i).add(j);
    }

    public Set<Integer> getSiguientes(int i) {
        return tabla.get(i);
    }
}






