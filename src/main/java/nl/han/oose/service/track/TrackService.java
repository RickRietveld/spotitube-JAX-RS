package nl.han.oose.service.track;

import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.track.TrackDAO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class TrackService {

    @Inject
    private TrackDAO trackDAO;

    public TrackCollection allTracks() {

        return trackDAO.getAllTracks();
    }

    public Track getTrackById(int id) {
        TrackCollection trackCollections = trackDAO.getAllTracks();
        for (Track track : trackCollections.getTracks()) {
            if (track.getId() == id) {
                return track;
            }
        }
        return null;
    }

    public TrackCollection getTrackWithPlaylistId(int id) {
        // Make new arraylist of TrackDTOS
        ArrayList<Track> tracks = new ArrayList<>();
        // Connect with DAO
        List trackIds = trackDAO.getTracksIdsFromPlaylistid(id);

        // loop through the id's and call getTracksById
        for (int i = 0; i < trackIds.size(); i++) {
            tracks.add(getTrackById((Integer) trackIds.get(i)));
        }
        // Create new TracksDTO and return it
        return new TrackCollection(tracks);
    }

}
