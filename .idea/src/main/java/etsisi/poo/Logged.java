package etsisi.poo;

import etsisi.poo.managers.UserManager;
import etsisi.poo.modelo.User;

public interface Logged  {
    void log(UserManager userManager, String parametro);

    String out();



}
