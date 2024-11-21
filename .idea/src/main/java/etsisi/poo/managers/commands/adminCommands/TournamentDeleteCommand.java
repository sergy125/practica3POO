package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Tournament;

public class TournamentDeleteCommand implements Command {
    private final TournamentManager tournamentManager;
    public TournamentDeleteCommand(TournamentManager tournamentManager){
        this.tournamentManager= tournamentManager;
    }

    public String execute(String parametro) {
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
}
