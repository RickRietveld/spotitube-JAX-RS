package nl.han.oose.login.service;

import nl.han.oose.entity.account_entity.UserToken;
import nl.han.oose.entity.playlist_entity.Playlist;
import nl.han.oose.entity.playlist_entity.PlaylistCollection;
import nl.han.oose.persistence.account_dao.TokenDAO;
import nl.han.oose.persistence.playlist_dao.PlaylistDAO;
import nl.han.oose.service.playlist_service.PlaylistServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    private final String VALID_TOKEN = "9999-9999-9999";
    private final String INVALID_TOKEN = "1111-1111-1111";
    private final String EXPECTED_MSG = "Usertoken mismatch.";
    private final UserToken USER_TOKEN = new UserToken(VALID_TOKEN, "rickrietveld");
    private final int PLAYLIST_ID = 1;
    private PlaylistCollection playlistCollection;
    private Playlist playlist;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TokenDAO userTokenDAOMock;

    @Mock
    private PlaylistDAO playlistDAOMock;

    @InjectMocks
    private PlaylistServiceImpl sut;

    @Before
    public void setUp() {
        playlistCollection = new PlaylistCollection();
        playlist = new Playlist();
    }

    @Test
    public void testThatGetAllPlaylistsReturnsPlaylistIfTokenIsValid() throws AuthenticationException {
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);
        assertEquals(playlistCollection, sut.getAllPlaylists(VALID_TOKEN));
    }

    @Test
    public void testThatGetAllPlaylistsReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);
        sut.getAllPlaylists(INVALID_TOKEN);
    }

    @Test
    public void testThatRenamePlaylistReturnsPlaylistCollectionIfTokenIsValid() throws AuthenticationException {
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);
        assertEquals(playlistCollection, sut.renamePlaylist(VALID_TOKEN, playlist));
    }

    @Test
    public void testThatRenamePlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);
        sut.renamePlaylist(INVALID_TOKEN, playlist);
    }

    @Test
    public void testThatDeletePlaylistReturnsPlaylistCollectionIfTokenIsValid() throws AuthenticationException {
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);
        assertEquals(playlistCollection, sut.deletePlaylist(VALID_TOKEN, PLAYLIST_ID));
    }

    @Test
    public void testThatDeletePlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);
        sut.deletePlaylist(INVALID_TOKEN, PLAYLIST_ID);
    }

    @Test
    public void testThatAddPlaylistReturnsPlaylistCollectionIfTokenIsValid() throws AuthenticationException {
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);
        assertEquals(playlistCollection, sut.addPlaylist(VALID_TOKEN, playlist));
    }

    @Test
    public void testThatAddPlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);
        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);
        sut.addPlaylist(INVALID_TOKEN, playlist);
    }
}