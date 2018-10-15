package nl.han.oose.controller.playlist;

import nl.han.oose.service.playlist.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@QueryParam("token") String token) {

        return Response.ok(playlistService.allPlaylists()).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response setTracks(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        return Response.ok(playlistService.tracksByPlaylistId(playlistId)).build();

    }

//    @POST
//    @Path("/{id}/tracks")
//    @Produces(MediaType.APPLICATION_JSON)
//    public TrackDTO addTracks(@PathParam("id") int id, @QueryParam("token") String token) {
//
//        return new TrackDTO();
//    }
//
//    @GET
//    @Path("/{id}/tracks")
//    @Produces(MediaType.APPLICATION_JSON)
//    public TrackDTO getTracksByPlayList(@PathParam("id") int id, @QueryParam("token") String token) {
//
//        return new TrackDTO();
//    }


//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/entity/{id}")
//    public Response replacePlayList(@QueryParam("token") String token, Playlist playList, @PathParam("id") int id) {
//        try {
//            playList.replacePlayList(id, playList);
//        } catch (RuntimeException e) {
//            return getNotFoundResponse();
//        }
//        return getOKResponse();
//    }


}
