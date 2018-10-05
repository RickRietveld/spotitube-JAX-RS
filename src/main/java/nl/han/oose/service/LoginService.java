package nl.han.oose.service;

import nl.han.oose.entity.Account;
import nl.han.oose.entity.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken login(Account user) throws LoginException;
}
