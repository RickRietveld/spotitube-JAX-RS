package nl.han.oose.service;

import nl.han.oose.entity.Account;
import nl.han.oose.entity.UserToken;

import javax.enterprise.inject.Default;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {


    @Override
    public UserToken login(Account user) throws LoginException {
        if (user.getUser().equals("rickrietveld") && user.getPassword().equals("password")) {
            return new UserToken("1234-1234-1234", "Rick Rietveld");
        } else {
            throw new LoginException("Wrong credentials");
        }
    }

}
