package nl.han.oose.login;

import javax.security.auth.login.LoginException;

public class LoginService {


    public UserToken login(Account user) throws LoginException {
        if (user.getUser().equals("rickrietveld") && user.getPassword().equals("password")) {
            return new UserToken("1234-1234-1234", "Rick Rietveld");
        } else {
            throw new LoginException("Wrong credentials");
        }
    }

}
