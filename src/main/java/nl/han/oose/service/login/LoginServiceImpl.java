package nl.han.oose.service.login;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.account.UserDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private TokenDAO tokenDAO;


    @Override
    public UserToken login(Account account) throws LoginException {
        if (tokenDAO.checkIfTokenAlreadyExists(account.getUser())) {
            return tokenDAO.getExistingUserAndToken(account.getUser());
        } else if (userDAO.verifyLogin(account)) {
            return tokenDAO.getNewToken(account);
        } else {
            throw new LoginException("Wrong credentials.");
        }
    }


}





