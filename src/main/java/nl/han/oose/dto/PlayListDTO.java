//package nl.han.oose.dto;
//
//import nl.han.oose.entity.playlist.Playlist;
//import nl.han.oose.persistence.playlist.PlaylistDAO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PlayListDTO {
//
//    private static List<Playlist> playlists;
//    private TrackDTO trackDTO;
//    private PlaylistDAO playlistDAO;
//
//    public PlayListDTO() {
//        playlists = new ArrayList<>();
//        assert playlistDAO != null;
//        //playlistDAO.getPlaylists();
//        //fillPlayListResources();
//        getLength();
//    }
//
////    public void fillPlayListResources() {
////        if (playlists.isEmpty()) {
////            playlists.add(new Playlist(1, "Death metal", true, new String[]{}));
////            playlists.add(new Playlist(2, "Pop", false, new String[]{}));
////        }
////    }
//
//    public List<Playlist> getPlaylists() {
//
//        return playlists;
//    }
//
//
//    public int getLength() {
//        int length = playlists.size();
//        //return trackDTO.calculateTotalDurationLength();
//        return length;
//    }
//
//}
