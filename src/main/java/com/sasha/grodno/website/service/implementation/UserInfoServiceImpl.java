package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Credentials;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.model.UserInfoDetails;
import com.sasha.grodno.website.repositories.UserInfoRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoServiceImpl extends CrudServiceJpaImpl<UserInfo> implements UserInfoService, UserDetailsService {

    @Autowired
    UserInfoRepository repo;

    @Override
    public UserInfo findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public UserInfo findByLogin(String login) {
        return repo.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserInfo> user = repo.getByLogin(login);
        UserInfo userInfo = user.orElseThrow(() -> new UsernameNotFoundException("Not found " + login));
        String userLogin = userInfo.getLogin();
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority((userInfo.getRole().name())));
        Optional<Credentials> credentials = userInfo.getCredentials().stream().filter(Credentials::getActive).findAny();
        String password = credentials.map(Credentials::getPassword).orElseThrow(()-> new UsernameNotFoundException("Not found password"));

        return new UserInfoDetails(userLogin, password, authorities);

    }

}
