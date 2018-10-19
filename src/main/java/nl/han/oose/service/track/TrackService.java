package nl.han.oose.service.track;

import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;

import javax.naming.AuthenticationException;

public interface TrackService {
    TrackCollection getAvailableTracks(String token, int playlistId) throws AuthenticationException;

    TrackCollection getAttachedTracks(String token, int playlistId) throws AuthenticationException;

    TrackCollection addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException;

    TrackCollection removeTrackFromPlaylist(String token, int playlistId, int trackId) throws AuthenticationException;

}
