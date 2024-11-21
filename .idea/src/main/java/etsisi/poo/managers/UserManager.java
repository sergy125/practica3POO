package etsisi.poo.managers;

import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class UserManager {  //Almacenamiento de correos y contraseñas de usuarios (player o admins)
    private static final String ERROR1 = "COMMAND USAGE: LOGIN [EMAIL];[PASSWORD]";
    //private static ArrayList<User> users;
    private HashMap<String, User> users;

    public UserManager(){
        //users=new ArrayList<User>();
        users = new HashMap<>();
    }
    public boolean existsUserWithUsername(String username){
        if(users==null) return false;
        else return users.containsKey(username);
    }
    public User getUserWithUsername(String username) {
        return users.get(username);
    }
    public Player getPlayerWithDni(String dni) {
        Player player = null;
        for (User user : users.values()) {
            if (user instanceof Player) {
                player = (Player) user;
                if (Objects.equals(player.getDni(), dni)) {
                    return player;
                }
            }
        }
        return player;
    }
    public boolean existsPlayerWithDni(String dni) {
        for (User user : users.values()) {
            if (user instanceof Player) {
                Player player = (Player) user;
                if (Objects.equals(player.getDni(), dni)) {
                    return true;
                }
            }
        }
        return false;
    }

    //generar getusers

    public HashMap<String, User> getUsers(){
        return users;
    }

    public void logout(User user){
        if (user!=null){
            users.remove(user.getUsername());
        }
    }
    public int getSize() {
        return users.size();
    }

    public void add(User user) {
        users.put(user.getUsername(), user);
    }
    public Player getPlayerPosition(int position) {
        if(users.get(position) instanceof Player) {
            return (Player) users.get(position);
        }else {
            return null;
        }
    }
    public void removeUser(String username) {
        users.remove(username);
    }
    public Player getPlayerWithUsername(String username) {
        if (users.get(username) instanceof Player) {
        return (Player) users.get(username);
        }else return null;
    }
    public ArrayList<Player> getPlayersList() {
        ArrayList<Player> players = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Player) {
                players.add((Player) user);
            }
        }
        return players;
    }
    public LinkedList<Player> getPlayersListl(){
        LinkedList<Player> players = new LinkedList<>();
        for (User user : users.values()) {
            if (user instanceof Player) {
                players.add((Player) user);
            }
        }
        return players;
    }




    /*
    public void rankearJugadores(){
        System.out.println(  "Jugadores ordenados: ");
        LinkedList<Player> jugadoresOrdenadosLista = obtenerListaOrdenadaPorValor();
        for(int i= 0; i < jugadoresOrdenadosLista.size(); i++) {
            System.out.println(jugadoresOrdenadosLista.get(i).toString() );
        }
    }
    private LinkedList<Player> obtenerListaOrdenadaPorValor(){
        LinkedList<Player> jugadoresOrdenados = new LinkedList<>(getPlayersListl()); // Crear una copia de la lista de jugadores
        int n = jugadoresOrdenados.size();  // Obtener el tamaño de la lista
        // Implementación del algoritmo de burbuja (Bubble Sort)
        for (int i = 0; i < n - 1; i++) { //Controla el número de pases sobre la lista, en cada pase, el valor más grande
            // flota hacia el final, por lo que en cada iteración necesitamos comparar menos elementos
            for (int j = 0; j < n - 1 - i; j++) { //El bucle va de 0 a n - 1 - i porque después de cada pase el último elemento ya está en la posición correcta
                // Compara la puntuacion de dos jugadores consecutivos
                if (jugadoresOrdenados.get(j).getCategoryMap().get < jugadoresOrdenados.get(j + 1).getPuntuacion()) {
                    // Intercambia los jugadores si están en el orden incorrecto
                    Jugador aux = jugadoresOrdenados.get(j);
                    jugadoresOrdenados.set(j, jugadoresOrdenados.get(j + 1));
                    jugadoresOrdenados.set(j + 1, aux);
                }
            }
        }
        return jugadoresOrdenados;
    }

     */



    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (User user : users.values()) {
            result.append(user.toString() + "\n");
        }
        return result.toString();
    }



}
