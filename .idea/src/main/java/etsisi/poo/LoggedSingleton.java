package etsisi.poo;

import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.User;

import java.util.Objects;

public class LoggedSingleton {
    private static LoggedSingleton instancia; // Única instancia
    private User usuarioLogueado;

    // Constructor privado para evitar instancias externas
    private LoggedSingleton() {}

    // Método estático para obtener la instancia única
    public static LoggedSingleton getInstance() {
        if (instancia == null) {
            instancia = new LoggedSingleton();
        }
        return instancia;
    }

    // Método para iniciar sesión
    public void log( UserManager userManager, String parametro) {
        if (usuarioLogueado == null) {
            if (parametro.contains(";")) {
                String[] credenciales= parametro.split(";");
                if (credenciales.length == 2) {
                    String correo = credenciales[0];
                    String password = credenciales[1];

                    if (!correo.contains(Utilities.MAIL_FORMAT)) {
                        System.out.println(Utilities.MAIL_FORMAT_ERROR);
                    } else if(!(password.length() >= Utilities.PASSWORD_LENGTH)) {
                        System.out.println(Utilities.PASSWORD_FORMAT_ERROR);
                    }else{
                        String[] user = correo.split("@");
                        if(  !userManager.existsUserWithUsername(user[0])||userManager==null ) {
                            Utilities.print(Utilities.NO_EXISTS_USER);
                        }else{  //Si existe el usuario
                            String username = user[0];
                            User u = userManager.getUserWithUsername(username);
                            if (u != null && Objects.equals(u.getPassword(), password)) {
                                usuarioLogueado = u;
                                //userManager.add(usuarioLogueado);
                                System.out.println(Utilities.OLD_USER);
                            }else System.out.println(Utilities.USERNAME_PASSWORD_ERROR);
                        }
                    }
                }else System.out.println(Utilities.WRONG_ARGS);
            }

        } else {
            System.out.println("Ya hay un usuario logueado: " + usuarioLogueado.getUsername());
        }
    }

    // Método para cerrar sesión
    public String out() {
        String resul="";
        if (usuarioLogueado != null) {
            resul="Cerrando sesión de: " + usuarioLogueado.getUsername();
            usuarioLogueado = null;
        } else {
            resul="No hay un usuario logueado.";
        }
        return resul;
    }

    // Obtener el usuario actual
    public User getUsuarioLogueado() {
        return usuarioLogueado;
    }
}
