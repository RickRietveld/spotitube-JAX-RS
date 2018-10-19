package nl.han.oose.persistence.track;

import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.ConnectionFactory;
import nl.han.oose.persistence.Datamapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackDAO extends Datamapper {

    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }


    public TrackCollection getAvailableTracks(int playlistId) {
        TrackCollection trackCollection = new TrackCollection();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track t LEFT JOIN trackPlaylistRelation " +
                        "ON trackPlaylistRelation.trackId = t.id AND trackPlaylistRelation.playlistId = ? " +
                        "WHERE t.id NOT IN(SELECT trackId FROM trackPlaylistRelation WHERE playlistId = ?);")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                initializeTrack(resultSet, trackCollection.getTracks());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackCollection;
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM trackPlaylistRelation WHERE playlistId = ? AND trackId = ?;")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO trackPlaylistRelation (playlistId, trackId, offlineAvailable) VALUES(?, ?, ?);")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, track.getId());
            preparedStatement.setBoolean(3, track.isOfflineAvailable());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TrackCollection getAttachedTracks(int playlistId) {
        TrackCollection trackCollection = new TrackCollection();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track t LEFT JOIN trackPlaylistRelation " +
                        "ON trackPlaylistRelation.trackId = t.id AND trackPlaylistRelation.playlistId = ? " +
                        "WHERE t.id IN(SELECT trackId FROM trackPlaylistRelation WHERE playlistId = ?);")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                initializeTrack(resultSet, trackCollection.getTracks());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackCollection;
    }

    private void initializeTrack(ResultSet resultSet, List<Track> tracks) throws SQLException {
        Track track = new Track();
        track.setId(resultSet.getInt("id"));
        track.setTitle(resultSet.getString("title"));
        track.setPerformer(resultSet.getString("performer"));
        track.setDuration(resultSet.getInt("duration"));
        track.setAlbum(resultSet.getString("album"));
        track.setPlaycount(resultSet.getInt("playcount"));
        track.setPublicationDate(resultSet.getString("publicationDate"));
        track.setDescription(resultSet.getString("description"));
        track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
        tracks.add(track);
    }


}
