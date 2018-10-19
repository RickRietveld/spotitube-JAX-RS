package nl.han.oose.service.login;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;

import javax.naming.AuthenticationException;

public interface LoginService {
    UserToken login(Account user) throws AuthenticationException;

}
