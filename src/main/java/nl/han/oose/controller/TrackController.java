package nl.han.oose.controller;

import nl.han.oose.dto.TrackDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/tracks")
public class TrackController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDTO getTracks(@QueryParam("token") String token) {

        return new TrackDTO();
    }

}
