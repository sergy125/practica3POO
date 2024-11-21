package etsisi.poo.modelo;

import etsisi.poo.managers.UserManager;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isPlayer;
    public User(String username, String password, UserManager userManager,boolean createAdmin) {
        this.username = username;
        this.password = password;
        if (createAdmin){
            isAdmin = true;
            isPlayer = false;
        } else {
            isAdmin = false;
            isPlayer = true;
        }
        userManager.add(this);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        boolean esUpm = username.contains("@upm.es");
        if (esUpm)this.username = username.replace("@upm.es", "");
        else System.out.println("Tiene que contener @upm.esº");;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //public User searchUser()

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public String toString() {
        return "Username: " + this.username + "\nPassword: " + this.password + "\n" + (isAdmin ? "Admin" : "Player");
    }
    public Admin toAdmin(UserManager userManager) {
        return new Admin(username, password,userManager);
    }
}

