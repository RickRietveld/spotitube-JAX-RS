package nl.han.oose.entity.track;

import nl.han.oose.entity.IEntity;

public class Video extends Track implements IEntity {

    private int playcount;
    private String publicationDate;
    private String description;

    public Video(int id, String title, String performer, int duration, int playcount,
                 String publicationDate, String description, boolean offlineAvailable) {
        super(id, title, performer, duration, offlineAvailable);
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
