package nl.han.oose.dto;

import nl.han.oose.entity.track.Song;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Video;

import java.util.ArrayList;
import java.util.List;

public class TrackDTO {

    private List<Track> tracks;

    public TrackDTO() {
        tracks = new ArrayList<>();
        fillTrackList();
    }

    public void fillTrackList() {
        if (tracks.isEmpty()) {
            tracks.add(new Song(3, "Ocean and a rock", "Lisa Hannigan",
                    337, "Sea sew", false));
            tracks.add(new Song(4, "So Long, Marianne", "Leonard Cohen",
                    546, "Song of Leonard Cohen", false));
            tracks.add(new Video(5, "One", "Metallica",
                    423, 37, "1-11-2001", "Long Version", true));
        }
    }

    public List<Track> getTracks() {

        return tracks;
    }

    public int calculateTotalDurationLength() {

        int totalDuration = 0;

        for (Track track : tracks) {
            totalDuration += track.getDuration();
            System.out.println(totalDuration);
        }
        return totalDuration;
    }

}
