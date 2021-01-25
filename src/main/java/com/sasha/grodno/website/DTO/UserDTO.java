package com.sasha.grodno.website.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @NotNull
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20)
    private String login;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;
}
