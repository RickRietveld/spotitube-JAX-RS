package nl.han.oose.service.track;

import nl.han.oose.entity.account.UserToken;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.track.TrackDAO;

import javax.inject.Inject;
import javax.naming.AuthenticationException;


public class TrackServiceImpl implements TrackService {

    @Inject
    private TrackDAO trackDAO;
    @Inject
    private TokenDAO tokenDAO;

    @Override
    public TrackCollection getAvailableTracks(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return trackDAO.getAvailableTracks(playlistId);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public TrackCollection getAttachedTracks(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return trackDAO.getAttachedTracks(playlistId);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public TrackCollection addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            trackDAO.addTrackToPlaylist(playlistId, track);
            return trackDAO.getAttachedTracks(playlistId);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public TrackCollection removeTrackFromPlaylist(String token, int playlistId, int trackId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            trackDAO.removeTrackFromPlaylist(playlistId, trackId);
            return trackDAO.getAttachedTracks(playlistId);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

}
