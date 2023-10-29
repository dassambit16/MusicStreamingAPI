package com.geekster.MusicStreamingAPI.Repo;

import com.geekster.MusicStreamingAPI.Model.Song;
import com.geekster.MusicStreamingAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepo extends JpaRepository<Song, Long> {

    Song findFirstByUsers(User user);


}
