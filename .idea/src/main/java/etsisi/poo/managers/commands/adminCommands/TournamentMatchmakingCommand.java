package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.GenericMatchmake;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Team;
import etsisi.poo.modelo.Tournament;

public class TournamentMatchmakingCommand implements Command {
    private final TournamentManager tournamentManager;
    public TournamentMatchmakingCommand(TournamentManager tournamentManager){
        this.tournamentManager= tournamentManager;
    }
    public String execute(String parametro) {
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
