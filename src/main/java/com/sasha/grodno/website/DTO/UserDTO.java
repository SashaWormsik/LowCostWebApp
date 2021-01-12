package com.sasha.grodno.website.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
}
