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
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String accountUser = resultSet.getString("user");
                boolean owner = false;
                if (accountUser.equals(userToken.getUser())) {
                    owner = true;
                }
                playlistCollection.getPlaylists().add(new Playlist(id, name, owner, new ArrayList<>()));
                playlistLength += getLengthOfPlaylist(id);
            }
            playlistCollection.setLength(playlistLength);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlistCollection;
    }

    public PlaylistCollection addPlaylist(UserToken userToken, Playlist playlist) {
        playlist.setOwner(true);
        PlaylistCollection playlistCollection;
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
            playlistCollection = getAllPlaylists(userToken);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistCollection;
    }


    public PlaylistCollection deletePlaylist(UserToken userToken, int id) {
        PlaylistCollection playlistCollection = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?;")
        ) {
            statement.setInt(1, id);

            statement.execute();

            playlistCollection = getAllPlaylists(userToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlistCollection;
    }

    public int getLengthOfPlaylist(int id) {
        int playlistLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(duration) AS summedDuration FROM track WHERE id = ?")
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playlistLength += resultSet.getInt("summedDuration");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistLength;
    }

    public PlaylistCollection renamePlaylist(UserToken userToken, Playlist playlist) {
        PlaylistCollection playlistCollection = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?;")
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, playlist.getId());

            preparedStatement.execute();

            playlistCollection = getAllPlaylists(userToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlistCollection;
    }


}
