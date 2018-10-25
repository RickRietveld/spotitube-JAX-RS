package nl.han.oose.login.controller;

import nl.han.oose.controller.track.TrackController;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.service.track.TrackServiceImpl;
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
public class TrackControllerTest {

    private final int playlistId = 1;

    @Mock
    private TrackServiceImpl trackServiceMock;

    @InjectMocks
    private TrackController sut;

    @Before
    public void setUp() {
    }

    @Test
    public void testThatGetAllAvailableTracksForPlaylistRespondsOK() throws AuthenticationException {
        String token = "9999-9999-9999";
        TrackCollection trackCollection = new TrackCollection();
        Mockito.when(trackServiceMock.getAvailableTracks(Mockito.anyString(), Mockito.anyInt())).thenReturn(trackCollection);
        Response playlistResponse = sut.getAllAvailableTracksForPlaylist(token, playlistId);
        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(trackCollection, playlistResponse.getEntity());
    }

    @Test
    public void testThatGetAllAvailableTracksForPlaylistRespondsUNAUTHORIZED() throws AuthenticationException {
        String token = "";
        Mockito.when(trackServiceMock.getAvailableTracks(Mockito.anyString(), Mockito.anyInt())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.getAllAvailableTracksForPlaylist(token, playlistId);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), playlistResponse.getStatus());
    }
}
