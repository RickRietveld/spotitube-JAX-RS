package nl.han.oose.persistence.track;

import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.TrackCollection;
import nl.han.oose.persistence.ConnectionFactory;
import nl.han.oose.persistence.Datamapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends Datamapper {

    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }


    public TrackCollection getAllTracks() {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Track> trackCollections = new ArrayList<>();

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM track;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
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
                trackCollections.add(track);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new TrackCollection(trackCollections);
    }

    public List getTracksIdsFromPlaylistid(int id) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List allIds = new ArrayList();

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM linktracktoplaylist where playlist = ?;");
            preparedStatement.setString(1, "" + id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allIds.add(resultSet.getInt("track"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allIds;
    }
}
