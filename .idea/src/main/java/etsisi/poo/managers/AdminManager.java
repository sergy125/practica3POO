package etsisi.poo.managers;



import etsisi.poo.modelo.Admin;

import java.util.LinkedList;

public class AdminManager {
    private LinkedList<Admin> admins;
    public AdminManager(){
        admins= new LinkedList<>();
    }

    public String altasEquiposAdmin(Admin admin){
        StringBuilder result = new StringBuilder("");
        result.append("Lista de equipos de " + admin.getUsername() + ":\n");
        result.append(admin.getListaAltasEquipos().toString());
        return result.toString();
    }

    public String altasJugadoresAdmin(Admin admin){
        StringBuilder result = new StringBuilder("");
        result.append("Lista de jugadores de " + admin.getUsername() + ":\n");
        result.append(admin.getListaAltasJugadores().toString());
        return result.toString();
    }
}//HOLA
