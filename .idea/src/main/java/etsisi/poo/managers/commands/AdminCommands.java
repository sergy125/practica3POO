package etsisi.poo.managers.commands;



import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.UserManager;

import etsisi.poo.managers.TournamentManager;
import etsisi.poo.modelo.*;

public class AdminCommands {
    public static String playerCreate2(Admin admin, String parametro, UserManager playerManager,UserManager userManager) {
        String resul="";
        if (!parametro.isEmpty()) {
            String[] multipleParameters = Utilities.splitArgs(parametro);
            if(multipleParameters.length==5) {
                String name = multipleParameters[0];
                String surname = multipleParameters[1];
                String dni = multipleParameters[2];
                String username = multipleParameters[3];
                String password = multipleParameters[4];

                if (!name.equals("")&&!name.contains(" ")&&!surname.equals("")&&!surname.contains(" ") && !playerManager.existsPlayerWithDni(dni) && !playerManager.existsUserWithUsername(username)) {
                    if(password.length()>=Utilities.PASSWORD_LENGTH){
                        if(dni.length()==Utilities.DNI_LENGTH) {
                            Player jugador = new Player(name, surname, playerManager, dni, username, password);
                            admin.getListaAltasJugadores().add(jugador);
                            userManager.add(jugador);
                            resul = jugador.toString();
                        }else resul = Utilities.DNI_FORMAT_ERROR;
                    }else resul=Utilities.PASSWORD_FORMAT_ERROR;

                } else {
                    resul = Utilities.CANT_CREATE_PLAYER;
                }
            }else{
                resul=Utilities.WRONG_ARGS;
            }
        } else {
            resul= Utilities.WRONG_ARGS;
        }
        return resul;
    }
    public static String deletePlayer2(Admin admin, TournamentManager tournamentManager, UserManager playerManager, String parametro,TeamManager teamManager) {
        String resul = "";
        if (!parametro.isEmpty()) {
            String[] multipleParameters= Utilities.splitArgs(parametro);
            if(multipleParameters.length==1) { //solo debe haber 1 argumento: el username
                String username = multipleParameters[0];
                if (!tournamentManager.existsActiveTournamentWithPlayerAlone(username) && !tournamentManager.existsActiveTournamentWithPlayerInTeam(username)) {
                    TeamManager newTeamManager = teamManager;
                    while (newTeamManager.existsTeamWithPlayer(username)) {
                            Team team = newTeamManager.getTeamWithPlayer(username);
                            if (team.justOnePlayer()) {
                                newTeamManager.removeTeamWithName(team.getName());
                                teamManager.removeTeamWithName(team.getName());
                                admin.getListaAltasEquipos().removeTeamWithName(team.getName());
                            }else{
                                team.removePlayer(playerManager.getPlayerWithUsername(username));
                            }
                            newTeamManager.removeTeamWithName(team.getName());

                    }
                    playerManager.removeUser(username);
                    admin.getListaAltasJugadores().removeUser(username);
                    resul = Utilities.ELIMINATED_PLAYER;
                } else resul = Utilities.ACTIVE_TOURNAMENT_ERROR;
            }else{resul=Utilities.WRONG_ARGS;}

            //MIRAR LOS ERRORES PQ ESTAN PIESTOS AHI PERO HAY QUE CAMBIARLOS
        } else {
            resul = Utilities.WRONG_ARGS;
        }
        return resul;
    }
    public static String teamCreate2(Admin admin, String parametro, UserManager allPlayers, TeamManager teamManager) {
        String resul = "";
        if (!parametro.isEmpty()) {
            // Eliminar los corchetes del nombre del jugador
            //String[] multipleParameters = parametro.split(";");
            String[] multipleParameters = Utilities.splitArgs(parametro);
            if (multipleParameters.length >= 2) {
                String name = multipleParameters[0];
                String username = multipleParameters[1];    //Siempre se va a añadir mínimo 1 jugador al equipo

                if (!teamManager.existsTeamWithName(name) && !name.equals("")) {
                    if (username != null && allPlayers.existsUserWithUsername(username)) {
                        Player player1 = allPlayers.getPlayerWithUsername(username);
                        Team team = new Team(name, teamManager, player1);
                        for (int i = 2; i < multipleParameters.length; i++) {
                            username = multipleParameters[i];
                            Player player = null;
                            if (username != null && allPlayers.existsUserWithUsername(username)) {
                                player = allPlayers.getPlayerWithUsername(username);
                                team.addPlayer(player);
                            } else resul = "No se ha encontrado a un jugador";
                        }
                        admin.getListaAltasEquipos().addTeam(team);
                        resul = "Team created: " + team.toString();
                    } else resul = "No se encuentra al jugador";
                }else resul = "No se puede crear un equipo con ese nombre";
                } else {
                resul = Utilities.CANT_CREATE_TEAM;
            }
        }else resul="Tienes que introducir al menos un jugador";
        return resul;

    }

    public static String deleteTeam2(Admin admin, TournamentManager tournamentManager,TeamManager teamManager,  String parametro) {
        String resul = "";
        if (!parametro.isEmpty()) {
            if (!tournamentManager.existsActiveTournamentWithTeam(parametro) && admin.getListaAltasEquipos().existsTeamWithName(parametro)) { //Si no existe un torneo en curso con este equipo
                admin.getListaAltasEquipos().removeTeamWithName(parametro); //Elimina el equipo de la lista asociada al admin
                teamManager.removeTeamWithName(parametro);  //Elimina el equipo de la lista de equipos general
                resul = "Team "+parametro+" deleted";
                System.out.println(admin.getListaAltasEquipos().toString());
            } else resul = Utilities.CANT_DELETE_TEAM;
        }else resul= Utilities.WRONG_ARGS;
        return resul;
    }

    public static String addPlayerToTeam2( UserManager playerManager, String parametro,TeamManager teamManager) {
        String resul = "";
        if (!parametro.isEmpty()) {
            String[] multipleParameters = Utilities.splitArgs(parametro);
            String teamName = multipleParameters[0];
            String username = multipleParameters[1];

            if (teamManager.existsTeamWithName(teamName)) {
                Team team = teamManager.getTeam(teamName);
                if(!team.existsPlayer(username) && !teamManager.existsTeamWithPlayer(username)) {
                    Player player = playerManager.getPlayerWithUsername(username);
                    team.addPlayer(player);
                    resul = "Player added to team. \n"+team.toString();

                } else resul = Utilities.CANT_ADD_PLAYER_TO_TEAM;  //No se ha podido añadir el jugador pq ya tiene equipo/no existe el jugador

            } else resul = Utilities.TEAM_DOESNT_EXIST;  //No existe el equipo
        } else resul = Utilities.WRONG_ARGS;

        return resul;
    }

    public static String removePlayerfromTeam2(UserManager playerManager, String parametro, TeamManager teamManager) {
        String resul = "";

        if (!parametro.isEmpty()) {
            String[] multipleParameters = Utilities.splitArgs(parametro);
            String teamName = multipleParameters[0];
            String username = multipleParameters[1];

            if (teamManager.existsTeamWithName(teamName)) { //El equipo existe
                Team team = teamManager.getTeam(teamName);

                    if (teamManager.existsTeamWithPlayer(username)) { //El jugador pertenece a un equipo

                        if (team.existsPlayer(username)) { //El jugador está en 'team'
                            if(team.getNumPlayers()>1) {
                                Player player = playerManager.getPlayerWithUsername(username);
                                team.removePlayer(player);
                                resul = "Player removed from team. \n" + team.toString();
                            } else resul = Utilities.ONE_PLAYER_TEAM;
                        } else resul = Utilities.PLAYER_NOT_IN_TEAM;  //El jugador no está en 'team'

                    } else resul = Utilities.CANT_REMOVE_PLAYER_FROM_TEAM;  //El jugador no pertenece a ningun equipo/no existe

            } else resul = Utilities.TEAM_DOESNT_EXIST;  //No existe 'team'
        } else resul = Utilities.WRONG_ARGS;
        return resul;
    }
    public static String createTournament2(String parametro, TournamentManager tournamentManager) {
        String result = "";
        if (!parametro.isEmpty()) {
            String partes[] = parametro.split(";");
            String name = partes[0];
            String startDate = partes[1];
            String endDate = partes[2];
            String ligue = partes[3];
            String sport = partes[4];

            if (!tournamentManager.existTournamentWithName(name)) {
                Tournament newTournament = new Tournament(name,Utilities.initialDate(startDate),Utilities.finalDate(startDate,endDate),ligue,sport,tournamentManager);
                tournamentManager.addTournament(newTournament);
                 result="Tournament created with name "+name+"\n"+ newTournament.toString();
            } else {
                result = Utilities.WRONG_ARGS;
            }
        } else {
            result = Utilities.WRONG_ARGS;
        }
        return result;
    }
    public static String tournamentDelete2(String parametro, TournamentManager tournamentManager) {
        String result = "";

        if (!parametro.isEmpty()) {
            String name = parametro;

            if (tournamentManager.existTournamentWithName(name)) {
                Tournament tournament = tournamentManager.getTournamentWithName(name);
                tournamentManager.removeTournament(tournament);
                result="Tournament with name "+ name+ " deleted";
            } else {
                result = Utilities.WRONG_ARGS;
            }
        } else {
            result = Utilities.WRONG_ARGS;
        }
        return result;
    }
    public static String tournamentMatchmake2(String parametro, TournamentManager tournamentManager) {
        String result = "";
        if (!parametro.isEmpty()) {
            String[] multipleParameters = parametro.split(";");
            String tournamentName = multipleParameters[0];
            String randomORmanual = multipleParameters[1];
            String category=multipleParameters[2];
            String name1 = "";
            String name2 = "";
            if (multipleParameters[3] != null) { name1 = multipleParameters[3];}
            if (multipleParameters[4] != null) { name2 = multipleParameters[4];}
            Tournament tournament = tournamentManager.getTournamentWithName(tournamentName);
            if(tournament.isRunning()) {
                if (randomORmanual.equals("a") ) {//SOLO SE EMPAREJA SI ESTA EN CURSO
                    //ALEATORIO
                    //LAS CONDICIONES SON LAS MISMAS QUE EN LA PRACTICA ANTERIOR
                    tournament.matchmakeByCategory(category);
                } else if (randomORmanual.equals("m") ) {//SOLO SE EMPAREJA SI ESTA EN CURSO
                    //MANUAL
                    if(tournament.getTeams().existsTeamWithName(name1) && tournament.getTeams().existsTeamWithName(name2)) {//HABRA QUE VER SI ES TEAM O PLAYER DOY PRIORIDAD A TEAM
                        Team team1 = tournament.getTeams().getTeam(name1);
                        Team team2 = tournament.getTeams().getTeam(name2);
                        GenericMatchmake<Team> teamMatchmake = new GenericMatchmake<Team>(tournament,team1, team2, tournament.getMatchmaked());
                    } else if(tournament.getPlayers().existsUserWithUsername(name1) && tournament.getPlayers().existsUserWithUsername(name2)) {
                        //HABRA QUE VER SI ES TEAM O PLAYER DOY PRIORIDAD A TEAM
                        Player player1 = tournament.getPlayers().getPlayerWithUsername(name1);
                        Player player2 = tournament.getPlayers().getPlayerWithUsername(name2);
                        GenericMatchmake<Player>  matchmake = new GenericMatchmake<Player>(tournament,player1, player2, tournament.getMatchmaked());

                    } else if(tournament.getPlayers().existsUserWithUsername(name1) && tournament.getTeams().existsTeamWithName(name2)|| tournament.getTeams().existsTeamWithName(name1) && tournament.getPlayers().existsUserWithUsername(name2)) {
                        Player player1 = null;
                        Player player2 = null;
                        Team team1 = null;
                        Team team2 = null;
                        if(tournament.getPlayers().existsUserWithUsername(name1)) {
                            player1 = tournament.getPlayers().getPlayerWithUsername(name1);
                            team2 = tournament.getTeams().getTeam(name2);
                            GenericMatchmake matchmake = new GenericMatchmake(tournament,player1, team2, tournament.getMatchmaked());
                        }
                        if(tournament.getTeams().existsTeamWithName(name1)) {
                            team1 = tournament.getTeams().getTeam(name1);
                            player2 = tournament.getPlayers().getPlayerWithUsername(name2);
                            GenericMatchmake matchmake = new GenericMatchmake(tournament,player2, team1, tournament.getMatchmaked());
                        }
                        if(tournament.getPlayers().existsUserWithUsername(name1)) {

                        }

                    }else result = Utilities.WRONG_ARGS;//NO EXISTE NI TEAM NI PLAYER CON ESOS NOMBRES

                } else {
                    result = Utilities.WRONG_ARGS;
                }
            } else result= Utilities.WRONG_ARGS; //TIENE QUE ESTAR EN CURSO
        } else {
            result = Utilities.WRONG_ARGS;
        }
        return result;
    }



}
