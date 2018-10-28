package nl.han.oose.controller.login_controller;

import nl.han.oose.entity.account_entity.Account;
import nl.han.oose.service.login_service.LoginService;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Account user) {
        try {
            return Response.ok().entity(loginService.login(user)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
