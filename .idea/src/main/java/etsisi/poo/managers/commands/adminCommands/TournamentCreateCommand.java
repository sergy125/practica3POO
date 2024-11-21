package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Tournament;

public class TournamentCreateCommand implements Command {
   private final TournamentManager tournamentManager;

    public TournamentCreateCommand( TournamentManager tournamentManager){
        this.tournamentManager= tournamentManager;
    }
    public String execute(String parametro) {
        String result = "";
        if (!parametro.isEmpty()) {
            String partes[] = parametro.split(";");
            String name = partes[0];
            String startDate = partes[1];
            String endDate = partes[2];
            String ligue = partes[3];
            String sport = partes[4];

            if (!tournamentManager.existTournamentWithName(name)) {
                Tournament newTournament = new Tournament(name, Utilities.initialDate(startDate),Utilities.finalDate(startDate,endDate),ligue,sport,tournamentManager);
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


}
