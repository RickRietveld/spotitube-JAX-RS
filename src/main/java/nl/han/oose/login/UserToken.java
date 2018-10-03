package nl.han.oose.login;

public class UserToken {

    private String username;
    private String token;

    public UserToken() {

    }

    public UserToken(String token, String username) {
        this.token = token;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
