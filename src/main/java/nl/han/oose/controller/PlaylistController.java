package nl.han.oose.controller;

import nl.han.oose.dto.PlayListDTO;
import nl.han.oose.dto.TrackDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class PlaylistController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlayListDTO getPlayLists(@QueryParam("token") String token) {

        return new PlayListDTO();
    }

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDTO addTracks(@PathParam("id") int id, @QueryParam("token") String token) {

        return new TrackDTO();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDTO getTracksByPlayList(@PathParam("id") int id, @QueryParam("token") String token) {

        return new TrackDTO();
    }


//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/entity/{id}")
//    public Response replacePlayList(@QueryParam("token") String token, PlayList playList, @PathParam("id") int id) {
//        try {
//            playList.replacePlayList(id, playList);
//        } catch (RuntimeException e) {
//            return getNotFoundResponse();
//        }
//        return getOKResponse();
//    }


}
