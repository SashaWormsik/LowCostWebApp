package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.Credentials;
import com.sasha.grodno.website.model.Role;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.repositories.UserInfoRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoServiceImpl extends CrudServiceJpaImpl<UserInfo> implements UserInfoService, InitializingBean {

    @Autowired
    private UserInfoRepository repo;

    @Autowired
    private UserConvector convector;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public UserInfo findByLogin(String login) {
        return repo.findByLogin(login);
    }

    public void saveAdmin(UserDTO userDTO) {
        UserInfo admin = convector.convertToUserInfo(userDTO);
        admin.setRole(Role.ROLE_ADMIN);
        repo.save(admin);
    }

    public void saveUser(UserDTO userDTO) {
        UserInfo user = convector.convertToUserInfo(userDTO);
        user.setRole(Role.ROLE_USER);
        repo.save(user);
    }

    @Override
    public List<UserInfo> findAllAdmins() {
        return repo.findAllByRole(Role.ROLE_ADMIN);
    }

    @Override
    public List<UserInfo> findAllUsers() {
        return repo.findAllByRole(Role.ROLE_USER);
    }

    @Override
    public void updateUserNames(UserDTO userDTO) {
        UserInfo user = repo.findByLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        repo.save(user);
    }

    @Override
    public void updateUserPassword(UserDTO userDTO) {
        UserInfo user = findByLogin(userDTO.getLogin());
        List<Credentials> credentials = user.getCredentials();
        for (Credentials credential : credentials) {
            credential.setActive(false);
        }
        credentials.add(new Credentials(null, passwordEncoder.encode(userDTO.getPassword()), true, new Date(), user));
        user.setCredentials(credentials);
        repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserInfo> user = repo.getByLogin(login);
        return user.orElseThrow(() -> new UsernameNotFoundException("Not found " + login));
    }

    //Create default USER and ADMIN
    @Override
    public void afterPropertiesSet() throws Exception {
        UserInfo admin = new UserInfo(null, "Admin", "Admin", "admin", "admin@mail.ru", Role.ROLE_ADMIN, null, null);
        UserInfo user = new UserInfo(null, "User", "User", "user", "user@mail.ru", Role.ROLE_USER, null, null);
        String password = passwordEncoder.encode("111");
        Credentials credentialsAdmin = new Credentials(null, password, true, new Date(), admin);
        Credentials credentialsUser = new Credentials(null, password, true, new Date(), user);
        admin.setCredentials(Collections.singletonList(credentialsAdmin));
        user.setCredentials(Collections.singletonList(credentialsUser));
        repo.save(admin);
        repo.save(user);
    }

}
