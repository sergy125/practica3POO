package etsisi.poo.managers;



import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Team;
import etsisi.poo.modelo.Tournament;
import etsisi.poo.modelo.User;

import java.util.*;

public class TournamentManager {
    private ArrayList<Tournament> tournaments;

    public TournamentManager() {
        this.tournaments = new ArrayList<Tournament>();
    }

    /**
     * Agrega un emparejamiento a la lista de emparejamientos
     *
     * @param tournament
     */
    public void addTournament(Tournament tournament) {
        this.tournaments.add(tournament);
    }

    public int getSize() {
        return this.tournaments.size();
    }

    public void removeTournament(Tournament tournament) {
        tournaments.remove(tournament);
    }

    public Tournament getTournamentWithName(String name) {
        Tournament tournament = null;
        for (int i = 0; i < tournaments.size(); i++) {
            if (Objects.equals(tournaments.get(i).getName(), name)) {
                tournament = tournaments.get(i);
                for (Tournament t : tournaments) {
                    if (Objects.equals(t.getName(), name)) {
                        tournament = t;
                    }
                }

            }
        }
        return tournament;

    }


    public boolean existTournamentWithName(String name) {
        for (int i = 0; i < tournaments.size(); i++) {
            if (Objects.equals(tournaments.get(i).getName(), name)) {
                return true;
            }
        }
        return false;


    }


    /**
     * Método que comprueba si un jugador nameT participa como jugador independiente en al menos un torneo
     *
     * @param user
     * @return
     */
    public boolean existsTournamentWithPlayerAlone(String user) {
        boolean exists = false;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team = tournament.getTeams().getTeamList().get(teamPos);
                        if (!team.existsPlayer(user) && tournament.getPlayers().existsUserWithUsername(user))
                            exists = true;
                    }
                }
            }
        }
        return exists;

    }

    /**
     * Método que busca si existe algún torneo donde el jugador user está participando como parte de un equipo
     *
     * @param user
     * @return
     */
    public boolean existsTournamentWithPlayerInTeam(String user) {
        boolean exists = false;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team = tournament.getTeams().getTeamList().get(teamPos);
                        if (team.existsPlayer(user)) exists = true;
                    }
                }
            }
        }
        return exists;

    }

    /**
     * Método que verifica si un equipo específico (name) está participando en algún torneo
     *
     * @param name
     * @return
     */
    public boolean existsTournamentWithTeam(String name) {
        boolean exists = false;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team = tournament.getTeams().getTeamList().get(teamPos);
                        if (Objects.equals(team.getName(), name)) exists = true;
                    }
                }
            }

        }
        return exists;
    }

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }
    /*public String showListShuffle() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < tournaments.size(); i++) {
            result.append( tournaments.get(i).getName() + "\n");
            for(int j = 0; j < tournaments.get(i).getTeams().getTeamList().size(); j++) {
                result.append( tournaments.get(i).getTeams().getTeamList().get(j).getName() + "\n");
            }
            for (int j = 0; j < tournaments.get(i).getPlayers().getTamano(); j++) {
                result.append( tournaments.get(i).getPlayers().getPosicionJugador(j).getUsername() + "\n");
            }
        }
        return result.toString();
    }*/
/*
    public String tournamentList( User user) {
        StringBuilder result = new StringBuilder("");
        if (user == null) {
            Collections.shuffle(tournaments);
            for (int i = 0; i < tournaments.size(); i++) {
                result.append(tournaments.get(i).getName()).append("\n");
                for (int j = 0; j < tournaments.get(i).getTeams().getTeamList().size(); j++) {
                    Collections.shuffle(tournaments.get(i).getTeams().getTeamList());
                    result.append(tournaments.get(i).getTeams().getTeamList().get(j).getName() + "\n");
                }
                for (int j = 0; j < tournaments.get(i).getPlayers().getSize(); j++) {
                    Collections.shuffle(tournaments.get(i).getPlayers().getPlayersList());
                    result.append(tournaments.get(i).getPlayers().getPlayerPosition(j).getUsername() + "\n");
                }
            }
        } else {
            if (user.isAdmin()) {
                for (int i = 0; i < tournaments.size(); i++) {
                    if (tournaments.get(i).isRunning()) {
                        result.append(tournaments.get(i).getName() + "\n");
                        for (int j = 0; j < tournaments.get(i).getTeams().getTeamList().size(); j++) {
                            result.append(tournaments.get(i).getTeams().getTeamList().get(j).getName() + "\n");
                        }
                        for (int j = 0; j < tournaments.get(i).getPlayers().getSize(); j++) {
                            result.append(tournaments.get(i).getPlayers().getPlayerPosition(j).getUsername() + "\n");
                        }
                    }
                }
                for (int i = 0; i < tournaments.size(); i++) {
                    if (!tournaments.get(i).isRunning()) {
                        tournaments.remove(i);
                    }
                }
            } else {
                for (int i = 0; i < tournaments.size(); i++) {
                    result.append(tournaments.get(i).getName() + "\n");
                    for (int j = 0; j < tournaments.get(i).getTeams().getTeamList().size(); j++) {
                        result.append(tournaments.get(i).getTeams().getTeamList().get(j).getName() + "\n");
                    }
                    for (int j = 0; j < tournaments.get(i).getPlayers().getSize(); j++) {
                        result.append(tournaments.get(i).getPlayers().getPlayerPosition(j).getUsername() + "\n");
                    }
                }
            }
        }
        return result.toString();
    }

 */


    public String tournamentList(User user) {
        if (user == null) {
            // Usuario no autenticado: aleatorizar
            return generateTournamentList(true, false);
        } else if (user.isAdmin()) {
            // Usuario administrador: limpiar torneos finalizados y listar
            cleanFinishedTournaments();
            return generateTournamentList(false, true);
        } else {
            // Usuario jugador: listar torneos por ranking
            return generateTournamentList(false, false);
        }
    }
    private void cleanFinishedTournaments() {
        Iterator<Tournament> iterator = tournaments.iterator();
        while (iterator.hasNext()) {
            Tournament tournament = iterator.next();
            if (!tournament.isRunning()) {
                iterator.remove();
            }
        }
    }
    private String generateTournamentList(boolean shuffle, boolean adminView) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < tournaments.size(); i++) {
            result.append("Tournament: ").append(tournaments.get(i).getName()).append("\n");

            // Lista de equipos
            LinkedList<Team> teams=tournaments.get(i).getTeams().getTeamList();
            if (shuffle) {
                Collections.shuffle(teams);
            } else {
                //teams.sort(Comparator.comparingInt(Team::getRanking)); // Ordenar por ranking
            }
            for (int j=0; j<teams.size();j++){
                result.append("  Team: ").append(teams.get(j).getName()).append("\n");
            }

            // Lista de jugadores
            LinkedList<Player> players=tournaments.get(i).getPlayers().getPlayersListl();
            if (shuffle) {
                Collections.shuffle(players);
            } else {
                //players.sort(Comparator.comparingInt(Player::getRanking)); // Ordenar por ranking
            }
            for (Player player : players) {
                result.append("  Player: ").append(player.getUsername()).append("\n");
            }
        }
        return result.toString();
    }
    private void sortPlayersByRanking(LinkedList<Player> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - i - 1; j++) {
                if (players.get(j).getCategoryMap().getScoredPoints() > players.get(j + 1).getCategoryMap().getScoredPoints()) { //REVISAR
                    // Intercambiar los jugadores
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
    }

    public boolean existsActiveTournamentWithPlayerAlone(String username) {
        boolean exists = false;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null && tournament.isRunning()) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team = tournament.getTeams().getTeamList().get(teamPos);
                        if (!team.existsPlayer(username) && tournament.getPlayers().existsUserWithUsername(username))
                            exists = true;
                    }
                }
            }
        }
        return exists;
    }
    public boolean existsActiveTournamentWithPlayerInTeam(String user) {
        boolean exists = false;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null&& tournament.isRunning()) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team = tournament.getTeams().getTeamList().get(teamPos);
                        if (team.existsPlayer(user)) exists = true;
                    }
                }
            }
        }
        return exists;

    }
    public boolean existsActiveTournamentWithTeam(String name) {
        boolean exists = false;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null&& tournament.isRunning()) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team = tournament.getTeams().getTeamList().get(teamPos);
                        if (Objects.equals(team.getName(), name)) exists = true;
                    }
                }
            }

        }
        return exists;
    }
    public Team getTeamWithPlayer(String username) {
        Team team = null;
        if (tournaments != null) {
            for (int i = 0; i < tournaments.size(); i++) {
                Tournament tournament = tournaments.get(i);
                if (tournament.getTeams() != null && tournament.isRunning()) {
                    for (int teamPos = 0; teamPos < tournament.getTeams().getTeamList().size(); teamPos++) {
                        Team team1 = tournament.getTeams().getTeamList().get(teamPos);
                        if (team1.existsPlayer(username)) {
                            team = team1;
                        }
                    }
                }
            }
        }
        return team;
    }


}