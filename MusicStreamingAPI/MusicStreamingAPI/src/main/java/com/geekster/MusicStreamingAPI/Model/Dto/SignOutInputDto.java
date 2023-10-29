package com.geekster.MusicStreamingAPI.Model.Dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignOutInputDto {

    @NotNull(message = "Email cannot be blank")
    @Email
    private String email;

    private String tokenValue;
}
