package com.geekster.MusicStreamingAPI.Service;

import com.geekster.MusicStreamingAPI.Model.AuthenticationToken;
import com.geekster.MusicStreamingAPI.Model.Dto.SignOutInputDto;
import com.geekster.MusicStreamingAPI.Repo.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthTokenRepo iAuthTokenRepo;

    public void createToken(AuthenticationToken token) {
        iAuthTokenRepo.save(token);
    }

    public boolean authenticate(String email, String token) {
        AuthenticationToken authToken = iAuthTokenRepo.findFirstByTokenValue(token);
        if(authToken != null) {
            return authToken.getUser().getUserEmail().equals(email);
        }else{
            return false;
        }
    }

    public void deleteToke(String tokenValue) {
        AuthenticationToken token = iAuthTokenRepo.findFirstByTokenValue(tokenValue);
        iAuthTokenRepo.delete(token);
    }
}
