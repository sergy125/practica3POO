/*package etsisi.poo.vista;

import etsisi.poo.LoggedSingleton;
import etsisi.poo.managers.*;
import etsisi.poo.managers.commands.AdminCommands;
import etsisi.poo.managers.commands.PlayerCommands;
import etsisi.poo.managers.commands.PublicCommands;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.*;

import java.util.Scanner;

/**
 * Clase main en la que según la entrada por scanner se ejecutan las distintas operaciones mediante comandos
 *
 * @author HIBA ARSI LHAMZAOUI
 * @author ANDREA VARAS VAZQUEZ
 * @author SERGIO PEREZ GOMEZ
 * @author PAULA CAMILA MORENO RUBIO
 */
/*public class Main {
    //Public commands
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String LOGOUT = "logout";
    private static final String LIST_TOURNAMENT = "tournament-list";

    // Admin Commands
    private static final String CREATE_PLAYER = "player-create";
    private static final String CREATE_TEAM = "team-create";

    private static final String DELETE_PLAYER = "player-delete";

    private static final String DELETE_TEAM = "team-delete";
    private static final String ADD_TEAM = "team-add";
    private static final String REMOVE_TEAM = "team-remove";
    private static final String CREATE_TOURNAMENT = "tournament-create";
    private static final String DELETE_TOURNAMENT = "tournament-delete";
    private static final String TOURNAMENT_MATCHMAKING = "tournament-matchmaking";

    //Player Commands
    private static final String ADD_TOURNAMENT = "tournament-add";
    private static final String REMOVE_TOURNAMENT = "tournament-remove";
    private static final String SHOW_STATISTICS = "statistics-show";

    private static final String EXIT = "exit";
   public static void main(String[] args) {
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

        /////////////////////PRUEBAS GENERIC MATCHMAKE////////////////////////////////
        Player myPlayer=new Player("Lucas","Gomez",globalPlayers,"789998766","luquitas","1234");
        Team myTeam=new Team("Nuestro",teamManager,myPlayer);
        Player myPlayer2 = new Player("Rober", "fernandez", globalPlayers, "789598766", "rober", "1234");
        Tournament myTournament = new Tournament("Torneo de futbol", "2023-06-01", "2023-06-02", "Liga", "Futbol", tournamentManager);
        GenericMatchmake genericMatchmake2 = new GenericMatchmake(myTournament, myTeam,myPlayer,genericMatchmakeManager);
        System.out.println(genericMatchmake2.toString());
        GenericMatchmake genericMatchmake = new GenericMatchmake(myTournament, myPlayer, myPlayer2, genericMatchmakeManager);
        System.out.println(genericMatchmake.toString());
        //////////////////////////////////////////////////////
        /////////////////PRUEBAS LOGGED//////////////////////
        LoggedSingleton loggedSingleton = LoggedSingleton.getInstance();



        //////////////////////////////////////////////////////
        while (!salir) {

            System.out.print("> ");
            command = scanner.nextLine();  // Leer el comando del usuario

            // Separar el comando y los parámetros (suponiendo que se usa un espacio para separarlos)
            String[] partes = command.split(" ");
            String action = partes[0];  // El primer elemento es el comando
            String parameter = partes.length > 1 ? partes[1] : "";  // Si hay un parámetro, será el segundo
            //System.out.println(action);
            switch (action) {
                case REGISTER:
                    publicCommands.registerUser(parameter,userManager );
                    break;
                case LOGIN: //CUALQUIERA

                    //logged=publicCommands.loginUser(parameter,userManager );
                    loggedSingleton.log(userManager, parameter);
                    if(logged!=null){
                        identified = true;
                        System.out.println(logged.toString()+"\nlogueado");

                    }
                    if(logged!=null && logged.isAdmin()) {
                        admin = new Admin(logged.getUsername(),logged.getPassword(),userManager);
                    }
                    break;
                case LOGOUT: //CUALQUIERA
                    //userManager.logout(logged);
                    loggedSingleton.out();
                    logged=null;
                    identified= false;
                    break;
                case LIST_TOURNAMENT: //CUALQUIERA
                    System.out.println(tournamentManager.tournamentList(logged));
                    break;
                case CREATE_PLAYER://ADMIN
                    if(identified && logged!=null) {//CREATE
                        if(logged.isAdmin()) {
                            System.out.println(AdminCommands.playerCreate2(admin, parameter, globalPlayers,userManager));
                        }else System.out.println(Utilities.NO_PERMISSION);
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;
                case CREATE_TEAM://ADMIN

                    if(identified && logged!=null) {
                        if (logged.isAdmin()) {
                            System.out.println(AdminCommands.teamCreate2(admin, parameter, globalPlayers,teamManager));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;
                case DELETE_PLAYER://ADMIN
                    if(identified && logged!=null) {
                        if (logged.isAdmin()) {
                            System.out.println(AdminCommands.deletePlayer2(admin, tournamentManager, globalPlayers, parameter, teamManager));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;

                case DELETE_TEAM://ADMIN
                    if (identified && logged != null) {
                        if (logged.isAdmin()) {
                            //Admin admin = (Admin) actualUser;
                            System.out.println(AdminCommands.deleteTeam2(admin, tournamentManager, teamManager, parameter));
                            System.out.println(adminManager.altasEquiposAdmin(admin));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;

                case ADD_TEAM://ADMIN
                    if (identified && logged != null) {
                        if (logged.isAdmin()) {
                            //Admin admin = (Admin) actualUser;
                            System.out.println(AdminCommands.addPlayerToTeam2(globalPlayers, parameter, teamManager));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);

                    break;
                case REMOVE_TEAM://ADMIN
                    if (identified && logged != null) {
                        if (logged.isAdmin()) {
                            //Admin admin = (Admin) actualUser;
                            System.out.println(AdminCommands.removePlayerfromTeam2(globalPlayers, parameter, teamManager));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;
                case CREATE_TOURNAMENT://ADMIN
                    if (identified && logged != null) {
                        if (logged.isAdmin()) {
                            //Admin admin = (Admin) actualUser;
                            System.out.println(AdminCommands.createTournament2( parameter, tournamentManager));

                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;
                case DELETE_TOURNAMENT://ADMIN
                    if (identified && logged != null) {
                        if (logged.isAdmin()) {
                            //Admin admin = (Admin) actualUser;
                            System.out.println(AdminCommands.tournamentDelete2( parameter, tournamentManager));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;
                case TOURNAMENT_MATCHMAKING://ADMIN
                    if (identified && logged != null) {
                        if (logged.isAdmin()) {
                            //Admin admin = (Admin) actualUser;
                            System.out.println(AdminCommands.tournamentMatchmake2(parameter, tournamentManager));
                        }
                    }else System.out.println(Utilities.NO_PERMISSION);
                    break;
                case ADD_TOURNAMENT: //PLAYER
                    if(identified && logged!=null) {
                        if(logged.isPlayer()){
                            Player player = (Player) logged;
                            System.out.println(PlayerCommands.tournamentAdd2(player,parameter, tournamentManager));
                        }
                    }
                    break;
                case REMOVE_TOURNAMENT://PLAYER
                    if (identified && logged != null) {
                        if (logged.isPlayer()) {
                            Player player = (Player) logged;
                            System.out.println(PlayerCommands.tournamentRemove(player, parameter, tournamentManager));
                        }
                    }
                    break;
                case SHOW_STATISTICS://PLAYER
                    if (identified && logged != null) {
                        if (logged.isPlayer()) {
                            Player player = (Player) logged;
                            System.out.println(PlayerCommands.showStatistics(player, parameter));
                        }
                    }
                    break;
                /*case "show","SHOW":
                    if(identified && actualUser.isAdmin()) {
                        playerManager.mostrarJugadores();
                    }
                    break;
                case "rank","RANK":
                    if(identified && actualUser.isAdmin()) {
                        playerManager.rankearJugadores();
                    }
                    break;
                case "score","SCORE":
                    if(identified && actualUser.isAdmin()) {
                        AdminCommands.score(partes, parameter, playerManager);
                    }
                    break;
                case "show_matchmake","SHOW_MATCHMAKE":
                    if(identified && actualUser.isAdmin()) {
                        matchmakeManager.mostrarEmparejamientos();
                    }
                    break;
                case "clear_matchmake","CLEAR_MATCHMAKE":
                    if(identified && actualUser.isAdmin()) {
                        matchmakeManager.eliminarTodosLosEmparejamientos();
                        System.out.println("Emparejamientos eliminados.");
                    }
                    break;
                case "matchmake","MATCHMAKE":
                    if(identified && actualUser.isAdmin()) {
                        AdminCommands.matchmake(partes, parameter, playerManager, matchmakeManager);
                    }
                    break;
                case "random_matchmake","RANDOM_MATCHMAKE":
                    if(identified && actualUser.isAdmin()) {
                        matchmakeManager.emparejarTodosRandom(playerManager);
                    }
                    break;

                 */
               /* case EXIT:
                    salir = true;  // Terminar el bucle
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Comando no reconocido.");
            }
        }
        scanner.close();  // Cerrar el escáner al finalizar el programa
    }

}*/