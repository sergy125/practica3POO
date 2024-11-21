package etsisi.poo.modelo;

import etsisi.poo.managers.GenericMatchmakeManager;

import etsisi.poo.managers.commands.Utilities;

public class GenericMatchmake<T> {
    private T[] emparejamiento;

    public GenericMatchmake(Tournament tournament,T jugador1, T jugador2, GenericMatchmakeManager matchmakeManager) {
        this.emparejamiento = (T[]) new Object[2];
        if (jugador1 instanceof Player && tournament.isPlayerMatchmaked((Player) jugador1)
                || jugador2 instanceof Player && tournament.isPlayerMatchmaked((Player) jugador2)
                || jugador1 instanceof Team && tournament.isTeamMatchmaked((Team) jugador1)
                || jugador2 instanceof Team && tournament.isTeamMatchmaked((Team) jugador2)) {
            String error = Utilities.ALREADY_MATCHMAKED;
            Utilities.print(error);
        } else {
            this.emparejamiento[0] = jugador1;
            this.emparejamiento[1] = jugador2;
            if (jugador1 instanceof Player) {
                tournament.matchmakePlayer((Player)jugador1);

            }
            if (jugador2 instanceof Player) {
                tournament.matchmakePlayer((Player)jugador2);
            }
            if (jugador1 instanceof Team) {
                tournament.matchmakeTeam((Team) jugador1);
            }
            if (jugador2 instanceof Team) {
                tournament.matchmakeTeam((Team) jugador2);
            }
            matchmakeManager.agregarEmparejamiento(this);
        }

    }

    public T[] getArrayJugadores() {
        return emparejamiento;
    }

    public String toString() {
        return "Emparejamiento{" +
                "jugador1=" + emparejamiento[0] +
                ", jugador2=" + emparejamiento[1] +
                '}';
    }
}
