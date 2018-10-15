package nl.han.oose.entity.track;

import java.util.List;

public class TrackCollection {
    private List<Track> tracks;


    public TrackCollection() {

    }

    public TrackCollection(List<Track> trackCollections) {

        this.tracks = trackCollections;
    }

    public List<Track> getTracks() {

        return this.tracks;
    }
}