package nl.han.oose.service.playlist_service;

import nl.han.oose.entity.account_entity.UserToken;
import nl.han.oose.entity.playlist_entity.Playlist;
import nl.han.oose.entity.playlist_entity.PlaylistCollection;
import nl.han.oose.persistence.account_dao.TokenDAO;
import nl.han.oose.persistence.playlist_dao.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public PlaylistCollection getAllPlaylists(String token) throws AuthenticationException {
        UserToken userToken = getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public PlaylistCollection addPlaylist(String token, Playlist playlist) throws AuthenticationException {
        UserToken userToken = getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.addPlaylist(userToken, playlist);
            return getAllPlaylists(token);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public PlaylistCollection renamePlaylist(String token, int playlistId, Playlist playlist) throws AuthenticationException {
        UserToken userToken = getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.renamePlaylist(playlistId, playlist);
            return getAllPlaylists(token);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }

    }

    @Override
    public PlaylistCollection deletePlaylist(String token, int id) throws AuthenticationException {
        UserToken userToken = getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.deletePlaylist(id);
            return getAllPlaylists(token);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    private UserToken getUserToken(String token) {
        return tokenDAO.getUserToken(token);
    }
}
