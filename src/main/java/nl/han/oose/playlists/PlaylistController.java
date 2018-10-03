package nl.han.oose.playlists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistController {

    private static List<PlayList> playLists = new ArrayList<>();

    public PlaylistController() {
        if (playLists.isEmpty()) {
            playLists.add(new PlayList(1, "Death metal", true, new String[]{}));
            playLists.add(new PlayList(2, "Pop", false, new String[]{}));
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlayList getPlayLists() {
        for (PlayList playList : playLists) {
            return playList;
        }
        return null;
    }

}
