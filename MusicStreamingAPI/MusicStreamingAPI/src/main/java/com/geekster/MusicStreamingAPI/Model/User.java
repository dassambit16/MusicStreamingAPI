package com.geekster.MusicStreamingAPI.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "name cannot be blank")
    private String userName;

    @NotBlank(message = "email cannot be blank")
    private String userEmail;

    @NotBlank(message = "password cannot be blank")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Role userRole;

}
