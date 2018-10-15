package nl.han.oose.entity.account;

import nl.han.oose.entity.IEntity;

public class Account implements IEntity {

    private String user;
    private String password;

    public Account() {

    }

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }
}
