package com.sasha.grodno.website.convert;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.model.Credentials;
import com.sasha.grodno.website.model.Role;
import com.sasha.grodno.website.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class UserConvector {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO convertToUserDTO (UserInfo userInfo){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(userInfo.getFirstName());
        userDTO.setLastName(userInfo.getLastName());
        userDTO.setLogin(userInfo.getLogin());

        Optional<Credentials> credentials = userInfo.getCredentials().stream().filter(Credentials::getActive).findAny();
        String password = credentials.map(Credentials::getPassword).orElseThrow(()-> new UsernameNotFoundException("Not found password"));

        userDTO.setPassword(password);
        userDTO.setEmail(userInfo.getEmail());

        return userDTO;
    }


    public UserInfo convertToUserInfo(UserDTO userDTO){
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userDTO.getFirstName());
        userInfo.setLastName(userDTO.getLastName());
        userInfo.setLogin(userDTO.getLogin());
        userInfo.setEmail(userDTO.getEmail());
        String password = passwordEncoder.encode(userDTO.getPassword());
        Credentials credentials = new Credentials(null, password, true ,new Date(), userInfo);
        userInfo.setCredentials(Collections.singletonList(credentials));
        return userInfo;
    }
}
