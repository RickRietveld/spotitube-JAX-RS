package nl.han.oose.dto;

import nl.han.oose.entity.PlayList;

import java.util.ArrayList;
import java.util.List;

public class PlayListDTO {

    private static List<PlayList> playLists;
    private int length;
    private TrackDTO trackDTO;

    public PlayListDTO() {
        playLists = new ArrayList<>();
        fillPlayListResources();
        getLength();
    }

    public void fillPlayListResources() {
        if (playLists.isEmpty()) {
            playLists.add(new PlayList(1, "Death metal", true, new String[]{}));
            playLists.add(new PlayList(2, "Pop", false, new String[]{}));
        }
    }

    public List<PlayList> getPlaylists() {

        return playLists;
    }

//    public void replacePlayList(int id, PlayList playList) throws RuntimeException {
//        if(id > playLists.size()) {
//            throw new RuntimeException();
//        }
//        playLists.set(id, new PlayList(id, playList.getName(), playList.isOwner(), playList.getTracks()));
//    }


    public int getLength() {
        length = playLists.size();
        //length = trackDTO.calculateTotalDurationLength();
        return length;
    }

    public void setLength(int length) {

        this.length = length;
    }
}
