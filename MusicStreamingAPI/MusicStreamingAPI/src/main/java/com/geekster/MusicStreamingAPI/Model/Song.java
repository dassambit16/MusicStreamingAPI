package com.geekster.MusicStreamingAPI.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @NotBlank(message ="name can't be blank")
    private String songName;

    @NotBlank(message ="title can't be blank")
    private String songTitle;

    @NotBlank(message ="artist can't be blank")
    private String artist;

    @OneToOne
    @JoinColumn(name = "fk_user")
    private User users;

}
