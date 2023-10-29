package com.geekster.MusicStreamingAPI.Repo;

import com.geekster.MusicStreamingAPI.Model.PlayList;
import com.geekster.MusicStreamingAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlayListRepo extends JpaRepository<PlayList, Long> {
    PlayList findFirstByUser(User user);

    PlayList findFirstByPlaylistId(Long playlistId);


    List<PlayList> findByUserUserId(Long userId);
}
