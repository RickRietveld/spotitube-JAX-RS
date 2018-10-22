package nl.han.oose.controller.track;

import nl.han.oose.service.track.TrackServiceImpl;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    private TrackServiceImpl trackService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvailableTracksForPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        try {
            return Response.status(Response.Status.OK).entity(trackService.getAvailableTracks(token, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
