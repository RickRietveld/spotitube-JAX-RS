package nl.han.oose.service.login_service;

import nl.han.oose.entity.account_entity.Account;
import nl.han.oose.entity.account_entity.UserToken;
import nl.han.oose.persistence.account_dao.TokenDAO;
import nl.han.oose.persistence.account_dao.UserDAO;

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





