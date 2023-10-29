package com.geekster.MusicStreamingAPI.Controller;

import com.geekster.MusicStreamingAPI.Model.Song;
import com.geekster.MusicStreamingAPI.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping("songs")
    public List<Song> getAllSong() {
        return songService.getAllSong();
    }

}
