package com.sasha.grodno.website.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo implements UserDetails {

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
    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerUser", fetch = FetchType.EAGER)
    private List<Credentials> credentials;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserInfo", fetch = FetchType.LAZY)
    private List<Ticket> tickets;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority((getRole().name())));
    }

    @Override
    public String getPassword() {
        Optional<Credentials> credentials = getCredentials().stream().filter(Credentials::getActive).findAny();
        return credentials.map(Credentials::getPassword).orElseThrow(()-> new UsernameNotFoundException("Not found password"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        Optional<Credentials> credentials = getCredentials().stream().filter(Credentials::getActive).findAny();
        return credentials.map(Credentials::getActive).orElse(false);
    }
}
