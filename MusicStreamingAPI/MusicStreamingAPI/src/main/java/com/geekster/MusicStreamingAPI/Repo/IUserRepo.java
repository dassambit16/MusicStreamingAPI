package com.geekster.MusicStreamingAPI.Repo;

import com.geekster.MusicStreamingAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {
    User findFirstByUserEmail(String userNewEmail);
}
