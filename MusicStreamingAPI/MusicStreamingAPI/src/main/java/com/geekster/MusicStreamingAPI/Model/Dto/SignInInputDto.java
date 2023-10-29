package com.geekster.MusicStreamingAPI.Model.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInputDto {

    @NotNull(message = "Email cannot be blank")
    @Email
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
