package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.persistence.playlist.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;


@Default
public class PlaylistService {


    @Inject
    private PlaylistDAO playlistDAO;

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


}
