package nl.han.oose.entity.playlist;


import java.util.ArrayList;


public class PlaylistCollection {

    private ArrayList<Playlist> playlists;
    private int length;

    public PlaylistCollection(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Playlist> getPlaylists() {

        return this.playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}