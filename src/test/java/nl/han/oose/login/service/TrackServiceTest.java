package nl.han.oose.login.service;

import nl.han.oose.entity.account.UserToken;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.service.track.TrackServiceImpl;
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
public class TrackServiceTest {

    private final String VALID_TOKEN = "9999-9999-9999";
    private final String INVALID_TOKEN = "1111-1111-1111";
    private final String EXPECTED_MSG = "Usertoken mismatch.";
    private final UserToken USER_TOKEN = new UserToken("9999-9999-9999", "rickrietveld");
    private final int PLAYLIST_ID = 1;
    private final int TRACK_ID = 2;
    private TrackCollection trackCollection;
    private Track track;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private TrackServiceImpl sut;

    @Before
    public void setUp() {
        trackCollection = new TrackCollection();
        track = new Track();
    }

    @Test
    public void testThatAvailableTrackIsReturnedAfterTokenIsValid() throws AuthenticationException {

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAvailableTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.getAvailableTracks(VALID_TOKEN, PLAYLIST_ID));
    }

    @Test
    public void testThatAvailableTracksReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAvailableTracks(VALID_TOKEN, PLAYLIST_ID);
    }

    @Test
    public void testThatGetAttachedTracksIsReturnedAfterTrackIsAddedAndTokenIsValid() throws AuthenticationException {

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAttachedTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.addTrackToPlaylist(VALID_TOKEN, 1, track));
    }

    @Test
    public void testThatAddTracksToPlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addTrackToPlaylist(INVALID_TOKEN, PLAYLIST_ID, new Track());
    }

    @Test
    public void testThatGetAttachedTracksIsReturnedAfterTrackIsRemovedAndTokenIsValid() throws AuthenticationException {

        Mockito.when(tokenDAO.getUserToken(Mockito.anyString())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAttachedTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.removeTrackFromPlaylist(VALID_TOKEN, PLAYLIST_ID, TRACK_ID));
    }

    @Test
    public void testThatRemoveTrackFromPlaylistReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.removeTrackFromPlaylist(INVALID_TOKEN, PLAYLIST_ID, TRACK_ID);
    }

    @Test
    public void testThatGetAttachedTracksIsReturnedAfterTokenIsValid() throws AuthenticationException {

        Mockito.when(tokenDAO.getUserToken(Mockito.anyString())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAttachedTracks(Mockito.anyInt())).thenReturn(trackCollection);

        assertEquals(trackCollection, sut.getAttachedTracks(VALID_TOKEN, PLAYLIST_ID));
    }

    @Test
    public void testThatGetAttachedTracksReturnsExceptionIfTokenIsInvalid() throws AuthenticationException {

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(EXPECTED_MSG);

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(USER_TOKEN);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAttachedTracks(INVALID_TOKEN, PLAYLIST_ID);
    }
}
