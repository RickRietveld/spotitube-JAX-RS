package nl.han.oose.login;

public class UserToken {

    private String user;
    private String token;

    public UserToken() {

    }

    public UserToken(String token, String user) {
        this.token = token;
        this.user = user;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
