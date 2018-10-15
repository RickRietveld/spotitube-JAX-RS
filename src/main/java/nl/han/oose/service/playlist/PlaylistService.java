package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.service.track.TrackService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;


@Default
public class PlaylistService {


    @Inject
    private PlaylistDAO playlistDAO;
    @Inject
    private TrackService trackService;

    public PlaylistCollection allPlaylists() {

        PlaylistCollection playlists = playlistDAO.getAllPlaylists();

        int totalDuration = 2;

//            for (PlaylistCollection playlist : playlists.getPlaylists()) {
//                TrackCollection tracks = trackService.tracksFromPlaylistId(playlist.getId());
//                for (TrackCollection track : tracks.getTracks()) {
//                    totalDuration += track.getDuration();
//                }
//                playlist.setTracks(tracks.getTracks());
//            }

        playlists.setLength(totalDuration);

        return playlists;
    }

    public Playlist playlistById(int id) {

        return playlistDAO.getPlaylist(id);
    }

    public TrackCollection tracksByPlaylistId(int id) {
        Playlist playlist = playlistById(id);

        TrackCollection trackCollections = trackService.getTrackWithPlaylistId(id);

        playlist.setTracks(trackCollections.getTracks());

        return new TrackCollection(playlist.getTracks());
    }


}
