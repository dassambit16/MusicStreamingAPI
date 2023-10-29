package com.geekster.MusicStreamingAPI.Controller;

import com.geekster.MusicStreamingAPI.Model.PlayList;
import com.geekster.MusicStreamingAPI.Service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayListController {
    @Autowired
    PlayListService playListService;

    @GetMapping("playlists")
    public List<PlayList> getAllPlaylist() {
        return playListService.getAllPlaylist();
    }
}
