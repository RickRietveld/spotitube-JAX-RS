package nl.han.oose.login.controller;

import nl.han.oose.controller.playlist.PlaylistController;
import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.service.playlist.PlaylistServiceImpl;
import nl.han.oose.service.track.TrackServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistServiceImpl playlistServiceMock;

    @Mock
    private TrackServiceImpl trackServiceMock;

    @InjectMocks
    private PlaylistController sut;

    @Test
    public void testThatGetAllPlaylistsRespondsOK() throws AuthenticationException {
        String token = "9999-9999-9999";
        PlaylistCollection playlistCollection = new PlaylistCollection();

        Mockito.when(playlistServiceMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);
        Response playlistResponse = sut.getAllPlaylists(token);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlistCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatGetAllPlaylistsRespondsUNAUTHORIZED() throws AuthenticationException {
        String token = "";

        Mockito.when(playlistServiceMock.getAllPlaylists(Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.getAllPlaylists(token);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatDeletePlaylistRespondsOK() throws AuthenticationException {
        PlaylistCollection playlistCollection = new PlaylistCollection();
        String token = "9999-9999-9999";
        int playlistId = 1;

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyString(), Mockito.anyInt())).thenReturn(playlistCollection);
        Response playlistResponse = sut.deletePlaylist(token, playlistId);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlistCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatDeletePlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        String token = "9999-9999-9999";
        int playlistId = 1;

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyString(), Mockito.anyInt())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.deletePlaylist(token, playlistId);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatAddPlaylistRespondsOK() throws AuthenticationException {
        PlaylistCollection playlists = new PlaylistCollection();
        String token = "9999-9999-9999";
        Playlist playlist = new Playlist();


        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenReturn(playlists);
        Response playlistResponse = sut.addPlaylist(token, playlist);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlists, playlistResponse.getEntity());
    }

    @Test
    public void testThatAddPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Playlist playlist = new Playlist();
        String token = "9999-9999-9999";

        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.addPlaylist(token, playlist);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatRenamePlaylistRespondsOK() throws AuthenticationException {
        PlaylistCollection playlistCollection = new PlaylistCollection();
        Playlist playlist = new Playlist();
        String token = "9999-9999-9999";

        Mockito.when(playlistServiceMock.renamePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistCollection);
        Response playlistResponse = sut.renamePlaylist(token, playlist);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlistCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatRenamePlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Playlist playlist = new Playlist();
        String token = "9999-9999-9999";

        Mockito.when(playlistServiceMock.renamePlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.renamePlaylist(token, playlist);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatAddTrackToPlaylistRespondsOK() throws AuthenticationException {
        TrackCollection trackCollection = new TrackCollection();
        Track track = new Track();
        int playlistId = 1;
        String token = "9999-9999-9999";

        Mockito.when(trackServiceMock.addTrackToPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.any())).thenReturn(trackCollection);
        Response playlistResponse = sut.addTrackToPlaylist(token, playlistId, track);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(trackCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatAddTrackToPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Track track = new Track();
        int playlistId = 1;
        String token = "9999-9999-9999";

        Mockito.when(trackServiceMock.addTrackToPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.addTrackToPlaylist(token, playlistId, track);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatRemoveTrackFromPlaylistRespondsOK() throws AuthenticationException {
        TrackCollection trackCollection = new TrackCollection();
        int playlistId = 1;
        int trackId = 2;
        String token = "9999-9999-9999";

        Mockito.when(trackServiceMock.removeTrackFromPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(trackCollection);
        Response playlistResponse = sut.removeTrackFromPlaylist(token, playlistId, trackId);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(trackCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatRemoveTrackFromPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        int playlistId = 1;
        int trackId = 2;
        String token = "9999-9999-9999";

        Mockito.when(trackServiceMock.removeTrackFromPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.removeTrackFromPlaylist(token, playlistId, trackId);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }


}
