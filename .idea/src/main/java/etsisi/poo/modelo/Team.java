package etsisi.poo.modelo;

import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.UserManager;

import java.util.HashMap;

public class Team {
    private String name;
    private UserManager PlayersList;
    private int numPlayers;
    private CategoryMap categoryMap;
    private boolean isEmparejado;

    // This method is already defined in the class, so it should be removed.
    // No code needs to be generated here.

    public void setEmparejado(boolean emparejado) {//HOLA
        isEmparejado = emparejado;
    }

    public Team(String name, TeamManager teamManager, Player player1) {
        this.name = name;
        this.PlayersList= new UserManager();
        this.PlayersList.add(player1);
        this.numPlayers = 1;
        teamManager.addTeam(this);
        this.categoryMap= new CategoryMap();
        isEmparejado= false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserManager getPlayersList() {
        return PlayersList;
    }

    public void setPlayersList(UserManager playersList) {
        PlayersList = playersList;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void geometricAverage(){
        CategoryMap geometric= new CategoryMap();
        int numPlayers= PlayersList.getSize();
        for( int i =0; i <numPlayers;i++){
            Player player=null;
            if(PlayersList.getPlayerPosition(i) instanceof Player) {
                 player = PlayersList.getPlayerPosition(i);
                HashMap<String, Double> playerstatistics = player.getCategoryMap().getStatistics();
            }
        }
        // Multiplicar las estadísticas de cada jugador para cada categoría
        for (String category : geometric.getStatistics().keySet()) {
            double currentStat = geometric.getStatistics().get(category);
            double playerStat = categoryMap.getStatistics().get(category);
            geometric.updateStatistics(category, currentStat * playerStat);
        }
        // Aplicar la raíz n-ésima para cada categoría
        for(String category : geometric.getStatistics().keySet()) {
            double product = geometric.getStatistics().get(category);
            // Calcular la raíz n-ésima
            double geometricMean = Math.pow(product, 1.0 / numPlayers);
            geometric.updateStatistics(category, geometricMean);
        }//REVISAR
    }


    public CategoryMap getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(CategoryMap categoryMap) {
        this.categoryMap = categoryMap;
    }
    public boolean existsPlayer(String user){
        return PlayersList.existsUserWithUsername(user);
    }
    public boolean isEmparejado() {
        return isEmparejado;
    }

    public void desemparejar() {
        this.isEmparejado = false;
    }
    public void emparejar(){
        this.isEmparejado = true;
    }
    public void addPlayer(Player player) {
        PlayersList.add(player);
        numPlayers++;
        geometricAverage();
    }
    public void removePlayer(Player player){PlayersList.removeUser(player.getUsername());
    geometricAverage();}
    public String toString() {
        return "Name: " + name + "\nPlayers: " + PlayersList.toString();
    }
    public boolean justOnePlayer() {
        return PlayersList.getSize() == 1;
    }

}
