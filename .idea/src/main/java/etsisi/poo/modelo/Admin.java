package etsisi.poo.modelo;


import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.UserManager;

public class Admin extends User {
    private UserManager listaAltasJugadores;
    private TeamManager listaAltasEquipos;
    private TournamentManager listasTorneos;



    public void setListaAltasEquipos(TeamManager listaAltasEquipos) {
        this.listaAltasEquipos = listaAltasEquipos;
    }
//segun el chat implements

    public Admin(String username, String password,UserManager userManager) {
        super(username, password, userManager,true);
        setAdmin(true);
        listaAltasJugadores = new UserManager();
        listaAltasEquipos = new TeamManager();
        listasTorneos = new TournamentManager();

    }
    public void registerPlayer(Player player,Admin admin){
        listaAltasJugadores.add(player);
    }
    public void registerTeam(Team team, Admin admin){
        listaAltasEquipos.addTeam(team);
    }
    public void registerTournament(Tournament tournament){
        listasTorneos.addTournament(tournament);
    }

    public UserManager getListaAltasJugadores() {
        return listaAltasJugadores;
    }

    public TeamManager getListaAltasEquipos() {
        return listaAltasEquipos;
    }

    public void setListaAltasJugadores(UserManager listaAltasJugadores) {
        this.listaAltasJugadores = listaAltasJugadores;
    }

    public TournamentManager getListasTorneos() {
        return listasTorneos;
    }

    public void setListasTorneos(TournamentManager listasTorneos) {
        this.listasTorneos = listasTorneos;
    }


}

