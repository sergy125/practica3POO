package etsisi.poo.modelo;


import etsisi.poo.managers.UserManager;

/**
 * Jugador es una clase que encapsula un jugador con sus correspondientes atributos, getters y setters. Además de
 * métodos para indicar si el jugador esta emparejado o no, etc.
 *
 * @author HIBA ARSI LHAMZAOUI
 * @author ANDREA VARAS VAZQUEZ
 * @author SERGIO PEREZ GOMEZ
 * @author PAULA CAMILA MORENO RUBIO
 */
public class Player extends User {
    private String name;
    private String surname;
    private String dni;
    //private boolean emparejado;
    private CategoryMap categoryMap;
    //private HashMap<String,Double> statistics;

    public CategoryMap getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(CategoryMap categoryMap) {
        this.categoryMap = categoryMap;
    }
    public Player(String name, String surname, UserManager listajugadores, String dni, String username, String password) {
        super(username, password,listajugadores,false);
        this.name = name;
        this.surname=surname;
        this.dni= dni;
        this.categoryMap=new CategoryMap();
        //this.emparejado= false;
    }


    public String getName() {
        return name;
    }
    public String getDni() {
        return dni;
    }//HOLA
      public String getSurname() {
          return surname;
      }
    @Override
    public String toString() {
        return name+ " " + surname+ " " + dni+ " " + getUsername() + " " + getPassword() ;
    }
}

