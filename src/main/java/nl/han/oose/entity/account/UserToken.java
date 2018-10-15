package nl.han.oose.entity.account;

public class UserToken {

    private String user;
    private String token;

    //java.util.UUID;  System.out.println(UUID.random.UUID().toString());

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
