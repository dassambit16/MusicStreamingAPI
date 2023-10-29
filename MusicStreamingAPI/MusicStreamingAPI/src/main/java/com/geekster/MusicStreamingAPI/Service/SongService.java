package com.geekster.MusicStreamingAPI.Service;

import com.geekster.MusicStreamingAPI.Model.Song;
import com.geekster.MusicStreamingAPI.Model.User;
import com.geekster.MusicStreamingAPI.Repo.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    ISongRepo iSongRepo;

    public void saveSong(Song song) {
        iSongRepo.save(song);
    }

    public Song getSong(User user) {
        return iSongRepo.findFirstByUsers(user);
    }


    public void cancelSong(Song song) {
        iSongRepo.delete(song);
    }

    public List<Song> getAllSong() {
        return iSongRepo.findAll();
    }
}
