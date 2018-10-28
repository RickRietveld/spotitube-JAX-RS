package nl.han.oose.login.controller;

import nl.han.oose.controller.playlist_controller.PlaylistController;
import nl.han.oose.entity.playlist_entity.Playlist;
import nl.han.oose.entity.playlist_entity.PlaylistCollection;
import nl.han.oose.entity.track_entity.Track;
import nl.han.oose.entity.track_entity.TrackCollection;
import nl.han.oose.service.playlist_service.PlaylistServiceImpl;
import nl.han.oose.service.track_service.TrackServiceImpl;
import org.junit.Before;
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

    private final int PLAYLIST_ID = 1;
    private final int TRACK_ID = 2;
    private PlaylistCollection playlistCollection;
    private Playlist playlist;
    private TrackCollection trackCollection;
    private Track track;
    private String token;

    @Mock
    private PlaylistServiceImpl playlistServiceMock;

    @Mock
    private TrackServiceImpl trackServiceMock;

    @InjectMocks
    private PlaylistController sut;

    @Before
    public void setUp() {
        playlistCollection = new PlaylistCollection();
        playlist = new Playlist();
        trackCollection = new TrackCollection();
        track = new Track();
        token = "9999-9999-9999";
    }

    @Test
    public void testThatGetAllPlaylistsRespondsOK() throws AuthenticationException {
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
        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyString(), Mockito.anyInt())).thenReturn(playlistCollection);
        Response playlistResponse = sut.deletePlaylist(token, PLAYLIST_ID);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlistCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatDeletePlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyString(), Mockito.anyInt())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.deletePlaylist(token, PLAYLIST_ID);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatAddPlaylistRespondsOK() throws AuthenticationException {
        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistCollection);
        Response playlistResponse = sut.addPlaylist(token, playlist);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlistCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatAddPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.addPlaylist(token, playlist);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatRenamePlaylistRespondsOK() throws AuthenticationException {
        Mockito.when(playlistServiceMock.renamePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistCollection);
        Response playlistResponse = sut.renamePlaylist(token, playlist);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlistCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatRenamePlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Mockito.when(playlistServiceMock.renamePlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.renamePlaylist(token, playlist);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatAddTrackToPlaylistRespondsOK() throws AuthenticationException {
        Mockito.when(trackServiceMock.addTrackToPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.any())).thenReturn(trackCollection);
        Response playlistResponse = sut.addTrackToPlaylist(token, PLAYLIST_ID, track);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(trackCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatAddTrackToPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Mockito.when(trackServiceMock.addTrackToPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.addTrackToPlaylist(token, PLAYLIST_ID, track);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatRemoveTrackFromPlaylistRespondsOK() throws AuthenticationException {
        Mockito.when(trackServiceMock.removeTrackFromPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(trackCollection);
        Response playlistResponse = sut.removeTrackFromPlaylist(token, PLAYLIST_ID, TRACK_ID);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(trackCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatRemoveTrackFromPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        Mockito.when(trackServiceMock.removeTrackFromPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.removeTrackFromPlaylist(token, PLAYLIST_ID, TRACK_ID);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void testThatGetAttachedTracksRespondsOK() throws AuthenticationException {
        Mockito.when(trackServiceMock.getAttachedTracks(Mockito.anyString(), Mockito.anyInt())).thenReturn(trackCollection);
        Response playlistResponse = sut.getAttachedTracks(token, PLAYLIST_ID);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(trackCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatGetAttachedTracksRespondsUNAUTHORIZED() throws AuthenticationException {
        Mockito.when(trackServiceMock.getAttachedTracks(Mockito.anyString(), Mockito.anyInt())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.getAttachedTracks(token, PLAYLIST_ID);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }


}
