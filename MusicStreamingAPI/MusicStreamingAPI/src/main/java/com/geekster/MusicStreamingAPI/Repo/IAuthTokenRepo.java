package com.geekster.MusicStreamingAPI.Repo;

import com.geekster.MusicStreamingAPI.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByTokenValue(String token);
}
