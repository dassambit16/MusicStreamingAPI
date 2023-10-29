package com.geekster.MusicStreamingAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    public AuthenticationToken(User existingUser) {
        this.user = existingUser;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    @OneToOne
    @JoinColumn(name = "fk_user")
    User user;
}
