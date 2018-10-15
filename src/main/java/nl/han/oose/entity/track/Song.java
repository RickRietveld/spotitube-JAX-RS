package nl.han.oose.entity.track;

import nl.han.oose.entity.IEntity;

public class Song extends Track implements IEntity {

    private String album;

    public Song(int id, String title, String performer, int duration, String album, boolean offlineAvailable) {
        super(id, title, performer, duration, offlineAvailable);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
