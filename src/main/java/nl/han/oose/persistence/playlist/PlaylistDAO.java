package nl.han.oose.persistence.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.PlaylistCollection;
import nl.han.oose.persistence.ConnectionFactory;
import nl.han.oose.persistence.Datamapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO extends Datamapper {


    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public PlaylistCollection getAllPlaylists() {
        ResultSet resultSet;
        ArrayList<Playlist> playlists = new ArrayList<>();
        PlaylistCollection playlistsCollection = null;

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(false);
                playlist.setTracks(null);
//                if (userId == resultSet.getInt("user")){
//                    playlist.setOwner(true);
//                }
                playlists.add(playlist);
            }
            playlistsCollection = new PlaylistCollection(playlists);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlistsCollection;
    }

    public Playlist getPlaylist(int id) {
        ResultSet resultSet;
        PreparedStatement preparedStatement = null;
        Playlist playlist = null;

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlists WHERE id = ?;");
            preparedStatement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getBoolean("owner"));
                playlist.setTracks(null);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlist;
    }

//    public List<Playlist> getPlaylists(String userToken) {
//        List<Playlist> result = new ArrayList<>();
//        try {
//            Connection connection = connectionFactory.getConnection();
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist;");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Playlist playlist = buildPlaylistFromResultSet(resultSet);
//                result.add(playlist);
//            }
//            connection.close();
//            return result;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

//    public Playlist getPlaylist(int playlistId) {
//        ResultSet resultSet = null;
//        PreparedStatement statement = null;
//        Playlist playlist = null;
//
//        try {
//            Connection connection = connectionFactory.getConnection();
//            statement = connection.prepareStatement("SELECT * FROM playlist");
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                playlist =  new Playlist();
//                playlist.setId(resultSet.getInt("id"));
//                playlist.setName(resultSet.getString("name"));
//                playlist.setOwner(resultSet.getBoolean("owner"));
//                playlist.setTracks(null);
//            }
//            connection.close();
//            return playlist;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


//    private Playlist buildPlaylistFromResultSet(ResultSet resultSet) throws SQLException {
//        Integer id = resultSet.getInt("id");
//        String name = resultSet.getString("name");
//        Boolean owner = resultSet.getBoolean("owner");
//        List<Track> list = new ArrayList<>();
//        return new Playlist(id, name, owner, list);
//    }


}
