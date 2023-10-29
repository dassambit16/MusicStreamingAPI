package com.geekster.MusicStreamingAPI.Service;

import com.geekster.MusicStreamingAPI.Model.PlayList;
import com.geekster.MusicStreamingAPI.Model.User;
import com.geekster.MusicStreamingAPI.Repo.IPlayListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListService {

    @Autowired
    IPlayListRepo iPlayListRepo;

    public void savePlayList(PlayList playList) {
        iPlayListRepo.save(playList);
    }



    public void deletePlaylist(PlayList playList) {
        iPlayListRepo.delete(playList);
    }

    public PlayList getPlayList(Long id) {
        return iPlayListRepo.findFirstByPlaylistId(id);
    }


    public List<PlayList> getAllPlaylist() {
        return iPlayListRepo.findAll();
    }
}
