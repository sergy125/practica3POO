package etsisi.poo.managers;

import etsisi.poo.modelo.GenericMatchmake;

import java.util.LinkedList;

public class GenericMatchmakeManager {
    private LinkedList<GenericMatchmake> matchmakes;
    public <T> void agregarEmparejamiento(GenericMatchmake<T> tGenericMatchmake) {
        this.matchmakes.add(tGenericMatchmake);
    }
    public GenericMatchmakeManager() {
        this.matchmakes = new LinkedList<GenericMatchmake>();
    }
    public void mostrarEmparejamientos() {

        if (this.matchmakes.isEmpty()) {
            System.out.println("No hay emparejamientos");
        } else {
            System.out.println("Emparejamientos: ");
            for (int i = 0; i < this.matchmakes.size(); i++) {
                System.out.println(matchmakes.get(i).toString());
            }
        }
    }
}
