package nl.han.oose.playlists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class PlaylistController {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlayList getPlayLists(@QueryParam("token") String token) {

        PlayList playList = new PlayList();

        return playList;

    }

}
