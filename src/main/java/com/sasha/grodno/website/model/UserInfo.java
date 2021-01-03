package com.sasha.grodno.website.model;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login", nullable = false, unique = true, updatable = false)
    private String login;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerUser", fetch = FetchType.LAZY)
    private List<Credentials> credentials;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserInfo", fetch = FetchType.LAZY)
    private List<UserDetailTicket> userDetailTickets;
}
