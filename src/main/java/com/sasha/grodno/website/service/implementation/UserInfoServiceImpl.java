package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.Credentials;
import com.sasha.grodno.website.model.Role;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.repositories.UserInfoRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.EmailService;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import net.bytebuddy.utility.RandomString;
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
public class UserInfoServiceImpl extends CrudServiceJpaImpl<UserInfo> implements UserInfoService{

    static private final Integer SIZE = 5;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserConvector convector;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void activateUser(UserInfo userInfo) {
        List<Credentials> credentials =userInfo.getCredentials();
        for (Credentials credential : credentials) {
            credential.setActive(true);
        }
        userInfo.setToken(null);
        userInfo.setCredentials(credentials);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    @Override
    public UserInfo findByLogin(String login) {
        return userInfoRepository.findByLogin(login);
    }

    @Override
    public UserInfo findByToken(String token) {
        return userInfoRepository.findByToken(token);
    }

    @Override
    public UserInfo getUserFromContext() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Page<UserInfo> getUsersPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return userInfoRepository.findAllByRole(Role.ROLE_USER, pageable);
    }

    @Override
    public Page<UserInfo> getAdminPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return userInfoRepository.findAllByRole(Role.ROLE_ADMIN, pageable);
    }

    @Override
    public void saveAdmin(UserDTO userDTO) {
        UserInfo admin = convector.convertToUserInfo(userDTO);
        admin.setRole(Role.ROLE_ADMIN);
        List<Credentials> credentials = admin.getCredentials();
        for (Credentials credential : credentials) {
            credential.setActive(true);
        }
        userInfoRepository.save(admin);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        UserInfo user = convector.convertToUserInfo(userDTO);
        user.setRole(Role.ROLE_USER);
        user.setToken(RandomString.make(30));
        userInfoRepository.save(user);
        sendRegistrationEmail(user);
    }

    @Override
    public void sendRegistrationEmail(UserInfo userInfo) {
        String email = userInfo.getEmail();
        String link = "http://localhost:8092/activate_user?token=" + userInfo.getToken();
        emailService.sendEmail(email, link);
    }

    @Override
    public void sendForgotPasswordEmail(UserInfo userInfo) {
        String email = userInfo.getEmail();
        String link = "http://localhost:8092/reset_password?token=" + userInfo.getToken();
        emailService.sendEmail(email, link);
    }

    @Override
    public void updateUserToken(UserInfo userInfo) {
        userInfo.setToken(RandomString.make(30));
        userInfoRepository.save(userInfo);
    }

    @Override
    public void updateUserPassword(UserInfo userInfo, String password) {
        UserInfo user = findByLogin(userInfo.getLogin());
        List<Credentials> credentials = user.getCredentials();
        for (Credentials credential : credentials) {
            credential.setActive(false);
        }
        credentials.add(new Credentials(null, passwordEncoder.encode(password), true, new Date(), user));
        user.setCredentials(credentials);
        userInfoRepository.save(user);
    }

    @Override
    public void updateUserNamesAndAuthentication(UserDTO userDTO) {
        UserInfo user = userInfoRepository.findByLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userInfoRepository.save(user);
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
        userInfoRepository.save(user);
        updateAuthentication(user);
    }

    @Override
    public void updateAuthentication(UserInfo userInfo) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserInfo> user = userInfoRepository.getByLogin(login);
        return user.orElseThrow(() -> new UsernameNotFoundException("Not found " + login));
    }

    //Create default USER and ADMIN
   /* @Override
    public void afterPropertiesSet() throws Exception {
        UserInfo admin = new UserInfo(null, "Admin", "Admin", "admin", "admin@mail.ru", Role.ROLE_ADMIN, null, null, null);
        UserInfo user = new UserInfo(null, "User", "User", "user", "user@mail.ru", Role.ROLE_USER, null, null, null);
        String password = passwordEncoder.encode("11111");
        Credentials credentialsAdmin = new Credentials(null, password, true, new Date(), admin);
        Credentials credentialsUser = new Credentials(null, password, true, new Date(), user);
        admin.setCredentials(Collections.singletonList(credentialsAdmin));
        user.setCredentials(Collections.singletonList(credentialsUser));
        userInfoRepository.save(admin);
        userInfoRepository.save(user);
    }*/

}
