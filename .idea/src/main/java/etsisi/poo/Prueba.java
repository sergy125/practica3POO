package etsisi.poo;


import etsisi.poo.managers.*;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.PublicCommands;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.managers.commands.adminCommands.*;
import etsisi.poo.managers.commands.playerCommands.*;
import etsisi.poo.managers.commands.publicCommands.*;
import etsisi.poo.modelo.Admin;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.User;

import java.util.Scanner;

public class Prueba {

    LoggedSingleton loggedSingleton = LoggedSingleton.getInstance();

    public void run() {
        UserManager userManager = new UserManager();
        UserManager globalPlayers = new UserManager();
        TeamManager teamManager = new TeamManager();
        GenericMatchmakeManager genericMatchmakeManager= new GenericMatchmakeManager();
        TournamentManager tournamentManager = new TournamentManager();
        AdminManager adminManager = new AdminManager();
        User logged = null;
        PublicCommands publicCommands = new PublicCommands();
        Scanner scanner = new Scanner(System.in);
        boolean identified=false;
        Admin admin=null;
        String command;
        boolean salir = false;
        String resul="";

        CommandManager commandManager = new CommandManager();
        commandManager.registerCommand(Utilities.LOGIN, new LoginCommand(userManager, loggedSingleton));
        commandManager.registerCommand(Utilities.LOGOUT, new LogoutCommand(loggedSingleton));
        commandManager.registerCommand(Utilities.REGISTER, new RegisterCommand(userManager));
        commandManager.registerCommand(Utilities.LIST_TOURNAMENT, new ListTournamentCommand(tournamentManager,loggedSingleton));

        commandManager.registerCommand(Utilities.CREATE_PLAYER, new PlayerCreateCommand(admin,globalPlayers, userManager));
        commandManager.registerCommand(Utilities.DELETE_PLAYER, new PlayerDeleteCommand(admin,tournamentManager,globalPlayers,teamManager));
        commandManager.registerCommand(Utilities.CREATE_TEAM, new TeamCreateCommand(admin, globalPlayers,teamManager));
        commandManager.registerCommand(Utilities.DELETE_TEAM, new TeamDeleteCommand(admin, tournamentManager, teamManager));
        commandManager.registerCommand(Utilities.ADD_TEAM, new TeamAddCommand(globalPlayers,teamManager));
        commandManager.registerCommand(Utilities.REMOVE_TEAM, new TeamRemoveCommand(globalPlayers, teamManager));
        commandManager.registerCommand(Utilities.CREATE_TOURNAMENT, new TournamentCreateCommand(tournamentManager));
        commandManager.registerCommand(Utilities.DELETE_TOURNAMENT, new TournamentDeleteCommand(tournamentManager));
        commandManager.registerCommand(Utilities.TOURNAMENT_MATCHMAKING, new TournamentMatchmakingCommand(tournamentManager));
        commandManager.registerCommand(Utilities.ADD_TOURNAMENT, new AddPlayerToTournamentCommand(loggedSingleton, tournamentManager));
        commandManager.registerCommand(Utilities.REMOVE_TOURNAMENT, new RemovePlayerFromTournamentCommand(loggedSingleton,tournamentManager));
        commandManager.registerCommand(Utilities.SHOW_STATISTICS, new ShowStatisticsFromPlayerCommand(loggedSingleton,tournamentManager));
        // Separar el comando y los parámetros (suponiendo que se usa un espacio para separarlos)
        //////PRUEBA TOURNAMENT LIST/////
        Admin=new Admin("Serfy";"cucu";userManager);

        while (!salir) {
            System.out.print("> ");
            command = scanner.nextLine();  // Leer el comando del usuario
            boolean isAdmin = loggedSingleton.getUsuarioLogueado() instanceof Admin;
            boolean isPlayer = loggedSingleton.getUsuarioLogueado() instanceof Player;
            // Separar el comando y los parámetros (suponiendo que se usa un espacio para separarlos)
            String[] partes = command.split(" ");
            String action = partes[0];  // El primer elemento es el comando
            String parameter = partes.length > 1 ? partes[1] : "";
            // Leer el comando del usuario
            if((action.equals(Utilities.CREATE_PLAYER)&& (isAdmin) )||action.equals(Utilities.CREATE_TEAM)&& (isAdmin)||action.equals(Utilities.DELETE_PLAYER)&& (isAdmin)
            || action.equals(Utilities.DELETE_TEAM)&& (isAdmin)|| action.equals(Utilities.ADD_TEAM) && (isAdmin)|| action.equals(Utilities.REMOVE_TEAM) && (isAdmin)||
                    action.equals(Utilities.CREATE_TOURNAMENT)&& (isAdmin) || action.equals(Utilities.DELETE_TOURNAMENT) && (isAdmin)|| action.equals(Utilities.TOURNAMENT_MATCHMAKING)&& (isAdmin)){

                System.out.println(commandManager.executeCommand(action,parameter));
            } else if (action.equals(Utilities.ADD_TOURNAMENT)&& (isPlayer)|| action.equals(Utilities.REMOVE_TOURNAMENT)&& (isPlayer)|| action.equals(Utilities.SHOW_STATISTICS)&& (isPlayer)) {

                System.out.println(commandManager.executeCommand(action,parameter));
            } else if (action.equals(Utilities.LOGIN) || action.equals(Utilities.LOGOUT) || action.equals(Utilities.REGISTER)) {
                System.out.println(commandManager.executeCommand(action,parameter));
            } else if( action.equals(Utilities.EXIT)){
                salir= true;
            }else{
                System.out.println("Comando no reconocido.");
            }




        }
}
}
