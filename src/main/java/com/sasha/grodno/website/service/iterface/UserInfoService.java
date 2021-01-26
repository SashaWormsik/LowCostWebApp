package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInfoService extends CrudService<UserInfo>, UserDetailsService {
    UserInfo findByEmail(String email);

    UserInfo findByLogin(String login);

    void saveAdmin(UserDTO userDTO);

    void saveUser(UserDTO userDTO);

    List<UserInfo> findAllAdmins();

    List<UserInfo> findAllUsers();

    void updateUserNamesAndAuthentication(UserDTO userDTO);

    void updateUserPasswordAndAuthentication(UserInfo user, String password);

    void updateAuthentication(UserInfo userInfo);

    UserInfo getUserFromContext();

    Page<UserInfo> getUsersPage(Integer pageNumber);

    Page<UserInfo> getAdminPage(Integer pageNumber);
}
