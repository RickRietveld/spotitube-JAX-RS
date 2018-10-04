package nl.han.oose.playlists;

import java.util.ArrayList;
import java.util.List;

public class PlayList {

    private static List<PlayListResource> playListResources;
    private int length;

    public PlayList() {
        playListResources = new ArrayList<>();
        fillPlayListResources();
        getLength();
    }

    public void fillPlayListResources() {
        if (playListResources.isEmpty()) {
            playListResources.add(new PlayListResource(1, "Death metal", true, new String[]{}));
            playListResources.add(new PlayListResource(2, "Pop", false, new String[]{}));
        }
    }

    public List<PlayListResource> getPlaylists() {
        return playListResources;

    }


    public int getLength() {
        length = playListResources.size();
        return length;
    }

    public void setLength(int length) {

        this.length = length;
    }
}
