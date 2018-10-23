package nl.han.oose.service.playlist;

import nl.han.oose.entity.account.UserToken;
import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.playlist.PlaylistDAO;

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
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public PlaylistCollection addPlaylist(String token, Playlist playlist) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.addPlaylist(userToken, playlist);
            return getAllPlaylists(token);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }

    @Override
    public PlaylistCollection renamePlaylist(String token, Playlist playlist) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.renamePlaylist(playlist);
            return getAllPlaylists(token);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }

    }

    @Override
    public PlaylistCollection deletePlaylist(String token, int id) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.deletePlaylist(id);
            return getAllPlaylists(token);
        } else {
            throw new AuthenticationException("Usertoken mismatch.");
        }
    }
}
