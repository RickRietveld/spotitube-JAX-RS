package nl.han.oose.persistence.playlist;

import nl.han.oose.entity.account.UserToken;
import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.persistence.ConnectionFactory;
import nl.han.oose.persistence.Datamapper;

import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO extends Datamapper {


    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }


    public PlaylistCollection getAllPlaylists(UserToken userToken) {
        ResultSet resultSet;
        PlaylistCollection playlistCollection = new PlaylistCollection();

        int playlistLength = 0;

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                String accountUser = resultSet.getString("user");
                playlist.setOwner(false);
                playlist.setTracks(new ArrayList<>());
                if (accountUser.equals(userToken.getUser())) {
                    playlist.setOwner(true);
                }

                playlistCollection.getPlaylists().add(playlist);
                playlistLength += getLengthOfPlaylist(resultSet.getInt("id"));
            }
            playlistCollection.setLength(playlistLength);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistCollection;
    }

    public void addPlaylist(UserToken userToken, Playlist playlist) {
        playlist.setOwner(true);
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist (name, user, owner) VALUES (?, ?, ?);"
                     , Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, playlist.getName());
            statement.setString(2, userToken.getUser());
            statement.setBoolean(3, playlist.isOwner());
            statement.execute();

            ResultSet tableKeys = statement.getGeneratedKeys();
            if (tableKeys.next()) {
                tableKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deletePlaylist(int id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?;")
        ) {
            statement.setInt(1, id);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLengthOfPlaylist(int id) {
        int playlistLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(duration) AS summedDuration FROM track t LEFT JOIN trackPlaylistRelation " +
                        "ON trackPlaylistRelation.trackId = t.id AND trackPlaylistRelation.playlistId = ? " +
                        "WHERE t.id IN(SELECT trackId FROM trackPlaylistRelation WHERE playlistId = ?);")
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playlistLength += resultSet.getInt("summedDuration");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistLength;
    }

    public void renamePlaylist(Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?;")
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, playlist.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
