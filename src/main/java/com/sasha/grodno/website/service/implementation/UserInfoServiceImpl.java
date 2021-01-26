package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.Credentials;
import com.sasha.grodno.website.model.Role;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.repositories.UserInfoRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoServiceImpl extends CrudServiceJpaImpl<UserInfo> implements UserInfoService, InitializingBean {

    static private final Integer SIZE = 5;

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

    @Override
    public void saveAdmin(UserDTO userDTO) {
        UserInfo admin = convector.convertToUserInfo(userDTO);
        admin.setRole(Role.ROLE_ADMIN);
        repo.save(admin);
    }

    @Override
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
    public void updateUserNamesAndAuthentication(UserDTO userDTO) {
        UserInfo user = repo.findByLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        repo.save(user);
        updateAuthentication(user);
    }

    @Override
    public void updateUserPasswordAndAuthentication(UserInfo userInfo, String password) {
        UserInfo user = findByLogin(userInfo.getLogin());
        List<Credentials> credentials = user.getCredentials();
        for (Credentials credential : credentials) {
            credential.setActive(false);
        }
        credentials.add(new Credentials(null, passwordEncoder.encode(password), true, new Date(), user));
        user.setCredentials(credentials);
        repo.save(user);
        updateAuthentication(user);
    }

    @Override
    public void updateAuthentication(UserInfo userInfo) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserInfo getUserFromContext() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Page<UserInfo> getUsersPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return repo.findAllByRole(Role.ROLE_USER, pageable);
    }

    @Override
    public Page<UserInfo> getAdminPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return repo.findAllByRole(Role.ROLE_ADMIN, pageable);
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
        String password = passwordEncoder.encode("11111");
        Credentials credentialsAdmin = new Credentials(null, password, true, new Date(), admin);
        Credentials credentialsUser = new Credentials(null, password, true, new Date(), user);
        admin.setCredentials(Collections.singletonList(credentialsAdmin));
        user.setCredentials(Collections.singletonList(credentialsUser));
        repo.save(admin);
        repo.save(user);
    }

}
