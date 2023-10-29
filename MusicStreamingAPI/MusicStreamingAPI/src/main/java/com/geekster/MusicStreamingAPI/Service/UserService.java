package com.geekster.MusicStreamingAPI.Service;

import com.geekster.MusicStreamingAPI.Model.*;
import com.geekster.MusicStreamingAPI.Model.Dto.SignInInputDto;
import com.geekster.MusicStreamingAPI.Repo.IPlayListRepo;
import com.geekster.MusicStreamingAPI.Repo.ISongRepo;
import com.geekster.MusicStreamingAPI.Repo.IUserRepo;
import com.geekster.MusicStreamingAPI.Service.EmailUtility.EmailHandler;
import com.geekster.MusicStreamingAPI.Service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    SongService songService;

    @Autowired
    ISongRepo iSongRepo;

    @Autowired
    PlayListService playListService;

    @Autowired
    IPlayListRepo iPlayListRepo;

    public String userSignUp(User newUser) {
        String userNewEmail = newUser.getUserEmail();
        User existingUser = iUserRepo.findFirstByUserEmail(userNewEmail);
        if(existingUser != null) {
            return "Email already in use";
        }
        String signUpPassword = newUser.getUserPassword();
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);
            newUser.setUserPassword(encryptedPassword);
            iUserRepo.save(newUser);
            return "User registered!!";
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!";
        }
    }

    public String userSignIn(SignInInputDto signInInput) {
        String email = signInInput.getEmail();
        User existingUser = iUserRepo.findFirstByUserEmail(email);
        if(existingUser == null) {
            return "User not registered, Please signUp first!";
        }
        String password = signInInput.getPassword();
        try {
            String encryptPassword = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(encryptPassword)) {
                AuthenticationToken token = new AuthenticationToken(existingUser);
                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                }else{
                    return "Error while generating token!!!";
                }
            }else {
                return "Invalid credential!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignOut(String email, String token) {
        if(authenticationService.authenticate(email, token)){
            authenticationService.deleteToke(token);
            return "Sign out successful!!";
        }else{
            return "Un authenticated access!!";
        }
    }

    public String addSongs(Song song, String email, String token) {
        Role userRole = iUserRepo.findFirstByUserEmail(email).getUserRole();
        if(userRole.equals(Role.ADMIN)) {
            if(authenticationService.authenticate(email, token)) {
                Long userId = song.getUsers().getUserId();
                boolean isValidUser = iUserRepo.existsById(userId);
                if(isValidUser) {
                    songService.saveSong(song);
                    return "Song added!!";
                }else{
                    return "Error occurred during adding song!!";
                }
            }
        }
        return "Normal User are not allowed to add song!!";
    }

    public String cancelSong(String email, String token, Long songId) {
        if (authenticationService.authenticate(email, token)) {
            User user = iUserRepo.findFirstByUserEmail(email);
            if (user.getUserRole().equals(Role.ADMIN)) {
                Song song = iSongRepo.findById(songId).orElse(null);
                if (song != null) {
                    iSongRepo.delete(song);
                    return "Song deleted successfully!!";
                } else {
                    return "Song not found!!";
                }
            } else {
                return "Unauthorized access!!";
            }
        } else {
            return "Unauthenticated access!!";
        }
    }

    public String updateSong(Song newSong, String email, String token, Long songId) {
        if (authenticationService.authenticate(email, token)) {
            User user = iUserRepo.findFirstByUserEmail(email);
            if (user.getUserRole().equals(Role.ADMIN)) {
                Optional<Song> existingSongOptional = iSongRepo.findById(songId);

                if (existingSongOptional.isPresent()) {
                    Song existingSong = existingSongOptional.get();
                    if (existingSong.getUsers().getUserEmail().equals(user.getUserEmail()) || user.getUserRole().equals(Role.ADMIN)) {
                        existingSong.setSongName(newSong.getSongName());
                        existingSong.setArtist(newSong.getArtist());
                        existingSong.setSongTitle(newSong.getSongTitle());

                        iSongRepo.save(existingSong);
                        return "Song updated successfully!!";
                    } else {
                        return "You are not authorized to update this song!!";
                    }
                } else {
                    return "Song does not exist!!";
                }
            } else {
                return "Unauthorized access!!";
            }
        } else {
            return "Unauthenticated access!!";
        }
    }



    public String addPlaylist(String email, String token, PlayList playList) {
        Role userRole = iUserRepo.findFirstByUserEmail(email).getUserRole();
        if(userRole.equals(Role.NORMAL)) {
            if(authenticationService.authenticate(email, token)) {
                Long userId = playList.getUser().getUserId();
                boolean isValidUser = iUserRepo.existsById(userId);
                if(isValidUser) {
                    playListService.savePlayList(playList);
                    return "Playlist added!!";
                }else{
                    return "Error occurred during adding song!!";
                }
            }
        }
        return "Admin User are not allowed to add song in playlist!!";
    }

    public String deletePlaylist(String email, String token, Long id) {
        if (authenticationService.authenticate(email, token)) {
            User user = iUserRepo.findFirstByUserEmail(email);
            if (user.getUserRole().equals(Role.NORMAL)) {
                PlayList playList = playListService.getPlayList(id);
                playListService.deletePlaylist(playList);
                }else {
                    return "Un authorised access!!";
                }
            }
        return "Unauthenticated access!!";
    }


    public String updatePlaylist(String email, String token, PlayList playList, Long id) {
        if (authenticationService.authenticate(email, token)) {
            User user = iUserRepo.findFirstByUserEmail(email);
            if (user.getUserRole().equals(Role.NORMAL)) {
                Long userId = playList.getUser().getUserId();
                boolean isUserValid = iUserRepo.existsById(userId);
                if (isUserValid) {
                    Optional<PlayList> optionalPlayList = iPlayListRepo.findById(id);
                    if (optionalPlayList.isPresent()) {
                        PlayList originalPlayList = optionalPlayList.get();
                        if (originalPlayList.getUser().getUserId().equals(userId)) {
                            if (playList.getPlaylistName() != null) {
                                originalPlayList.setPlaylistName(playList.getPlaylistName());
                                iPlayListRepo.save(originalPlayList);
                                return "Playlist Updated!!";
                            } else {
                                return "Playlist name is required to update!!";
                            }
                        } else {
                            return "Unauthorised access!!";
                        }
                    } else {
                        return "Playlist not found!!";
                    }
                } else {
                    return "User not found!!";
                }
            } else {
                return "Unauthorised access!!";
            }
        } else {
            return "Unauthenticated access!!";
        }
    }

    public List<PlayList> getPlaylists(String email, String token) {
        if (authenticationService.authenticate(email, token)) {
            User user = iUserRepo.findFirstByUserEmail(email);
            if (user.getUserRole().equals(Role.NORMAL)) {
                List<PlayList> playlists = iPlayListRepo.findByUserUserId(user.getUserId());
                return playlists;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
