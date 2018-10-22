package nl.han.oose.login.service;

import nl.han.oose.entity.account.UserToken;
import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.service.playlist.PlaylistServiceImpl;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TokenDAO userTokenDAOMock;

    @Mock
    private PlaylistDAO playlistDAOMock;

    @InjectMocks
    private PlaylistServiceImpl sut;

    @Test
    public void testThatGetAllPlaylistsReturnsPlaylistIfTokenIsValid() throws AuthenticationException {
        PlaylistCollection playlistCollection = new PlaylistCollection();
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);

        assertEquals(playlistCollection, sut.getAllPlaylists("9999-9999-9999"));
    }

    @Test
    public void testThatGetAllPlaylistsReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllPlaylists("1111-1111-1111");
    }

    @Test
    public void testThatRenamePlaylistReturnsPlaylistCollectionIfTokenIsValid() throws AuthenticationException {
        PlaylistCollection playlistCollection = new PlaylistCollection();
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        Playlist playlist = new Playlist();

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);

        assertEquals(playlistCollection, sut.renamePlaylist("9999-9999-9999", playlist));
    }

    @Test
    public void testThatRenamePlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        Playlist playlist = new Playlist();

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.renamePlaylist("1111-1111-1111", playlist);
    }

    @Test
    public void testThatDeletePlaylistReturnsPlaylistCollectionIfTokenIsValid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        PlaylistCollection playlistCollection = new PlaylistCollection();
        int playlistId = 1;

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);

        assertEquals(playlistCollection, sut.deletePlaylist("9999-9999-9999", playlistId));
    }

    @Test
    public void testThatDeletePlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        int playlistId = 1;

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.deletePlaylist("1111-1111-1111", playlistId);
    }

    @Test
    public void testThatAddPlaylistReturnsPlaylistCollectionIfTokenIsValid() throws AuthenticationException {
        PlaylistCollection playlistCollection = new PlaylistCollection();
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        Playlist playlist = new Playlist();

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlistCollection);

        assertEquals(playlistCollection, sut.addPlaylist("9999-9999-9999", playlist));
    }

    @Test
    public void testThatAddPlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        Playlist playlist = new Playlist();

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");

        Mockito.when(userTokenDAOMock.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(userTokenDAOMock.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addPlaylist("1111-1111-1111", playlist);
    }
}