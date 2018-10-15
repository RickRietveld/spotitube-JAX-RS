package nl.han.oose.service.login;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken verifyLogin(Account user) throws LoginException;

}
