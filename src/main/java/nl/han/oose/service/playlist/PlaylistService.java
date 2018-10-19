package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;

import javax.naming.AuthenticationException;

public interface PlaylistService {
    PlaylistCollection getAllPlaylists(String token) throws AuthenticationException;

    PlaylistCollection addPlaylist(String token, Playlist playlist) throws AuthenticationException;

    PlaylistCollection renamePlaylist(String token, Playlist playlist) throws AuthenticationException;

    PlaylistCollection deletePlaylist(String token, int id) throws AuthenticationException;


}
