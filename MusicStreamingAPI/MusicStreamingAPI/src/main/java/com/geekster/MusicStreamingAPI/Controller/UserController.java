package com.geekster.MusicStreamingAPI.Controller;

import com.geekster.MusicStreamingAPI.Model.Dto.SignInInputDto;
import com.geekster.MusicStreamingAPI.Model.Dto.SignOutInputDto;
import com.geekster.MusicStreamingAPI.Model.PlayList;
import com.geekster.MusicStreamingAPI.Model.Role;
import com.geekster.MusicStreamingAPI.Model.Song;
import com.geekster.MusicStreamingAPI.Model.User;
import com.geekster.MusicStreamingAPI.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("user/signUp")
    public String userSignUp(@Valid @RequestBody User newUser) {
        return userService.userSignUp(newUser);
    }

    @PostMapping("user/signIn")
    public String userSignIn (@RequestBody SignInInputDto signInInput)  {
        return userService.userSignIn(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token) {
        return userService.userSignOut(email, token);
    }

    @PostMapping("song")
    public String addSong(@RequestBody Song song, @RequestParam String email, @RequestParam String token) {
        return userService.addSongs(song, email, token);
    }

    @PutMapping("song")
    public String updateSong(@RequestBody Song newSong, @RequestParam String email, @RequestParam String token, @RequestParam Long songId) {
        return userService.updateSong(newSong,email,token,songId);
    }

    @DeleteMapping("song")
    public String cancelSong(@RequestParam String email, @RequestParam String token, @RequestParam Long songId) {
        return userService.cancelSong(email,token, songId);
    }

    @PostMapping("playlist")
    public String addPlaylist(@RequestParam String email, @RequestParam String token, @RequestBody PlayList playList) {
        return userService.addPlaylist(email, token, playList);
    }

    @DeleteMapping("playlist/{id}")
    public String deletePlaylist(@RequestParam String email, @RequestParam String token, @PathVariable Long id) {
        return userService.deletePlaylist(email, token, id);
    }


    @PutMapping("playlist/{id}")
    public String updatePlaylist(@RequestParam String email, @RequestParam String token,@RequestBody PlayList playList, @PathVariable Long id) {
        return userService.updatePlaylist(email, token, playList, id);
    }

    @GetMapping("playlist")
    public List<PlayList> getPlaylist(@RequestParam String email, @RequestParam String token) {
        return userService.getPlaylists(email, token);
    }

}
