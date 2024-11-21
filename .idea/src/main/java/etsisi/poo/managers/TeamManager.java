package etsisi.poo.managers;



import etsisi.poo.modelo.Team;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class TeamManager {
    private LinkedList<Team> teamList;

    public TeamManager() {
        this.teamList = new LinkedList<Team>();
    }

    public boolean existsTeamWithName(String name) {
        boolean existe = false;
        if (teamList != null) {
            Iterator<Team> iterador = teamList.iterator();
            while (iterador.hasNext() && !existe) {
                if (Objects.equals(name, iterador.next().getName())) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    /**
     * Agrega un emparejamiento a la lista de emparejamientos
     *
     * @param team
     */
    public void addTeam(Team team) {
        this.teamList.add(team);
    }

    public int getSize() {
        return this.teamList.size();
    }

    public LinkedList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(LinkedList<Team> teamList) {
        this.teamList = teamList;
    }
    public Team getTeamRandom() {
        Team jugador = null;
        boolean emparejado = true;

        while (emparejado) {
            int random = (int) (Math.random() * this.teamList.size());
            jugador = this.teamList.get(random);
            if (!jugador.isEmparejado()) {
                emparejado = false;//HOLA
            }
        }
        return jugador;
    }
    public Team getPosicionTeam(int posicion) {
        return teamList.get(posicion);
    }
    public Team getTeam(String name) {
        Team team = null;
        if (teamList != null) {
            Iterator<Team> iterador = teamList.iterator();
            while (iterador.hasNext()) {
                Team team1 = iterador.next();
                if (Objects.equals(name, team1.getName())) {
                    team = team1;
                }
            }
        }
        return team;
    }

    public void removeTeamWithName(String name) {
        Iterator<Team> iterator = teamList.iterator();
        while (iterator.hasNext()) {
            Team team = iterator.next();
            if (Objects.equals(name, team.getName())) {
                iterator.remove();
                break;
            }
        }
    }
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < teamList.size(); i++) {
            result.append(teamList.get(i).toString() + "\n");

        }
        return result.toString();
    }
    public boolean existsTeamWithPlayer(String name) {
        boolean existe = false;
        if (teamList != null) {
            Iterator<Team> iterador = teamList.iterator();
            while (iterador.hasNext() && !existe) {
                Team team = iterador.next();
                if(team.existsPlayer(name))existe=true;

            }
        }
        return existe;
    }
    public Team getTeamWithPlayer(String name) {
        Team team = null;
        if (teamList != null) {
            Iterator<Team> iterador = teamList.iterator();
            while (iterador.hasNext() && team==null) {
                Team team1 = iterador.next();
                if(team1.existsPlayer(name))team=team1;
            }
        }
        return team;
    }
    private void sortTeamsByRanking(LinkedList<Team> teams) {
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = 0; j < teams.size() - i - 1; j++) {
                if (teams.get(j).getCategoryMap().getScoredPoints()> teams.get(j + 1).getCategoryMap().getScoredPoints()) { //REVISAR
                    // Intercambiar los equipos
                    Team temp = teams.get(j);
                    teams.set(j, teams.get(j + 1));
                    teams.set(j + 1, temp);
                }
            }
        }
    }
}