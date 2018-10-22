package nl.han.oose.login.service;

import nl.han.oose.entity.account.UserToken;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.service.track.TrackServiceImpl;
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
public class TrackServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private TokenDAO tokenDAO;


    @InjectMocks
    private TrackServiceImpl sut;

    @Test
    public void testThatAvailableTrackIsReturnedAfterTokenIsValid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        TrackCollection trackCollection = new TrackCollection();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAvailableTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.getAvailableTracks("9999-9999-9999", 1));
    }

    @Test
    public void testThatAvailableTracksReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");

        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAvailableTracks("1111-1111-1111", 1);
    }

    @Test
    public void testThatGetAttachedTracksIsReturnedAfterTrackIsAddedAndTokenIsValid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        TrackCollection trackCollection = new TrackCollection();
        Track track = new Track();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAttachedTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.addTrackToPlaylist("9999-9999-9999", 1, track));
    }

    @Test
    public void testThatAddTracksToPlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");

        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addTrackToPlaylist("1111-1111-1111", 1, new Track());
    }

    @Test
    public void testThatGetAttachedTracksIsReturnedAfterTrackIsRemovedAndTokenIsValid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        TrackCollection trackCollection = new TrackCollection();
        int playlistId = 1;
        int trackId = 2;

        Mockito.when(tokenDAO.getUserToken(Mockito.anyString())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAttachedTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.removeTrackFromPlaylist("9999-9999-9999", playlistId, trackId));
    }

    @Test
    public void testThatRemoveTrackFromPlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");
        int playlistId = 1;
        int trackId = 2;

        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.removeTrackFromPlaylist("1111-1111-1111", playlistId, trackId);
    }

    @Test
    public void testThatGetAttachedTracksIsReturnedAfterTokenIsValid() throws AuthenticationException {
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");
        TrackCollection trackCollection = new TrackCollection();
        int playlistId = 1;

        Mockito.when(tokenDAO.getUserToken(Mockito.anyString())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAttachedTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.getAttachedTracks("9999-9999-9999", playlistId));
    }

    @Test
    public void testThatGetAttachedTracksReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken mismatch.");
        int playlistId = 1;

        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAttachedTracks("1111-1111-1111", playlistId);
    }
}
