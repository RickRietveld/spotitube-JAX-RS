package nl.han.oose.controller.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.track.Track;
import nl.han.oose.service.playlist.PlaylistServiceImpl;
import nl.han.oose.service.track.TrackServiceImpl;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistServiceImpl playlistService;
    @Inject
    private TrackServiceImpl trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getAllPlaylists(token)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.addPlaylist(token, playlist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachedTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        try {
            return Response.status(Response.Status.OK).entity(trackService.getAttachedTracks(token, id)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, Track track) {
        try {
            return Response.status(Response.Status.OK).entity(trackService.addTrackToPlaylist(token, playlistId, track)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response renamePlaylist(@QueryParam("token") String token, Playlist playlist) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.renamePlaylist(token, playlist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.deletePlaylist(token, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{playlistId}/tracks/{trackId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        try {
            return Response.status(Response.Status.OK).entity(trackService.removeTrackFromPlaylist(token, playlistId, trackId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }



}
