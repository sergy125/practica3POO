package etsisi.poo.managers.commands;

import java.util.Scanner;

public class Utilities {
    public static final String NO_PERMISSION = "No tienes permisos para ejecutar este comando";
    public static final String MAIL_FORMAT = "@upm.es";
    public static final String MAIL_FORMAT_ERROR = "El correo debe tener el formato @upm.es";
    public static final String PASSWORD_FORMAT_ERROR = "La contraseña debe tener al menos 4 caracteres";
    public static final int PASSWORD_LENGTH = 4;
    public static final String NEW_USER="Se crea un nuevo usuario";
    public static final String OLD_USER="Se inicia sesion";
    public static final String USERNAME_PASSWORD_ERROR = "El usuario o la contraseña no son correctos";
    public static final String WRONG_ARGS = "Los parametros no son correctos";
    public static final String ELIMINATED_PLAYER = "El jugador ha sido eliminado";
    public static final int DNI_LENGTH = 9;
    public static final String DNI_FORMAT_ERROR = "El DNI debe tener 9 caracteres";
    public static final String CANT_CREATE_PLAYER = "El jugador no se ha podido crear";

    public static final String ACTIVE_TOURNAMENT_ERROR = "No se puede ya que participa en un torneo activo";
    public static final String CANT_CREATE_TEAM = "No se ha podido crear el equipo";
    public static final String CANT_DELETE_TEAM = "No se ha podido borrar el equipo";
    public static final String CANT_ADD_PLAYER_TO_TEAM = "No se ha podido añadir el jugador al equipo";
    public static final String TEAM_DOESNT_EXIST = "Este equipo no existe";
    public static final String CANT_REMOVE_PLAYER_FROM_TEAM = "No se ha podido eliminar el jugador del equipo";
    public static final String PLAYER_NOT_IN_TEAM = "Este jugador no existe en el equipo";
    public static final String ONE_PLAYER_TEAM = "Este equipo solo tiene un jugador";

    public static final String ALREADY_MATCHMAKED = "Uno de los jugadores ya está emparejado";
    public static final String NO_EXISTS_USER = "El usuario no existe";
    public static final String EXISTS_USER = "El usuario ya existe";
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String LOGOUT = "logout";
    public static final String LIST_TOURNAMENT = "tournament-list";

    // Admin Commands
    public static final String CREATE_PLAYER = "player-create";
    public static final String CREATE_TEAM = "team-create";

    public static final String DELETE_PLAYER = "player-delete";

    public static final String DELETE_TEAM = "team-delete";
    public static final String ADD_TEAM = "team-add";
    public static final String REMOVE_TEAM = "team-remove";
    public static final String CREATE_TOURNAMENT = "tournament-create";
    public static final String DELETE_TOURNAMENT = "tournament-delete";
    public static final String TOURNAMENT_MATCHMAKING = "tournament-matchmaking";

    //Player Commands
    public static final String ADD_TOURNAMENT = "tournament-add";
    public static final String REMOVE_TOURNAMENT = "tournament-remove";
    public static final String SHOW_STATISTICS = "statistics-show";

    public static final String EXIT = "exit";

    public static boolean validDate(String date) {
        if (date == null || date.length() != 10) {
            return false;
        }

        // dd/MM/yyyy
        if (date.charAt(2) != '/' || date.charAt(5) != '/') {
            return false; // La posición de las barras debe ser correcta
        }

        try {
            // Extraer día, mes y año como enteros
            int dia = Integer.parseInt(date.substring(0, 2));
            int mes = Integer.parseInt(date.substring(3, 5));
            int anio = Integer.parseInt(date.substring(6, 10));

            // Validar mes y día según el calendario básico
            if (mes < 1 || mes > 12) {
                return false; // Mes fuera de rango
            }

            if (dia < 1 || dia > daysMonth(mes, anio)) {
                return false; // Día fuera de rango para el mes
            }

            return true; // La fecha es válida

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int daysMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31; // Meses con 31 días
            case 4:
            case 6:
            case 9:
            case 11:
                return 30; // Meses con 30 días
            case 2:
                // Febrero (considerar años bisiestos
                return (esBisiesto(year) ? 29 : 28); //esto m lo ha hecho chatgpt lo de bisiesto
            default:
                return 0; // Esto no debería ocurrir
        }
    }


    public static boolean esBisiesto(int anio) {
        // Año bisiesto: divisible por 4 pero no por 100, excepto si es divisible por 400
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }

    public static String initialDate(String date) {
        if (validDate(date)) return date.substring(0, 10);
        else return "La fecha no es válida";
    }

    public static String finalDate(String initialDate, String finalDate) {
        if (!validDate(finalDate)) return "Its not valid";

        // Desglosamos ambas fechas (inicial y final) en día, mes y año
        int initialDay = Integer.parseInt(initialDate.substring(0, 2));
        int initialMonth = Integer.parseInt(initialDate.substring(3, 5));
        int initialYear = Integer.parseInt(initialDate.substring(6, 10));

        int finalDay = Integer.parseInt(finalDate.substring(0, 2));
        int finalMonth = Integer.parseInt(finalDate.substring(3, 5));
        int finalYear = Integer.parseInt(finalDate.substring(6, 10));

        // Comparamos año, mes y día para asegurarnos de que la fecha final sea posterior
        if (finalYear > initialYear) {
            return finalDate;
        } else if (finalYear == initialYear) {
            if (finalMonth > initialMonth) {
                return finalDate;
            } else if (finalMonth == initialMonth) {
                if (finalDay > initialDay) {
                    return finalDate;
                } else return "It has to be after the initial date.";

            } else return "It has to be after the initial date.";

        } else return "It has to be after the initial date";

    }
    public static void print(String message) {
        System.out.println(message);
    }

    public static String[] splitArgs(String args){
        return args.split(";");
    }

}
