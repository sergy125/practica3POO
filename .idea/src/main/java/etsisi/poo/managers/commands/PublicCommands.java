package etsisi.poo.managers.commands;


import etsisi.poo.managers.UserManager;
import etsisi.poo.modelo.User;

import java.util.Objects;
import java.util.Scanner;

//PUBLIC COMMANDS
public class PublicCommands {
    private User logged;
    //private UserManager userManager;

    /*public String login (String parametro){
        String resul="";
        logged=userManager.loginUser(parametro);
        if(logged!=null){
            resul = "Welcome, " + logged.getUsername();
        }else{
            resul = "The user does not exist or the credentials are incorrect.";
        }
        return resul;
    }*/

    public void registerUser(String parametro,UserManager userManager) {
        if (parametro.contains(";")) {
            String[] credenciales = Utilities.splitArgs(parametro);
            if (credenciales.length == 2) {
                String correo = credenciales[0];
                String password = credenciales[1];
                String[] user = correo.split("@");
                if (!userManager.existsUserWithUsername(user[1])) {
                    if(userManager== null) userManager = new UserManager();
                    User newUser=new User(user[0], password, userManager, true);
                    Utilities.print("Usuario creado con éxito: "+newUser.toString());
                } else Utilities.print(Utilities.EXISTS_USER);
            } else Utilities.print(Utilities.WRONG_ARGS);
        }else Utilities.print(Utilities.WRONG_ARGS);

    }
    public User loginUser(String parametro,UserManager userManager) {

        User resul=null;
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
                            resul = u;
                            //userManager.add(resul);
                            System.out.println(Utilities.OLD_USER);
                        }else System.out.println(Utilities.USERNAME_PASSWORD_ERROR);
                    }
                }
            }else System.out.println(Utilities.WRONG_ARGS);
        }

        return resul;
    }


    public String logout(UserManager userManager) {
        if (logged!=null) {
            userManager.logout(logged);
            logged = null;
        }
        return "Desconectado";
    }


    /*public User login2(UserManager userManager, Scanner scanner,  boolean identified, String parametro) {
        User actualUser = null;

        if(userManager== null) userManager = new UserManager();
        if(identified)userManager.logout(actualUser);

        actualUser = loginUser(parametro, userManager);

        if (actualUser != null) {
            userManager.add(actualUser);
            identified = true;
        }
        return actualUser;
    }*/

}