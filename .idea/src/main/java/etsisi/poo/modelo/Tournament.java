package etsisi.poo.modelo;


import etsisi.poo.managers.*;

public class Tournament {
    private String name;
    private String startDate;
    private String endDate;
    private String ligue;
    private String sport;
    private UserManager players;
    private TeamManager teams;
    private boolean closedInscriptions;
    private GenericMatchmakeManager matchmaked;
    //private TeamMatchmakeManager matchmakedTeams;
    private int prioridad;
    private boolean isRunning;
    boolean[] isTeamMatchmaked ;
    boolean[] isPlayerMatchmaked ;

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", ligue='" + ligue + '\'' +
                ", sport='" + sport + '\'' +
                '}';
    }

    public Tournament(String name, String startDate, String endDate, String ligue, String sport, TournamentManager tournamentManager) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ligue = ligue;
        this.sport = sport;
        this.closedInscriptions= false;
        this.isRunning = false;
        tournamentManager.addTournament(this);
        this.players= new UserManager();
        this.teams = new TeamManager();
        isTeamMatchmaked = new boolean[teams.getSize()];
        isPlayerMatchmaked = new boolean[players.getSize()];

        for (int i = 0; i < isTeamMatchmaked.length; i++) {
            isTeamMatchmaked[i] = false;
        }
        for (int i = 0; i < isPlayerMatchmaked.length; i++) {
            isPlayerMatchmaked[i] = false;
        }
    }
    // hacer funciones de apumtar jugador y equipos en torneo, emparejar participantes cuando se cierran
    // inscripciones
    public void signUpPlayer(Player player){
        if(!closedInscriptions){
            players.add(player);
        }else{
            System.out.println("Se han cerrado las inscripciones ");
        }
    }

    public double getPrioridad (int prioridad) {
        CategoryMap category = new CategoryMap();
        switch (prioridad) {
            case 1:
                return category.getScoredPoints();
            case 2:
                return category.getMatchesWon();

            case 3:
                return category.getAssistsPoints();

            case 4:
                return category.getTournamentsWon();

            case 5:
                return category.getMoneyGenerated();

            default:
                System.out.println("No hay ninguna de estas opciones");
        }
        return prioridad;
    }
    public void signUpTeam(Team team){
        if(!closedInscriptions){
            teams.addTeam(team);
        }else System.out.println("Se han cerrado las inscripciones ");
    }
    /*public void closeInscriptionsRandom(User user) {
        if(!isClosedInscriptions()) {
            if (user.isAdmin()) {

                MatchmakeManager playermatchmakeManager = new MatchmakeManager();
                playermatchmakeManager.emparejarTodosRandom(players);
                TeamMatchmakeManager teammatchmakeManager = new TeamMatchmakeManager();
                teammatchmakeManager.emparejarTeamsRandom(teams);
                closedInscriptions = true;
            }
        }
    }*/
    public boolean isClosedInscriptions() {
        return closedInscriptions;
    }

    public void setClosedInscriptions(boolean closedInscriptions) {
        this.closedInscriptions = closedInscriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLigue() {
        return ligue;
    }

    public void setLigue(String ligue) {
        this.ligue = ligue;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public UserManager getPlayers() {
        return players;
    }


    public TeamManager getTeams() {
        return teams;
    }

    public void setTeams(TeamManager teams) {
        this.teams = teams;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public GenericMatchmakeManager getMatchmaked() {
        return matchmaked;
    }






    /*public void emparejarTodosRandom() {
        matchmakedPlayers.eliminarTodosLosEmparejamientos();
        matchmakedTeams.eliminarTodosLosEmparejamientos();
        if (players.getTamano() % 2 == 0 &&teams.getSize() % 2 == 0) {
            if(players.getTamano()>=2){//PUES QUE SI SOLO HAY TEAMS QUE LO DE LOS PLAYERS NO LO HAGA
                for (int i = 0; i < players.getTamano()/2; i++) {
                    Player jugador1 = null;
                    Player jugador2 = null;
                    do {
                        jugador1 = players.getJugadorRandom();
                    } while (jugador1.isEmparejado());

                    do {//HOLA
                        jugador2 = players.getJugadorRandom();
                    } while (jugador2.isEmparejado() || jugador1 == jugador2);
                    Matchmake matchmake = new Matchmake(jugador1, jugador2, matchmakedPlayers);
                    matchmakedPlayers.agregarEmparejamiento(matchmake);
                }

            }
            if (teams.getSize() >= 2) { //PUES QUE SI SOLO HAY PLAYERS QUE LO DE LOS TEAMS NO LO HAGA

                for (int i = 0; i < teams.getSize() / 2; i++) {
                    Team team1 = null;
                    Team team2 = null;
                    do {
                        team1 = teams.getTeamRandom();
                    } while (team1.isEmparejado());
                    do {
                        team2 = teams.getTeamRandom();
                    } while (team2.isEmparejado() || team1 == team2);
                    TeamMatchmake matchmake = new TeamMatchmake(team1, team2, matchmakedTeams);
                    matchmakedTeams.agregarEmparejamiento(matchmake);
                }
            }
            System.out.println("Se ha emparejado aleatoriamente");
        } else {
            System.out.println("No se puede emparejar aleatoriamente");
        }


    }
    */

    public void matchmakeByCategory(String category) {

        if (players.getSize() % 2 == 0 && teams.getSize() % 2 == 0) {
            if (players.getSize() >= 2) {
                for (int i = 0; i < players.getSize() / 2; i++) {
                    Player jugador1 = selectBestPlayer(category);
                    Player jugador2 = selectBestPlayer(category);
                    GenericMatchmake<Player> matchmake = new GenericMatchmake<Player>(this,jugador1, jugador2, matchmaked);
                }
            }
            if (teams.getSize() >= 2) {
                for (int i = 0; i < teams.getSize() / 2; i++) {
                    Team team1 = selectBestTeam(category);
                    Team team2 = selectBestTeam(category);
                    GenericMatchmake<Team> matchmake = new GenericMatchmake<Team>(this,team1, team2, matchmaked);
                }
            }
        }

    }
    public Player selectBestPlayer(String category) {
        Player bestCandidate = null;
        double bestScore = Double.MIN_VALUE;
        for (int i = 0; i < players.getSize(); i++) {
            Player player=null;
            if(players.getPlayerPosition(i) instanceof Player) {
                player = players.getPlayerPosition(i);
                if (!isPlayerMatchmaked[i] && player.getCategoryMap().getStatistics().get(category) > bestScore) {
                    isPlayerMatchmaked[i] = true;
                    bestCandidate = player;
                    bestScore = player.getCategoryMap().getStatistics().get(category);
                }
            }
        }
        return bestCandidate;
    }
    public Team selectBestTeam(String category) {
        Team bestCandidate = null;
        double bestScore = Double.MIN_VALUE;
        for (int i = 0; i < teams.getSize(); i++) {
            Team team = teams.getPosicionTeam(i);
            if (!isTeamMatchmaked[i] && team.getCategoryMap().getStatistics().get(category) > bestScore) {
                isTeamMatchmaked[i]=true;
                bestCandidate = team;
                bestScore = team.getCategoryMap().getStatistics().get(category);
            }
        }
        return bestCandidate;
    }

    public boolean[] getIsTeamMatchmaked() {
        return isTeamMatchmaked;
    }

    public void setIsTeamMatchmaked(boolean[] isTeamMatchmaked) {
        this.isTeamMatchmaked = isTeamMatchmaked;
    }

    public boolean[] getIsPlayerMatchmaked() {
        return isPlayerMatchmaked;
    }

    public void setIsPlayerMatchmaked(boolean[] isPlayerMatchmaked) {
        this.isPlayerMatchmaked = isPlayerMatchmaked;
    }
    public boolean isPlayerMatchmaked(Player player) {
        boolean resul=false;
        int i=0;
        while(!resul&& i < players.getSize()) {
            if (players.getPlayerPosition(i).equals(player)) {
                resul = isPlayerMatchmaked[i];
            }i++;
        }
        return resul;
    }
    public boolean isTeamMatchmaked(Team team) {
        boolean resul = false;
        int i = 0;
        while (!resul && i < teams.getSize()) {
            if (teams.getPosicionTeam(i).equals(team)) {
                resul = isTeamMatchmaked[i];
            }
            i++;
        }
        return resul;

    }
    public void matchmakePlayer(Player player){
        for (int i = 0; i < players.getSize(); i++) {
            if (players.getPlayerPosition(i).equals(player)) {
                isPlayerMatchmaked[i]=true;
            }
        }
    }
    public void matchmakeTeam(Team team){
        for (int i = 0; i < teams.getSize(); i++) {
            if (teams.getPosicionTeam(i).equals(team)){
                isTeamMatchmaked[i]=true;
            }
        }
    }
    public void matchmakeTeamWithPlayer(Team team,Player player){
        for(int i=0;i< teams.getSize();i++){
            if (teams.getPosicionTeam(i).equals(team)){
                for (int j = 0; j< players.getSize(); j++) {
                    if (players.getPlayerPosition(j).equals(player)) {
                        isTeamMatchmaked[i]=true;
                        isPlayerMatchmaked[j]=true;
                    }
                }
            }
        }
    }
    public void unmatchmakePlayer(Player player){
        for (int i = 0; i < players.getSize(); i++) {
            if (players.getPlayerPosition(i).equals(player)) {
                isPlayerMatchmaked[i]=false;
            }
        }
    }
    public void unmatchmakeTeam(Team team){
        for (int i = 0; i < teams.getSize(); i++) {
            if (teams.getPosicionTeam(i).equals(team)) {
                isTeamMatchmaked[i]=false;
            }
        }
    }
}